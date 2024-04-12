import { Component,HostListener } from '@angular/core';

import { nachricht } from 'src/model/nachricht/nachricht';
import { NachrichtService } from 'src/app/service/nachricht/nachricht.service';

import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { NachrichtSendenService } from 'src/app/service/nachrichtSenden/nachricht-senden.service';
import { nachrichtSenden } from 'src/model/nachrichtSenden/nachrichtSenden';
import { nachrichtSendenId } from 'src/model/nachrichtSenden/nachrichtSendenId';
import { Router, ActivatedRoute } from '@angular/router';

class NachrichtSendenModell implements nachrichtSenden {
  constructor(
    public id: nachrichtSendenId,
    public gelesen: boolean
  ){}
}

@Component({
  selector: 'app-nachrichten-uebersicht',
  templateUrl: './nachrichten-uebersicht.component.html',
  styleUrls: ['./nachrichten-uebersicht.component.scss']
})
export class NachrichtenUebersichtComponent {

  nachrichten: nachrichtSenden[] = [];
  filteredNachrichten: nachrichtSenden[] = [];
  selectedNachricht!: nachrichtSenden;
  isMobile: boolean = true;

  months: string[] = ['Januar', 'Februar', 'März', 'April', 'Mai', 'Juni', 'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember'];
  years: number[] = [2022, 2023, 2024]; // Beispieljahre

  selectedMonth: string = '';
  selectedYear: number = 0;
  isDropdownVisible: boolean = false;

  unreadCount: number = 0;


  constructor(
    private nachrichtenService: NachrichtService,
    private authService: AuthService,
    private mitarbeiterService: MitabeiterService,
    private nachrichtSendenService: NachrichtSendenService,
    private router: Router, 
    private route: ActivatedRoute
  ) {
    this.isMobile = window.innerWidth <= 1286
  }

  ngOnInit(): void {
    this.getNachricht();
    this.populateYears();
  }

    //Änderungen der Bildschirmbreite
    @HostListener('window:resize', ['$event'])
    onResize(event: Event) {
      this.isMobile = window.innerWidth <= 1286;
    }

  toggleDropdown(): void {
    this.isDropdownVisible = !this.isDropdownVisible;
  }

  getNachricht() {
    var username: String = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) => {
      this.nachrichtSendenService.getNachrichtSendenByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
        this.nachrichten = data;
        this.filterNachrichten(); // Filtern Sie die Nachrichten beim Laden der Seite
        this.unreadCount = this.nachrichten.filter(nachricht => !nachricht.gelesen).length;
      });
    });
  }

  selectNachricht(nachricht: nachrichtSenden) {
    this.selectedNachricht = nachricht;

    if (!nachricht.gelesen) {
      const id: nachrichtSendenId = {
        filiale: nachricht.id.filiale,
        nachricht: nachricht.id.nachricht,
      };
      const nachrichtSendenModel: NachrichtSendenModell = new NachrichtSendenModell(id, true);
      this.nachrichtSendenService.updateNachrichtSenden(nachricht.id.nachricht.id, nachricht.id.filiale.id, nachrichtSendenModel).subscribe();
    }
    console.log('Selected Nachricht:', nachricht);
  }

  filterNachrichten() {
    // Filtern der Nachrichten nach ausgewähltem Monat und Jahr
    this.filteredNachrichten = this.nachrichten.filter(nachricht => {
      const nachrichtDate = new Date(nachricht.id.nachricht.datum);
      const selectedMonthIndex = this.months.indexOf(this.selectedMonth);
      const selectedYear = this.selectedYear;
      return nachrichtDate.getMonth() === selectedMonthIndex && nachrichtDate.getFullYear() === selectedYear;
    });
  }

  populateYears() {
    const currentYear = new Date().getFullYear();
    for (let year = currentYear; year >= currentYear - 10; year--) {
      this.years.push(year);
    }
  }

refreshPage() {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate([currentUrl]);
    });
  }
  
}
