import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { kundenbestellung } from 'src/model/kundenbestellung/kundenbestellung';
import { KundenbestellungService } from 'src/app/service/kundenbestellung/kundenbestellung.service';

// Hinzufügen
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';
import { ProduktgruppeService } from './../../../service/produktgruppe/produktgruppe.service';

import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { ToastrService } from 'ngx-toastr';
import { LieferbarService } from 'src/app/service/lieferbar/lieferbar.service';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';
import { lieferbar } from 'src/model/lieferbar/lieferbar';

@Component({
  selector: 'app-kundenbestellungs-uebersicht',
  templateUrl: './kundenbestellungs-uebersicht.component.html',
  styleUrls: ['./kundenbestellungs-uebersicht.component.scss']
})
export class KundenbestellungsUebersichtComponent implements OnInit {

  kundenbestellungen: kundenbestellung[] = [];

  // Kundenbestellungsprodukte ausgeben mit Kategorie
  produktgruppen: produktgruppe[] = [];

  //Hinzufügen
  produkte: lieferbar[] = [];
  ausgewaehlteProduktgruppe: produktgruppe | null = null;
  angezeigteProdukte: lieferbar[] = [];
  datum: Date = new Date();
  tage: string[] = ["montag", "dienstag", "mittwoch", "donnerstag", "freitag", "samstag", "sonntag"]

  constructor(private kundenbestellungService: KundenbestellungService,
    private lieferbarService: LieferbarService,
    private authService: AuthService,
    private mitarbeiterService: MitabeiterService,
    private produktgruppeService: ProduktgruppeService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.getKundenbestellungByFiliale();
  }
  
//---------Hinzufügen-POPUP:
  getProdukteAndGruppen(mitabeiter: mitabeiter) {
    if(mitabeiter.filiale?.firma == null){
      return
    }
    // Produkte
    this.lieferbarService.getLieferbarByFirma(mitabeiter.filiale?.firma.name).subscribe((data: any) => {
      this.produkte = data;
      this.angezeigteProdukte = data
      // Save input values before changing the displayed products
      this.saveInputValues();
    });

    //Produktgruppen
    this.produktgruppeService.getProduktgruppen().subscribe((data: any) => {
      this.produktgruppen = data;
    });
  }

  onProduktgruppeClicked(produktgruppe: produktgruppe | null) {
    if(produktgruppe == null){
      this.angezeigteProdukte = this.produkte
    }else{
      this.ausgewaehlteProduktgruppe = produktgruppe;
      this.angezeigteProdukte = this.produkte.filter((produkt) =>
        produkt.id.produkt.produktgruppe && produkt.id.produkt.produktgruppe.name === produktgruppe.name
      );
    }
    
  }
  
  produktInputs: { [key: string]: { frisch: number, teigig: number } } = {};
  saveInputValues(): void {
    for (const produkt of this.angezeigteProdukte) {
      this.produktInputs[produkt.id.produkt.name] = {
        frisch: 0,
        teigig: 0,
      };
    }
  }
  
  //überprüft mengen input
  validateInput(event: any): boolean {
    return (event.charCode >= 48 && event.charCode <= 57) || event.charCode === 46;
  }

  getProduktInputKeys(): string[] {
    return Object.keys(this.produktInputs);
  }

