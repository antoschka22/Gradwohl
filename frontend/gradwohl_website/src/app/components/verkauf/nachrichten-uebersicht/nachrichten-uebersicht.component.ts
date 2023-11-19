import { Component } from '@angular/core';

import { nachricht } from 'src/model/nachricht/nachricht';
import { NachrichtService } from 'src/app/service/nachricht/nachricht.service';

import { AuthService } from 'src/app/service/auth/auth.service';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';

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
    private authService: AuthService,
    private mitarbeiterService: MitabeiterService,
  ) {}

  ngOnInit(): void {
    
    this.getNachricht();

  }

    getNachricht() {
      var username: String = this.authService.getUsernameFromToken();
      this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) =>{
      this.nachrichtenService.getAllNachrichten().subscribe((data: any) => {
        this.nachrichten = data;
        console.log(this.nachrichten);
      });
    });
  }

}
