import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';
import { role } from 'src/model/role/role';
import { filiale } from 'src/model/filiale/filiale';
import { ToastrService } from 'ngx-toastr';
import { catchError, throwError } from 'rxjs';
import { AuthService } from 'src/app/service/auth/auth.service';
import { Router } from '@angular/router';

class MitarbeiterModell implements mitabeiter {
  constructor(
    public id: number,
    public name: string,
    public password: string,
    public role: role,
    public filiale: filiale,
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
  @ViewChild('schliessen') schliessenButton!: ElementRef<HTMLElement>;

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

}
