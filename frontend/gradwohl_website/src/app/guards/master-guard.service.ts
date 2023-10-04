import { Injectable, Injector } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, concatMap, first, from, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MasterGuardService implements CanActivate{

  constructor(private injector: Injector) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>{
      return from(route.data['guards']).pipe(concatMap((value)=>{
        const guard = this.injector.get(value);
        const result = guard.canActivate(route, state);
        if(result instanceof Observable){
          return result
        } else if ( result instanceof Promise){
          return from(result);
        }else{
          return of(result);
        }
      }), first((x) => x === false, true))
  }
}
