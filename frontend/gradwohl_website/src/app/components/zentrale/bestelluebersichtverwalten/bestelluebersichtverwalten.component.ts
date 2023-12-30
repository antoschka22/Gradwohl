import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FilialeService } from 'src/app/service/filiale/filiale.service';
import { filiale } from 'src/model/filiale/filiale';

@Component({
  selector: 'app-bestelluebersichtverwalten',
  templateUrl: './bestelluebersichtverwalten.component.html',
  styleUrls: ['./bestelluebersichtverwalten.component.scss']
})
export class BestelluebersichtverwaltenComponent {

  firmenNamen: string[] = [];
  filialenMitFirma: filiale[] = []
  filialenOhneFirma: filiale[] = []
  selectedFiliale!: filiale

  currentDate = new Date();
  selectedDate: Date | null = null;
  selectedDateBestellung: Date = new Date();

  constructor(private filialeService: FilialeService,
              private toastr: ToastrService,
              private router: Router){}

  ngOnInit(): void {
    this.getFilialen()
  }

  getFilialen(){
    this.filialeService.getAllFilialen().subscribe((filialen: any) =>{
      filialen.forEach((item: filiale) => {
        if(item.firma != undefined){
          this.filialenMitFirma.push(item)
          if (!this.firmenNamen.includes(item.firma.name)) {
            this.firmenNamen.push(item.firma.name);
          }
        }else{
          this.filialenOhneFirma.push(item)
        }
      });
    });
  }

  selectFiliale(filiale: filiale){
    this.selectedFiliale = filiale
  }

  navbarToggle: boolean = false;
  toggleNavbar(bol: boolean){
    this.navbarToggle = bol
  }

  onDateSelectBestellung(event: Date) {
    this.selectedDateBestellung = event;
  }

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = date.getMonth() + 1; // getMonth() returns 0-11
    const day = date.getDate();

    // Pad the month and day with leading zeros if necessary
    const formattedMonth = month < 10 ? `0${month}` : `${month}`;
    const formattedDay = day < 10 ? `0${day}` : `${day}`;

    return `${year}-${formattedMonth}-${formattedDay}`;
  }

  @ViewChild('close') close!: ElementRef<HTMLElement>;
  typeBestellung(){
    if (this.currentDate.getTime() > this.selectedDateBestellung.getTime()) {
      this.toastr.error("Datum darf nicht von früher sein", "Error")
    } else if (this.currentDate.getTime() < this.selectedDateBestellung.getTime()) {
      this.close.nativeElement.click()
      this.router.navigate(['/warenbestellungEingabe', this.formatDate(this.selectedDateBestellung), this.selectedFiliale.id]);
    } else {
      this.toastr.error("Datum darf nicht von früher sein", "Error")
    }
  }

}
