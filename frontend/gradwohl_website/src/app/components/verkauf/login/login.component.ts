import { Component, ViewChild, HostListener } from '@angular/core';
import { Route, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { catchError, throwError } from 'rxjs';
import { AuthService } from 'src/app/service/auth/auth.service';
import { authRequest } from 'src/model/auth/AuthRequest';

class loginModel implements authRequest{
  constructor(
    public name: string,
    public password: string
  ){

  }
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  isScreenSizeSmall = false;
  desktopLayout: boolean = false;
  gradwohlLogo: string = "assets/img/GradwohlLogo_tra.png";

  password: string = '';
  passwordHidden: boolean = true;
  eyeOn: boolean= true;

  @ViewChild('f') form:any

  loginModel: loginModel = new loginModel("", "")

  constructor(private authService: AuthService,
              private toastr: ToastrService,
              private router: Router){}

  ngOnInit() {
    this.checkScreenSize();
  }

  // Responsiv LoginDesign
  @HostListener('window:resize', ['$event'])
  onResize(event: any): void {
    this.checkScreenSize();
  }

  private checkScreenSize(): void {
    this.isScreenSizeSmall = window.innerWidth < 765;
  }

  // Password
  togglePasswordVisibility() {
    this.passwordHidden = !this.passwordHidden;
  }

  // Button Eye
  toggleEyeButtonOn(){
    this.eyeOn = !this.eyeOn;
  }

  //Anmelde Button
  onSubmit(){
    this.authService.loginUser(this.loginModel, true)
    .pipe(
      catchError((error) => {
        if (error.status === 403 || error.status == 401) {
          this.toastr.error('Zugriff verweigert. Username or password incorrect.', 'Login Error', {
            timeOut: 3000,
          });
        }
        return throwError(error);
      })
    ).subscribe((data: string) =>{
      if(!data){
        this.toastr.error("Username oder Passwort falsch", 'Login error', {
          timeOut: 3000,
        });
      }

      if(this.authService.getUserRole() === 'Zentrale'){
        this.router.navigate(['/zentrale'])
      }else{
        this.router.navigate(['/'])
      }
      this.toastr.success('Willkommen, ' + this.authService.getUsernameFromToken() + "!");
    })
    
  }


}
