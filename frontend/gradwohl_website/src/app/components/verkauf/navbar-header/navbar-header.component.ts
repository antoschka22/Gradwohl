import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-navbar-header',
  templateUrl: './navbar-header.component.html',
  styleUrls: ['./navbar-header.component.scss']
})
export class NavbarHeaderComponent {
  gradwohlLogo: string = "assets/img/GradwohlLogo_tra.png";
  isMobile: boolean;

  constructor() {
    this.isMobile = window.innerWidth <= 1199;
  }

  //Änderungen der Bildschirmbreite
  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.isMobile = window.innerWidth <= 1199;
  }
}
