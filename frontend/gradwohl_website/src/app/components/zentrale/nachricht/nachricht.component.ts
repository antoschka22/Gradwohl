import { Component, ElementRef, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FilialeService } from 'src/app/service/filiale/filiale.service';
import { NachrichtService } from 'src/app/service/nachricht/nachricht.service';
import { NachrichtSendenService } from 'src/app/service/nachrichtSenden/nachricht-senden.service';
import { filiale } from 'src/model/filiale/filiale';
import { nachricht } from 'src/model/nachricht/nachricht';
import { nachrichtSenden } from 'src/model/nachrichtSenden/nachrichtSenden';
import { nachrichtSendenId } from 'src/model/nachrichtSenden/nachrichtSendenId';

class NachrichtModell implements nachricht {
  constructor(
    public id: number,
    public nachricht: string,
    public datum: string,
    public betreff: string
  ){}
}

class NachrichtSendenModell implements nachrichtSenden {
  constructor(
    public id: nachrichtSendenId,
    public gelesen: boolean
  ){}
}
@Component({
  selector: 'app-nachricht',
  templateUrl: './nachricht.component.html',
  styleUrls: ['./nachricht.component.scss']
})
export class NachrichtComponent {

  nachrichten: nachricht[] = []
  nachrichtSendenDetail: nachrichtSenden[] = []
  nachrichtSendenDetailFilialen: filiale[] = []
  nachrichtDetail: nachricht | undefined
  filialen: filiale[] = []

  seeNachrichtSenden: boolean = false;
  nachrichtText: string = '';
  betreff: string = '';

  constructor(private nachrichtenService: NachrichtService,
              private nachrichtenSendenService: NachrichtSendenService,
              private filialeService: FilialeService,
              private toastr: ToastrService){}

  ngOnInit(): void {
    this.getNachrichtenAndFilialen()
  }

  getNachrichtenAndFilialen(){
    this.nachrichtenService.getAllNachrichten().subscribe((data: any) => {
      this.nachrichten = data
    })

    this.filialeService.getAllFilialen().subscribe((data: any) => {
      this.filialen = data
    })
  }

  getNachrichtDetailsFromNachrichtId(nachrichtId: number){
    this.nachrichtenSendenService.getNachrichtSendenByNachrichtId(nachrichtId).subscribe((data: any) => {
      this.nachrichtSendenDetail = data
      this.nachrichtSendenDetailFilialen = []
      for(const nachricht of this.nachrichtSendenDetail){
        this.filialeService.getFilialeById(nachricht.id.filiale.id).subscribe((data: any) => {
          this.nachrichtSendenDetailFilialen.push(data)
        })
      }
      this.nachrichtDetail = this.nachrichten.find((data: nachricht) => data.id == nachrichtId)
    })
    this.seeNachrichtSenden = false
  }

  navbarToggle: boolean = false;
  toggleNavbar(bol: boolean){
    this.navbarToggle = bol
  }

  sendMessage(){
    this.seeNachrichtSenden = !this.seeNachrichtSenden
  }

  insertNachricht(){
    const currDate = new Date()
    console.log(this.nachrichtText, this.betreff, this.selectedFilialen, currDate.getFullYear()+"-"+(currDate.getMonth()+1)+"-"+currDate.getDate())

    if(this.selectedFilialen.length == 0){
      this.toastr.error("Sie müssen eine Filiale auswählen", "Input Error")
      return 
    }

    if(this.betreff.trim() == ""){
      this.toastr.error("Sie müssen einen Betreff schreiben", "Input Error")
      return 
    }

    if(this.nachrichtText.trim() == ""){
      this.toastr.error("Sie müssen eine Nachricht schreiben", "Input Error")
      return
    }

    const nachrichtModel: NachrichtModell = 
    new NachrichtModell(99999, this.nachrichtText, currDate.getFullYear()+"-"+(currDate.getMonth()+1)+"-"+currDate.getDate(), this.betreff)

    let nachrichtenSendenModel: NachrichtSendenModell[] = []
    this.nachrichtenService.insertNachricht(nachrichtModel).subscribe((data: any) => {
      this.toastr.success("Nachricht gesendet", "Erfolg")
      this.nachrichten.push(data)

      for(const filiale of this.selectedFilialen){
        const id: nachrichtSendenId = {
          filiale: filiale,
          nachricht: data
        }
        nachrichtenSendenModel.push(new NachrichtSendenModell(id, false))
      }

      this.nachrichtenSendenService.insertNachrichtSenden(nachrichtenSendenModel).subscribe()

      this.getNachrichtDetailsFromNachrichtId(data.id)
      this.nachrichtText = ""
      this.betreff = ""
      this.allSelected = false
      this.selectedFilialen = []
    })
  }

  isNachrichtGelesenByFiliale(filialeId: number){
    return this.nachrichtSendenDetail.find((data: nachrichtSenden) => data.id.filiale.id == filialeId)?.gelesen
  }


  // SUGESTION CODE
  @ViewChild('searchInput') searchInput!: ElementRef;
  searchText = '';
  filteredFilialen: filiale[] = [];
  selectedFilialen: filiale[] = [];
  allSelected = false;
  allText = 'alle';

  filterFilialen() {
    if (this.searchText.toLowerCase() === this.allText) {
      this.filteredFilialen = [];
    } else {
      this.filteredFilialen = this.filialen.filter(filiale =>
        filiale.name.toLowerCase().includes(this.searchText.toLowerCase())
      );
    }
  } 

  selectFiliale(filiale: filiale) {
    if (!this.allSelected) {
      this.selectedFilialen.push(filiale);
      this.searchText = '';
      this.filteredFilialen = [];
      this.focusInput();
    }
  }

  confirmSelection() {
    if (this.searchText.toLowerCase() === this.allText) {
      this.selectAllFilialen();
    } else if (this.filteredFilialen.length === 1) {
      this.selectFiliale(this.filteredFilialen[0]);
    }
  }

  selectAllFilialen() {
    this.allSelected = true;
    this.selectedFilialen = [...this.filialen];
    this.searchText = "";
    this.filteredFilialen = [];
    this.focusInput();
  }

  removeFiliale(filialeToRemove: filiale | null) {
    if (this.allSelected) {
      this.allSelected = false;
      this.searchText = '';
      this.selectedFilialen = []
    } else {
      this.selectedFilialen = this.selectedFilialen.filter(filiale => filiale !== filialeToRemove);
    }
    this.focusInput();
  }

  focusInput() {
    this.searchInput.nativeElement.focus();
  }

  getInputWidth() {
    // Adjust logic to accommodate 'alle' text
    if (this.allSelected) {
      return (this.allText.length * 8) + 'px';
    }
    const textLength = this.searchText.length;
    const charWidth = 8; // average width per character, adjust as needed
    const width = textLength * charWidth;
    const minWidth = 50; // minimum width of the input
    return (width > minWidth ? width : minWidth) + 'px';
  }
}