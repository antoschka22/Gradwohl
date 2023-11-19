import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';
import { role } from 'src/model/role/role';
import { filiale } from 'src/model/filiale/filiale';
import { ToastrService } from 'ngx-toastr';
import { catchError, throwError } from 'rxjs';
import { AuthService } from 'src/app/service/auth/auth.service';
import { Router } from '@angular/router';

/**
 * 
 * SELBER USER KANN DAS PASSWORT NICHT VERÄNDERN (NICOLE KANN NICHT DAS PASSWORT VON NICOLE ÄNDERN)
 */

class MitarbeiterModell implements mitabeiter {
  constructor(
    public id: number,
    public name: string,
    public password: string,
    public role: role,
    public filiale: filiale | null,
    public springer: boolean,
    public wochenstunden: number
  ){}
}

@Component({
  selector: 'app-mitarbeiter-models',
  templateUrl: './mitarbeiter-models.component.html',
  styleUrls: ['./mitarbeiter-models.component.scss']
})
export class MitarbeiterModelsComponent {

  @Input() mitarbeiter: any;
  @Input() changeMitarbeiter: any;
  @Input() filialen: any;
  @Input() rolen: any;
  @Input() mitarbeiterName: any
  @ViewChild('schliessenDelete') schliessenButton!: ElementRef<HTMLElement>;

  constructor(private mitarbeiterService: MitabeiterService,
              private toastr: ToastrService,
              private authService: AuthService,
              private router: Router){}

  delete(){
    delete this.changeMitarbeiter.enabled;
    delete this.changeMitarbeiter.username;
    delete this.changeMitarbeiter.accountNonExpired;
    delete this.changeMitarbeiter.accountNonLocked;
    delete this.changeMitarbeiter.credentialsNonExpired;
    delete this.changeMitarbeiter.authorities;

    this.mitarbeiterService.deleteMitarbeiterById(this.changeMitarbeiter.id).pipe(
      catchError((error) => {
        if (error.status === 403) {
          this.authService.logoutUser();
          this.router.navigate(['/login'])
          this.toastr.error('Zugriff verweigert.', 'Löschen Error', {
            timeOut: 3000
          });
        } else if(error.status === 401){
          this.toastr.error('Fehler.', 'Löschen Error', {
            timeOut: 3000
          });
        }
        return throwError(error);
      })
    ).subscribe((data: any) => {
      if(this.authService.getUsernameFromToken() == this.changeMitarbeiter.name){
        this.authService.logoutUser();
        this.router.navigate(['/login'])
      }
      
      this.toastr.success(this.changeMitarbeiter.name + " wurde gelöscht.");
      this.schliessenButton.nativeElement.click();
      const index = this.mitarbeiter.indexOf(this.changeMitarbeiter);
      if (index !== -1) {
        this.mitarbeiter.splice(index, 1);
      }
    })
  }

  isDropdownOpenFiliale: boolean = false;
  isDropdownOpenRolle: boolean = false;
  isDropdownOpenWochenstunden: boolean = false;
  toggleDropdownFiliale() {
    this.isDropdownOpenFiliale = !this.isDropdownOpenFiliale;
    this.isDropdownOpenRolle = false;
    this.isDropdownOpenWochenstunden = false
  }

  toggleDropdownRolle(){
    this.isDropdownOpenFiliale = false;
    this.isDropdownOpenRolle = !this.isDropdownOpenRolle
    this.isDropdownOpenWochenstunden = false;
  }

  toggleDropdownWochenstunden(){
    this.isDropdownOpenFiliale = false;
    this.isDropdownOpenRolle = false;
    this.isDropdownOpenWochenstunden = !this.isDropdownOpenWochenstunden
  }
  
  selectedFiliale: filiale | null = null;
  selectFiliale(filiale: any) {
    this.selectedFiliale = filiale;
    if(filiale == null){
      this.changeMitarbeiter.filiale = null
    }
    this.isDropdownOpenFiliale = false;
  }

  selectedRole: role | null = null;
  selectRole(role: role){
    this.selectedRole = role;
    this.isDropdownOpenRolle = false
  }

  selectedWochenstunden: number = 0;
  selectWochenstunde(wochenstunden: number){
    this.selectedWochenstunden = wochenstunden
    this.isDropdownOpenWochenstunden = false
  }

