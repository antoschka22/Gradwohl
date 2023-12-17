import { Component } from '@angular/core';
import { FilialeService } from 'src/app/service/filiale/filiale.service';
import { FirmaService } from 'src/app/service/firma/firma.service';
import { filiale } from 'src/model/filiale/filiale';
import { firma } from 'src/model/firma/firma';

@Component({
  selector: 'app-filiale',
  templateUrl: './filiale.component.html',
  styleUrls: ['./filiale.component.scss']
})
export class FilialeComponent {

  filialen: filiale[] = [];
  firmen: firma[] = []
  selectedFiliale!: filiale

  constructor(private filialeService: FilialeService,
              private firmaService: FirmaService){}

  ngOnInit(): void {
    this.getFilialen()
  }

  navbarToggle: boolean = false;
  toggleNavbar(bol: boolean){
    this.navbarToggle = bol
  }

  getFilialen(){
    this.filialeService.getAllFilialen().subscribe((filiale: any) => {
      this.filialen = filiale
      this.firmaService.getAllFirma().subscribe((firmen: any) => {
        this.firmen = firmen
      })
    })
  }

  select(filiale: filiale){
    this.selectedFiliale = filiale
  }

  searchTerm: string = '';
  get filteredFiliale(): filiale[] {
    if ('offen'.startsWith(this.searchTerm.toLowerCase()) || 'geschlossen'.startsWith(this.searchTerm.toLowerCase())) {
      const soOffenSearchTerm = this.searchTerm.startsWith('offen');
      return this.filialen.filter(filiale =>
        (filiale.name.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
        (filiale.firma && filiale.firma.name.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
        (filiale.sooffen === soOffenSearchTerm));
    } else {
      return this.filialen.filter(filiale =>
        filiale &&
        filiale.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        (filiale.firma && filiale.firma.name.toLowerCase().includes(this.searchTerm.toLowerCase()))
      );
    }
  }
  
}
