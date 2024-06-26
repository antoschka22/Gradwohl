import { Component } from '@angular/core';

import { ProduktService } from 'src/app/service/produkt/produkt.service';
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';
import { ProduktgruppeService } from './../../../service/produktgruppe/produktgruppe.service';

import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';

import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';

//Bestellvorlage
import { vorlage } from 'src/model/vorlage/vorlage';
import { vorlageId } from 'src/model/vorlage/vorlageId';
import { VorlageService } from 'src/app/service/vorlage/vorlage.service';
import { LieferbarService } from 'src/app/service/lieferbar/lieferbar.service';
import { lieferbar } from 'src/model/lieferbar/lieferbar';
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';
import { warenbestellungID } from 'src/model/warenbestellung/warenbestellungID';
import { ToastrService } from 'ngx-toastr';
import { produkt } from 'src/model/produkt/produkt';
import { ActivatedRoute } from '@angular/router';
import { filiale } from 'src/model/filiale/filiale';
import { FilialeService } from 'src/app/service/filiale/filiale.service';

class warenbestellungModel implements warenbestellung {
  constructor(
    public id: warenbestellungID,
    public menge: number){}
}

@Component({
  selector: 'app-warenbestellung-eingabe',
  templateUrl: './warenbestellung-eingabe.component.html',
  styleUrls: ['./warenbestellung-eingabe.component.scss']
})
export class WarenbestellungEingabeComponent {

  date: string = '';
  angezeigteProdukte: lieferbar[] = [];
  produkte: lieferbar[] = [];
  tippedProdukte: warenbestellung[] = []
  filiale!: filiale;
  Allprodukte: produkt[] = []
  
  //Buttons
  produktgruppen: produktgruppe[] = [];
  ausgewaehlteProduktgruppe: produktgruppe | null = null;

  //Vorlagen
  vorlageId: vorlageId[] = [];
  vorlage: vorlage[] = [];
  selectedVorlage: vorlage | null = null;
  groupedVorlagen: { [key: string]: vorlage[] } = {};
  role: string = ""


