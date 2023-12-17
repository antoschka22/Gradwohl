import { Component } from '@angular/core';

//Bestellvorlage
import { vorlage } from 'src/model/vorlage/vorlage';
import { vorlageId } from 'src/model/vorlage/vorlageId';
import { VorlageService } from 'src/app/service/vorlage/vorlage.service';

import { produkt } from 'src/model/produkt/produkt';
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';
import { ProduktgruppeService } from './../../../service/produktgruppe/produktgruppe.service';

import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';


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

    public loadAllBestellvorlagen(): void {
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

    private groupByTemplateName(vorlagen: vorlage[]): { [key: string]: vorlage[] } {
      return vorlagen.reduce((grouped, vorlage) => {
        const name = vorlage.id.name;
        grouped[name] = grouped[name] || [];
        grouped[name].push(vorlage);
        return grouped;
      }, {} as { [key: string]: vorlage[] }); // Specify the type here as well
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
  private checkIfLeiter(): void {
    if(this.authService.getUserRole() == "Leiter"){
      this.isLeiter = true;
    }
  }

}
