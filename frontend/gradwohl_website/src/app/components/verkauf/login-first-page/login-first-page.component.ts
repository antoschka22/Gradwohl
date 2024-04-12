import { Component, OnInit, HostListener, ElementRef, ViewChild } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';
import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';

import { Router, ActivatedRoute } from '@angular/router';

// Tabellen:
import { filiale } from 'src/model/filiale/filiale';
import { produkt } from 'src/model/produkt/produkt';
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';

// Linke und Rechte Obere Box
import { KundenbestellungService } from 'src/app/service/kundenbestellung/kundenbestellung.service';

// Vorlagen
import { lieferbar } from 'src/model/lieferbar/lieferbar';
import { LieferbarService } from 'src/app/service/lieferbar/lieferbar.service';

import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';
import { ProduktgruppeService } from './../../../service/produktgruppe/produktgruppe.service';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';
import { ToastrService } from 'ngx-toastr';
import { VorlageService } from 'src/app/service/vorlage/vorlage.service';
import { vorlage } from 'src/model/vorlage/vorlage';
import { vorlageId } from 'src/model/vorlage/vorlageId';
import { ProduktService } from 'src/app/service/produkt/produkt.service';

// Kalender warenbestellung einsehen: 
import { MatCalendarCellCssClasses } from '@angular/material/datepicker';


class vorlageModel implements vorlage {
  constructor(
    public id: vorlageId,
    public menge: number){}
}

interface warenbestellungID {
  datum: string | Date ;
  produkt: produkt;
  filiale: filiale;
}

function groupAndSortDates(warenbestellungen: warenbestellung[]): { [datum: string]: warenbestellungID[] } {
  // Gruppierung der Bestellungen nach Datum
  const groupedOrders: { [datum: string]: warenbestellungID[] } = {};

  warenbestellungen.forEach(warenbestellung => {
    // Datum formatieren
    let formattedDatum: Date;
    if (typeof warenbestellung.id.datum === 'string') {
      formattedDatum = new Date(warenbestellung.id.datum);
    } else {
      formattedDatum = warenbestellung.id.datum as Date;
    }

    // Überprüfen, ob das Datum in der Vergangenheit liegt
    const currentDate = new Date();
    if (formattedDatum >= currentDate) {
      // Datum als Schlüssel für die Gruppierung verwenden
      const year = formattedDatum.getFullYear();
      const month = formattedDatum.getMonth() + 1;
      const day = formattedDatum.getDate();
      const key = `${year}-${month < 10 ? '0' + month : month}-${day < 10 ? '0' + day : day}`;

      // Gruppierung erstellen, wenn noch nicht vorhanden
      if (!groupedOrders[key]) {
        groupedOrders[key] = [];
      }

      // Bestellung zur entsprechenden Gruppe hinzufügen
      groupedOrders[key].push({
        datum: formattedDatum,
        produkt: warenbestellung.id.produkt,
        filiale: warenbestellung.id.filiale,
      });
    }
  });

  // Sortieren der Gruppen nach Datum
  const sortedKeys = Object.keys(groupedOrders).sort();

  // Neue sortierte Gruppen erstellen
  const sortedGroupedOrders: { [datum: string]: warenbestellungID[] } = {};
  sortedKeys.forEach(key => {
    sortedGroupedOrders[key] = groupedOrders[key];
  });

  return sortedGroupedOrders;
}


// Datum in das gewünschte Format
function formatDate(date: string | Date): string {
  const d = typeof date === 'string' ? new Date(date) : date;
  const year = d.getFullYear();
  const month = String(d.getMonth() + 1).padStart(2, '0');
  const day = String(d.getDate()).padStart(2, '0');
  return `${year}-${month}-${day}`;
}


@Component({
  selector: 'app-login-first-page',
  templateUrl: './login-first-page.component.html',
  styleUrls: ['./login-first-page.component.scss']
})

export class LoginFirstPageComponent implements OnInit {

  isMobile: boolean;

  //Kalender Menü, rechts
  isKundenbestellungDropdown: boolean = false;
  dropdownUp: boolean = true;


  //Kalender
  currentDate = new Date();
  selectedDate: Date | null = null;
  selectedDateBestellung: Date = new Date();

  warenbestellungen: warenbestellung[] = [];
  filiale: filiale[] = [];
  Allprodukte: produkt[] = [];

  groupbyDateWarenbestellung: { [datum: string]: warenbestellungID[] } = {};

  loggedInUserFilialeName: string = '';
  loggedInUserFiliale!: filiale;


  constructor(
    private warenbestellungService: WarenbestellungService,
    private authService: AuthService,
    private router: Router, private route: ActivatedRoute,
    private kundenbestellungService: KundenbestellungService,
    private mitarbeiterService: MitabeiterService,
    private lieferbarService: LieferbarService,
    private produktgruppeService: ProduktgruppeService,
    private toastr: ToastrService,
    private vorlageService: VorlageService,
    private produktService: ProduktService
  ) { this.isMobile = window.innerWidth <= 1199,
    this.groupbyDateWarenbestellung = {};}


