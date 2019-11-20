import { Component, OnInit } from '@angular/core';
import { TripService } from 'src/app/Service/trip.service';
import { Trip } from 'src/app/Model/Trip';
import { ModalService } from '../../Service/modal.service'
import { Router } from '@angular/router';
//import swal from 'sweetalert'

import * as _swal from 'sweetalert';
import { SweetAlert } from 'sweetalert/typings/core';


@Component({
  selector: 'app-alltrips',
  templateUrl: './alltrips.component.html',
  styleUrls: ['./alltrips.component.css']
})
export class AlltripsComponent implements OnInit {

  trips: Trip[];
  swal: SweetAlert = _swal as any;
  constructor(private tripService: TripService, private router: Router) {

    tripService.getAllTrips().subscribe((data) => {
      this.trips = <Trip[]>data;
      this.trips.forEach(x => x.requestedReserveSeat = 0);
      console.log(this.trips);
      // console.log(this.trips[0].tripStartingTime. + "" + this.trips[0].tripEndTime.getDate() + "" + this.trips[0].tripEndTime.getTime())
      // this.trips[0].tripEndTime.getMonth + 
    });
  }
  ngOnInit() {
  }

  reserveTrip(trip: Trip) {
    console.log(trip);
    trip.requestedReserveSeat = 1;
    this.tripService.saveTrip(trip).subscribe((data) => {
      this.swal("Trip Reserved!", "for " + trip.requestedReserveSeat + "seats \n  please check your message for notification", "success");
      // this.router.navigate(['alltrips']);

    })
  }

}
