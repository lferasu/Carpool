import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../Model/User'
import { Response } from '../../Model/Response'
import { AuthService } from '../../Service/auth.service';
import { Login } from '../../Model/Login';
import * as _swal from 'sweetalert';
import { SweetAlert } from 'sweetalert/typings/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  response: Response;
  loginForm: FormGroup;
  isSubmitted = false;
  userInfo: Login
  swal: SweetAlert = _swal as any;
  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    if (this.authService.isLoggedIn()) {
      console.log("is logged in.........")
      localStorage.removeItem('ACCESS_TOKEN');
      this.router.navigate(['/product'])
    } else {
      console.log("is Not logged in")

      this.loginForm = this.formBuilder.group({
        email: ['', Validators.required],
        password: ['', Validators.required]
      });
    }
  }

  login() {
    //console.log(this.loginForm.value);
    this.isSubmitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    let loginUser: Login;

    console.log(this.loginForm.value);

    // console.log(loginUser);
    this.authService.login(this.loginForm.value).subscribe(data => {

      console.log("loggedIn");
      this.response = <Response>data;
      localStorage.setItem('ACCESS_TOKEN', <string>this.response.accessToken);
      this.swal("Logged in!", "", "success");

      this.router.navigate(['/alltrips'])
    },
      err => {
        console.log(err);
      });

  }
}