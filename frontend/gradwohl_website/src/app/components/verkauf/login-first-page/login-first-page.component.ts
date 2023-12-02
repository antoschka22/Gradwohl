import { Component, OnInit, HostListener } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';
import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';

import { Router, ActivatedRoute } from '@angular/router';

// Tabellen:
import { filiale } from 'src/model/filiale/filiale';
import { produkt } from 'src/model/produkt/produkt';
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';

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

  //Kalender Menü, rechts
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
    private router: Router, private route: ActivatedRoute,
    private kundenbestellungService: KundenbestellungService,
    private mitarbeiterService: MitabeiterService,
  ) { this.isMobile = window.innerWidth <= 1199,
    this.groupbyDateWarenbestellung = {};}


  @HostListener('window:resize', ['$event'])
  onResize() {
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
    this.checkIfLeiter();

  }

  // Kundenbestellungen zaehlen für die Linke und Rechte Box oben

  // Linke Box-----------
  heutigeKundenbestellungenCount: number = 0;
  countHeutigeKundenbestellungen() {
    const datum = new Date();
    const year = datum.getFullYear().toString();
    const month = (datum.getMonth() + 1).toString().padStart(2, '0');
    const day = datum.getDate().toString().padStart(2, '0');
    const heute = `${year}-${month}-${day}`;

    this.kundenbestellungService.getKundenbestellungByDate(heute.trim()).subscribe((data: any) => {
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

  //PopUP-Bestellung aufgeben:--------------------------------------------

  datePickerBestellungen(selectedDateBestellung: Date | undefined): string {
    if (!selectedDateBestellung) {
      return 'Keine Warenbestellung gefunden';
    }
    const currentDate = new Date();
    const selectedDateString = selectedDateBestellung.toDateString();
    const dateExists = Object.keys(this.groupbyDateWarenbestellung).includes(selectedDateString);

    if (dateExists) {
      if (selectedDateBestellung.getDate() === currentDate.getDate() && currentDate.getHours() < 18) {
        return '';
      } else {
        this.router.navigate(['/bestelluebersichtAbgeschickt', selectedDateString]);
        return'';
      }
    } else {
      return '! Anstehende Warenbestellung ! ';
    }
  }
  onDateSelectBestellung(event: Date) {
    this.selectedDateBestellung = event;
  }

  ///----------------------------------------------------------------------

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

  // is Leiter? Ja, mache  die Vorlagen sichtbar
  isLeiter: boolean = false;
  private checkIfLeiter(): void {
    if(this.authService.getUserRole() == "Leiter"){
      this.isLeiter = true;
    }
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
        this.loggedInUserFilialeName = mitarbeiter.filiale.name;
      }
    })
  }

  onDateSelect(event: Date) {
    this.selectedDate = event;
  }

  // Kundenbestellungen
  toggleKundenbestellungDropdown() {
    this.isKundenbestellungDropdown = !this.isKundenbestellungDropdown;
    this.dropdownUp = !this.dropdownUp;
  }

  // Vorlagen

  isVorlageDropdown: boolean = false;
  dropdownUpVorlage: boolean = true;
  toggleVorlagenDropdown() {
    this.isVorlageDropdown = !this.isVorlageDropdown;
    this.dropdownUpVorlage = !this.dropdownUpVorlage;
  }
}