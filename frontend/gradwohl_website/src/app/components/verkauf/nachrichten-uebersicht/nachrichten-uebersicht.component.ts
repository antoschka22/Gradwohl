import { Component } from '@angular/core';

import { nachricht } from 'src/model/nachricht/nachricht';
import { NachrichtService } from 'src/app/service/nachricht/nachricht.service';

import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { NachrichtSendenService } from 'src/app/service/nachrichtSenden/nachricht-senden.service';
import { nachrichtSenden } from 'src/model/nachrichtSenden/nachrichtSenden';
import { nachrichtSendenId } from 'src/model/nachrichtSenden/nachrichtSendenId';

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
  selectedNachricht!: nachrichtSenden

  constructor(
    private nachrichtenService: NachrichtService,
    private authService: AuthService,
    private mitarbeiterService: MitabeiterService,
    private nachrichtSendenService: NachrichtSendenService
  ) {}

  ngOnInit(): void {
    this.getNachricht();
  }

  getNachricht() {
    var username: String = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) =>{
      this.nachrichtSendenService.getNachrichtSendenByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
        this.nachrichten = data;
        console.log(data)
      });
    });
  }

  selectNachricht(nachricht: nachrichtSenden){
    this.selectedNachricht = nachricht

    if(!nachricht.gelesen){
      const id: nachrichtSendenId = {
        filiale: nachricht.id.filiale,
        nachricht: nachricht.id.nachricht
      }
      const nachrichtSendenModel: NachrichtSendenModell = new NachrichtSendenModell(id, true)
      this.nachrichtSendenService.updateNachrichtSenden(nachricht.id.nachricht.id, nachricht.id.filiale.id, nachrichtSendenModel).subscribe()
    }
  }

}
