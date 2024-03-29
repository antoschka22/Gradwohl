import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { AuthService } from '../service/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private authService: AuthService,
              private router: Router) {}

  canActivate(
  route: ActivatedRouteSnapshot): boolean {
    if(this.authService.isLoggedIn()){
      if (this.checkPermissions(route)){
        return true;
      } else {
        this.router.navigate(['/login']);
        return false;
      }
    } else{
      this.router.navigate(['/login']);
      return false;
    }
  }

  checkPermissions(route: ActivatedRouteSnapshot): boolean{
    const userRole = this.authService.getUserRole();
    if(route.data['roles'].includes(userRole)){
      return true
    }
    window.alert('Not the necessary permissions');
    return false
  }
}
