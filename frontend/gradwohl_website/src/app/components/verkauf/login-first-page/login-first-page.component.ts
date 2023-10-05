import { Component, OnInit, HostListener} from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth/auth.service';
import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';

import { Router, ActivatedRoute } from '@angular/router';

// Tabellen:
import { filiale } from 'src/model/filiale/filiale';
import { produkt } from 'src/model/produkt/produkt';
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';

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

  currentDate = new Date();
  selectedDate: Date | null = null;

  warenbestellungen: warenbestellung[] = [];
  filiale: filiale[] = [];
  produkt: produkt[] = [];

  groupbyDateWarenbestellung: { [datum: string]: warenbestellungID[] } = {};

  constructor(
    private warenbestellungService: WarenbestellungService,
    private authService: AuthService,
    private toastr: ToastrService,
    private router: Router, private route: ActivatedRoute,
   
  ) { this.isMobile = window.innerWidth <= 1199}

  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.isMobile = window.innerWidth <= 1199;
  }

  ngOnInit(): void {
    
    this.getWarenbestellungen();
    this.route.paramMap.subscribe(params => {
      const date = params.get('date');
    });
  }

  getFormattedDate(dateStr: string): string {
    const date = new Date(dateStr);
  
    const options = { weekday: 'short', day: '2-digit', month: 'short', year: 'numeric' } as Intl.DateTimeFormatOptions;
    return date.toLocaleDateString('de-DE', options);

  }

  getWarenbestellungen() {
    this.warenbestellungService.getWarenbestellungByFiliale(14).subscribe((data: any) => {
      data.forEach((warenbestellung: warenbestellung) => {
        warenbestellung.id.datum = new Date(warenbestellung.id.datum);
      });
  
      this.warenbestellungen = data;
      this.groupbyDateWarenbestellung = groupDates(this.warenbestellungen);
    });
  }
  

  onDateSelect(event: Date) {
    this.selectedDate = event;
  }

  toggleKundenbestellungDropdown() {
    this.isKundenbestellungDropdown = !this.isKundenbestellungDropdown;
    this.dropdownUp = !this.dropdownUp;
  }

}
