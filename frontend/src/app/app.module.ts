import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AlltripsComponent } from './Components/alltrips/alltrips.component';
import { HttpClientModule } from '@angular/common/http';
import { ModalComponent } from './Components/modal/modal.component';
import { ModalModule } from 'ngb-modal';
import { TripComponent } from './Components/trip/trip.component'
@NgModule({
  declarations: [
    AppComponent,
    AlltripsComponent,
    ModalComponent,
    TripComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ModalModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
