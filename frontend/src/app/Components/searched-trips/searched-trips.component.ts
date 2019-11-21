import { Component, OnInit } from '@angular/core';
import { TripService } from 'src/app/Service/trip.service';
import { Trip } from 'src/app/Model/Trip';
import { ModalService } from '../../Service/modal.service'
import { Router } from '@angular/router';
//import swal from 'sweetalert'

@Component({
  selector: 'app-searched-trips',
  templateUrl: './searched-trips.component.html',
  styleUrls: ['./searched-trips.component.css']
})
export class SearchedTripsComponent implements OnInit {

  searchedTrips: Trip[];
  constructor(private tripService: TripService, private router: Router) {

    this.searchedTrips = tripService.getSearchedTrips();
  }
  ngOnInit() {
  }

}