  @ViewChild('passwordInput') passwordInput!: ElementRef;
  @ViewChild('usernameInput') usernameInput!: ElementRef;
  @ViewChild('schliessenInsert') schliessenButtonInsert!: ElementRef<HTMLElement>;
  isCheckedSpringer: boolean = false;
  insertMitarbeiter(){
    if(this.selectedRole == null 
      || this.selectedWochenstunden == null
      || this.usernameInput.nativeElement.value.trim() == ""
      || this.passwordInput.nativeElement.value.trim() == ""){
      this.toastr.error('Bitte füllen Sie alles aus', 'Input Fehler', {
        timeOut: 3000
      });
      return
    }

    if(this.mitarbeiter.some((mit:mitabeiter) => mit.name === this.usernameInput.nativeElement.value)){
      this.toastr.error('Username ist schon vergeben', 'Input Fehler', {
        timeOut: 3000
      });
      return
    }

    const mitarbeiterInsert: MitarbeiterModell = 
    new MitarbeiterModell(1000000, this.usernameInput.nativeElement.value, this.passwordInput.nativeElement.value,
    this.selectedRole, this.selectedFiliale, this.isCheckedSpringer, this.selectedWochenstunden)
    
    this.mitarbeiterService.insertMitarbeiter(mitarbeiterInsert).pipe(
      catchError((error) => {
        if (error.status === 403) {
          this.authService.logoutUser();
          this.router.navigate(['/login'])
          this.schliessenButtonInsert.nativeElement.click();
          this.toastr.error('Zugriff verweigert.', 'Löschen Error', {
            timeOut: 3000
          });
        } else if(error.status === 401){
          this.toastr.error('Fehler.', 'Löschen Error', {
            timeOut: 3000
          });
        }
        return throwError(error);
      })
    ).subscribe((mit: any) => {
      if(this.isCheckedSpringer){
        mit.role.role = "Springer"
      }
      this.mitarbeiter.push(mit);
      this.schliessenButtonInsert.nativeElement.click();
      this.isCheckedSpringer = false
      this.selectedWochenstunden = 0;
      this.selectedRole = null;
      this.selectedFiliale = null
      this.usernameInput.nativeElement.value = "";
      this.passwordInput.nativeElement.value = "";


      this.toastr.success(mit.name + " wurde hinzugefügt.");
    })
  }

  @ViewChild('schliessenUpdate') schliessenButtonUpdate!: ElementRef<HTMLElement>;
  close(){
    this.isCheckedSpringer = false
    this.selectedWochenstunden = 0;
    this.selectedRole = null;
    this.selectedFiliale = null
    this.usernameInput.nativeElement.value = "";
    this.passwordInput.nativeElement.value = "";
  }

  @ViewChild('passwordInputUpdate') passwordInputUpdate!: ElementRef;
  update(){
    if(this.changeMitarbeiter.role.role == "Springer"){
      this.changeMitarbeiter.role.role = "Verkauf"
    }
    delete this.changeMitarbeiter.enabled;
    delete this.changeMitarbeiter.username;
    delete this.changeMitarbeiter.accountNonExpired;
    delete this.changeMitarbeiter.accountNonLocked;
    delete this.changeMitarbeiter.credentialsNonExpired;
    delete this.changeMitarbeiter.authorities;

    if(!this.selectedFiliale){
      this.selectedFiliale = this.changeMitarbeiter.filiale
    }

    if(!this.selectedRole){
      this.selectedRole = this.changeMitarbeiter.role
    }

    if(!this.selectedWochenstunden){
      this.selectedWochenstunden = this.changeMitarbeiter.wochenstunden
    }

    if(!this.selectedRole){
      return
    }

    if(this.mitarbeiter.some((mit:mitabeiter) => mit.name === this.mitarbeiterName) 
    && this.changeMitarbeiter.name != this.mitarbeiterName){
      this.toastr.error('Username ist schon vergeben', 'Input Fehler', {
        timeOut: 3000
      });
      return
    }

    if(this.passwordInputUpdate.nativeElement.value.trim() != "" && this.selectedRole){
      const mitarbeiterInsert: MitarbeiterModell = 
        new MitarbeiterModell(1000000, this.mitarbeiterName, this.passwordInputUpdate.nativeElement.value,
        this.selectedRole, this.selectedFiliale, this.changeMitarbeiter.springer, this.selectedWochenstunden)
      this.mitarbeiterService.insertMitarbeiter(mitarbeiterInsert).subscribe((data: any) => {
        if(this.changeMitarbeiter.springer){
          data.role.role = "Springer"
        }
        this.mitarbeiterService.deleteMitarbeiterById(data.id).subscribe((data: any) => {
          const index = this.mitarbeiter.indexOf(this.changeMitarbeiter);
          if (index !== -1) {
            this.mitarbeiter.splice(index, 1);
          }
        })
        this.mitarbeiter.push(data)
        this.passwordInputUpdate.nativeElement.value = "";
        this.toastr.success(data.name + " wurde geupdatet.");
        this.schliessenButtonUpdate.nativeElement.click()
      })
    }else{
      const mitarbeiterInsert: MitarbeiterModell = 
        new MitarbeiterModell(1000000, this.mitarbeiterName, this.changeMitarbeiter.password,
        this.selectedRole, this.selectedFiliale, this.changeMitarbeiter.springer, this.selectedWochenstunden)

      this.mitarbeiterService.updateMitarbeiterById(this.changeMitarbeiter.id, mitarbeiterInsert).subscribe((data: any) => {
        const index = this.mitarbeiter.indexOf(this.changeMitarbeiter);
        if (index !== -1) {
          this.mitarbeiter.splice(index, 1);
        }
        if(this.changeMitarbeiter.springer){
          data.role.role = "Springer"
        }
        this.mitarbeiter.push(data)
        this.passwordInputUpdate.nativeElement.value = "";
        this.toastr.success(data.name + " wurde geupdatet.");
        this.schliessenButtonUpdate.nativeElement.click()
      })
    }
  }
}
