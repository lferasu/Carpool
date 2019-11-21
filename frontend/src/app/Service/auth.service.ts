import { Injectable } from '@angular/core';
import { User } from '../Model/User'
import { HttpClient } from '@angular/common/http';
import * as jwt_decode from "jwt-decode";
import { Login } from '../Model/Login';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedInUser: User
  url = "172.19.142.217:9789"

  constructor(private httpClient: HttpClient) {

  }

  public login(userInfo: Login) {
    // return this.httpClient.post("http://localhost:8099/api/auth/login", userInfo);
    return this.httpClient.post("http://" + this.url + "/auth/sign-in", userInfo);
  }

  public isLoggedIn() {
    //console.log(localStorage.getItem('ACCESS_TOKEN'));
    return localStorage.getItem('ACCESS_TOKEN') != null;
  }

  public logout() {
    localStorage.removeItem('ACCESS_TOKEN');
  }

  getDecodedAccessToken(): any {
    let value = localStorage.getItem('ACCESS_TOKEN');
    try {
      return jwt_decode(localStorage.getItem('ACCESS_TOKEN'));
    }
    catch (Error) {
      return null;
    }
  }

  registerUser(user: User): any {
    return this.httpClient.post("http://" + this.url + "/auth/register", user);
  }
}

