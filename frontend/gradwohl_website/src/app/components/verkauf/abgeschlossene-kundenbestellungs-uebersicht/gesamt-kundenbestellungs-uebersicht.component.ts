import { Component } from '@angular/core';
import { kundenbestellung } from 'src/model/kundenbestellung/kundenbestellung';
import { KundenbestellungService } from 'src/app/service/kundenbestellung/kundenbestellung.service';
import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';
import { group } from '@angular/animations';

@Component({
  selector: 'app-gesamt-kundenbestellungs-uebersicht',
  templateUrl: './gesamt-kundenbestellungs-uebersicht.component.html',
  styleUrls: ['./gesamt-kundenbestellungs-uebersicht.component.scss']
})
export class GesamtKundenbestellungsUebersichtComponent {
  kundenbestellung: kundenbestellung[] = [];
  groupedKundenbestellungen: { datum: Date, kunden: string[], data: kundenbestellung[] }[] = [];
  selectedYear: string | undefined;
  selectedMonth: string | undefined;

  uniqueYears: number[] = [];
  uniqueMonths: number[] = [];
  


  gleichDatum: Date[] = [];
  gleichKunden: string[] = [];


  // Kundenbestellungsprodukte ausgeben mit Kategorie
  currentCategory: string | undefined;

  constructor(
    private kundenbestellungService: KundenbestellungService,
    private authService: AuthService,
    private mitarbeiterService: MitabeiterService
  ) {
  }

  ngOnInit() {
    this.selectedYear = undefined;
    this.selectedMonth = undefined;
    this.uniqueYears = [];
    this.uniqueMonths = [];

    this.extractUniqueYearsAndMonths();
    this.getKundenbestellungByFiliale();
  }

  filialenName: string = '';

  getKundenbestellungByFiliale() {
    var username: string = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) => {
      this.filialenName = mitarbeiter.filiale.name;
      this.kundenbestellungService.getKundenbestellungByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
        data.forEach((kundenbestellung: kundenbestellung) => {
          kundenbestellung.id.datum = new Date(kundenbestellung.id.datum);
        });

        this.kundenbestellung = data;
        this.groupKundenbestellungen();
        this.filterKundenbestellungen();
        this.extractUniqueYearsAndMonths();
      });
    });
  }


  groupKundenbestellungen() {
    this.groupedKundenbestellungen = [];

    this.kundenbestellung.forEach((kundenbestellung: kundenbestellung) => {
      const datum = kundenbestellung.id.datum;
      const kunde = kundenbestellung.id.kunde;
      const group = this.groupedKundenbestellungen.find((item) => {
        return (
          item.datum.getTime() === datum.getTime() &&
          item.kunden.includes(kunde)
        );
      });

      if (group) {
        group.data.push(kundenbestellung);
      } else {
        this.groupedKundenbestellungen.push({
          datum: datum,
          kunden: [kunde],
          data: [kundenbestellung],
        });
      }
    });
  }

// ...
filterKundenbestellungen() {
  console.log('Selected Year:', this.selectedYear);
  console.log('Selected Month:', this.selectedMonth);


  const today = new Date();
  today.setHours(0, 0, 0, 0);

  this.groupedKundenbestellungen = this.groupedKundenbestellungen.filter((group) => {
    let isDateMatch = group.datum < today;
  
    if (this.selectedYear && !isNaN(+this.selectedYear)) {
      isDateMatch = isDateMatch && group.datum.getFullYear() === +this.selectedYear;
    }
  
    if (this.selectedMonth && !isNaN(+this.selectedMonth)) {
      isDateMatch = isDateMatch && group.datum.getMonth() + 1 === +this.selectedMonth;
    }
  
    // Nur Bestellungen dieses Monats anzeigen
    isDateMatch = isDateMatch && group.datum.getMonth() === today.getMonth();
  
    return isDateMatch;
  });
  
}




  formattedDate(group: { datum: Date; kunden: string[]; data: kundenbestellung[] }): string {
    const abgeschicktAm = group.datum;
    const year = abgeschicktAm.getFullYear();
    const month = (abgeschicktAm.getMonth() + 1).toString().padStart(2, '0');
    const day = abgeschicktAm.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  extractUniqueYearsAndMonths() {
    const uniqueYears = new Set<number>();
    const uniqueMonths = new Set<number>();
  
    for (const kundenbestellung of this.kundenbestellung) {
      const datum = kundenbestellung.id.datum;
      uniqueYears.add(datum.getFullYear());
      uniqueMonths.add(datum.getMonth() + 1);
    }
  
    // alle Kundenbestellungsdatume aufgeteilt in years und months
    this.uniqueYears = Array.from(uniqueYears);
    this.uniqueMonths = Array.from(uniqueMonths);
  
    console.log('Unique Years:', this.uniqueYears);
    console.log('Unique Months:', this.uniqueMonths);
  }
  
  

  
}
