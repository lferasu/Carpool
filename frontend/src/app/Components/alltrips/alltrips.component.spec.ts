import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlltripsComponent } from './alltrips.component';

describe('AlltripsComponent', () => {
  let component: AlltripsComponent;
  let fixture: ComponentFixture<AlltripsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlltripsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlltripsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
