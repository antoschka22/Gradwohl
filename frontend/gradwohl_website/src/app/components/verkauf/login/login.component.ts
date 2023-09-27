import { Component, ViewChild } from '@angular/core';
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
  gradwohlLogo: string = "assets/img/GradwohlLogo_tra.png";

  password: string = '';
  passwordHidden: boolean = true;

  @ViewChild('f') form:any

  eyeOn: boolean= true;

  loginModel: loginModel = new loginModel("", "")

  constructor(private authService: AuthService){}


  togglePasswordVisibility() {
    this.passwordHidden = !this.passwordHidden;
  }

  toggleEyeButtonOn(){
    this.eyeOn = !this.eyeOn;
  }

  onSubmit(){
    this.authService.loginUser(this.loginModel, true).subscribe((data: string) =>{
      console.log(data)
    })
  }
}
