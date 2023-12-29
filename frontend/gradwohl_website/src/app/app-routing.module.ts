import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginFirstPageComponent } from './components/verkauf/login-first-page/login-first-page.component';
import { LoginComponent } from './components/verkauf/login/login.component';
import { MasterGuardService } from './guards/master-guard.service';
import { AuthGuardService } from './guards/auth-guard.service';
import { DienstplanComponent } from './components/zentrale/dienstplan/dienstplan.component';
import { BestelluebersichtComponent } from './components/verkauf/bestelluebersicht/bestelluebersicht.component';
import { NachrichtenUebersichtComponent } from './components/verkauf/nachrichten-uebersicht/nachrichten-uebersicht.component';
import { KundenbestellungsUebersichtComponent } from './components/verkauf/kundenbestellungs-uebersicht/kundenbestellungs-uebersicht.component';
import { GesamtKundenbestellungsUebersichtComponent } from './components/verkauf/abgeschlossene-kundenbestellungs-uebersicht/gesamt-kundenbestellungs-uebersicht.component';
import { LandingPageComponent } from './components/zentrale/landing-page/landing-page.component';
import { DienstplanVerwaltungComponent } from './components/zentrale/dienstplan-verwaltung/dienstplan-verwaltung.component';
import { DienstplanViewComponent } from './components/zentrale/dienstplan-view/dienstplan-view.component';
import { WarenbestellungEingabeComponent } from './components/verkauf/warenbestellung-eingabe/warenbestellung-eingabe.component';
import { VorlagenViewComponent } from './components/verkauf/vorlagen-view/vorlagen-view.component';
import { MitarbeiterComponent } from './components/zentrale/mitarbeiter/mitarbeiter.component';
import { FilialeComponent } from './components/zentrale/filiale/filiale.component';
import { FirmaComponent } from './components/zentrale/firma/firma.component';
import { ProdukteComponent } from './components/zentrale/produkte/produkte.component';
import { NachrichtComponent } from './components/zentrale/nachricht/nachricht.component';
import { BestelluebersichtverwaltenComponent } from './components/zentrale/bestelluebersichtverwalten/bestelluebersichtverwalten.component';

const routes: Routes = [
  {path: '', component: LoginFirstPageComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale', 'Leiter', 'Verkauf']}},
  {path: 'login', component: LoginComponent },
  {path: 'dienstplan', component: DienstplanComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale', 'Leiter', 'Verkauf']}},
  {path: 'bestelluebersichtAbgeschickt/:date', component: BestelluebersichtComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale', 'Leiter', 'Verkauf']}},
  {path: 'nachricht', component: NachrichtenUebersichtComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale', 'Leiter', 'Verkauf']}},
  {path: 'kundenbestellungsUebersicht', component: KundenbestellungsUebersichtComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale', 'Leiter', 'Verkauf']}},
  {path: 'abgeschlosseneUebersicht', component: GesamtKundenbestellungsUebersichtComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale', 'Leiter', 'Verkauf']}},
  {path: 'warenbestellungEingabe/:datum', component: WarenbestellungEingabeComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale', 'Leiter', 'Verkauf']}},
  {path: 'vorlagenView', component: VorlagenViewComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Leiter']}},
  {path: 'zentrale', component: LandingPageComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale']}},
  {path: 'dienstplan/verwalten', component: DienstplanVerwaltungComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale']}},
  {path: 'dienstplan/view/:filialeId', component: DienstplanViewComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale']}},
  {path: 'mitarbeiter/verwalten', component: MitarbeiterComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale']}},
  {path: 'filiale/verwalten', component: FilialeComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale']}},
  {path: 'firma/verwalten', component: FirmaComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale']}},
  {path: 'produkte/verwalten', component: ProdukteComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale']}},
  {path: 'nachricht/verwalten', component: NachrichtComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale']}},
  {path: 'bestellungen/verwalten', component: BestelluebersichtverwaltenComponent,
  canActivate: [MasterGuardService], data: {guards: [AuthGuardService], roles: ['Zentrale']}},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
