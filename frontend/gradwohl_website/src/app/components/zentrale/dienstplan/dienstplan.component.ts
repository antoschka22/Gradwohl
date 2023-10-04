import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';
import { DienstplanService } from 'src/app/service/dienstplan/dienstplan.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { dienstplan } from 'src/model/dienstplan/dienstplan';

@Component({
  selector: 'app-dienstplan',
  templateUrl: './dienstplan.component.html',
  styleUrls: ['./dienstplan.component.scss']
})
export class DienstplanComponent {

  jahre: String[] = [];
  dienstplan: dienstplan[] = [];
  monate: String[] = ["Jänner", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"];
  currDate = new Date();
  currentMonat: number = this.currDate.getMonth()+1;

  constructor(private dienstplanService: DienstplanService,
              private authService: AuthService,
              private mitarbeiterService: MitabeiterService){}

  ngOnInit(): void {
    this.getDienstplanByFiliale();
  }

  getDienstplanByFiliale() {
    var username: String = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) =>{
      this.dienstplanService.getDienstplanByFiliale(mitarbeiter.filiale.id).subscribe((dienstplan: any) => {
        //Get the unique Jahre
        const uniqueYears = new Set<string>();

        // Im Dezember/November kann man den Dienstplan vom nächsten Jahr eingeben
        if(this.currDate.getMonth()+1 == 11 || this.currDate.getMonth()+1 == 12){
          const nextYear = this.currDate.getFullYear()+1
          uniqueYears.add(nextYear.toString())
        }
  
        for (const item of dienstplan) {
          const datum = item.id.datum;
          const year = new Date(datum).getFullYear().toString(); // Extract the year from the date
          uniqueYears.add(year);
        }
  
        //Store Jahre
        this.jahre = Array.from(uniqueYears);
        //Store Dienstplan
        this.dienstplan = dienstplan;
        //console.log(dienstplan)
        //Test if the current month of this year has a Stundenplan
        this.isCurrentMonth(this.currentMonat, new Date().getFullYear())
      });
    });
  }
  
  currentYear: string = new Date().getFullYear().toString();
  isDropdownOpen: boolean = false;
  selectYear(year: any) {
    this.currentYear = year;
    this.isDropdownOpen = false;
  }

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }
  
  monatIsSet: boolean = false;
  // returns true if in the DB there is a month and year of the parameters
  isCurrentMonth(month: number, year: number) {

    for (const jsonData of this.dienstplan) {
      const datumString: string = jsonData.id.datum.toString();
      const dateParts: string[] = datumString.split('-');
      if (dateParts.length === 3) {
        const jsonYear: number = parseInt(dateParts[0], 10);
        const jsonMonth: number = new Date(datumString).getMonth() + 1;

        if (jsonMonth === month && jsonYear === year) {
          this.monatIsSet = true; 
          return true;
        }
      }
    }

    this.monatIsSet = false; 
    return false;
  }

  createDienstplan(){
    this.monatIsSet = true;
  }

  //Alle Tage aufgelistet für die Dienstplantabelle
  tagDesMonats() {
    const weekdays = ['So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa'];
    const year = this.currDate.getFullYear();
    const lastDayOfMonth = new Date(year, this.currentMonat, 0).getDate();

    const result = [];

    for (let day = 1; day <= lastDayOfMonth; day++) {
      const date = new Date(year, this.currentMonat - 1, day);
      const weekday = weekdays[date.getDay()];

      result.push({ tagNummer: day, wochentag: weekday });
    }

    return result;
  }
}
