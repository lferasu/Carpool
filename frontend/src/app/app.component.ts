import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { SearchForm } from './Model/SearchForm';
import { SearchService } from './Service/search.service';
import { Router } from '@angular/router'
import { TripService } from './Service/trip.service';
import { Trip } from './Model/Trip';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  searchForm: FormGroup;
  searchFormModel: SearchForm;

  constructor(private fb: FormBuilder, private tripService: TripService, private router: Router) {
    this.searchForm = this.fb.group({
      'from': ['', Validators.required],
      'to': ['', Validators.required],
      'date': ['', Validators.required],
      'noOfSeats': ['', Validators.required],
      'priceRangeFrom': ['', Validators.required],
      'priceRangeUntil': ['', Validators.required],
      'driverGender': ['', Validators.required]
    });
  }

  async submitSearchForm() {
    let sf: SearchForm = this.searchForm.value;


    console.log("hello")
    console.log(this.searchForm.value);
    await this.tripService.searchTrip(<SearchForm>this.searchForm.value).subscribe((data) => {
      console.log(data);
      this.tripService.assignSearchedTrips(<Trip[]>data);
      this.router.navigate(['searchedtrips'])

    });

  }

}
