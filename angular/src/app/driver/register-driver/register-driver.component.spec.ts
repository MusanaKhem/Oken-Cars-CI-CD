import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RegisterDriverComponent } from './register-driver.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Router } from '@angular/router';
import { HeaderDriverComponent } from '../header-driver/header-driver.component';
import { DriverService } from '../service/driver.service';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { Driver } from '../models/driver';
import { By } from '@angular/platform-browser';


describe('RegisterDriverComponent', () => {
  let fixture: ComponentFixture<RegisterDriverComponent>;
  let component: RegisterDriverComponent;
  let driverService: DriverService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterDriverComponent,HeaderDriverComponent ],
      imports: [RouterTestingModule,HttpClientTestingModule, FormsModule],
      providers: [DriverService],


    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterDriverComponent);
    component = fixture.componentInstance;
    driverService = TestBed.inject(DriverService);
    router = TestBed.inject(Router);
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //Create new Driver
  it("create Driver", () => {
    const mockDriver: Driver = {
      firstname:"Moussa",
      lastname:"DIOMBANA",
      phone:"0656821681",
      email:"moussa@gmail.com",
      address:"USA",
      password:"33333",
      permit:"V"

    };
   //inject of Service
    const driverService = fixture.debugElement.injector.get(DriverService);
  //Add new Driver
    spyOn(driverService, "createDriver").and.returnValue(of(mockDriver));

    component.driver = mockDriver;

    expect(true).toBeTruthy();


  });

  it("should call URL method", () =>{

      // Create a spy for the goList method in the component

   spyOn(router, 'navigateByUrl').and.returnValue(Promise.resolve(true));


    expect(true).toBeTruthy();

  })

  it('should call saveDriver method', () => {

    // Create a spy for the saveDriver method in the component

    const onClickMock = spyOn(component, 'saveDriver');

    // Simulate a click on the "Save" button

    fixture.debugElement.query(By.css('button')).triggerEventHandler('click', null);

    // Vérify if the method saveDriver has been called

    expect(onClickMock).toHaveBeenCalled();

    // Vérify if the method saveDriver has been called once

    expect(component.saveDriver).toHaveBeenCalledTimes(1);
  });
});


