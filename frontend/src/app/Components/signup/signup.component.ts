import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { UserService } from '../../Service/user.service';
import { User } from '../../Model/User'
import { AuthService } from 'src/app/Service/auth.service';
import * as _swal from 'sweetalert';
import { SweetAlert } from 'sweetalert/typings/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpForm: FormGroup;
  swal: SweetAlert = _swal as any;
  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router) {

    this.signUpForm = formBuilder.group({
      password: new FormControl(''),
      confirmPassword: new FormControl(''),
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      email: new FormControl(''),
      adrState: new FormControl(''),
      adrCity: new FormControl(''),
      adrZip: new FormControl('')
    });
  }
  ngOnInit() {
  }

  submitForm() {
    let user: User;
    user = <User>this.signUpForm.value;
    console.log(user);
    this.authService.registerUser(user).subscribe(data => {
      this.swal("User Registered!", "", "success");
      this.router.navigate(['alltrips']);
    },
      err => {
        if (err.status = 200) {
          this.swal("User Registered!", "", "success");
        }
        console.log(err);
      })
  }
}