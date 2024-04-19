import { TestBed } from '@angular/core/testing';
import { DriverFilterPipe } from './driver-filter.pipe';
import { Driver } from '../models/driver';

fdescribe('DriverFilterPipe', () => {
  let pipe: DriverFilterPipe;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DriverFilterPipe],
    });
    pipe = TestBed.inject(DriverFilterPipe);
  });

  it('should create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('should filter drivers by first name', () => {
    const drivers: Driver[] = [
      {id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333",permit:"V"},

      {id: 2, firstname:"Amadou",lastname:"Camara",phone:"0705765678",email:"amadou@gmail.com",address:"MLI",password:"1234",permit:"B"},

      {id: 3, firstname:"Birahima",lastname:"Sangare",phone:"0708095645",email:"birahima@gmail.com",address:"BK",password:"1234",permit:"B"},

    ];

    const searchText = 'Amadou';
    const result = pipe.transform(drivers, searchText);

    expect(result.length).toBe(1);
    expect(result[0].firstname).toBe('Amadou');
  });

  it('should handle empty searchText', () => {
    const drivers: Driver[] = [
      {id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333",permit:"V"},

      {id: 2, firstname:"Amadou",lastname:"Camara",phone:"0705765678",email:"amadou@gmail.com",address:"MLI",password:"1234",permit:"B"},

      {id: 3, firstname:"Birahima",lastname:"Sangare",phone:"0708095645",email:"birahima@gmail.com",address:"BK",password:"1234",permit:"B"},
    ];

    const searchText = '';
    const result = pipe.transform(drivers, searchText);

    expect(result.length).toBe(drivers.length);
  });

  it('should handle empty list', () => {
    const drivers: Driver[] = [];

    const searchText = 'Amadou';
    const result = pipe.transform(drivers, searchText);

    expect(result.length).toBe(0);
  });
});