  // welches Datum wurde gewählt?

selectedDate: string | null = null; 

onDateSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    this.selectedDate = inputElement.value;
}

  
  //Search-button-Hinzu
  searchProduct(event: Event): void {
    const query = (event.target as HTMLInputElement).value;
    if (query) {
      this.angezeigteProdukte = this.produkte.filter((produkt) =>
        produkt.id.produkt.name.toLowerCase().includes(query.toLowerCase())
      );
    } else {
      // zeige alle Produkte an (wenn leer)
      this.angezeigteProdukte = this.produkte;
    }
  }

  resetForm() {
    (document.getElementById('auftragnameKundenbestellung') as HTMLInputElement).value = '';
    (document.getElementById('datumInput') as HTMLInputElement).value = '';
    this.ausgewaehlteProduktgruppe = null;
  }

  showBestellung: boolean = false;
  datumInput: string = "";
  auftragname: string = "";
  tel: string = "";
  showKundenbestellung(){
    if(document.getElementById('datumInput') as HTMLInputElement != null
    && document.getElementById('auftragnameKundenbestellung') as HTMLInputElement != null
    && document.getElementById('telefonnummerInput') as HTMLInputElement){
      this.datumInput = (document.getElementById('datumInput') as HTMLInputElement).value
      this.auftragname = (document.getElementById('auftragnameKundenbestellung') as HTMLInputElement).value
      this.tel = (document.getElementById('telefonnummerInput') as HTMLInputElement).value
    }
    console.log(this.datumInput, this.auftragname, this.tel)
    this.showBestellung = !this.showBestellung
  }

  //Überprüft ob die Eingabe folständig ist
  validateForm(){
    const datumSplit = (document.getElementById('datumInput') as HTMLInputElement).value.split("-")
    if(parseInt(datumSplit[0]) < this.datum.getFullYear() 
    || parseInt(datumSplit[1]) < this.datum.getMonth()+1
    || parseInt(datumSplit[2]) <= this.datum.getDate()){
      this.toastr.error("Falsches Datum", "Input Fehler")
      return
    }
    
    if((document.getElementById('auftragnameKundenbestellung') as HTMLInputElement).value == ''
    || (document.getElementById('datumInput') as HTMLInputElement).value == ''
    || (document.getElementById('telefonnummerInput') as HTMLInputElement).value == ''){
      this.toastr.error("Kunde, Datum oder Telefonnummer fehlt!", "Input Fehler")
      return
    }

    for(const produktId of this.getProduktInputKeys()){
      if(this.produktInputs[produktId].frisch != 0 || this.produktInputs[produktId].teigig != 0){
        this.showKundenbestellung()
        return
      }
    }

    this.toastr.error("Sie haben kein Produkt eingegeben!", "Input Fehler")
  }

//---------ENDE-Hinzufügen-POPUP------  

  filialenName: string = '';
  getKundenbestellungByFiliale() {
    var username: string = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) => {
      this.getProdukteAndGruppen(mitarbeiter)
      this.filialenName = mitarbeiter.filiale.name;
      this.kundenbestellungService.getKundenbestellungByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
        this.kundenbestellungen = data;
      });
    });
  }

  // gibt alle Kundenbestellungen vom Datum parameter zurück
  //, wenn null dann alle die nach dem heutigen Datum eingetragen sind
  getKundenbestellungenByDate(datum: string | null) {
    let heuteKundenbestellungen: kundenbestellung[] = [];
    const today = new Date();
    
    for (const item of this.kundenbestellungen) {
      const itemDate = new Date(item.id.datum);
      if (datum === null) {
        if (itemDate > today) {
          heuteKundenbestellungen.push(item);
        }
      } else if (datum !== null && item.id.datum === datum) {
        heuteKundenbestellungen.push(item);
      }
    }
    
    return heuteKundenbestellungen;
  }

  getDetailKundenbestellung(kunde: string, datum: string){
    let detailKundenbestellung: kundenbestellung[] = []
    for (const item of this.kundenbestellungen) {
      if (item.id.kunde === kunde && item.id.datum === datum) {
        detailKundenbestellung.push(item);
      }
    }
    return detailKundenbestellung;
  }

  //Formatiert das Datum der Kundenbestellung auf 2023-10-20 Format
  //, wenn parameter null dann heutiges Datum
  formattedDate(kundenbestellung: kundenbestellung | null): string {
    if(kundenbestellung){
      const abgeschicktAm = kundenbestellung.id.datum;
      const year = abgeschicktAm[0];
      const month = abgeschicktAm[1]+1;
      const day = abgeschicktAm[2];
      return `${year}-${month}-${day}`;
    }else{
      const year = this.datum.getFullYear();
      const month = this.datum.getMonth()+1;
      const day = this.datum.getDate();
      return `${year}-${month}-${day}`;
    }
  }

  @ViewChild('schliessenButton1') schliessenButton1!: ElementRef<HTMLElement>;
  @ViewChild('schliessenButton2') schliessenButton2!: ElementRef<HTMLElement>;
  deleteKundenbestellung(kundenbestellung: kundenbestellung, count: number){
    this.kundenbestellungService.deleteKundenbestellung(
      kundenbestellung.id.datum, 
      kundenbestellung.id.produkt.id, kundenbestellung.id.filiale.id, kundenbestellung.id.kunde).subscribe((data: any)=>{
        const index = this.kundenbestellungen.findIndex(
          (item) => item === kundenbestellung
        );

        if (index !== -1) {
          this.kundenbestellungen.splice(index, 1);
        }

        if(count == 1){
          this.schliessenButton1.nativeElement.click()
        }else if(count == 2){
          this.schliessenButton2.nativeElement.click()
        }
        
        this.toastr.success("Kundebestellung wurde gelöscht", 'Kundenbestellung', {
          timeOut: 3000,
        });
      })
  } 
}