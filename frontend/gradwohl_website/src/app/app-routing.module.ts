import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { LoginFirstPageComponent } from './components/verkauf/login-first-page/login-first-page.component';
import { LoginComponent } from './components/verkauf/login/login.component';

const routes: Routes = [
  {path: '', component: LoginFirstPageComponent },
  {path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
