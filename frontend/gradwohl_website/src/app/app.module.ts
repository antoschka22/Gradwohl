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


import { LoginFirstPageComponent } from './components/verkauf/login-first-page/login-first-page.component';
import { NavbarHeaderComponent } from './components/verkauf/navbar-header/navbar-header.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptorService } from './interceptor/auth.interceptor.service';
import { LoginComponent } from './components/verkauf/login/login.component';


const routes : Routes = [
  {path: '', component: LoginFirstPageComponent},
];
@NgModule({
  declarations: [
    AppComponent,
    LoginFirstPageComponent,
    NavbarHeaderComponent,
    LoginComponent,
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
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptorService,
    multi: true,
  }],
  bootstrap: [AppComponent,LoginFirstPageComponent,NavbarHeaderComponent]
})
export class AppModule {}
