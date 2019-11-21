import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlltripsComponent } from './Components/alltrips/alltrips.component';
import { TripComponent } from './Components/trip/trip.component';
import { SearchedTripsComponent } from './Components/searched-trips/searched-trips.component';
import { LoginComponent } from './Components/login/login.component';
import { SignupComponent } from './Components/signup/signup.component';
import { AuthGuard } from './Service/auth-guard.service';

const routes: Routes = [
  { path: 'alltrips', component: AlltripsComponent },
  { path: 'trip', component: TripComponent, canActivate: [AuthGuard] },
  { path: 'searchedtrips', component: SearchedTripsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: '', redirectTo: 'alltrips', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
