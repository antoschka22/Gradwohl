import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  gradwohlLogo: string = "assets/img/GradwohlLogo_tra.png";

  password: string = '';
  passwordHidden: boolean = true;

  eyeOn: boolean= true;


  togglePasswordVisibility() {
    this.passwordHidden = !this.passwordHidden;
  }

  toggleEyeButtonOn(){
    this.eyeOn = !this.eyeOn;
  }
}
