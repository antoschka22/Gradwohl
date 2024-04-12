import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { ProduktService } from 'src/app/service/produkt/produkt.service';
import { produkt } from 'src/model/produkt/produkt';
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';

class ProduktModell implements produkt {
  constructor(
    public id: number,
    public name: string,
    public produktgruppe: produktgruppe,
    public bio: boolean,
    public mehl: string,
    public hb: boolean
  ){}
}

@Component({
  selector: 'app-produkte-models',
  templateUrl: './produkte-models.component.html',
  styleUrls: ['./produkte-models.component.scss']
})

export class ProdukteModelsComponent {

  constructor(private produktService: ProduktService,
              private toastrService: ToastrService){}

  @Input() selectedProdukt!: produkt;
  @Input() produkte!: produkt[];
  @Input() produktgruppen!: produktgruppe[]

  @ViewChild('id') idInput!: ElementRef;
  @ViewChild('name') nameInput!: ElementRef;
  
  @ViewChild('closeDelete') closeDelete!: ElementRef<HTMLElement>
  @ViewChild('schliessenUpdate') closeUpdate!: ElementRef<HTMLElement>
  @ViewChild('schliessenInsert') closeInsert!: ElementRef<HTMLElement>

  delete(){
    this.produktService.deleteProdukt(this.selectedProdukt.id).subscribe((data: any) => {
      this.toastrService.success("Produkt wurde gelöscht", "Erfolg")
      this.closeDelete.nativeElement.click()

      const index = this.produkte.indexOf(this.selectedProdukt)
      if(index !== -1){
        this.produkte.splice(index, 1)
      }
    })
  }

  isDropdownOpenProdukt: boolean = false;
  toggleDropdownProdukt() {
    this.isDropdownOpenProdukt = !this.isDropdownOpenProdukt;
  }

  selectedProduktInput: produkt | null = null;
  selectProdukt(produkt: any) {
    this.selectedProduktInput = produkt;
    this.isDropdownOpenProdukt = false;
  }

  close(){
    this.isDropdownOpenProdukt = false;
    this.selectedProduktInput  = null
    if(this.selectedProdukt){
      this.idInput.nativeElement.value = this.selectedProdukt.id
      this.nameInput.nativeElement.value = this.selectedProdukt.name
    }else{
      this.idInput.nativeElement.value = ""
      this.nameInput.nativeElement.value = ""
    }
  }

  update(){
    if(this.idInput.nativeElement.value == ""
      || this.nameInput.nativeElement.value == ""){
        this.toastrService.error("Sie habe nichts verändert", "Input error")
        return
    }

    if(this.selectedProduktInput){
      if(this.idInput.nativeElement.value == this.selectedProdukt.id
        && this.nameInput.nativeElement.value == this.selectedProdukt.name
        && (this.selectedProduktInput.name == this.selectedProdukt.produktgruppe.name)){
          this.toastrService.error("Sie habe nichts verändert", "Input error")
          return
        }
    }else if(!this.selectedProduktInput
              && this.idInput.nativeElement.value == this.selectedProdukt.id
              && this.nameInput.nativeElement.value == this.selectedProdukt.name){
      this.toastrService.error("Sie habe nichts verändert", "Input error")
      return
    }


    let produktModell: ProduktModell;
    if(this.selectedProduktInput){
      produktModell = new ProduktModell(this.idInput.nativeElement.value, this.nameInput.nativeElement.value,
                                                              this.selectedProduktInput, this.selectedProdukt.bio, this.selectedProdukt.mehl,
                                                              this.selectedProdukt.hb)
    }else{
      produktModell = new ProduktModell(this.idInput.nativeElement.value, this.nameInput.nativeElement.value,
                                                              this.selectedProdukt.produktgruppe, this.selectedProdukt.bio, this.selectedProdukt.mehl,
                                                              this.selectedProdukt.hb)
    }

    this.produktService.updateProdukt(this.selectedProdukt.id, produktModell).subscribe((data: any) => {
      const index = this.produkte.findIndex((pro: produkt) => pro.id === this.selectedProdukt.id)
      this.produkte.splice(index, 1)
      this.produkte.push(data)
      this.closeUpdate.nativeElement.click()
      this.toastrService.success("Produkt verändert", "Erfolg")
    })
  }

  insert(){
    if(this.idInput.nativeElement.value == ""
    || this.nameInput.nativeElement.value == ""
    || !this.selectedProduktInput){
      this.toastrService.error("Sie müssen alles eingeben", "Input error")
      return
    }

    if(this.produkte.some((data: produkt) => data.id == this.idInput.nativeElement.value)){
      this.toastrService.error("ID ist schon vergeben", "Input error")
      return
    }

    const produktModell: ProduktModell = new ProduktModell(this.idInput.nativeElement.value, this.nameInput.nativeElement.value,
      this.selectedProduktInput, false, "Dinkel", false)

    this.produktService.insertProdukt(produktModell).subscribe((data: any) => {
      this.produkte.push(data)
      this.toastrService.success("Produkt wurde hinzugefügt", "Erfolg")
      this.closeInsert.nativeElement.click()
    })

  }
}
