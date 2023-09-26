import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { LoginFirstPageComponent } from './components/verkauf/login-first-page/login-first-page.component';

const routes: Routes = [
  {path: '', component: LoginFirstPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
