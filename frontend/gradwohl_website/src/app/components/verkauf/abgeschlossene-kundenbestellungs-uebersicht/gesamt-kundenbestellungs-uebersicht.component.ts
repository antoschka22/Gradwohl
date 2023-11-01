import { Component } from '@angular/core';
import { kundenbestellung } from 'src/model/kundenbestellung/kundenbestellung';
import { KundenbestellungService } from 'src/app/service/kundenbestellung/kundenbestellung.service';

@Component({
  selector: 'app-gesamt-kundenbestellungs-uebersicht',
  templateUrl: './gesamt-kundenbestellungs-uebersicht.component.html',
  styleUrls: ['./gesamt-kundenbestellungs-uebersicht.component.scss']
})
export class GesamtKundenbestellungsUebersichtComponent {
  kundenbestellung: kundenbestellung[] = [];
  groupedKundenbestellungen: { datum: Date, kunden: string[], data: kundenbestellung[] }[] = [];
  selectedYear: number;
  selectedMonth: number;
  uniqueYears: number[];
  uniqueMonths: number[];

  gleichDatum: Date[] = [];
  gleichAuftragsnamen: String[] = [];

  // Kundenbestellungsprodukte ausgeben mit Kategorie
  currentCategory: string | undefined;

  constructor(private kundenbestellungService: KundenbestellungService) {
    this.selectedYear = 0;
    this.selectedMonth = 0;
    this.uniqueYears = [];
    this.uniqueMonths = [];
  }

  ngOnInit() {
    this.kundenbestellungService.getAllKundenbestellungen()
      .subscribe((data: Object) => {
        this.kundenbestellung = this.mapToKundenbestellungen(data);
        this.findGleichDatum();
      });
  }

  mapToKundenbestellungen(data: Object): kundenbestellung[] {
    return data as kundenbestellung[];
  }  

  findGleichDatum() {
    // So wie in derzeitige Kundenbestellungen
    this.gleichDatum = Array.from(new Set(this.kundenbestellung.map(entry => entry.id.datum)));
    this.groupedKundenbestellungen = [];
  
    this.gleichDatum.forEach(date => {
      const kundenbestellungenForDate = this.kundenbestellung.filter(entry => entry.id.datum === date);
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

    // Dropdown
    const yearsSet = new Set<number>();
    const monthsSet = new Set<number>();

    this.kundenbestellung.forEach(entry => {
      const date = new Date(entry.id.datum);
      yearsSet.add(date.getFullYear());
      monthsSet.add(date.getMonth() + 1); 
    });

    this.uniqueYears = Array.from(yearsSet).sort();
    this.uniqueMonths = Array.from(monthsSet).sort();
  }

filterKundenbestellungen() {
  const today = new Date();
  today.setHours(0, 0, 0, 0);

  this.groupedKundenbestellungen = this.groupedKundenbestellungen.filter(group => {
    return new Date(group.datum) < today;
  });
}

}
