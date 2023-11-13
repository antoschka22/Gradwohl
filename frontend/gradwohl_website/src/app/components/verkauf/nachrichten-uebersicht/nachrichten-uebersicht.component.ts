import { Component } from '@angular/core';
import { nachricht } from 'src/model/nachricht/nachricht';

import { NachrichtService } from 'src/app/service/nachricht/nachricht.service';

interface NachrichtenID {
  nachricht: nachricht;
}

@Component({
  selector: 'app-nachrichten-uebersicht',
  templateUrl: './nachrichten-uebersicht.component.html',
  styleUrls: ['./nachrichten-uebersicht.component.scss']
})
export class NachrichtenUebersichtComponent {

  nachrichten: nachricht[] = [];

  constructor(
    private nachrichtenService: NachrichtService,
   
  ) {}

  ngOnInit(): void {
    
    this.getNachricht();

  }

  getNachricht() {
    
      this.nachrichtenService.getAllNachrichten().subscribe((data: any) => {
        data.forEach((nachricht: nachricht) => {
          nachricht.datum = nachricht.datum;
        });
    
        this.nachrichten = data;

      });
  }

}
