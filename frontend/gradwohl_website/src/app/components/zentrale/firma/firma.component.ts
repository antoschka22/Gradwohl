import { Component, EventEmitter, Output } from '@angular/core';
import { FilialeService } from 'src/app/service/filiale/filiale.service';
import { FirmaService } from 'src/app/service/firma/firma.service';
import { LieferbarService } from 'src/app/service/lieferbar/lieferbar.service';
import { ProduktService } from 'src/app/service/produkt/produkt.service';
import { filiale } from 'src/model/filiale/filiale';
import { firma } from 'src/model/firma/firma';
import { lieferbar } from 'src/model/lieferbar/lieferbar';
import { produkt } from 'src/model/produkt/produkt';

@Component({
  selector: 'app-firma',
  templateUrl: './firma.component.html',
  styleUrls: ['./firma.component.scss']
})
export class FirmaComponent {

  filialen: filiale[] = []
  firmen: firma[] = []
  produkte: produkt[] = []
  selectedFirma: firma | null = null
  filialenSelectedFirma: filiale[]= []
  lieferbareProdukteVonFirma: lieferbar[] = []

  constructor(private firmaService: FirmaService,
              private filialeService: FilialeService,
              private produktService: ProduktService,
              private lieferbarSerivice: LieferbarService){}

  ngOnInit(): void {
    this.getFirmen()
  }

  navbarToggle: boolean = false;
  toggleNavbar(bol: boolean){
    this.navbarToggle = bol
  }

  getFirmen(){
    this.firmaService.getAllFirma().subscribe((firmen: any) => {
      this.firmen = firmen;
    })

    this.filialeService.getAllFilialen().subscribe((filiale: any) => {
      this.filialen = filiale
    })

    this.produktService.getAllProdukts().subscribe((produkte: any) => {
      this.produkte = produkte
    })
  }

  storeClickedFirma(firma: firma) {
    if(this.selectedFirma == null || firma.name != this.selectedFirma.name){
      this.lieferbareProdukteVonFirma = []
      this.lieferbarSerivice.getLieferbarByFirma(firma.name).subscribe((data: any) => {
        this.lieferbareProdukteVonFirma = data
      })
    }

    this.filialeService.getAllFilialen().subscribe((filialen: any) => {
      this.filialen = filialen
      this.selectedFirma = firma;
      this.filialenSelectedFirma = []
      for(let filiale of this.filialen){
        if(filiale.firma != null){
          if(filiale.firma.name == firma.name){
            this.filialenSelectedFirma.push(filiale)
          }
        }
      }
    })

  }
}
