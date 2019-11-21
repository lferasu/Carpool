import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { User } from '../Model/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  loggedInUser: User
  user_url = "34.66.20.177";
  constructor(private httpClient: HttpClient, private authService: AuthService) {

  }

  // getLoggedInUserInfo() {
  //   console.log(this.authService.getDecodedAccessToken().sub);
  //   return this.httpClient.get("http://" + this.user_url + "/users/" + this.authService.getDecodedAccessToken().sub);
  // }
}