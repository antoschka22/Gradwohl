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


interface VorlageWithProducts {
  vorlage: vorlage;
  products: produkt[];
}

@Component({
  selector: 'app-vorlagen-view',
  templateUrl: './vorlagen-view.component.html',
  styleUrls: ['./vorlagen-view.component.scss']
})
export class VorlagenViewComponent {

    //Vorlagen
    vorlage: vorlage[] = [];
    selectedVorlage: vorlage | null = null;
    groupedVorlagen: VorlageWithProducts[] = [];

    constructor(
      private vorlageService: VorlageService,
      private authService: AuthService,
      private mitarbeiterService: MitabeiterService,
    ){}

    ngOnInit(){
      this.loadAllBestellvorlagen();
    }

    public loadAllBestellvorlagen(): void {
      var username: String = this.authService.getUsernameFromToken();
      this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) => {
        this.vorlageService.getVorlageByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
          this.vorlage = data;
          //Bestellvorlage: 
          this.groupVorlagen();
        });
      });
    }

    groupVorlagen(): void {
      const groupedVorlagenMap = new Map<number, VorlageWithProducts>();
  
      for (const vorlageItem of this.vorlage) {
        const id = vorlageItem.id.id;
        if (groupedVorlagenMap.has(id)) {
          groupedVorlagenMap.get(id)?.products.push(vorlageItem.id.produkt);
        } else {
          groupedVorlagenMap.set(id, { vorlage: vorlageItem, products: [vorlageItem.id.produkt] });
        }
      }
  
      this.groupedVorlagen = Array.from(groupedVorlagenMap.values());
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


}
