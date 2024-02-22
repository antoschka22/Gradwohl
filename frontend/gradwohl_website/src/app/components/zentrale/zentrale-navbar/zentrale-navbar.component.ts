import { Component, EventEmitter, HostListener, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-zentrale-navbar',
  templateUrl: './zentrale-navbar.component.html',
  styleUrls: ['./zentrale-navbar.component.scss']
})
export class ZentraleNavbarComponent {
  isNavbarCollapsed: boolean = false;

  constructor(private authService: AuthService,
    private toastr: ToastrService,
    private router: Router) {}

  @Output() newItemEvent = new EventEmitter<boolean>()

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.collapseNavbarBasedOnWidth();
  }

  ngOnInit() {
    this.collapseNavbarBasedOnWidth();
  }

  toggleNavbar(input: null | string) {
    if(input != null){
      this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }
    this.newItemEvent.emit(this.isNavbarCollapsed)
  }

  collapseNavbarBasedOnWidth() {
    if(this.isNavbarCollapsed == false){
      const screenWidth = window.innerWidth;
      const breakpointWidth = 992; 

      this.isNavbarCollapsed = screenWidth < breakpointWidth;
    }
    this.toggleNavbar(null)
  }

  logout(){
    this.authService.logoutUser();
    this.toastr.success("Erfolgreich augeloggt", "Erfolg")
    this.router.navigate(['/login']);
  }
}
