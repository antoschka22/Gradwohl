import { NgModule } from '@angular/core';
import { RouterModule,Routes } from '@angular/router';


import { BrowserModule } from '@angular/platform-browser';
// zusaetzliche Module
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToastrModule } from 'ngx-toastr';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import {MatTableModule} from '@angular/material/table';
// Login
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';

import { MatSelectModule } from '@angular/material/select';


import { LoginFirstPageComponent } from './components/verkauf/login-first-page/login-first-page.component';
import { NavbarHeaderComponent } from './components/verkauf/navbar-header/navbar-header.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptorService } from './interceptor/auth.interceptor.service';
import { LoginComponent } from './components/verkauf/login/login.component';
import { DienstplanComponent } from './components/zentrale/dienstplan/dienstplan.component';
import { BestelluebersichtComponent } from './components/verkauf/bestelluebersicht/bestelluebersicht.component';
import { NachrichtenUebersichtComponent } from './components/verkauf/nachrichten-uebersicht/nachrichten-uebersicht.component';


// PopUp
import { MatDialogModule } from '@angular/material/dialog';
import { KundenbestellungsUebersichtComponent } from './components/verkauf/kundenbestellungs-uebersicht/kundenbestellungs-uebersicht.component';
import { GesamtKundenbestellungsUebersichtComponent } from './components/verkauf/abgeschlossene-kundenbestellungs-uebersicht/gesamt-kundenbestellungs-uebersicht.component';

const routes : Routes = [
  {path: '', component: LoginFirstPageComponent},
];
@NgModule({
  declarations: [
    AppComponent,
    LoginFirstPageComponent,
    NavbarHeaderComponent,
    LoginComponent,
    DienstplanComponent,
    BestelluebersichtComponent,
    NachrichtenUebersichtComponent,
    KundenbestellungsUebersichtComponent,
    GesamtKundenbestellungsUebersichtComponent,
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
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatSelectModule, 
    MatDialogModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptorService,
    multi: true,
  }],
  bootstrap: [AppComponent,LoginFirstPageComponent,NavbarHeaderComponent, BestelluebersichtComponent, KundenbestellungsUebersichtComponent, GesamtKundenbestellungsUebersichtComponent]
})
export class AppModule {}
