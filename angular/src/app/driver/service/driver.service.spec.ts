import { TestBed } from '@angular/core/testing';
import { DriverService } from './driver.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Driver } from '../models/driver';
describe('DriverService', () => {
  let driverService: DriverService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DriverService],
      imports: [HttpClientTestingModule]
    });
    driverService = TestBed.inject(DriverService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(driverService).toBeTruthy();

  });

  //Create new Driver

  it('should create a driver', () => {

    const createDriver : Driver  = {firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333",permit:"V"};

    driverService.createDriver(createDriver).subscribe((response) => {
      
      expect(response).toEqual(createDriver);
    });

    const req = httpMock.expectOne('/drivers/addDriver');
    expect(req.request.method).toBe('POST');
    req.flush(createDriver);
  });

  //Update Driver

  it('should update a driver', () => {
    const id = 1;
    const updatedDriver: Driver  = {firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333",permit:"V"};
    const mockDriver: Driver =  {firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333",permit:"V"};

    driverService.updateDriver(id, updatedDriver).subscribe(response => {

      expect(response).toBeTruthy();

      expect({Driver:updatedDriver}).toEqual({Driver:mockDriver});

    });

    const request = httpMock.expectOne(`/drivers/update/${id}`);
    expect(request.request.method).toBe('PUT');
    expect(request.request.body).toBe(updatedDriver);

    const mockResponse = {

      message: 'Driver updated successfully',
      status: 200

    };
    request.flush(mockResponse);
  });
//List drivers
  it('should retrieve drivers from the API via GET', () => {
    const mockDrivers = [

      {id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333",permit:"V"},

      { id: 2, firstname:"jack",lastname:"BAUER",phone:"0709090808",email:"jack@gmail.com",address:"USA",password:"111222",permit:"B"}
    ];

    driverService.getDriverList().subscribe(drivers => {
      expect(drivers).toEqual(mockDrivers);
    });

    const req = httpMock.expectOne('/drivers');
    expect(req.request.method).toBe('GET');
    req.flush(mockDrivers);
  });

//Get Driver By Id
  it('should retrieve a driver by ID', () => {

    const mockDriverId: Driver = {

      id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333",permit:"V"
    };
    const driverId = 1

    // call method getDriver

    driverService.getDriver(driverId).subscribe((driver) => {

      expect(driver).toEqual(mockDriverId);
    });

    const req = httpMock.expectOne(`/drivers/edit/${driverId}`);

    expect(req.request.method).toBe('GET');

    //Success
    req.flush(mockDriverId);

  });

  //Delete by Driver
  it('should delete a driver by ID', () => {
    const id = 1
    const expectedUrl = '/drivers/delete/' + id;

    // call method deleteDriver
    driverService.deleteDriver(id).subscribe((response) => {
      expect(response).toBeTruthy();
    });

    // Vérify the request DELETE send to good URL
    const req = httpMock.expectOne(expectedUrl);
    expect(req.request.method).toBe('DELETE');
    expect(false).toBeFalse();

    req.flush(expectedUrl);
  });

});
