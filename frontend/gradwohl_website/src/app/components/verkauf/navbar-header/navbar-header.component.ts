import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth/auth.service';

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

  constructor(private authService: AuthService,
              private toastr: ToastrService,
              private router: Router) {
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

  logout(){
    this.authService.logoutUser();
    this.toastr.success("Erfolgreich augeloggt", "Erfolg")
    this.router.navigate(['/login']);

  }
}
