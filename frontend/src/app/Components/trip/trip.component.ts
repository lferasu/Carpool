import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators, FormGroup } from '@angular/forms';
import { Trip } from 'src/app/Model/Trip';
import { TripService } from 'src/app/Service/trip.service';
import * as _swal from 'sweetalert';
import { SweetAlert } from 'sweetalert/typings/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trip',
  templateUrl: './trip.component.html',
  styleUrls: ['./trip.component.css']
})
export class TripComponent implements OnInit {

  trip: Trip;
  tripForm: FormGroup;
  swal: SweetAlert = _swal as any;

  constructor(private formBuilder: FormBuilder, private tripService: TripService, private router: Router) {
    this.tripForm = this.formBuilder.group({
      'pickupPlace': ['', Validators.required],
      'dropOffPlace': ['', Validators.required],
      'tripStartingTime': ['', Validators.required],
      'numberOfAvailableSeats': ['', Validators.required],
      'tripPrice': ['', Validators.required],
      'tripDescription': ['', Validators.required],
    });
  }

  ngOnInit() {
  }

  submitTripForm() {
    this.trip = this.tripForm.value;



    this.tripService.registerTrip(this.trip).subscribe((data) => {
      this.swal("Trip Registered!", "from: " + this.trip.pickupPlace + " to: " + this.trip.dropOffPlace + "seats \n  please check your message for notification", "success");
      this.router.navigate(['alltrips']);

    });
    console.log("whats up")
    console.log(this.tripForm.value);

  }

}
