import { Component, Input } from '@angular/core';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';

@Component({
  selector: 'app-dienstplan-models',
  templateUrl: './dienstplan-models.component.html',
  styleUrls: ['./dienstplan-models.component.scss']
})
export class DienstplanModelsComponent {
  
  @Input() displayMitarbeiter: any;
  @Input() allMitarbeiter: any;
  @Input() selectedMitarbeiterToDelete: any;
  selectedMitarbeiterToInsert: mitabeiter[] = [];

  //store the selected mitarbeiter of toggle to an array
  toggleSelectedMitarbeiter(mitarbeiter: any) { // TO DO Springer is in dropdown wenn schon im dienstplan von anfang an ist
    if(mitarbeiter.isChecked) {
      this.selectedMitarbeiterToInsert.push(mitarbeiter);
    } else {
      const index = this.selectedMitarbeiterToInsert.findIndex(item => item === mitarbeiter);
      if (index !== -1) {
        this.selectedMitarbeiterToInsert.splice(index, 1);
      }
    }
  }

  insertSelectedMitarbeiter() {
    this.selectedMitarbeiterToInsert.forEach((mitarbeiter: any) => {
      if (!this.displayMitarbeiter.includes(mitarbeiter)) {
        this.displayMitarbeiter.push(mitarbeiter);
      }
    });
  
    this.selectedMitarbeiterToInsert = [];
  
    this.allMitarbeiter.forEach((mitarbeiter: any) => {
      mitarbeiter.isChecked = false;
    });
  }
  

  loeschen(mitabeiter: mitabeiter){
    const index = this.displayMitarbeiter.indexOf(mitabeiter);
    if (index !== -1) {
      this.displayMitarbeiter.splice(index, 1);
    }
  }
}
