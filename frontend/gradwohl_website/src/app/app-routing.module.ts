import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { LoginFirstPageComponent } from './components/login-first-page/login-first-page.component';
import { StaffRosterMainComponent } from './components/staff-roster-main/staff-roster-main.component';

const routes: Routes = [
  {path: '', component: LoginFirstPageComponent },
  {path: 'dienstplan', component: StaffRosterMainComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
