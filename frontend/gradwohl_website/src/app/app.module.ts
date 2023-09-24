import { NgModule } from '@angular/core';
import { RouterModule,Routes } from '@angular/router';


import { BrowserModule } from '@angular/platform-browser';
// zusaetzliche Module
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import {MatTableModule} from '@angular/material/table';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';


import { LoginFirstPageComponent } from './components/login-first-page/login-first-page.component';
import { NavbarHeaderComponent } from './components/navbar-header/navbar-header.component';
import { StaffRosterMainComponent } from './components/staff-roster-main/staff-roster-main.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


const routes : Routes = [
  {path: '', component: LoginFirstPageComponent},
  {path: 'staff-roster-main', component: StaffRosterMainComponent}
];
@NgModule({
  declarations: [
    AppComponent,
    LoginFirstPageComponent,
    NavbarHeaderComponent,
    StaffRosterMainComponent,
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatInputModule,
    FormsModule,
    MatNativeDateModule,
    MatIconModule,
    MatTableModule,
  ],
  providers: [],
  bootstrap: [AppComponent,LoginFirstPageComponent,NavbarHeaderComponent, StaffRosterMainComponent]
})
export class AppModule {}
