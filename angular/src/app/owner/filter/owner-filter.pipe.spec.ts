import { TestBed } from '@angular/core/testing';
import { OwnerFilterPipe } from './owner-filter.pipe';
import { Owner } from '../models/owner';


fdescribe('OwnerFilterPipe', () => {
  let pipe: OwnerFilterPipe;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OwnerFilterPipe],
    });
    pipe = TestBed.inject(OwnerFilterPipe);
  });

  it('should create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('should filter owners by first name', () => {
    const owners: Owner[] = [
      {id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333"},

      {id: 2, firstname:"Amadou",lastname:"Camara",phone:"0705765678",email:"amadou@gmail.com",address:"MLI",password:"1234"},

      {id: 3, firstname:"Birahima",lastname:"Sangare",phone:"0708095645",email:"birahima@gmail.com",address:"BK",password:"1234"},

    ];

    const searchText = 'Amadou';
    const result = pipe.transform(owners, searchText);

    expect(result.length).toBe(1);
    expect(result[0].firstname).toBe('Amadou');
  });

  it('should handle empty searchText', () => {
    const owners: Owner[] = [
      {id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333"},

      {id: 2, firstname:"Amadou",lastname:"Camara",phone:"0705765678",email:"amadou@gmail.com",address:"MLI",password:"1234"},

      {id: 3, firstname:"Birahima",lastname:"Sangare",phone:"0708095645",email:"birahima@gmail.com",address:"BK",password:"1234"},
    ];

    const searchText = '';
    const result = pipe.transform(owners, searchText);

    expect(result.length).toBe(owners.length);
  });

  it('should handle empty list', () => {
    const owners: Owner[] = [];

    const searchText = 'Amadou';
    const result = pipe.transform(owners, searchText);

    expect(result.length).toBe(0);
  });
});
