import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { LoginFirstPageComponent } from './components/verkauf/login-first-page/login-first-page.component';
import { LoginComponent } from './components/verkauf/login/login.component';
import { MasterGuardService } from './guards/master-guard.service';
import { AuthGuardService } from './guards/auth-guard.service';
import { DienstplanComponent } from './components/zentrale/dienstplan/dienstplan.component';

const routes: Routes = [
  {path: '', component: LoginFirstPageComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale', 'Leiter', 'Verkauf']}},
  {path: 'login', component: LoginComponent },
  {path: 'dienstplan', component: DienstplanComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale', 'Leiter', 'Verkauf']}},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
