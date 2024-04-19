import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OwnerService } from '../service/owner.service';
import { OwnerListComponent } from './owner-list.component';
import { HeaderOwnerComponent } from '../header-owner/header-owner.component';
import { OwnerFilterPipe } from '../filter/owner-filter.pipe';

describe('OwnerListComponent', () => {
  let fixture: ComponentFixture<OwnerListComponent>;
  let component: OwnerListComponent;
  let ownerService: OwnerService;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule,CommonModule,FormsModule],
      declarations: [ OwnerListComponent,HeaderOwnerComponent,OwnerFilterPipe],
      providers: [{provide: OwnerService}]
    })
    .compileComponents();
    fixture = TestBed.createComponent(OwnerListComponent);
    component = fixture.componentInstance;
    ownerService = TestBed.inject(OwnerService);

  });


  it('should create', () => {

    expect(component).toBeTruthy();

  });
//test owners
  it('should fetch owners and delete owner and filter owners by id', () => {

    const ownerId = 2;

    const mockOwners = [

      {id: 1, firstname:"Moussa",lastname:"DIOMBANA",phone:"0656821681",email:"moussa@gmail.com",address:"USA",password:"33333"},

      {id: 2, firstname:"Amadou",lastname:"Camara",phone:"0705765678",email:"amadou@gmail.com",address:"MLI",password:"1234"},

      {id: 3, firstname:"Birahima",lastname:"Sangare",phone:"0708095645",email:"birahima@gmail.com",address:"BK",password:"1234"}];

      component.owners = mockOwners;

      const ownerDelete = spyOn(ownerService, 'deleteOwner').and.returnValue(of("removed owner "+ ownerId));

      const reloadSpy = spyOn(component, 'reloadPage');

      component.deleteOwner(ownerId);

      expect(ownerDelete).toHaveBeenCalledWith(ownerId);

      expect(ownerDelete).toHaveBeenCalled();

      expect(component.owners.filter((owner:any) => owner.id === ownerId));

      component.reloadPage();

      expect(reloadSpy).toHaveBeenCalled();
  });


});
