import { Component, OnInit, HostListener } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth/auth.service';
import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';

import { Router, ActivatedRoute } from '@angular/router';

// Tabellen:
import { filiale } from 'src/model/filiale/filiale';
import { produkt } from 'src/model/produkt/produkt';
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { FilialeService } from 'src/app/service/filiale/filiale.service';

// Popup / Generieren/ Händisch
import { MatDialog } from '@angular/material/dialog';

// Linke und Rechte Obere Box
import { KundenbestellungService } from 'src/app/service/kundenbestellung/kundenbestellung.service';


interface warenbestellungID {
  datum: Date;
  produkt: produkt;
  filiale: filiale;
}

function groupDates(warenbestellungen: warenbestellung[]): { [datum: string]: warenbestellungID[] } {
  const groupedData: { [datum: string]: warenbestellungID[] } = {};


  for (const warenbestellung of warenbestellungen) {
    const datum = warenbestellung.id.datum.toDateString();

    if (!groupedData[datum]) {
      groupedData[datum] = [];
    }

    const warenbestellungAusgabe: warenbestellungID = {
      datum: warenbestellung.id.datum,
      produkt: warenbestellung.id.produkt,
      filiale: warenbestellung.id.filiale,
    };

    groupedData[datum].push(warenbestellungAusgabe);
  }

  return groupedData;
}

@Component({
  selector: 'app-login-first-page',
  templateUrl: './login-first-page.component.html',
  styleUrls: ['./login-first-page.component.scss']
})

export class LoginFirstPageComponent implements OnInit {

  isMobile: boolean;

  isKundenbestellungDropdown: boolean = false;
  dropdownUp: boolean = true;

  //Kalender
  currentDate = new Date();
  selectedDate: Date | null = null;
  selectedDateBestellung: Date | null = null;

  warenbestellungen: warenbestellung[] = [];
  filiale: filiale[] = [];
  produkt: produkt[] = [];

  groupbyDateWarenbestellung: { [datum: string]: warenbestellungID[] } = {};

  loggedInUserFilialeName: string = '';



  constructor(
    private warenbestellungService: WarenbestellungService,
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router, private route: ActivatedRoute,
    private dialogRef: MatDialog,
    private kundenbestellungService: KundenbestellungService,
    private mitarbeiterService: MitabeiterService,
    private filialeService: FilialeService,
   
  ) { this.isMobile = window.innerWidth <= 1199,
    this.groupbyDateWarenbestellung = {};}


  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.isMobile = window.innerWidth <= 1199;
  }

  ngOnInit(): void {
    
    this.getWarenbestellungen();
    this.route.paramMap.subscribe(params => {
      const date = params.get('date');
    });
    //Boxen oben
    this.countHeutigeKundenbestellungen();
    this.updateHeutigeWarenbestellungStatus();

  }


  // Kundenbestellungen zaehlen für die Linke und Rechte Box oben

    // Linke Box-----------
    heutigeKundenbestellungenCount: number = 0;
    countHeutigeKundenbestellungen() {
      const heute = new Date();
      heute.setHours(0, 0, 0, 0);
      this.kundenbestellungService.getKundenbestellungByDate(heute).subscribe((data: any) => {
        this.heutigeKundenbestellungenCount = data.length;
      });
    }

    // Rechte Box-----------
    heutigerWarenbestellungsStatus: string = '';

    updateHeutigeWarenbestellungStatus() {
      const aktuelleUhrzeit = new Date();
      const abgabeWarenbestellung = new Date();
      abgabeWarenbestellung.setHours(18, 0, 0, 0);
      
      if (aktuelleUhrzeit >= abgabeWarenbestellung) {
        this.heutigerWarenbestellungsStatus = 'abgeschickt';
      } else {
        this.heutigerWarenbestellungsStatus = 'offen';
      }
    }

  //----------------------------------------------------------------


  //KALENDER Warenbestellungen einsehen
  isEmptyGroupbyDateWarenbestellung(selectedDate: Date | undefined): string {
    if (!selectedDate) {
      return 'Keine Warenbestellung gefunden';
    }
    const currentDate = new Date();
    const selectedDateString = selectedDate.toDateString();
    const dateExists = Object.keys(this.groupbyDateWarenbestellung).includes(selectedDateString);

    if (dateExists) {
      if (selectedDate.getDate() === currentDate.getDate() && currentDate.getHours() < 18) {
        return '';
      } else {
        this.router.navigate(['/bestelluebersichtAbgeschickt', selectedDateString]);
        return'';
      }
    } else {
      return '! Anstehende Warenbestellung ! ';
    }
  }


  getFormattedDate(dateStr: string): string {
    const date = new Date(dateStr);
  
    const options = { weekday: 'short', day: '2-digit', month: 'short', year: 'numeric' } as Intl.DateTimeFormatOptions;
    return date.toLocaleDateString('de-DE', options);

  }

  // Und geht filialen namen
  getWarenbestellungen() {
      var username: String = this.authService.getUsernameFromToken();
      this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) =>{
        this.warenbestellungService.getWarenbestellungByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
          data.forEach((warenbestellung: warenbestellung) => {
            warenbestellung.id.datum = new Date(warenbestellung.id.datum);
          });
      
          this.warenbestellungen = data;
          this.groupbyDateWarenbestellung = groupDates(this.warenbestellungen);

        });
        if (mitarbeiter && mitarbeiter.filiale) {
          const filialeId: number = mitarbeiter.filiale.id;
          this.getFilialeNameById(filialeId);
        }
      })
  }

  private getFilialeNameById(filialeId: number): void {
    this.filialeService.getFilialeById(filialeId).subscribe((filiale: any) => {
      if (filiale) {
        this.loggedInUserFilialeName = filiale.name;
      }
    });
  }

  onDateSelect(event: Date) {
    this.selectedDate = event;
  }

  toggleKundenbestellungDropdown() {
    this.isKundenbestellungDropdown = !this.isKundenbestellungDropdown;
    this.dropdownUp = !this.dropdownUp;
  }

  onDateSelectBestellung(event: Date) {
    this.selectedDateBestellung = event;
  }

  

}
