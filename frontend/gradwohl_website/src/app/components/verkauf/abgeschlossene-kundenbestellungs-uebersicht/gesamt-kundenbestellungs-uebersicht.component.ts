import { Component } from '@angular/core';
import { kundenbestellung } from 'src/model/kundenbestellung/kundenbestellung';
import { KundenbestellungService } from 'src/app/service/kundenbestellung/kundenbestellung.service';
import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';

@Component({
  selector: 'app-gesamt-kundenbestellungs-uebersicht',
  templateUrl: './gesamt-kundenbestellungs-uebersicht.component.html',
  styleUrls: ['./gesamt-kundenbestellungs-uebersicht.component.scss']
})
export class GesamtKundenbestellungsUebersichtComponent {
  kundenbestellung: kundenbestellung[] = [];
  groupedKundenbestellungen: { datum: Date, kunden: string[], data: kundenbestellung[] }[] = [];

  uniqueYears: number[] = [];
  uniqueMonths: number[] = [];

  gleichDatum: Date[] = [];
  gleichKunden: string[] = [];
  datum: Date = new Date();
  selectedYear: string = this.datum.getFullYear().toString();
  selectedMonth: string = (this.datum.getMonth()+1).toString();

  // Kundenbestellungsprodukte ausgeben mit Kategorie
  currentCategory: string | undefined;

  constructor(
    private kundenbestellungService: KundenbestellungService,
    private authService: AuthService,
    private mitarbeiterService: MitabeiterService
  ) {
  }

  ngOnInit() {
    this.getKundenbestellungByFiliale();
  }

  filialenName: string = '';
  getKundenbestellungByFiliale() {
    var username: string = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) => {
      this.filialenName = mitarbeiter.filiale.name;
      this.kundenbestellungService.getKundenbestellungByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
        this.kundenbestellung = data;
        this.filterKundenbestellungen(this.datum.getFullYear().toString(), (this.datum.getMonth()+1).toString());
        this.extractUniqueYearsAndMonths();
      });
    });
  }

  filterKundenbestellungen(year: string, month: string) {
    let heuteKundenbestellungen: kundenbestellung[] = [];
  
    for (const item of this.kundenbestellung) {
      const datumSplit = item.id.datum.split('-');
  
      if (parseInt(datumSplit[0]) == parseInt(year)
        && parseInt(datumSplit[1]) == parseInt(month)
        && parseInt(datumSplit[2]) < this.datum.getDate()) {
        const exists = heuteKundenbestellungen.some(existingItem => 
          existingItem.id.kunde === item.id.kunde && existingItem.id.datum === item.id.datum
        );
  
        if (!exists) {
          heuteKundenbestellungen.push(item);
        }
      }
    }
  
    return heuteKundenbestellungen;
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
      const datum: string[] = kundenbestellung.id.datum.split("-");
      uniqueYears.add(parseInt(datum[0]));
      uniqueMonths.add(parseInt(datum[1]));
    }
  
    // alle Kundenbestellungsdatume aufgeteilt in years und months
    this.uniqueYears = Array.from(uniqueYears);
    this.uniqueMonths = Array.from(uniqueMonths);
  }

  getDetailKundenbestellung(kunde: string, datum: string){
    let detailKundenbestellung: kundenbestellung[] = []
    for (const item of this.kundenbestellung) {
      if (item.id.kunde === kunde && item.id.datum === datum) {
        detailKundenbestellung.push(item);
      }
    }
    return detailKundenbestellung;
  }
}