import { Component } from '@angular/core';
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

  constructor(private filialeService: FilialeService){}

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

  navbarToggle: boolean = false;
  toggleNavbar(bol: boolean){
    this.navbarToggle = bol
  }

}
