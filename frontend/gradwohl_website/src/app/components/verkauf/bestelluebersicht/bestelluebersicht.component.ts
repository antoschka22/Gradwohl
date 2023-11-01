import { Component, OnInit } from '@angular/core';
import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';
import { ActivatedRoute } from '@angular/router';

// Tabellen:
import { filiale } from 'src/model/filiale/filiale'; 
import { produkt } from 'src/model/produkt/produkt'; 
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe'; 
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter'; 

import { warenbestellungID } from 'src/model/warenbestellung/warenbestellungID'; 


interface WarenbestellungID {
  datum: Date;
  produkt: produkt;
  filiale: filiale;
  produktgruppe: produktgruppe;
  warenbestellungID: warenbestellung;
  menge: number;
  hb: boolean;

}

@Component({
  selector: 'app-bestelluebersicht',
  templateUrl: './bestelluebersicht.component.html',
  styleUrls: ['./bestelluebersicht.component.scss']
})
export class BestelluebersichtComponent implements OnInit {

  date: string = '';
  
  warenbestellungen: WarenbestellungID[] = [];

  currentCategory: string | undefined;


  constructor(
    public route: ActivatedRoute,
    private warenbestellungService: WarenbestellungService,
    
  ) {this.date = this.route.snapshot.params['date'] || '';}

  ngOnInit(): void {
    const date: string = this.route.snapshot.params['date'];
    this.loadWarenbestellungenForDate(date);

  }

  getFormattedDate(dateStr: string): string {
    const date = new Date(dateStr);
  
    const options = { weekday: 'short', day: '2-digit', month: 'short', year: 'numeric' } as Intl.DateTimeFormatOptions;
    return date.toLocaleDateString('de-DE', options);

  }

  public loadWarenbestellungenForDate(date: string): void {
    this.warenbestellungService.getWarenbestellungByFiliale(14).subscribe((data: any) => {
      data.forEach((warenbestellung: warenbestellung) => {
        warenbestellung.id.datum = new Date(warenbestellung.id.datum);
      });
      
      this.warenbestellungen = data;
      this.warenbestellungen = this.mapToWarenbestellungID(data);

    });
  }


  public mapToWarenbestellungID(warenbestellungenFromService: warenbestellung[]): WarenbestellungID[] {
    const categories: Set<string> = new Set<string>(); // Kategorien speichern

    
    return warenbestellungenFromService.map((warenbestellung: warenbestellung) => {
      const produktgruppeName = warenbestellung.id.produkt.produktgruppe.name;
      const menge = warenbestellung.menge;
      
      // Hinzufügen der Kategorien
      categories.add(produktgruppeName);
      
      return {
        datum: warenbestellung.id.datum,
        produkt: warenbestellung.id.produkt,
        filiale: warenbestellung.id.filiale,
        produktgruppe: warenbestellung.id.produkt.produktgruppe,
        warenbestellungID: warenbestellung,
        menge: menge,
        hb:warenbestellung.id.produkt.hb,
      };
    });
  }

  
}
