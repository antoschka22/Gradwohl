import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';
import { DienstplanService } from 'src/app/service/dienstplan/dienstplan.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { dienstplan } from 'src/model/dienstplan/dienstplan';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';

/**
 * WICHTIG wenn ein Mitarbeiter gekündigt wird, nicht löschen, wenn man den Dienstplan von dieser Person noch sehen will
 * Wenn man es noch sehen will kann man ihn von der Filiale rauswerfen
 */

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
  isFilialeSoOffen: boolean = false;
  allMitarbeiter: mitabeiter[] = [];
  displayMitarbeiter: mitabeiter[] = [];
  loginMitarbeiter: any;
  selectedMonth: number = this.currDate.getMonth();

  constructor(private dienstplanService: DienstplanService,
              private authService: AuthService,
              private mitarbeiterService: MitabeiterService){}

  ngOnInit(): void {
    this.getDienstplanByFiliale();
    this.tagDesMonats(this.currDate.getFullYear(), this.currentMonat)
  }

  getDienstplanByFiliale() {
    var username: String = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) =>{
      this.dienstplanService.getDienstplanByFiliale(mitarbeiter.filiale.id).subscribe((dienstplan: any) => {
        this.loginMitarbeiter = mitarbeiter;
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
        this.isFilialeSoOffen = dienstplan[0].id.filiale.sooffen;
        //Test if the current month of this year has a Stundenplan
        this.isCurrentMonth(this.currentMonat, new Date().getFullYear())
        //Store all Mitarbeiters of that filiale + springer
        this.storeMitarbeiter(mitarbeiter.filiale.id, this.currentMonat)
        this.extractNeededDateFromMonth(this.currentMonat.toString(), this.currentYear)
      });
    });
  }

  changeDienstplanMonth(monthIndex: number){
    this.resultVonBisTageDesMonats = []
    if(monthIndex < 10){
      this.extractNeededDateFromMonth("0"+monthIndex.toString(), this.currentYear)
    }else{
      this.extractNeededDateFromMonth(monthIndex.toString(), this.currentYear)
    }
    this.tagDesMonats(parseInt(this.currentYear), monthIndex)
    this.storeMitarbeiter(this.loginMitarbeiter.filiale.id, monthIndex)
    this.selectedMonth = monthIndex -1
  }

  //Store all Mitarbeiters of this filiale + the springer
  //Store the Mitarbeiter that should be displayed
  storeMitarbeiter(filialeId: number, month: number){
    this.allMitarbeiter = []
    this.displayMitarbeiter = []
    this.mitarbeiterService.getMitarbeiterByFilialeIdWithSpringer(filialeId).subscribe((mitarbeitern: any) =>{
      //all mitarbeiter
      this.allMitarbeiter = mitarbeitern;

      //displayed mitarbeiter
      for(const mitabeiter of this.allMitarbeiter){
        if(!mitabeiter.springer){
          this.displayMitarbeiter.push(mitabeiter)
        }
      }

      //add springer die im momentanen monat schon im dienstplan sind
      for (const jsonData of this.dienstplan) {
        const datumTeile = jsonData.id.datum.toString().split('-');
        if(parseInt(datumTeile[1]) == month && 
        parseInt(datumTeile[0]) == parseInt(this.currentYear) &&
        (jsonData.id.mitarbeiter.filiale == undefined || jsonData.id.mitarbeiter.filiale.name != this.loginMitarbeiter.filiale.name)){
          if (!this.displayMitarbeiter.some(item => item.id === jsonData.id.mitarbeiter.id)) {
            this.displayMitarbeiter.push(jsonData.id.mitarbeiter);
          }
        }
      }
    });
  }
  
  currentYear: string = new Date().getFullYear().toString();
  isDropdownOpen: boolean = false;
  selectYear(year: any) {
    this.currentYear = year;
    this.isDropdownOpen = false;
  }

  createDienstplan(){
    this.monatIsSet = true;
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

  //Alle Tage aufgelistet für die Dienstplantabelle
  resultTageDesMonats: any = [];
  tagDesMonats(year: number, month: number) {
    this.resultTageDesMonats = []
    const weekdays = ['So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa'];
    const lastDayOfMonth = new Date(year, month, 0).getDate();

    for (let day = 1; day <= lastDayOfMonth; day++) {
      const date = new Date(year, month - 1, day);
      const weekday = weekdays[date.getDay()];

      this.resultTageDesMonats.push({ tagNummer: day, wochentag: weekday,  year: year, month: month});
    }
  }


  resultVonBisTageDesMonats: any = [];
  extractNeededDateFromMonth(month: string, year:string){
    for (const jsonData of this.dienstplan) {
      const datumTeile = jsonData.id.datum.toString().split('-');
      if(year == datumTeile[0] && month == datumTeile[1]){
        this.resultVonBisTageDesMonats.push({ von: jsonData.id.von, bis: jsonData.bis, month: month, year: year, day: datumTeile[2], mitarbeiter: jsonData.id.mitarbeiter})
      }
    }
    console.log(this.resultVonBisTageDesMonats)
  }

  //datum formatierung ändern 
  pad(number: number, width: number): string {
    let numberString = number.toString();
    while (numberString.length < width) {
      numberString = '0' + numberString;
    }
    return numberString;
  }

  // This method prepares the data for your template
  getVonBisForTag(tag: any, mitarbeiterId: any) {
    const tagNumber = parseInt(tag.tagNummer, 10);
    const paddedTag = this.pad(tagNumber, 2);
    const vonBisEntry = this.resultVonBisTageDesMonats.find((item: any) => item.day === paddedTag && item.mitarbeiter.id === mitarbeiterId);
  
    if (vonBisEntry) {
      const formattedVon = this.formatTime(vonBisEntry.von);
      const formattedBis = this.formatTime(vonBisEntry.bis);
  
      return `${formattedVon} - ${formattedBis}`;
    } else {
      return '-';
    }
  }  

  getHoursSpentVonBis(tag: any, mitarbeiterId: any) {
    const tagNumber = parseInt(tag.tagNummer, 10);
    const paddedTag = this.pad(tagNumber, 2);
    const vonBisEntry = this.resultVonBisTageDesMonats.find((item: any) => item.day === paddedTag && item.mitarbeiter.id === mitarbeiterId);
  
    if (vonBisEntry) {
      const hoursSpent = this.calculateHoursSpent(vonBisEntry.von, vonBisEntry.bis);
      return hoursSpent;
    } else {
      return '0:00';
    }
  }
  

  calculateHoursSpent(von: string, bis: string): string {
    if (von && bis) {
      const vonParts = von.split(':');
      const bisParts = bis.split(':');
      
      const vonHours = parseInt(vonParts[0], 10);
      const vonMinutes = parseInt(vonParts[1], 10);
      
      const bisHours = parseInt(bisParts[0], 10);
      const bisMinutes = parseInt(bisParts[1], 10);
  
      let hoursDiff = bisHours - vonHours;
      let minutesDiff = bisMinutes - vonMinutes;
  
      if (minutesDiff < 0) {
        hoursDiff -= 1;
        minutesDiff += 60;
      }
  
      // Format the result as "H:MM"
      return `${hoursDiff}:${minutesDiff < 10 ? '0' : ''}${minutesDiff}`;
    } else {
      return '0:00';
    }
  }
  
  
  formatTime(time: string): string {
    const parts = time.split(':');
    if (parts.length >= 2) {
      return `${parts[0]}:${parts[1]}`;
    }
    return time; 
  }
  

  // This method checks if the item exists in the resultVonBisTageDesMonats array
  isVonBisEntryExists(tag: any, mitarbeiter: any): boolean {
    const tagNumber = parseInt(tag.tagNummer, 10);
    const paddedTag = this.pad(tagNumber, 2);
    return this.resultVonBisTageDesMonats.some((item: any) => {
      return item.day === paddedTag && item["mitarbeiter"].id == mitarbeiter;
    });
  }  
  
  areAllMitarbeiterInDisplayMitarbeiter(): boolean {
    return this.allMitarbeiter.every(mitarbeiter =>
      this.displayMitarbeiter.some(item => item.id === mitarbeiter.id)
    );
  }

  selectedMitarbeiterToDelete: any;
  getNameToDelete(mitabeiter: mitabeiter){
    this.selectedMitarbeiterToDelete = mitabeiter;
  }
}
