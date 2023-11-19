import { Component } from '@angular/core';

import { ProduktService } from 'src/app/service/produkt/produkt.service';
import { produkt } from 'src/model/produkt/produkt';
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';
import { ProduktgruppeService } from './../../../service/produktgruppe/produktgruppe.service';

import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';

import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';
import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';

import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

//Bestellvorlage
import { vorlage } from 'src/model/vorlage/vorlage';
import { vorlageId } from 'src/model/vorlage/vorlageId';
import { VorlageService } from 'src/app/service/vorlage/vorlage.service';

interface VorlageWithProducts {
  vorlage: vorlage;
  products: produkt[];
}


@Component({
  selector: 'app-warenbestellung-eingabe',
  templateUrl: './warenbestellung-eingabe.component.html',
  styleUrls: ['./warenbestellung-eingabe.component.scss']
})
export class WarenbestellungEingabeComponent {

  date: string = '';
  produkt: produkt[] = [];
  
  //Buttons
  produktgruppen: produktgruppe[] = [];
  ausgewaehlteProduktgruppe: produktgruppe | null = null;
  angezeigteProdukte: produkt[] = [];

  //Vorlagen
  vorlage: vorlage[] = [];
  selectedVorlage: vorlage | null = null;
  groupedVorlagen: VorlageWithProducts[] = [];

  form: FormGroup;

  constructor(
    private authService: AuthService,
    private mitarbeiterService: MitabeiterService,
    private produktgruppeService: ProduktgruppeService,
    private produktService: ProduktService,
    private warenbestellungService: WarenbestellungService,
    private vorlageService: VorlageService,
    private fb: FormBuilder,
    private router: Router, private route: ActivatedRoute,
  ) {
    this.form = this.fb.group({
      frisch: ['', [Validators.required, this.validateInput]],
      teigig: ['', [Validators.required, this.validateInput]]
    });
  }

  validateInput(control: FormControl) {
    const value = parseFloat(control.value.replace(',', '.'));
    const roundedValue = Math.floor(value * 2) / 2;
  
    if (isNaN(value) || roundedValue !== value) {
      return { invalidStep: true };
    }
  
    return null;
  }


  ngOnInit(): void {
    this.loadWarenbestellungenForDate(this.date);
    this.loadAllBestellvorlagen();
    this.angezeigteProdukte = this.produkt;
  }

  public loadWarenbestellungenForDate(date: string): void {
    var username: String = this.authService.getUsernameFromToken();

    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) => {
      this.produktService.getAllProdukts().subscribe((data: any) => {
        this.produkt = data; 
        this.angezeigteProdukte = this.produkt;
      });
      this.produktgruppeService.getProduktgruppen().subscribe((data: any) => {
        this.produktgruppen = data;
      });
    });
  }
  public loadAllBestellvorlagen(): void {
    var username: String = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) => {
      this.vorlageService.getVorlageByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
        this.vorlage = data;
        //Bestellvorlage: 
      });
    });
  }

  // Bestellvorlagen-----------ENDE------------------------------------------

  //Produktgruppen + produkte + Button nach kategorie filtern

  onProduktgruppeClicked(produktgruppe: produktgruppe) {
    this.ausgewaehlteProduktgruppe = produktgruppe;
    this.angezeigteProdukte = this.produkt.filter((produkt) => produkt.produktgruppe && produkt.produktgruppe.name === produktgruppe.name);
  }

  searchProduct(event: Event): void {
    const query = (event.target as HTMLInputElement).value;
    if (query) {
      this.angezeigteProdukte = this.produkt.filter((produkt) =>
        produkt.name.toLowerCase().includes(query.toLowerCase())
      );
    } else {
      // zeige alle Produkte an (wenn leer)
      this.angezeigteProdukte = this.produkt;
    }
  }
  
  //-----------------
  

  
}
