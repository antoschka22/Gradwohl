import { Component } from '@angular/core';
import { ProduktService } from 'src/app/service/produkt/produkt.service';
import { ProduktgruppeService } from 'src/app/service/produktgruppe/produktgruppe.service';
import { produkt } from 'src/model/produkt/produkt';
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';

@Component({
  selector: 'app-produkte',
  templateUrl: './produkte.component.html',
  styleUrls: ['./produkte.component.scss']
})
export class ProdukteComponent {

  produkte: produkt[] = []
  produktgruppen: produktgruppe[] = []

  constructor(private produkteService: ProduktService,
              private produkteGruppenService: ProduktgruppeService){}

  ngOnInit(): void {
    this.getAllProdukte()
  }

  navbarToggle: boolean = false;
  toggleNavbar(bol: boolean){
    this.navbarToggle = bol
  }

  getAllProdukte(){
    this.produkteService.getAllProdukts().subscribe((data: any)=>{
      this.produkte = data;

      this.produkteGruppenService.getProduktgruppen().subscribe((data: any) => {
        this.produktgruppen = data;
      })
    })
  }

  searchTerm: string = '';
  get filteredProdukte(): produkt[] {
    return this.produkte.filter(produkt =>
      produkt.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      (produkt.produktgruppe && produkt.produktgruppe.name.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
      produkt.id.toString().includes(this.searchTerm.toLowerCase())
    );
  }

  selectedProdukt!: produkt;
  selectProdukt(produkt: produkt){
    this.selectedProdukt = produkt
  }

  selectGruppe(group: produktgruppe){
    this.searchTerm = group.name
  }
}
