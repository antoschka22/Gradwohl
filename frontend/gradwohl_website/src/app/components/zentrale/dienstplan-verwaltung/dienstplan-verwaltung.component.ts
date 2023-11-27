import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FilialeService } from 'src/app/service/filiale/filiale.service';
import { filiale } from 'src/model/filiale/filiale';

@Component({
  selector: 'app-dienstplan-verwaltung',
  templateUrl: './dienstplan-verwaltung.component.html',
  styleUrls: ['./dienstplan-verwaltung.component.scss']
})
export class DienstplanVerwaltungComponent {

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

}