  @HostListener('window:resize', ['$event'])
  onResize() {
    this.isMobile = window.innerWidth <= 1199;
  }

  ngOnInit(): void {                                  
    this.getWarenbestellungen();
    this.route.paramMap.subscribe(params => {
      const date = params.get('date');
    });
    //Boxen oben
    this.countHeutigeKundenbestellungen();
    this.updateHeutigeWarenbestellungStatus();
    this.checkIfLeiter();

  }

  getWarenbestellungen() {
    var username: String = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) =>{

      this.warenbestellungService.getWarenbestellungByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
        data.forEach((warenbestellung: warenbestellung) => {
          warenbestellung.id.datum = new Date(warenbestellung.id.datum);
        });
    
        this.warenbestellungen = data;
        this.groupbyDateWarenbestellung = groupAndSortDates(this.warenbestellungen);


      });

      this.getProdukteAndGruppen(mitarbeiter)

      this.produktService.getAllProdukts().subscribe((data: any) => {
        this.Allprodukte = data
      })

      if (mitarbeiter && mitarbeiter.filiale) {
        this.loggedInUserFilialeName = mitarbeiter.filiale.name;
        this.loggedInUserFiliale = mitarbeiter.filiale
      }
    })
  }

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

  // Kundenbestellungen zaehlen für die Linke und Rechte Box oben

  // Linke Box-----------
  heutigeKundenbestellungenCount: number = 0 ;
  countHeutigeKundenbestellungen() {
    const datum = new Date();
    const heute = `${datum.getFullYear()}-${(datum.getMonth() + 1).toString().padStart(2, '0')}-${datum.getDate().toString().padStart(2, '0')}`;
  
    this.kundenbestellungService.getKundenbestellungByDate(heute).subscribe((data: any) => {
      if (Array.isArray(data)) {
        this.heutigeKundenbestellungenCount = data.length;
      } else {
        this.heutigeKundenbestellungenCount = 0;
      }
    });
  }
  
  // Rechte Box-----------
  heutigerWarenbestellungsStatus: string = '';
  updateHeutigeWarenbestellungStatus() {
    setTimeout(() => {
      for(const ware of this.warenbestellungen){
        if(ware.id.datum instanceof Date){
          if(ware.id.datum.getDate() == this.currentDate.getDate()+1 && ware.id.datum.getMonth() == this.currentDate.getMonth()){
            this.heutigerWarenbestellungsStatus = 'abgeschickt';
          }
        }
      }

      if(this.heutigerWarenbestellungsStatus.length == 0){
        this.heutigerWarenbestellungsStatus = 'offen'
      }
    }, 500);
  }

  //----------------------------------------------------------------

  //PopUP-Bestellung aufgeben:--------------------------------------------

  onDateSelectBestellung(event: Date) {
    this.selectedDateBestellung = event;
  }

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = date.getMonth() + 1; // getMonth() returns 0-11
    const day = date.getDate();

    // Pad the month and day with leading zeros if necessary
    const formattedMonth = month < 10 ? `0${month}` : `${month}`;
    const formattedDay = day < 10 ? `0${day}` : `${day}`;

    return `${year}-${formattedMonth}-${formattedDay}`;
  }

  typeBestellung(){
    if (this.currentDate.getTime() > this.selectedDateBestellung.getTime()) {
      this.toastr.error("Datum ist schon vergangen!", "Error")
    } else if (this.currentDate.getTime() < this.selectedDateBestellung.getTime()) {
      this.router.navigate(['/warenbestellungEingabe', this.formatDate(this.selectedDateBestellung), this.loggedInUserFiliale.id]);
    } else {
      this.toastr.error("Datum ist schon vergangen!", "Error")
    }
  }

  //ENDE des Kalenders--------------------------------------------------------------------------------------------------------------

  getFormattedDate(dateStr: string): string {
    const date = new Date(dateStr);
    const options = { weekday: 'short', day: '2-digit', month: 'short', year: 'numeric' } as Intl.DateTimeFormatOptions;
    return date.toLocaleDateString('de-DE', options);
  }
  

  // is Leiter? Ja, mache  die Vorlagen-erstellen sichtbar
  isLeiter: boolean = false;
  private checkIfLeiter(): void {
    if(this.authService.getUserRole() == "Leiter"){
      this.isLeiter = true;
    }
  }

  onDateSelect(event: Date) {
    this.selectedDate = event;
    let success: boolean = false;
    for (const ware of this.warenbestellungen) {
        const formattedSelectedDate = this.formatDate(event);
        const formattedWareDate = this.formatDate(new Date(ware.id.datum));

        if (formattedSelectedDate === formattedWareDate) {
            this.router.navigate(['/bestelluebersichtAbgeschickt', formattedSelectedDate]);
            success = true;
            break; 
        }
    }

    if (!success) {
        this.toastr.error("Es wurde keine Bestellung zu diesem Datum gefunden", "Error");
    }
}


  // Kundenbestellungen
  toggleKundenbestellungDropdown() {
    this.isKundenbestellungDropdown = !this.isKundenbestellungDropdown;
    this.dropdownUp = !this.dropdownUp;
  }

  // Vorlagen
  isVorlageDropdown: boolean = false;
  dropdownUpVorlage: boolean = true;

  produkte: lieferbar[] = [];
  angezeigteProdukte: lieferbar[] = [];
  produktgruppen: produktgruppe[] = [];
  ausgewaehlteProduktgruppe: produktgruppe | null = null;
  toggleVorlagenDropdown() {
    this.isVorlageDropdown = !this.isVorlageDropdown;
    this.dropdownUpVorlage = !this.dropdownUpVorlage;
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent): void {
    const element = event.target as HTMLElement;
    if (element.id === 'save') {
      this.vorlageSpeichern();
    }
  }
  
  vorlageSpeichern(){
    let insertVorlage: vorlageModel[] = []
    console.log(this.angezeigteProdukte)
    const vorlageName = document.getElementById("vorlagenname") as HTMLInputElement
    if(!vorlageName.value){
      this.toastr.error("Sie müssen dieser Vorlage einen Namen geben", "Input Error")
      return
    }

    if(this.produktInputs.hasOwnProperty(vorlageName.value)){
      this.toastr.error("Der Vorlagenname ist schon vergeben", "Input Error")
      return
    }

    for (const key of Object.keys(this.produktInputs)) {
      const elementIdFrisch = 'frisch_' + key;
      const elementFrisch = document.getElementById(elementIdFrisch) as HTMLInputElement;

      const elementIdTeigig = 'teigig_' + key;
      const elementTeigig = document.getElementById(elementIdTeigig) as HTMLInputElement;

      const selectedLieferbar: lieferbar | undefined = this.angezeigteProdukte.find((data: lieferbar) => data.id.produkt.name == key)    
      if(selectedLieferbar){
        const selectedLieferbarHB: produkt | undefined = this.Allprodukte.find((data: produkt) => data.id == selectedLieferbar?.id.produkt.id + 2000)
        if (elementFrisch && parseInt(elementFrisch.value) > 0 && selectedLieferbar) {
          const vorlageId: vorlageId = {
            name: vorlageName.value,
            produkt: selectedLieferbar.id.produkt,
            filiale: this.loggedInUserFiliale
          }
          const vorlagemodel: vorlageModel = new vorlageModel(vorlageId, parseInt(elementFrisch.value))
          insertVorlage.push(vorlagemodel)
        }

        if (elementTeigig && parseInt(elementTeigig.value) > 0 && selectedLieferbarHB) {
          console.log(selectedLieferbarHB, selectedLieferbar?.id.produkt.id + 2000)
          const vorlageId: vorlageId = {
            name: vorlageName.value,
            produkt: selectedLieferbarHB,
            filiale: this.loggedInUserFiliale
          }
          const vorlagemodel: vorlageModel = new vorlageModel(vorlageId, parseInt(elementTeigig.value))
          insertVorlage.push(vorlagemodel)
        }
      }
    }

    if(insertVorlage.length > 0){
      this.vorlageService.insertVorlage(insertVorlage).subscribe((data: any) => {
        const reset = document.getElementById('reset') as HTMLButtonElement;
        if (reset) {
          reset.click();
        }
  
        this.toastr.success("Vorlage erstellt", "Erfolg")
      })
    }
  }

  validateProdukt(event: KeyboardEvent, produktGruppeName: string): void {
    const allowedKeys = ['Backspace', 'ArrowLeft', 'ArrowRight', 'Tab'];
    if (allowedKeys.includes(event.key)) {
        return;
    }

    const isDecimalPoint = event.key === '.' || event.key === ',';
    const isDigit = event.key.match(/^\d$/);

    if (isDecimalPoint && produktGruppeName !== 'VK Brot Stangen') {
        event.preventDefault();
    }

    if (!isDigit && !isDecimalPoint) {
        event.preventDefault();
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

  validateInput(event: KeyboardEvent, produktGruppeName: string): void {
    const allowedKeys = ['Backspace', 'ArrowLeft', 'ArrowRight', 'Tab'];
    if (allowedKeys.includes(event.key)) {
        return; // Allow navigation and deletion keys
    }

    const isDecimalPoint = event.key === '.' || event.key === ',';
    const isDigit = event.key.match(/^\d$/); // Matches any digit from 0 to 9

    // Allow decimal points only for 'VK Brot Stangen'
    if (isDecimalPoint && produktGruppeName !== 'VK Brot Stangen') {
        event.preventDefault();
    }

    // Prevent if not a digit or a decimal point
    if (!isDigit && !isDecimalPoint) {
        event.preventDefault();
    }
  }

  resetForm() {
    (document.getElementById('auftragnameKundenbestellung') as HTMLInputElement).value = '';
    (document.getElementById('datumInput') as HTMLInputElement).value = '';
    this.ausgewaehlteProduktgruppe = null;
  }

  



}