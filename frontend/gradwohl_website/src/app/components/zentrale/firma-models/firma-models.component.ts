import { Location } from '@angular/common';
import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FilialeService } from 'src/app/service/filiale/filiale.service';
import { FirmaService } from 'src/app/service/firma/firma.service';
import { LieferbarService } from 'src/app/service/lieferbar/lieferbar.service';
import { filiale } from 'src/model/filiale/filiale';
import { firma } from 'src/model/firma/firma';
import { lieferbar } from 'src/model/lieferbar/lieferbar';
import { produkt } from 'src/model/produkt/produkt';

class firmaModell implements firma {
  constructor(
    public name: string
  ){}
}

class filialeModell{
  constructor(
    public id: number,
    public name: string,
    public firma: firma | null,
    public soOffen: boolean
  ){}
}

@Component({
  selector: 'app-firma-models',
  templateUrl: './firma-models.component.html',
  styleUrls: ['./firma-models.component.scss']
})
export class FirmaModelsComponent {

  @Input() firmen: any;
  @Input() filialen: any;
  @Input() produkte: any;
  @Input() selectedFirma: any;
  @Input() filialenSelectedFirma: any;
  @Input() lieferbareProdukteVonFirma: any;
  @ViewChild('schliessenDelete') schliessenButton!: ElementRef<HTMLElement>;
  firmenName: string = '';
  selectedFilialen: filiale[] = [];
  selectedProdukte: produkt[] = [];
  daysOfWeek: string[] = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag', 'Sonntag'];

  lieferbarData: lieferbar[] = [];
  
  constructor(private firmenService: FirmaService,
              private toastr: ToastrService,
              private filialeService: FilialeService,
              private lieferbarService: LieferbarService,
              private location: Location){}

  // toggle selected products for lieferbare produkte
  toggleSelection(produkt: produkt, update: boolean): void {
    let index;
    if(!update){ 
      index = this.selectedProdukte.findIndex(p => p.id === produkt.id);
      if (index > -1) {
        this.selectedProdukte.splice(index, 1);
        this.removeProductFromLieferbarData(produkt, update);
      } else {
        this.selectedProdukte.push(produkt);
        this.addProductToLieferbarData(produkt, update);
      }
    }else if(update){
      index = this.lieferbareProdukteVonFirma.findIndex((p: any) => p.id.produkt.id === produkt.id);
      if (index > -1) {
        this.removeProductFromLieferbarData(produkt, update);
      } else {
        this.addProductToLieferbarData(produkt, update);
      }
    }
  }

  // Produkt mit lieferbare tagen (default alle true)
  addProductToLieferbarData(produkt: produkt, update: boolean): void {
    const lieferbarObj: lieferbar = {
      id: {
        produkt: {
          id: produkt.id,
          name: produkt.name,
          produktgruppe: produkt.produktgruppe,
          bio: produkt.bio,
          mehl: produkt.mehl,
          hb: produkt.hb,
        },
        firma: {
          name: this.firmenName,
        },
      },
      montag: true,
      dienstag: true,
      mittwoch: true,
      donnerstag: true,
      freitag: true,
      samstag: true,
      sonntag: true,
    };
    if(!update){
      this.lieferbarData.push(lieferbarObj);
    }else{
      this.lieferbareProdukteVonFirma.push(lieferbarObj)
    }
  }

  removeProductFromLieferbarData(produkt: produkt, update: boolean): void {
    let index; 
    if(!update){
      index = this.lieferbarData.findIndex(item => item.id.produkt.id === produkt.id);
      if (index > -1) {
        this.lieferbarData.splice(index, 1);
      }
    }else{
      index = this.lieferbareProdukteVonFirma.findIndex((item:any) => item.id.produkt.id === produkt.id);
      if (index > -1) {
        this.lieferbareProdukteVonFirma.splice(index, 1);
      }
    }
  }

