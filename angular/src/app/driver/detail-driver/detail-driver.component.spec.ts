import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DetailDriverComponent } from './detail-driver.component';
import { RouterTestingModule } from '@angular/router/testing';
import { DriverService } from '../service/driver.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HeaderDriverComponent } from '../header-driver/header-driver.component';
import { of } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Driver } from '../models/driver';
describe('DetailDriverComponent', () => {
  let fixture: ComponentFixture<DetailDriverComponent>;
  let component: DetailDriverComponent;
  let activatedRoute: ActivatedRoute;
  let driverService: DriverService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule],
      declarations: [ DetailDriverComponent,HeaderDriverComponent ],
      providers: [DriverService]
    })
    .compileComponents();
    fixture = TestBed.createComponent(DetailDriverComponent);
    component = fixture.debugElement.componentInstance;
    driverService = TestBed.inject(DriverService);
  });

  it('should create', () => {

    expect(component).toBeTruthy();
  });


  it('should fetch driver details on ngOnInit', () => {

   const driverId = 1.

    const expectedDriver: Driver = {

      id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333",permit:"V"

    };

    // Spy on the service's getDriver method and return an Observable containing the simulated driver

    const driverSpy = spyOn(driverService, 'getDriver').and.returnValue(of(expectedDriver));

    // Set a dummy ID for the driver from ActivatedRoute

    activatedRoute.snapshot.params = { id: '1' };

   // Call ngOnInit to trigger driver loading
    component.ngOnInit();

    expect(component.driver$).toEqual(driverSpy);

  });

 
});
