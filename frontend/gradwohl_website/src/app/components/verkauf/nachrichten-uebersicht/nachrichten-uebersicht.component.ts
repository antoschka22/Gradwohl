import { Component } from '@angular/core';
import { nachricht } from 'src/model/nachricht/nachricht';

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

  public mapToNachrichten(nachrichtenservice: nachricht[]): NachrichtenID[] {
    
    return nachrichtenservice.map((nachricht: nachricht) => {

      return {
        nachricht: nachricht,
      };
    });
  }
}
