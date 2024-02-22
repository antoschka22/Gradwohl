import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent {

  gradwohlLogo: string = "assets/img/GradwohlLogo.png";

  constructor(private authService: AuthService,
    private toastr: ToastrService,
    private router: Router) {}

  logout(){
    this.authService.logoutUser();
    this.toastr.success("Erfolgreich augeloggt", "Erfolg")
    this.router.navigate(['/login']);
  }

}
