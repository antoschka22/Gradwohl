import { NgModule } from '@angular/core';


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
import { EditableDirective } from './editable.directive';
import { CommonModule } from '@angular/common';
import { DienstplanModelsComponent } from './components/zentrale/dienstplan-models/dienstplan-models.component';
import { LandingPageComponent } from './components/zentrale/landing-page/landing-page.component';
import { DienstplanVerwaltungComponent } from './components/zentrale/dienstplan-verwaltung/dienstplan-verwaltung.component';
import { DienstplanViewComponent } from './components/zentrale/dienstplan-view/dienstplan-view.component';
import { ZentraleNavbarComponent } from './components/zentrale/zentrale-navbar/zentrale-navbar.component';
import { WarenbestellungEingabeComponent } from './components/verkauf/warenbestellung-eingabe/warenbestellung-eingabe.component';
import { MitarbeiterModelsComponent } from './components/zentrale/mitarbeiter-models/mitarbeiter-models.component';
import { MitarbeiterComponent } from './components/zentrale/mitarbeiter/mitarbeiter.component';
import { VorlagenViewComponent } from './components/verkauf/vorlagen-view/vorlagen-view.component';
import { FilialeComponent } from './components/zentrale/filiale/filiale.component';
import { FirmaComponent } from './components/zentrale/firma/firma.component';
import { FirmaModelsComponent } from './components/zentrale/firma-models/firma-models.component';
import { FilialeModelsComponent } from './components/zentrale/filiale-models/filiale-models.component';
import { ProdukteComponent } from './components/zentrale/produkte/produkte.component';
import { ProdukteModelsComponent } from './components/zentrale/produkte-models/produkte-models.component';
import { NachrichtComponent } from './components/zentrale/nachricht/nachricht.component';

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
    DienstplanModelsComponent,
    EditableDirective,
    LandingPageComponent,
    DienstplanVerwaltungComponent,
    DienstplanViewComponent,
    ZentraleNavbarComponent,
    DienstplanModelsComponent,
    WarenbestellungEingabeComponent,
    MitarbeiterModelsComponent,
    MitarbeiterComponent,
    VorlagenViewComponent,
    FilialeComponent,
    FirmaComponent,
    FirmaModelsComponent,
    FilialeModelsComponent,
    ProdukteComponent,
    ProdukteModelsComponent,
    NachrichtComponent,
  ],
  imports: [
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
    CommonModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptorService,
    multi: true,
  }],
  bootstrap: [AppComponent]
})
export class AppModule {}
