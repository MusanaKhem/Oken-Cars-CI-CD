import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { DetailOwnerComponent } from './detail-owner.component';
import { OwnerService } from '../service/owner.service';
import { Owner } from '../models/owner';

describe('DetailOwnerComponent', () => {
  let component: DetailOwnerComponent;
  let fixture: ComponentFixture<DetailOwnerComponent>;
  let mockActivatedRoute: ActivatedRoute;
  let mockOwnerService: OwnerService;

  beforeEach(() => {
    // Create mocks for ActivatedRoute and OwnerService
    mockActivatedRoute.snapshot.params = { id: '1' };
    
    const mockOwner: Owner = {
      firstname:"Moussa",
      lastname:"DIOMBANA",
      phone:"0656821681",
      email:"moussa@gmail.com",
      address:"USA",
      password:"33333"

    };

    spyOn(mockOwnerService, 'getOwner').and.returnValue(of(mockOwner));

    TestBed.configureTestingModule({
      declarations: [DetailOwnerComponent],
      providers: [
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
        { provide: OwnerService, useValue: mockOwnerService }
      ]
    });

    fixture = TestBed.createComponent(DetailOwnerComponent);

    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch owner details on initialization', () => {

    fixture.detectChanges(); // Triggers the change detection cycle

    expect(mockOwnerService.getOwner).toHaveBeenCalledWith(1); // Check that the getOwner method was called with the expected ID

    expect(component.owner$).toBeDefined(); // Check that owner$ has been defined
  });
});
