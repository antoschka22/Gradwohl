import { Component, HostListener } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';
import { authRequest } from 'src/model/auth/AuthRequest';

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

  constructor(private authService: AuthService) {
    this.isMobile = window.innerWidth <= 1199;
    
  }
  ngOnInit() {
    this.loggedInUserName = this.authService.getUsernameFromToken().substring(0, 2).toUpperCase();

  }

  //Änderungen der Bildschirmbreite
  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.isMobile = window.innerWidth <= 1199;
  }
}
