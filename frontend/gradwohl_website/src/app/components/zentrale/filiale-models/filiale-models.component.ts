import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FilialeService } from 'src/app/service/filiale/filiale.service';
import { filiale } from 'src/model/filiale/filiale';
import { firma } from 'src/model/firma/firma';

class FilialeModell implements filiale {
  constructor(
    public id: number,
    public name: string,
    public firma: firma,
    public sooffen: boolean
  ){}
}

@Component({
  selector: 'app-filiale-models',
  templateUrl: './filiale-models.component.html',
  styleUrls: ['./filiale-models.component.scss']
})
export class FilialeModelsComponent {

  @Input() filialen: any;
  @Input() selectedFiliale: any;
  @Input() firmen: any
  isCheckedSonntag: boolean = false;

  constructor(private filialenService: FilialeService,
              private toastrService: ToastrService){}

  @ViewChild('closeDelete') closeDelete!: ElementRef<HTMLElement>
  delete(){
    this.filialenService.deleteFiliale(this.selectedFiliale.id).subscribe(
      (data: any) => {
        this.toastrService.success("Filiale gelöscht", "Löschen");
        this.closeDelete.nativeElement.click()
        const index = this.filialen.indexOf(this.selectedFiliale);
        if (index !== -1) {
          this.filialen.splice(index, 1);
        }
      },
      (error: any) => {
        this.toastrService.error("Fehler beim Löschen der Filiale", "Löschen fehlgeschlagen");
      }
    );
    
  }

  isDropdownOpenFirma: boolean = false;
  toggleDropdownFirma() {
    this.isDropdownOpenFirma = !this.isDropdownOpenFirma;
  }

  @ViewChild('name') name!: ElementRef;
  @ViewChild('closeInsert') closeInsert!: ElementRef<HTMLElement>
  insertFiliale(){
    if(this.name.nativeElement.value.trim() === ""){
      this.toastrService.error("Sie müssen einen Namen eingeben", "Input Fehler")
      return
    }

    if(this.selectedFirma == null){
      this.toastrService.error("Sie müssen eine Lieferfirma auswählen", "Input Fehler")
      return
    }

    if(this.filialen.some((fil: any) => fil.name === this.name.nativeElement.value)){
      this.toastrService.error("Filialename ist schon vergeben", "Input Fehler")
      return
    }

    const filialeModell: FilialeModell = new FilialeModell(2147483647, this.name.nativeElement.value, this.selectedFirma, this.isCheckedSonntag)
    this.filialenService.insertFiliale(filialeModell).subscribe((data: any) => {
      this.filialen.push(data)
      this.toastrService.success("Filiale wurde hinzugefügt", "Erfolg")
      this.name.nativeElement.value = ""
      this.selectedFirma = null
      this.isCheckedSonntag = false;
      this.closeInsert.nativeElement.click()
    })
  }

  selectedFirma: firma | null = null;
  selectFirma(firma: any) {
    this.selectedFirma = firma;
    this.isDropdownOpenFirma = false;
  }

  @ViewChild('nameUpdate') nameUpdate!: ElementRef;
  reset(){
    this.selectedFirma = null
    this.name.nativeElement.value = "";
    this.nameUpdate.nativeElement.value = this.selectedFiliale.name
    this.isCheckedSonntag = false;
  }

  @ViewChild('closeUpdate') closeUpdate!: ElementRef<HTMLElement>
  update(){
    if(this.nameUpdate.nativeElement.value == this.selectedFiliale.name
      && this.selectedFirma == null
      && this.isCheckedSonntag == this.selectedFiliale.sooffen){
        this.toastrService.error("Sie haben nichts veränder", "Input Fehler")
        return
    }

    let filialeModel: FilialeModell
    if(this.selectedFirma != null){
      filialeModel = new FilialeModell(2147483647, this.nameUpdate.nativeElement.value, this.selectedFirma, this.isCheckedSonntag)
    }else{
      filialeModel = new FilialeModell(2147483647, this.nameUpdate.nativeElement.value, this.selectedFiliale.firma, this.isCheckedSonntag)
    }

    this.filialenService.updateFiliale(this.selectedFiliale.id, filialeModel).subscribe((data: any) => {
      this.toastrService.success("Filiale verändert", "Erfolg")
      this.closeUpdate.nativeElement.click()
      const index = this.filialen.findIndex((fil: any) => fil.id == this.selectedFiliale.id)
      if(index > -1){
        this.filialen.splice(index, 1);
      }
      this.filialen.push(data)
    })
  }
}
