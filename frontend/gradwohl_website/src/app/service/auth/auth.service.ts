import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators'
import { Globals } from 'src/global/global';
import { authRequest } from 'src/model/auth/AuthRequest';
import jwt_decode from 'jwt-decode'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authBaseUri: string = this.globals.backendUri + '/auth/authenticate';

  constructor(private globals: Globals, private httpClient: HttpClient) { }

  loginUser(authRequest: authRequest, keepLogin: boolean): Observable<string>{
    return this.httpClient
      .post(this.authBaseUri, authRequest, {responseType: 'text'})
      .pipe(tap((authResponse: string)=> this.setToken(authResponse, keepLogin)));
  }

  isLoggedIn(): boolean{
    let token = this.getToken();
    if(token == null){
      return false;
    } else{
      let exp = this.getTokenExpirationDate(token);
      if (exp == null){
        return false
      } else{
        return exp.valueOf() > new Date().valueOf();
      }
    }
  }

  logoutUser(): void {
    localStorage.removeItem('authToken');
    sessionStorage.removeItem('authToken')
  }

  getToken(): string | null {
    let token = localStorage.getItem('authToken');
    if(token == null){
      token = sessionStorage.getItem('authToken');
    }
    return token
  }

  getUserRole(){
    let token = this.getToken();
    if(token != null){
      const decoded: any = jwt_decode(token);
      const authInfo: string[] = decoded.role;
      // console.log(authInfo);
      if(authInfo.includes('ADMIN')){
        return 'ADMIN'
      }
    }
    return 'UNDEFINED'
  }

  getUsernameFromToken(){
    let token = this.getToken();
    if(token != null){
      const decoded: any = jwt_decode(token);
      return decoded.sub
    }
    return "Error"
  }

  private setToken(authResponse: string, keepLogin: boolean){
    const jsonObject = JSON.parse(authResponse);
    const jwtToken = jsonObject.token;

    if(keepLogin){
      localStorage.setItem('authToken', jwtToken);
    }else{
      sessionStorage.setItem('authToken', jwtToken);
    }
  }

  getTokenExpirationDate(token: string): Date | null{
    const decoded: any = jwt_decode(token);
    if(decoded.exp === undefined){
      return null;
    }

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date
  }
  
}