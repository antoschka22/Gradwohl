import { Component, OnInit } from '@angular/core';
import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';
import { ActivatedRoute } from '@angular/router';

// Tabellen:
import { filiale } from 'src/model/filiale/filiale'; 
import { produkt } from 'src/model/produkt/produkt'; 
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';

interface WarenbestellungID {
  datum: Date;
  produkt: produkt;
  filiale: filiale;
}

@Component({
  selector: 'app-bestelluebersicht',
  templateUrl: './bestelluebersicht.component.html',
  styleUrls: ['./bestelluebersicht.component.scss']
})
export class BestelluebersichtComponent implements OnInit {

  date: string = '';
  
  warenbestellungen: WarenbestellungID[] = [];

  constructor(
    public route: ActivatedRoute,
    private warenbestellungService: WarenbestellungService,
    
  ) {this.date = this.route.snapshot.params['date'] || '';}

  ngOnInit(): void {
    const date: string = this.route.snapshot.params['date'];

    this.loadWarenbestellungenForDate(date);

  }

  public loadWarenbestellungenForDate(date: string): void {
    this.warenbestellungService.getWarenbestellungByFiliale(14).subscribe((data: any) => {
      this.warenbestellungen = this.mapToWarenbestellungID(data);


    });
  }

  public mapToWarenbestellungID(warenbestellungenFromService: warenbestellung[]): WarenbestellungID[] {
    return warenbestellungenFromService.map((warenbestellung: warenbestellung) => {
      return {
        datum: warenbestellung.id.datum,
        produkt: warenbestellung.id.produkt,
        filiale: warenbestellung.id.filiale
      };
    });
  }
}
