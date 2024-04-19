import { TestBed } from '@angular/core/testing';
import { Manager } from '../models/manager';
import { ManagerFilterPipe } from './manager-filter.pipe';


fdescribe('ManagerFilterPipe', () => {
  let pipe: ManagerFilterPipe;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ManagerFilterPipe],
    });
    pipe = TestBed.inject(ManagerFilterPipe);
  });

  it('should create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('should filter managers by first name', () => {
    const managers: Manager[] = [
      {id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333"},

      {id: 2, firstname:"Amadou",lastname:"Camara",phone:"0705765678",email:"amadou@gmail.com",address:"MLI",password:"1234"},

      {id: 3, firstname:"Birahima",lastname:"Sangare",phone:"0708095645",email:"birahima@gmail.com",address:"BK",password:"1234"},

    ];

    const searchText = 'Amadou';
    const result = pipe.transform(managers, searchText);

    expect(result.length).toBe(1);
    expect(result[0].firstname).toBe('Amadou');
  });

  it('should handle empty searchText', () => {
    const managers: Manager[] = [
      {id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333"},

      {id: 2, firstname:"Amadou",lastname:"Camara",phone:"0705765678",email:"amadou@gmail.com",address:"MLI",password:"1234"},

      {id: 3, firstname:"Birahima",lastname:"Sangare",phone:"0708095645",email:"birahima@gmail.com",address:"BK",password:"1234"},
    ];

    const searchText = '';
    const result = pipe.transform(managers, searchText);

    expect(result.length).toBe(managers.length);
  });

  it('should handle empty list', () => {
    const managers: Manager[] = [];

    const searchText = 'Amadou';
    const result = pipe.transform(managers, searchText);

    expect(result.length).toBe(0);
  });
});
