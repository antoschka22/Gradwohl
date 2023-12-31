import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth/auth.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { NachrichtSendenService } from 'src/app/service/nachrichtSenden/nachricht-senden.service';
import { nachrichtSenden } from 'src/model/nachrichtSenden/nachrichtSenden';

interface AuthName {
  name: string;
}

@Component({
  selector: 'app-navbar-header',
  templateUrl: './navbar-header.component.html',
  styleUrls: ['./navbar-header.component.scss']
})
export class NavbarHeaderComponent {
  gradwohlLogo: string = "assets/img/GradwohlLogo_tra.png";
  isMobile: boolean;

  loggedInUserName: string ='';

  nachrichten: nachrichtSenden[] = []

  constructor(private authService: AuthService,
              private toastr: ToastrService,
              private router: Router,
              private nachrichtSendenService: NachrichtSendenService,
              private mitarbeiterService: MitabeiterService) {
    this.isMobile = window.innerWidth <= 1199;
    
  }
  ngOnInit() {
    this.loggedInUserName = this.authService.getUsernameFromToken().substring(0, 2).toUpperCase();
    this.getNachrichten()

  }

  //Änderungen der Bildschirmbreite
  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.isMobile = window.innerWidth <= 1199;
  }

  getNachrichten(){
    const username: string = this.authService.getUsernameFromToken()
    this.mitarbeiterService.getMitarbeiterByName(username).subscribe((data: any) => {
      this.nachrichtSendenService.getNachrichtSendenByFiliale(data.filiale.id).subscribe((data: any) => {
        for(const nachricht of data){
          if(!nachricht.gelesen){
            this.nachrichten.push(nachricht)
          }
        }
      })
    })
  }

  logout(){
    this.authService.logoutUser();
    this.toastr.success("Erfolgreich augeloggt", "Erfolg")
    this.router.navigate(['/login']);

  }
}
