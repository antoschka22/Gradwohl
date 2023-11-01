import { ProduktService } from './../../../service/produkt/produkt.service';
import { ProduktgruppeService } from './../../../service/produktgruppe/produktgruppe.service';
import { Component, OnInit } from '@angular/core';

import { kundenbestellung } from 'src/model/kundenbestellung/kundenbestellung';
import { kundenbestellungId } from './../../../../model/kundenbestellung/kundenbestellungId';
import { KundenbestellungService } from 'src/app/service/kundenbestellung/kundenbestellung.service';

// Hinzufügen
import { produkt } from './../../../../model/produkt/produkt';
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';

interface KundenbestellungId {
  menge: number;
  hb: boolean;
  produktgruppe: produktgruppe;
  produkt: produkt;

}
@Component({
  selector: 'app-kundenbestellungs-uebersicht',
  templateUrl: './kundenbestellungs-uebersicht.component.html',
  styleUrls: ['./kundenbestellungs-uebersicht.component.scss']
})
export class KundenbestellungsUebersichtComponent implements OnInit {
  kundenbestellung: kundenbestellung[] = [];

  gleichDatum: Date[] = [];
  gleichAuftragsnamen: String[] = [];

  groupedKundenbestellungen: { datum: Date, kunden: string[], data: kundenbestellung[] }[] = [];

  // Kundenbestellungsprodukte ausgeben mit Kategorie
  currentCategory: string | undefined;
  
  produktgruppen: produktgruppe[] = [];



  constructor(private kundenbestellungService: KundenbestellungService,
    private produktService: ProduktgruppeService) {}

  ngOnInit() {
    this.kundenbestellungService.getAllKundenbestellungen()
      .subscribe((data: Object) => {
        this.kundenbestellung = this.mapToKundenbestellungen(data);
        this.findGleichDatum();
      });

      this.produktService.getProduktgruppen()
      .subscribe((data: any) => {
        this.produktgruppen = data;
      
      });

  }

  //---------Hinzufügen-POPUP:

  alleProdukte: produkt[] = [];
  ausgewaehlteKategorie: string | null = null;
  ausgewaehlteKategorieProdukt: produkt[] = [];

  waehlenKategorie(categoryName: string): void {
    this.ausgewaehlteKategorie = categoryName;
    console.log('Filter-Kategorie:', categoryName);
    this.ausgewaehlteKategorieProdukt = this.alleProdukte.filter(produkt => {
        const produktgruppe = produkt.produktgruppe;
        if (produktgruppe && produktgruppe.name === categoryName) {
            return true;
        }
        return false;
    });
    console.log('Ausgewählte Produkte:', this.ausgewaehlteKategorieProdukt);
}
  

  //---------ENDE-Hinzufügen-POPUP------

  displayProduktgruppenNames() {
    this.produktgruppen.forEach((produktgruppe: produktgruppe) => {
      console.log(produktgruppe.name);
    });
  }
  

  mapToKundenbestellungen(data: Object): kundenbestellung[] {
    return data as kundenbestellung[];
  }  

  public loadKundenbestellungForDate(date: string): void {
    this.kundenbestellungService.getKundenbestellungByFiliale(14).subscribe((data: any) => {
      data.forEach((kundenbestellung: kundenbestellung) => {
        kundenbestellung.id.datum = new Date(kundenbestellung.id.datum);
      });
      
      this.kundenbestellung = data;
      this.kundenbestellung = this.mapToKundenbestellungen(data);

    });
  }

  findGleichDatum() {
    this.gleichDatum = Array.from(new Set(this.kundenbestellung.map(entry => entry.id.datum)));
    this.groupedKundenbestellungen = [];
  
    this.gleichDatum.forEach(date => {
      const kundenbestellungenForDate = this.kundenbestellung.filter(entry => entry.id.datum === date);
  
      // verhindert doppelte einträge
      const uniqueKunden = new Set<string>();
      kundenbestellungenForDate.forEach(entry => {
        uniqueKunden.add(entry.id.kunde);
      });
  
      this.groupedKundenbestellungen.push({
        datum: date,
        kunden: Array.from(uniqueKunden),
        data: kundenbestellungenForDate
      });
    });
  } 
  
}

