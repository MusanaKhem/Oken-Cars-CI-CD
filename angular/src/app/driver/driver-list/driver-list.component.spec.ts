import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DriverListComponent } from './driver-list.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { DriverService } from '../service/driver.service';
import { HeaderDriverComponent } from '../header-driver/header-driver.component';
import { DriverFilterPipe } from '../filter/driver-filter.pipe';
import { of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

describe('DriverListComponent', () => {
  let fixture: ComponentFixture<DriverListComponent>;
  let component: DriverListComponent;
  let driverService: DriverService;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule,CommonModule,FormsModule],
      declarations: [ DriverListComponent,HeaderDriverComponent,DriverFilterPipe],
      providers: [{provide: DriverService}]
    })
    .compileComponents();
    fixture = TestBed.createComponent(DriverListComponent);
    component = fixture.componentInstance;
    driverService = TestBed.inject(DriverService);

  });


  it('should create', () => {

    expect(component).toBeTruthy();

  });

  it('should fetch drivers and delete driver and filter drivers by id', () => {

    const driverId = 3;

    const mockDrivers = [

      {id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333",permit:"V"},

      {id: 2, firstname:"Amadou",lastname:"Camara",phone:"0705765678",email:"amadou@gmail.com",address:"MLI",password:"1234",permit:"B"},

      {id: 3, firstname:"Birahima",lastname:"Sangare",phone:"0708095645",email:"birahima@gmail.com",address:"BK",password:"1234",permit:"B"}];

      component.drivers = mockDrivers;

      const driverDelete = spyOn(driverService, 'deleteDriver').and.returnValue(of("removed driver "+driverId));

      const reloadSpy = spyOn(component, 'reloadPage');

      component.deleteDriver(driverId);

      expect(driverDelete).toHaveBeenCalledWith(driverId);

      expect(driverDelete).toHaveBeenCalled();

      expect(component.drivers.filter((driver:any) => driver.id === driverId));

      component.reloadPage();

      expect(reloadSpy).toHaveBeenCalled();
  });


});