  constructor(
    private route: ActivatedRoute,
    private filialeService: FilialeService,
    private authService: AuthService,
    private produktgruppeService: ProduktgruppeService,
    private produktService: ProduktService,
    private warenbestellungService: WarenbestellungService,
    private vorlageService: VorlageService,
    private lieferbarService: LieferbarService,
    private toastr: ToastrService) {}


  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const date = params.get('datum')
      const filialeId = params.get('id')
      if(date != null && filialeId != null){
        this.date = date
        this.loadWarenbestellungenForDate(this.date, parseInt(filialeId));
      }
      this.role = this.authService.getUserRole()
    })
  }

  navbarToggle: boolean = false;
  toggleNavbar(bol: boolean){
    this.navbarToggle = bol
  }

  loadWarenbestellungenForDate(date: string, filialeId: number): void {
    this.filialeService.getFilialeById(filialeId).subscribe((filiale: any)=>{
      this.filiale = filiale

      this.loadAllBestellvorlagen(filiale)
      
      this.lieferbarService.getLieferbarByFirma(filiale.firma.name).subscribe((data: any) => {
        this.angezeigteProdukte = data; 
        this.produkte = data

      });

      this.produktgruppeService.getProduktgruppen().subscribe((data: any) => {
        this.produktgruppen = data;
      });

      this.warenbestellungService.getWarenbestellungByDate(date).subscribe((data: any) => {
        this.tippedProdukte = data
        setTimeout(() => {
          this.saveInputValues();
        }, 500);
      })

      this.produktService.getAllProdukts().subscribe((data: any) => {
        this.Allprodukte = data
      })
    })
  }

  loadAllBestellvorlagen(filiale: filiale): void {
    this.vorlageService.getVorlageByFiliale(filiale.id).subscribe((data: any) => {
      this.vorlageId = data;
      this.vorlage = data;

      // Group Vorlagen by name
      this.groupedVorlagen = this.groupByTemplateName(this.vorlage);
      console.log(this.groupedVorlagen)
    });
  }

  produktInputs: { [key: string]: { frisch: number, teigig: number, id: number } } = {};
  saveInputValues(): void {
    for (const produkt of this.angezeigteProdukte) {
        const frischId = produkt.id.produkt.id;
        let frischValue = 0;
        let teigigValue = 0;

        const matchingFrischProdukt = this.tippedProdukte.find(tipped => tipped.id.produkt.id === frischId);
        if (matchingFrischProdukt && matchingFrischProdukt.id.datum == this.date) {
            frischValue = matchingFrischProdukt.menge;
        }

        const teigigId = frischId < 100 ? 2000 + frischId : 2000 + frischId;
        const matchingTeigigProdukt = this.tippedProdukte.find(tipped => tipped.id.produkt.id === teigigId);
        if (matchingTeigigProdukt && matchingTeigigProdukt.id.datum == this.date) {
            teigigValue = matchingTeigigProdukt.menge;
        }
    
        this.produktInputs[produkt.id.produkt.name] = {
            frisch: frischValue,
            teigig: teigigValue,
            id: frischId
        };
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

  isReadOnly(produktId: number): boolean {
    return this.Allprodukte.some(p => p.id === produktId + 2000);
  }

  save(){
    let warenArray: warenbestellungModel[] = []
    for(const produkt of this.angezeigteProdukte){
      if(this.produktInputs[produkt.id.produkt.name] != undefined){
        if(this.produktInputs[produkt.id.produkt.name].frisch != 0 || this.produktInputs[produkt.id.produkt.name].teigig != 0){

          // check produkt for new value frisch
          const matchingNeuProdukt = this.tippedProdukte.find(tipped => tipped.id.produkt.id == produkt.id.produkt.id && tipped.id.datum == this.date)
          if(!matchingNeuProdukt && this.filiale && this.produktInputs[produkt.id.produkt.name].frisch > 0){
            const id: warenbestellungID = {
              datum: this.date,
              produkt: produkt.id.produkt,
              filiale: this.filiale
            }

            const warenModel: warenbestellungModel = new warenbestellungModel(id, this.produktInputs[produkt.id.produkt.name].frisch)
            warenArray.push(warenModel)
          }

          // check produkt for new value frisch
          const teigigIdNewProdukt = produkt.id.produkt.id < 100 ? 2000 + produkt.id.produkt.id : 2000 + produkt.id.produkt.id;
          const matchingNewTeigigProdukt = this.tippedProdukte.find(tipped => tipped.id.produkt.id === teigigIdNewProdukt && tipped.id.datum == this.date);
          const produktTeigig = this.Allprodukte.find(pro => pro.id == teigigIdNewProdukt)
          if (!matchingNewTeigigProdukt && this.filiale && this.produktInputs[produkt.id.produkt.name].teigig > 0 && produktTeigig) {
            const id: warenbestellungID = {
              datum: this.date,
              produkt: produktTeigig,
              filiale: this.filiale
            }

            const warenModel: warenbestellungModel = new warenbestellungModel(id, this.produktInputs[produkt.id.produkt.name].teigig)
            warenArray.push(warenModel)
          }

          // check existing produkt for new value frisch
          const matchingProdukt = this.tippedProdukte.find(tipped => tipped.id.produkt.id == this.produktInputs[produkt.id.produkt.name].id)
          if(matchingProdukt != undefined && this.filiale && matchingProdukt.id.datum == this.date){
            if(matchingProdukt.menge != this.produktInputs[produkt.id.produkt.name].frisch){
              const id: warenbestellungID = {
                datum: this.date,
                produkt: matchingProdukt.id.produkt,
                filiale: this.filiale
              }

              const warenModel: warenbestellungModel = new warenbestellungModel(id, this.produktInputs[produkt.id.produkt.name].frisch)
              this.warenbestellungService.updateWarenbestellung(this.date, matchingProdukt.id.produkt.id, this.filiale.id,warenModel).subscribe((data: any) => {
                this.toastr.success("Bestellung gespeichert", "Erfolg")
              })
            }
          }

          // check existing produkt for new value teigig
          const teigigId = produkt.id.produkt.id < 100 ? 2000 + produkt.id.produkt.id : 2000 + produkt.id.produkt.id;
          const matchingTeigigProdukt = this.tippedProdukte.find(tipped => tipped.id.produkt.id === teigigId);
          if (matchingTeigigProdukt != undefined && this.filiale && matchingTeigigProdukt.id.datum == this.date) {
            if(matchingTeigigProdukt.menge != this.produktInputs[produkt.id.produkt.name].teigig){
              const id: warenbestellungID = {
                datum: this.date,
                produkt: matchingTeigigProdukt.id.produkt,
                filiale: this.filiale
              }

              const warenModel: warenbestellungModel = new warenbestellungModel(id, this.produktInputs[produkt.id.produkt.name].teigig)
              this.warenbestellungService.updateWarenbestellung(this.date, matchingTeigigProdukt.id.produkt.id, this.filiale.id, warenModel).subscribe((data: any) => {
                this.toastr.success("Bestellung gespeichert", "Erfolg")
              })
            }
          }

        }
      }
    }

    if(warenArray.length > 0){
      this.warenbestellungService.createWarenbestellung(warenArray).subscribe((data: any) => {
        this.toastr.success("Bestellung gespeichert", "Erfolg")
      })
    }
  }

  insertVorlage(vorlage: { [key: string]: vorlage[] }){
    if(this.selectedGroupName){
      for (const key of Object.keys(this.produktInputs)) {
        const produkt = this.produktInputs[key];
        const foundProdukt = vorlage[this.selectedGroupName].find((pro: any) => pro.id.produkt.id == produkt.id)
        const foundHBProdukt = vorlage[this.selectedGroupName].find((pro: any) => pro.id.produkt.id == produkt.id+2000)
        if(foundProdukt){
          produkt.frisch =  foundProdukt.menge
        }

        if(foundHBProdukt){
          produkt.teigig = foundHBProdukt.menge
        }
      }

      this.toastr.success("Mengen hinzugefügt", "Erfolg")
    }
  }

  groupByTemplateName(vorlagen: vorlage[]): { [key: string]: vorlage[] } {
    return vorlagen.reduce((grouped, vorlage) => {
      const name = vorlage.id.name;
      grouped[name] = grouped[name] || [];
      grouped[name].push(vorlage);
      return grouped;
    }, {} as { [key: string]: vorlage[] });
  }

  getHBValueFromFrischId(produkt: produkt){
    if(this.selectedGroupName){
      const menge = this.groupedVorlagen[this.selectedGroupName].find((vor: vorlage) => vor.id.produkt.id == produkt.id+2000)?.menge
      if(menge){
        return menge
      }else{
        return 0;
      }
    }else{
      return 0
    }
  }

  // Einklappen / Ausklappen
  toggleProducts(groupName: string): void {
    if (this.isSelected(groupName)) {
        this.selectedGroupName = null;
    } else {
        this.selectedGroupName = groupName;
    }
  }

  isSelected(groupName: string): boolean {
    return this.selectedGroupName === groupName;
  }

  selectedGroupName: string | null = null;
  showProducts(groupName: string): void {
    this.selectedGroupName = groupName;
  }

  onProduktgruppeClicked(produktgruppe: produktgruppe | null) {
    if(produktgruppe == null){
      this.angezeigteProdukte = this.produkte
      this.ausgewaehlteProduktgruppe = null
    }else{
      this.ausgewaehlteProduktgruppe = produktgruppe;
      this.angezeigteProdukte = this.produkte.filter((produkt) =>
        produkt.id.produkt.produktgruppe && produkt.id.produkt.produktgruppe.name === produktgruppe.name
      );
    }
  }

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

  generateOrder(){
    this.warenbestellungService.getGenerateWarenbestellung(this.date, this.filiale.id).subscribe((data: any) => {
      this.loadWarenbestellungenForDate(this.date, this.filiale.id);
    });
  }
}
