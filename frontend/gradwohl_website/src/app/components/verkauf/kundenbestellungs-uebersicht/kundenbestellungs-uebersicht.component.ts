import { filiale } from 'src/model/filiale/filiale';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { kundenbestellung } from 'src/model/kundenbestellung/kundenbestellung';
import { KundenbestellungService } from 'src/app/service/kundenbestellung/kundenbestellung.service';

// Hinzufügen
import { produkt } from './../../../../model/produkt/produkt';
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';
import { ProduktgruppeService } from './../../../service/produktgruppe/produktgruppe.service';
import { ProduktService } from 'src/app/service/produkt/produkt.service';

import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';



interface KundenbestellungId {
  menge: number;
  hb: boolean;
  produktgruppe: produktgruppe;
  produkt: produkt;

}

@Component({
  selector: 'app-kundenbestellungs-uebersicht',
  templateUrl: './kundenbestellungs-uebersicht.component.html',
  styleUrls: ['./kundenbestellungs-uebersicht.component.scss']
})
export class KundenbestellungsUebersichtComponent implements OnInit {

  kundenbestellung: kundenbestellung[] = [];

  gleichDatum: Date[] = [];
  gleichAuftragsnamen: String[] = [];

  groupedKundenbestellungen: { datum: Date, kunden: string[], data: kundenbestellung[] }[] = [];

  // Kundenbestellungsprodukte ausgeben mit Kategorie
  currentCategory: string | undefined;
  produktgruppen: produktgruppe[] = [];

  //Hinzufügen
  produkte: produkt[] = [];
  ausgewaehlteProduktgruppe: produktgruppe | null = null;
  angezeigteProdukte: produkt[] = [];


  constructor(private kundenbestellungService: KundenbestellungService,
    private produktService: ProduktService,
    private authService: AuthService,
    private mitarbeiterService: MitabeiterService,
    private produktgruppeService: ProduktgruppeService,
    private router: Router,
    ) {}

  ngOnInit() {
    //Hinzufügen
    this.loadAllProduktgruppen();
    this.loadKundenbestellprodukte();
    //Kundenbestellungen abfragen
    this.getKundenbestellungByFiliale();

  }
  
//---------Hinzufügen-POPUP:

          public loadKundenbestellprodukte(): void {
            var username: String = this.authService.getUsernameFromToken();

              this.produktService.getAllProdukts().subscribe((data: any) => {
                this.produkte = data;
            });

          }

            public loadAllProduktgruppen(): void {
              this.produktgruppeService.getProduktgruppen().subscribe((data: any) => {
                this.produktgruppen = data;
              });
            }

            onProduktgruppeClicked(produktgruppe: produktgruppe) {
              this.ausgewaehlteProduktgruppe = produktgruppe;
              this.angezeigteProdukte = this.produkte.filter((produkt) => produkt.produktgruppe && produkt.produktgruppe.name === produktgruppe.name);
            }

            //Search-button-Hinzu
            searchProduct(event: Event): void {
              const query = (event.target as HTMLInputElement).value;
              if (query) {
                this.angezeigteProdukte = this.produkte.filter((produkt) =>
                  produkt.name.toLowerCase().includes(query.toLowerCase())
                );
              } else {
                // zeige alle Produkte an (wenn leer)
                this.angezeigteProdukte = this.produkte;
              }
            }


            resetForm() {
              this.angezeigteProdukte = []; 

              (document.getElementById('auftragnameKundenbestellung') as HTMLInputElement).value = '';
              (document.getElementById('datumInput') as HTMLInputElement).value = '';

              this.ausgewaehlteProduktgruppe = null;
            }
            
            onWeiterClicked(): void {

              const auftragsname = (document.getElementById('auftragnameKundenbestellung') as HTMLInputElement).value
              const datum = (document.getElementById('datumInput') as HTMLInputElement).value;

              this.router.navigate(['/kundenbestellung'], {
                state: {
                  auftragsname,
                  datum,
                },
              });
            }

//---------ENDE-Hinzufügen-POPUP------

  displayProduktgruppenNames() {
    this.produktgruppen.forEach((produktgruppe: produktgruppe) => {
      console.log(produktgruppe.name);
    });
  }
  

  mapToKundenbestellungen(data: Object): kundenbestellung[] {
    return data as kundenbestellung[];
  }  

  filialenName: string = '';
  getKundenbestellungByFiliale() {

    var username: string = this.authService.getUsernameFromToken();
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((mitarbeiter: any) => {
      this.filialenName = mitarbeiter.filiale.name;
      this.kundenbestellungService.getKundenbestellungByFiliale(mitarbeiter.filiale.id).subscribe((data: any) => {
        data.forEach((kundenbestellung: kundenbestellung) => {
          kundenbestellung.id.datum = new Date(kundenbestellung.id.datum);
        });

        this.kundenbestellung = data;
        this.groupKundenbestellungen();
      });
    });
  }

  groupKundenbestellungen() {
    this.groupedKundenbestellungen = [];

    this.kundenbestellung.forEach((kundenbestellung: kundenbestellung) => {
      const datum = kundenbestellung.id.datum;
      const kunde = kundenbestellung.id.kunde;
      const group = this.groupedKundenbestellungen.find((item) => {
        return (
          item.datum.getTime() === datum.getTime() &&
          item.kunden.includes(kunde)
        );
      });

      if (group) {
        group.data.push(kundenbestellung);
      } else {
        this.groupedKundenbestellungen.push({
          datum: datum,
          kunden: [kunde],
          data: [kundenbestellung],
        });
      }
    });
  }

  formattedDate(group: { datum: Date; kunden: string[]; data: kundenbestellung[] }): string {
    const abgeschicktAm = group.datum;
    const year = abgeschicktAm.getFullYear();
    const month = (abgeschicktAm.getMonth() + 1).toString().padStart(2, '0');
    const day = abgeschicktAm.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
  
}

