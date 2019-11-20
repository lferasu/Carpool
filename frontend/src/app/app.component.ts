import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { SearchForm } from './Model/SearchForm';
import { SearchService } from './Service/search.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  searchForm: FormGroup;
  searchFormModel: SearchForm;

  constructor(private fb: FormBuilder, private productService: SearchService) {
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

  submitSearchForm() {
    console.log(this.searchForm.value);
  }

}
