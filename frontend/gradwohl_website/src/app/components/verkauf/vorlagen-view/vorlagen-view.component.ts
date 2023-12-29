import { Component } from '@angular/core';

//Bestellvorlage
import { vorlage } from 'src/model/vorlage/vorlage';
import { vorlageId } from 'src/model/vorlage/vorlageId';
import { VorlageService } from 'src/app/service/vorlage/vorlage.service';

import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { produkt } from 'src/model/produkt/produkt';


@Component({
  selector: 'app-vorlagen-view',
  templateUrl: './vorlagen-view.component.html',
  styleUrls: ['./vorlagen-view.component.scss']
})
export class VorlagenViewComponent {

    //Vorlagen
    vorlageId: vorlageId[] = [];
    vorlage: vorlage[] = [];
    selectedVorlage: vorlage | null = null;
    groupedVorlagen: { [key: string]: vorlage[] } = {};

    constructor(
      private vorlageService: VorlageService,
      private authService: AuthService,
      private mitarbeiterService: MitabeiterService,
    ){}

    ngOnInit(){
      this.loadAllBestellvorlagen();
      this.checkIfLeiter();
    }

    loadAllBestellvorlagen(): void {
      var username: String = this.authService.getUsernameFromToken();
      this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) => {
        this.vorlageService.getVorlageByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
          this.vorlageId = data;
          this.vorlage = data;

          // Group Vorlagen by name
          this.groupedVorlagen = this.groupByTemplateName(this.vorlage);
        });
      });
    }

    loeschen(){
      for (const key of Object.keys(this.groupedVorlagen)) {
        if(key == this.selectedGroupName){
          for (const vorlage of this.groupedVorlagen[key]) {
            this.vorlageService.deleteVorlage(vorlage.id.name, vorlage.id.filiale.id, vorlage.id.produkt.id).subscribe()
          }
          delete this.groupedVorlagen[key]
        }
      }
    }

    groupByTemplateName(vorlagen: vorlage[]): { [key: string]: vorlage[] } {
      return vorlagen.reduce((grouped, vorlage) => {
        const name = vorlage.id.name;
        grouped[name] = grouped[name] || [];
        grouped[name].push(vorlage);
        return grouped;
      }, {} as { [key: string]: vorlage[] }); // Specify the type here as well
    }

    getHBValueFromFrischId(produkt: produkt){
      if(this.selectedGroupName){
        const menge = this.groupedVorlagen[this.selectedGroupName].find((vor: vorlage) => vor.id.produkt.id == produkt.id+2000)?.menge
        if(menge){
          return menge
        }else{
          return 0;
        }
      }else{
        return 0
      }
    }
  
    toggleAccordion(group: any) {
      this.accordionStates[group.vorlage.id.id] = !this.accordionStates[group.vorlage.id.id];
    }
  
    isAccordionOpen(id: number): boolean {
      return this.accordionStates[id];
    }

    accordionStates: { [id: number]: boolean } = {};
    selectVorlage(vorlage: vorlage): void {
      this.selectedVorlage = vorlage;
    }

    // Einklappen / Ausklappen
    toggleProducts(groupName: string): void {
      if (this.isSelected(groupName)) {
          this.selectedGroupName = null;
      } else {
          this.selectedGroupName = groupName;
      }
    }

    isSelected(groupName: string): boolean {
      return this.selectedGroupName === groupName;
    }

    selectedGroupName: string | null = null;
    showProducts(groupName: string): void {
      this.selectedGroupName = groupName;
    }

    // Kategorie Überschrift
    currentCategory: string | undefined;

    // Löschen Button
    isLeiter: boolean = false;
    checkIfLeiter(): void {
      if(this.authService.getUserRole() == "Leiter"){
        this.isLeiter = true;
      }
    }

}
