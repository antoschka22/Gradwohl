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

  filialen: filiale[] = [];
  firmenNamen: string[] = [];

  constructor(private filialeService: FilialeService){}

  ngOnInit(): void {
    this.getFilialen()
  }


  getFilialen(){
    this.filialeService.getAllFilialen().subscribe((filialen: any) =>{
      this.filialen = filialen;

      filialen.forEach((item: filiale) => {
        if (!this.firmenNamen.includes(item.firma.name)) {
          this.firmenNamen.push(item.firma.name);
        }
      });
    });
  }

}
