import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Trip } from '../Model/Trip';
import { SearchForm } from '../Model/SearchForm'
@Injectable({
  providedIn: 'root'
})
export class TripService {

  trips: Trip[];

  constructor(private httpClient: HttpClient) { }
  getAllTrips(): any {
    return this.httpClient.get("http://172.19.142.47:8080/search/findAll");
    // return this.httpClient.get("http://localhost:9000/");
    // http://localhost:8080/search/findAll
    // return this.httpClient.get("http://172.19.142.160:9092/trip/alltrip");
    // http://172.19.142.47:8080/search/serachDateLocations/vbnv/zxcv/2019-11-19/2019-11-20
    //return this.httpClient.get("http://172.19.142.160:9092/trip/alltrip");
    //http://localhost:9000/search/placeSearch/ghjk
  }

  saveTrip(trip: Trip): any {
    return this.httpClient.post("http://172.19.142.217:8091/travel/reserve/1", trip);
    // return this.httpClient.post("http://localhost:9000/", trip);
    // return null;
  }

  registerTrip(trip: Trip): any {
    trip.tripEndTime = trip.tripStartingTime;
    return this.httpClient.post("http://172.19.142.160:8081/trip/register/56", trip);
  }

  searchTrip(searchForm: SearchForm) {
    return this.httpClient.post("http://172.19.142.47:8080/search", searchForm);
  }

  assignSearchedTrips(trips: Trip[]) {
    this.trips = trips
  }

  getSearchedTrips() {
    return this.trips;
  }
}
