import { Component, OnInit } from '@angular/core';
import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';
import { ActivatedRoute } from '@angular/router';

// Tabellen: 
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';

@Component({
  selector: 'app-bestelluebersicht',
  templateUrl: './bestelluebersicht.component.html',
  styleUrls: ['./bestelluebersicht.component.scss']
})
export class BestelluebersichtComponent implements OnInit {

  date: string = '';
  
  warenbestellungen: warenbestellung[] = [];

  currentCategory: string | undefined;


  constructor(
    public route: ActivatedRoute,
    private warenbestellungService: WarenbestellungService,) {
      this.date = this.route.snapshot.params['date'] || '';
    }

  ngOnInit(): void {
    const date: string = this.route.snapshot.params['date'];
    this.loadWarenbestellungenForDate(date);
  }

  formatDateString(dateString: string) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
  }


  getFormattedDate(dateStr: string): string {
    const date = new Date(dateStr);
  
    const options = { weekday: 'short', day: '2-digit', month: 'short', year: 'numeric' } as Intl.DateTimeFormatOptions;
    return date.toLocaleDateString('de-DE', options);

  }

  loadWarenbestellungenForDate(date: string): void {
    this.warenbestellungService.getWarenbestellungByDate(this.formatDateString(date)).subscribe((data: any) => {
      data.forEach((warenbestellung: warenbestellung) => {
        warenbestellung.id.datum = new Date(warenbestellung.id.datum);
      });
      
      this.warenbestellungen = data;
    });
  }

  getHBMenge(warenbestellung: any) {
    const teigigId = warenbestellung.id.produkt.id + 2000;
  
    if (teigigId < 3000) {
      for (const produkt of this.warenbestellungen) {
        if (teigigId === produkt.id.produkt.id) {
          return produkt.menge;
        }
      }
    }
  
    return 0;
  }
  

}