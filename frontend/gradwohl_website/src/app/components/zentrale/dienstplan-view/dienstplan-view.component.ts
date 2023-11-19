import { Component, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as jspdf from 'jspdf';
import { AuthService } from 'src/app/service/auth/auth.service';
import { DienstplanService } from 'src/app/service/dienstplan/dienstplan.service';
import { FirmenurlaubService } from 'src/app/service/firmenurlaub/firmenurlaub.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { dienstplan } from 'src/model/dienstplan/dienstplan';
import { firmenUrlaub } from 'src/model/firmenUrlaub/firmenUrlaub';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';
import html2canvas from 'html2canvas';
import { dienstplanId } from 'src/model/dienstplan/dienstplanId';
import { FilialeService } from 'src/app/service/filiale/filiale.service';
import { filiale } from 'src/model/filiale/filiale';

class dienstplanModell implements dienstplan {
  constructor(
    public id: dienstplanId,
    public bis: string,
    public urlaub: boolean) {
    }
  }

@Component({
  selector: 'app-dienstplan-view',
  templateUrl: './dienstplan-view.component.html',
  styleUrls: ['./dienstplan-view.component.scss']
})

export class DienstplanViewComponent {

  @ViewChild('tableToSave') tableToSave!: ElementRef;
  jahre: String[] = [];
  dienstplan: dienstplan[] = [];
  monate: String[] = ["Jänner", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"];
  currDate = new Date();
  currentMonat: number = this.currDate.getMonth()+1;
  isFilialeSoOffen: boolean = false;
  allMitarbeiter: mitabeiter[] = [];
  displayMitarbeiter: mitabeiter[] = [];
  selectedMonth: number = this.currDate.getMonth();
  firmenUrlaube: firmenUrlaub[] = [];
  filialeId: number = 0;
  userRole: String = "";
  loginFiliale: filiale | undefined;

  constructor(private route: ActivatedRoute,
              private dienstplanService: DienstplanService,
              private authService: AuthService,
              private mitarbeiterService: MitabeiterService,
              private firmenUrlaubService: FirmenurlaubService,
              private filialeService: FilialeService){}

  ngAfterViewInit(){

  }

  saveToPDF() {
    const pdf = new jspdf.jsPDF('p', 'mm', 'a4');
    const content = this.tableToSave.nativeElement;

    html2canvas(content).then((canvas) => {
      const imgData = canvas.toDataURL('image/png');
      const imgWidth = 190; // A4 size: 210 x 297 mm, minus margins
      const imgHeight = (canvas.height * imgWidth) / canvas.width;

      pdf.addImage(imgData, 'PNG', 10, 10, imgWidth, imgHeight);
      pdf.save('dienstplan.pdf');
    });
  }

  ngOnInit(){
    this.userRole = this.authService.getUserRole()

    this.route.paramMap.subscribe(params => {
      const id = params.get('filialeId')
      if(id){
        this.getDienstplan(parseInt(id));
        this.filialeId = parseInt(id)
      }
    })
    this.tagDesMonats(this.currDate.getFullYear(), this.currentMonat)
  }

  getDienstplan(filialeId: number){
    this.filialeService.getFilialeById(filialeId).subscribe((filiale: any) => {
      this.loginFiliale = filiale;
      this.isFilialeSoOffen = filiale.sooffen
      //get firmenUrlaube
      this.firmenUrlaubService.getfirmenUrlaubByFirma(filiale.firma.name).subscribe((firmenUrlaub: any) => {
        this.firmenUrlaube = firmenUrlaub
      });
    })

    this.dienstplanService.getDienstplanByFiliale(filialeId).subscribe((dienstplan: any) => {
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
      //Test if the current month of this year has a Stundenplan
      this.isCurrentMonth(this.currentMonat, new Date().getFullYear())
      //Store all Mitarbeiters of that filiale + springer
      this.storeMitarbeiter(filialeId, this.currentMonat)
      this.extractNeededDateFromMonth(this.currentMonat.toString(), this.currentYear)
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
    this.storeMitarbeiter(this.filialeId, monthIndex)
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
        (jsonData.id.mitarbeiter.filiale == undefined || jsonData.id.mitarbeiter.filiale.id != this.filialeId)){
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

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }
  
  // returns true if in the DB there is a month and year of the parameters
  isCurrentMonth(month: number, year: number) {
    for (const jsonData of this.dienstplan) {
      const datumString: string = jsonData.id.datum.toString();
      const dateParts: string[] = datumString.split('-');
      if (dateParts.length === 3) {
        const jsonYear: number = parseInt(dateParts[0], 10);
        const jsonMonth: number = new Date(datumString).getMonth() + 1;

        if (jsonMonth === month && jsonYear === year) {
          return true;
        }
      }
    }

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
      if(year == datumTeile[0] 
        && month == datumTeile[1]){
        this.resultVonBisTageDesMonats.push({ urlaub: jsonData.urlaub, von: jsonData.id.von, bis: jsonData.bis, month: month, year: year, day: datumTeile[2], mitarbeiter: jsonData.id.mitarbeiter})
      }
    }
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
    const tagNumber = parseInt(tag, 10);
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

  getHoursSpentVonBis(tag: string, mitarbeiter: mitabeiter, urlaub: boolean) {
    const tagNumber = parseInt(tag, 10);
    const paddedTag = this.pad(tagNumber, 2);
    const vonBisEntry = this.resultVonBisTageDesMonats.find((item: any) => item.day === paddedTag && item.mitarbeiter.id === mitarbeiter.id);
  
    if (vonBisEntry && !urlaub) {
      const hoursSpent = this.calculateHoursSpent(vonBisEntry.von, vonBisEntry.bis);
      return hoursSpent;
    } else if(vonBisEntry && vonBisEntry.von == vonBisEntry.bis){
      return "0"
    } else{
      return (mitarbeiter.wochenstunden / 6).toFixed(2);
    }
  }
  

  calculateHoursSpent(von: string, bis: string): string {
    if (von && bis) {
      const [vonHours, vonMinutes] = von.split(':').map(Number);
      const [bisHours, bisMinutes] = bis.split(':').map(Number);
  
      const totalMinutesVon = vonHours * 60 + vonMinutes;
      const totalMinutesBis = bisHours * 60 + bisMinutes;
  
      const minutesDifference = totalMinutesBis - totalMinutesVon;
      const hours = Math.floor(minutesDifference / 60);
      const minutes = minutesDifference % 60;
  
      // Calculate the decimal amount of minutes
      const decimalMinutes = minutes / 60;
      
      // Combine the hours and decimal minutes to get the total hours spent
      const totalHoursSpent = hours + decimalMinutes;
  
      return totalHoursSpent.toFixed(2);
    } else {
      return "0";
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
  isVonBisEntryExists(tag: string, mitarbeiter: any): boolean {
    const tagNumber = parseInt(tag, 10);
    const paddedTag = this.pad(tagNumber, 2);
    return this.resultVonBisTageDesMonats.some((item: any) => {
      return item.day === paddedTag && item["mitarbeiter"].id == mitarbeiter;
    });
  }  

  isVonBisEntryUrlaub(tag: string, mitarbeiter: any): boolean {
    const tagNumber = parseInt(tag, 10);
    const paddedTag = this.pad(tagNumber, 2);
    return this.resultVonBisTageDesMonats.some((item: any) => {
      return item.day === paddedTag && item["mitarbeiter"].id == mitarbeiter && item.urlaub;
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

  isFeiertag(day: string, month: string): boolean{
    for (const jsonData of this.firmenUrlaube) {
      const datumTeile = jsonData.id.urlaubstage.datum.toString().split('-');
      if(day.length == 1)
        day = "0"+day

      if(month.length == 1){
        month = "0"+month
      }
        
      if(day == datumTeile[2] && parseInt(month) == parseInt(datumTeile[1])-1){
        return true
      }
    }
    return false;
  }

  // Get gesamte stunden pro mitarbeiter
  getTotalHours(mitarbeiter: mitabeiter){
    let totalHoursSpent:number = 0;

    for (const item of this.resultVonBisTageDesMonats) {
      if (mitarbeiter.id === item.mitarbeiter.id && !item.urlaub) {
        const time1 = new Date(`2000-01-01T${item.von}`);
        const time2 = new Date(`2000-01-01T${item.bis}`);
    
        const timeDifference = Math.abs(time1.getTime() - time2.getTime());
    
        totalHoursSpent += timeDifference / 3600000;
      }else if(item.urlaub && mitarbeiter.id === item.mitarbeiter.id && item.von != item.bis){
        totalHoursSpent+= parseFloat((mitarbeiter.wochenstunden / 6).toFixed(2));
      }
    }
    

    for(const item of this.firmenUrlaube){
      const date = new Date(item.id.urlaubstage.datum);
      const month = date.getMonth() + 1;
      const day = date.getDate();
      const newDate = new Date(parseInt(this.currentYear), month - 1, day);
      if(newDate.getDay() === 0 && !this.isFilialeSoOffen){
        continue;
      }else{
        const split = item.id.urlaubstage.datum.split('-')
        let month: string = "";
        if(this.selectedMonth+1 < 10){
          month = '0'+(this.selectedMonth+1).toString()
        }else{
          month = (this.selectedMonth+1).toString()
        }
        if(split[1] == month){
          totalHoursSpent+= parseFloat((mitarbeiter.wochenstunden / 6).toFixed(2));
        }
      }
    }
    return totalHoursSpent.toFixed(2);
  }

  alreadyUpdated: boolean = false;
  alreadyDeleted: boolean = false;
  alreadyUpdate: boolean = false;
  alreadyUrlaub: boolean = false;
  editDienstplan(event: Event, mitabeiter: any, tag: string){
    let vonBis: string = "";
    let neu: boolean = false;
    let loeschen: boolean = false;
    let initialText: string = "";
    let urlaub: boolean = false;
    let za: boolean = false;
    if(event instanceof CustomEvent){
      vonBis = event.detail.textContent.split('-')
      neu = event.detail.neu
      loeschen = event.detail.delete
      initialText = event.detail.initialText.split('-')
      urlaub = event.detail.urlaub
      za = event.detail.za
    }

    delete mitabeiter.enabled;
    delete mitabeiter.username;
    delete mitabeiter.accountNonExpired;
    delete mitabeiter.accountNonLocked;
    delete mitabeiter.credentialsNonExpired;
    delete mitabeiter.authorities;

    const updateDate = new Date(parseInt(this.currentYear), this.selectedMonth+1, parseInt(tag));
    const year = updateDate.getFullYear();
    const month = (updateDate.getMonth()).toString().padStart(2, '0');
    const day = updateDate.getDate().toString().padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;

    if(!this.alreadyUpdated){
      this.alreadyUpdated = true
      if(neu && this.loginFiliale){
        //insert
        const updateDienstplanId: dienstplanId = { datum: formattedDate, filiale: this.loginFiliale, von: vonBis[0].trim()+":00", mitarbeiter: mitabeiter}
        let dienstplanUpdate: dienstplanModell = new dienstplanModell(updateDienstplanId, vonBis[1].trim()+":00", false)
        this.dienstplanService.insertDienstplan(dienstplanUpdate).subscribe((data: any) => {
          this.alreadyUpdated = false;
          this.alreadyDeleted = false;
          this.alreadyUpdate = false;
          this.alreadyUrlaub = false;
          this.resultVonBisTageDesMonats.push({ 
            urlaub: data.urlaub,
            von: data.id.von, 
            bis: data.bis, 
            month: data.id.datum.split('-')[1], 
            year: data.id.datum.split('-')[0], 
            day: data.id.datum.split('-')[2], 
            mitarbeiter: data.id.mitarbeiter})
        });
      }
    }
    
    if(this.alreadyUpdated){
      //delete
      if(loeschen && !this.alreadyDeleted){
        this.alreadyDeleted = true;
        const currentVonBis: any = this.getVonBisForTag(tag, mitabeiter.id).split("-")
        this.dienstplanService.deleteDienstplan(formattedDate, 
                                                this.filialeId, 
                                                currentVonBis[0].trim()+":00", 
                                                mitabeiter.id).subscribe(() => {
          this.alreadyUpdated = false;
          this.alreadyDeleted = false;
          this.alreadyUpdate = false;
          this.alreadyUrlaub = false;
          const indexToRemove = this.resultVonBisTageDesMonats.findIndex((item: { urlaub: boolean, von: string; bis: string; month: string; year: number; day: string; mitarbeiter: mitabeiter; }) => {
            return (
              item.von === currentVonBis[0].trim() + ":00" &&
              item.bis === currentVonBis[1].trim() + ":00" &&
              item.month === month &&
              item.year == year &&
              item.day === day &&
              item.mitarbeiter.id === mitabeiter.id
            );
          });
          
          if (indexToRemove !== -1) {
            this.resultVonBisTageDesMonats.splice(indexToRemove, 1);
          }
        });
      } else if(urlaub && !this.alreadyUrlaub && this.loginFiliale){
        // URLAUB
        this.alreadyUrlaub = true
        const currentVonBis: any = this.getVonBisForTag(tag, mitabeiter.id).split("-")
        const indexToRemove = this.resultVonBisTageDesMonats.findIndex((item: { urlaub: boolean, von: string; bis: string; month: string; year: number; day: string; mitarbeiter: mitabeiter; }) => {
          return (
            ((item.urlaub === !urlaub) || (item.urlaub === za) || (item.urlaub === urlaub))&& 
            item.von === currentVonBis[0].trim() + ":00" &&
            item.bis === currentVonBis[1].trim() + ":00" &&
            item.month === month &&
            item.year == year &&
            item.day === day &&
            item.mitarbeiter.id === mitabeiter.id
          );
        });
        
        if (indexToRemove != -1) {
          //UPDATE URLAUB
          this.resultVonBisTageDesMonats.splice(indexToRemove, 1);
          let dienstplanUpdate: dienstplanModell;
          if(!za){
            const updateDienstplanId: dienstplanId = { datum: formattedDate, filiale: this.loginFiliale, von: "05:30:00", mitarbeiter: mitabeiter}
            dienstplanUpdate = new dienstplanModell(updateDienstplanId, "12:00:00", urlaub)   
          } else{
            const updateDienstplanId: dienstplanId = { datum: formattedDate, filiale: this.loginFiliale, von: "05:00:00", mitarbeiter: mitabeiter}
            dienstplanUpdate = new dienstplanModell(updateDienstplanId, "05:00:00", urlaub)  
          }
          this.dienstplanService.updateDienstplan(formattedDate, 
                                                this.filialeId, 
                                                currentVonBis[0].trim()+":00", 
                                                mitabeiter.id,
                                                dienstplanUpdate).subscribe((data: any) => {
          this.alreadyUrlaub = false;
          this.resultVonBisTageDesMonats.push({ 
            urlaub: data.urlaub,
            von: data.id.von, 
            bis: data.bis, 
            month: data.id.datum.split('-')[1], 
            year: data.id.datum.split('-')[0], 
            day: data.id.datum.split('-')[2], 
            mitarbeiter: data.id.mitarbeiter
          })
          });
        }else{
          //INSERT URLAUB
          let dienstplanUpdate: dienstplanModell;
          if(!za){
            const updateDienstplanId: dienstplanId = { datum: formattedDate, filiale: this.loginFiliale, von: "05:30:00", mitarbeiter: mitabeiter}
            dienstplanUpdate = new dienstplanModell(updateDienstplanId, "12:00:00", urlaub)  
          } else{
            const updateDienstplanId: dienstplanId = { datum: formattedDate, filiale: this.loginFiliale, von: "05:00:00", mitarbeiter: mitabeiter}
            dienstplanUpdate = new dienstplanModell(updateDienstplanId, "05:00:00", urlaub)  
          }
          this.dienstplanService.insertDienstplan(dienstplanUpdate).subscribe((data: any) => {
            this.alreadyUrlaub = false;
            if(!za){
              this.resultVonBisTageDesMonats.push({ 
                urlaub: data.urlaub,
                von: "05:30:00", 
                bis: "12:00:00", 
                month: data.id.datum.split('-')[1], 
                year: data.id.datum.split('-')[0], 
                day: data.id.datum.split('-')[2], 
                mitarbeiter: data.id.mitarbeiter})
            }else{
              this.resultVonBisTageDesMonats.push({ 
                urlaub: data.urlaub,
                von: "05:00:00", 
                bis: "05:00:00", 
                month: data.id.datum.split('-')[1], 
                year: data.id.datum.split('-')[0], 
                day: data.id.datum.split('-')[2], 
                mitarbeiter: data.id.mitarbeiter})
            }
          });
          console.log("NEU")
        }
        this.alreadyUpdated = false;
        this.alreadyDeleted = false;
        this.alreadyUpdate = false;
      } else if(!neu && !this.alreadyDeleted && !this.alreadyUpdate && !this.alreadyUrlaub && this.loginFiliale){
        //update
        this.alreadyUpdate = true;
        const currentVonBis: any = this.getVonBisForTag(tag, mitabeiter.id).split("-")
        const updateDienstplanId: dienstplanId = { datum: formattedDate, filiale: this.loginFiliale, von: vonBis[0].trim()+":00", mitarbeiter: mitabeiter}
        let dienstplanUpdate: dienstplanModell = new dienstplanModell(updateDienstplanId, vonBis[1].trim()+":00", false)
        this.dienstplanService.updateDienstplan(formattedDate, 
                                                this.filialeId, 
                                                currentVonBis[0].trim()+":00", 
                                                mitabeiter.id,
                                                dienstplanUpdate).subscribe((data: any) => {
          this.alreadyUpdated = false;
          this.alreadyDeleted = false;
          this.alreadyUpdate = false;
          this.alreadyUrlaub = false;
          const indexToRemove = this.resultVonBisTageDesMonats.findIndex((item: { urlaub: boolean, von: string; bis: string; month: string; year: number; day: string; mitarbeiter: mitabeiter; }) => {
            return (
              item.urlaub === !urlaub,
              item.von === currentVonBis[0].trim() + ":00" &&
              item.bis === currentVonBis[1].trim() + ":00" &&
              item.month === month &&
              item.year == year &&
              item.day === day &&
              item.mitarbeiter.id === mitabeiter.id
            );
          });
          
          if (indexToRemove !== -1) {
            this.resultVonBisTageDesMonats.splice(indexToRemove, 1);
          }
          this.resultVonBisTageDesMonats.push({ 
            urlaub: data.urlaub,
            von: data.id.von, 
            bis: data.bis, 
            month: data.id.datum.split('-')[1], 
            year: data.id.datum.split('-')[0], 
            day: data.id.datum.split('-')[2], 
            mitarbeiter: data.id.mitarbeiter
          })
          });
      }
    }
  }

  // Get die stunden die jeder mitarbeiter machen sollte
  getRequiredWorkingHours(mitarbeiter: mitabeiter){
    let workingDays: number = 0;

    for(const item of this.resultTageDesMonats){
      if(item.wochentag != 'So' && !this.isFeiertag(item.tagNummer, (parseInt(item.month)-1).toString())){
        workingDays++
      }

      if(this.isFilialeSoOffen && item.wochentag == 'So'){
        workingDays++
      }
    }
    return (workingDays * (mitarbeiter.wochenstunden / 6)).toFixed(2);
  }
}
