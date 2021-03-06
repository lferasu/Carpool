import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AlltripsComponent } from './Components/alltrips/alltrips.component';
import { HttpClientModule } from '@angular/common/http';
import { ModalComponent } from './Components/modal/modal.component';
import { ModalModule } from 'ngb-modal';
import { TripComponent } from './Components/trip/trip.component';
import { SearchedTripsComponent } from './Components/searched-trips/searched-trips.component';
import { LoginComponent } from './Components/login/login.component';
import { SignupComponent } from './Components/signup/signup.component'
import { AuthGuard } from './Service/auth-guard.service';
@NgModule({
  declarations: [
    AppComponent,
    AlltripsComponent,
    ModalComponent,
    TripComponent,
    SearchedTripsComponent,
    LoginComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ModalModule,

  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
