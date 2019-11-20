import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlltripsComponent } from './Components/alltrips/alltrips.component';
import { TripComponent } from './Components/trip/trip.component';


const routes: Routes = [
  { path: 'alltrips', component: AlltripsComponent },
  { path: 'trip', component: TripComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