  // suche nach Produkt
  searchTerm: string = '';
  get filteredProdukte(): produkt[] {
    return this.produkte.filter((produkt: any) =>
      produkt.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
  
  //checks if the produkt is already clicked
  isSelected(productId: number, day: string, update: boolean): boolean {
    let product; 
    if(!update){
      product = this.lieferbarData.find(data => data.id.produkt.id === productId);
    } else{
      product = this.lieferbareProdukteVonFirma.find((data:any) => data.id.produkt.id === productId);
    }

    if (product) {
      const selectedDayValue = product[day.toLowerCase() as keyof lieferbar];
      
      if (typeof selectedDayValue === 'boolean') {
        return selectedDayValue;
      }
    }

    return false;
  }

  speichern(){
    if(this.selectedFilialen.length == 0){
      this.toastr.error("Sie haben keine Filiale ausgewählt", "Input Fehler")
      return
    }

    if(this.firmenName.trim() == ""){
      this.toastr.error("Sie haben keinen Firmennamen eingegeben", "Input Fehler")
      return
    }

    if(this.lieferbarData.length <= 0){
      this.toastr.error("Sie haben kein Produkt, welches geliefert werden soll, ausgewählt", "Input Fehler")
      return
    }

    if(this.firmen.some((firma:any) => firma.name === this.firmenName.trim())){
      this.toastr.error("Den Firmennamen gibt es schon", "Input Fehler")
      return
    }

    for(const produkt of this.lieferbarData){
      produkt.id.firma.name = this.firmenName
    }

    const model: firmaModell = new firmaModell(this.firmenName)
    this.firmenService.insertFirma(model).subscribe((data: any) => {
      this.firmen.push(data)
      for(const filiale of this.selectedFilialen){
        const filialeModel: filialeModell = new filialeModell(filiale.id, filiale.name, data, filiale.soOffen)
        this.filialeService.updateFiliale(filiale.id, filialeModel).subscribe()
      }
      this.lieferbarService.insertLieferbar(this.lieferbarData).subscribe()

      this.schliessenButton.nativeElement.click()
      this.toastr.success("Erfolgreich hinzugefügt", "Erfolg")

      this.firmenName = ""
      this.selectedFilialen = []
      this.lieferbarData = []
    })
  }

  //toggle between the modals for detail produkt and insert firma modal
  @ViewChild('deltailToOpenInsert') deltailToOpenInsert!: ElementRef<HTMLElement>;
  @ViewChild('closeInsert') closeInsert!: ElementRef<HTMLElement>;
  @ViewChild('insertToOpenDetail') insertToOpenDetail!: ElementRef<HTMLElement>;
  @ViewChild('closeDetail') closeDetail!: ElementRef<HTMLElement>;
  openFirstModal() {
    this.deltailToOpenInsert.nativeElement.click()
    this.closeDetail.nativeElement.click()
    this.lieferbarProdukt = null
  }

  lieferbarProdukt: produkt | null = null
  openSecondModal(produkt: produkt){
    if(this.lieferbarProdukt == null){
      this.lieferbarProdukt = produkt
    }
    this.insertToOpenDetail.nativeElement.click()
    this.closeInsert.nativeElement.click()
  }
  
  //toggelt die lieferbare Tage eines Produkts
  toggleProductDaySelection(productId: number, day: string, update: boolean) {
    if(!update){
      const productIndex = this.lieferbarData.findIndex(data => data.id.produkt.id === productId);

      if (productIndex !== -1) {
        const productData: any = this.lieferbarData[productIndex];
        productData[day.toLowerCase()] = !productData[day.toLowerCase()];
        this.lieferbarData[productIndex] = { ...productData };
      }
    }else{
      const productIndex = this.lieferbareProdukteVonFirma.findIndex((data:any) => data.id.produkt.id === productId);

      if (productIndex !== -1) {
        const productData: any = this.lieferbareProdukteVonFirma[productIndex];
        productData[day.toLowerCase()] = !productData[day.toLowerCase()];
        this.lieferbareProdukteVonFirma[productIndex] = { ...productData };
      }
    }
  }

  //toggelt die schon vorhandenen Firmen/Firmen löschen
  @ViewChild('deleteDetailToOpenInsert') deleteDetailToOpenInsert!: ElementRef<HTMLElement>;
  @ViewChild('closeDelete') closeDelete!: ElementRef<HTMLElement>;
  @ViewChild('deleteToOpenDeleteDetail') deleteToOpenDeleteDetail!: ElementRef<HTMLElement>;
  @ViewChild('closeDeleteDetail') closeDeleteDetail!: ElementRef<HTMLElement>;
  openFirstModalDelete() {
    this.deleteDetailToOpenInsert.nativeElement.click()
    this.closeDeleteDetail.nativeElement.click()
  }

  openSecondModaldelete(){
    this.deleteToOpenDeleteDetail.nativeElement.click()
    this.closeDelete.nativeElement.click()
  }

  loeschen() {
    this.firmenService.deleteFirma(this.selectedFirma.name).subscribe((data: any) => {
      this.location.go(this.location.path());
      window.location.reload();
      this.toastr.success("Firma wurde erfolgreich gelöscht", "Löschen");
      this.closeDeleteDetail.nativeElement.click();
    });
  }
  
  @ViewChild('firmenNameInput') firmenNameInput!: ElementRef;
  close(){
    this.firmenNameInput.nativeElement.value = this.selectedFirma.name
    this.filialenSelectedFirma = []
    for(let filiale of this.filialen){
      if(filiale.firma != undefined){
        if(filiale.firma.name == this.selectedFirma.name){
          this.filialenSelectedFirma.push(filiale)
        }
      }
    }
  }

  //toggelt die schon vorhandenen Firmen/Firmen bearbeiten
  @ViewChild('deleteDetailToOpenUpdate') deleteDetailToOpenUpdate!: ElementRef<HTMLElement>;
  @ViewChild('closeDelete') closeDetailUpdate!: ElementRef<HTMLElement>;
  @ViewChild('deleteToOpenUpdateDetail') deleteToOpenUpdateDetail!: ElementRef<HTMLElement>;
  @ViewChild('closeDetailUpdate') closeUpdateDetail!: ElementRef<HTMLElement>;
  openFirstModalUpdateDetail() {
    this.deleteDetailToOpenUpdate.nativeElement.click()
    this.closeUpdateDetail.nativeElement.click()
    this.lieferbarProdukt = null
  }

  openSecondModalUpdateDetail(produkt: produkt){
    if(this.lieferbarProdukt == null){
      this.lieferbarProdukt = produkt
    }
    this.deleteToOpenUpdateDetail.nativeElement.click()
    this.closeDetailUpdate.nativeElement.click()
  }

  update(){
    if(this.filialenSelectedFirma.length == 0){
      this.toastr.error("Keine Filiale wurde ausgewählt", "Input Fehler")
      return
    }

    if(this.firmenNameInput.nativeElement.value.trim() === ""){
      this.toastr.error("Ungültiger Name", "Input Fehler")
      return 
    }

    if(this.lieferbareProdukteVonFirma.length == 0){
      this.toastr.error("Keine Produkte zum Liefern ausgewählt", "Input Fehler")
      return
    }

    if(this.selectedFirma.name != this.firmenNameInput.nativeElement.value){
      const model: firmaModell = new firmaModell(this.firmenNameInput.nativeElement.value)
      this.firmenService.updateFirma(this.selectedFirma.name, model).subscribe((data: any) => {
        const index = this.firmen.findIndex((firma:any) => firma.name === this.selectedFirma.name);
        if (index > -1) {
          this.firmen.splice(index, 1);
        }
        this.firmen.push(data)

        for(const filiale of this.filialen){
          const indexSF = this.filialenSelectedFirma.findIndex((filialeFind: any) => filialeFind.id == filiale.id)
          if(filiale.firma != null){
            if(filiale.firma.name == this.selectedFirma.name && indexSF == -1){
              const filialeModel: filialeModell = new filialeModell(filiale.id, filiale.name, null, filiale.soOffen)
              this.filialeService.updateFiliale(filiale.id, filialeModel).subscribe()
            } else if(indexSF > -1){
              const filialeModel: filialeModell = new filialeModell(filiale.id, filiale.name, data, filiale.soOffen)
              this.filialeService.updateFiliale(filiale.id, filialeModel).subscribe()
            }
          }else if(filiale.firma == null && indexSF > -1){
            const filialeModel: filialeModell = new filialeModell(filiale.id, filiale.name, data, filiale.soOffen)
            this.filialeService.updateFiliale(filiale.id, filialeModel).subscribe()
          }
        }

        for(const lieferbar of this.lieferbareProdukteVonFirma){
          lieferbar.id.firma = data
        }
        this.lieferbarService.insertLieferbar(this.lieferbareProdukteVonFirma).subscribe()
        this.toastr.success("Firma wurde verändert", "Erfolg")
        this.closeDelete.nativeElement.click()
      })
    }else{
      for(const filiale of this.filialen){
        const index = this.filialenSelectedFirma.findIndex((filialeFind: any) => filialeFind.id == filiale.id)
        if(filiale.firma != null){
          if(filiale.firma.name == this.selectedFirma.name && index == -1){
            const filialeModel: filialeModell = new filialeModell(filiale.id, filiale.name, null, filiale.soOffen)
            this.filialeService.updateFiliale(filiale.id, filialeModel).subscribe()
          } else if(index > -1 && filiale.firma.name != this.selectedFirma.name){
            const filialeModel: filialeModell = new filialeModell(filiale.id, filiale.name, this.selectedFirma, filiale.soOffen)
            this.filialeService.updateFiliale(filiale.id, filialeModel).subscribe()
          }
        }else if(filiale.firma == null && index > -1){
          const filialeModel: filialeModell = new filialeModell(filiale.id, filiale.name, this.selectedFirma, filiale.soOffen)
          this.filialeService.updateFiliale(filiale.id, filialeModel).subscribe()
        }
      }

      for(const lieferbar of this.lieferbareProdukteVonFirma){
        lieferbar.id.firma = this.selectedFirma
      }
      this.lieferbarService.insertLieferbar(this.lieferbareProdukteVonFirma).subscribe()
      this.toastr.success("Firma wurde verändert", "Erfolg")
      this.closeDelete.nativeElement.click()
    }
  }
}