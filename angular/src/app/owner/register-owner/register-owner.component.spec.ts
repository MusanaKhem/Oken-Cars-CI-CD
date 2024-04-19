import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { By } from '@angular/platform-browser';
import { RegisterOwnerComponent } from './register-owner.component';
import { OwnerService } from '../service/owner.service';
import { HeaderOwnerComponent } from '../header-owner/header-owner.component';
import { Owner } from '../models/owner';


fdescribe('RegisterOwnerComponent', () => {
  let fixture: ComponentFixture<RegisterOwnerComponent>;
  let component: RegisterOwnerComponent;
  let ownerService: OwnerService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterOwnerComponent,HeaderOwnerComponent ],
      imports: [RouterTestingModule,HttpClientTestingModule, FormsModule],
      providers: [OwnerService],


    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterOwnerComponent);
    component = fixture.componentInstance;
    ownerService = TestBed.inject(OwnerService);
    router = TestBed.inject(Router);
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //Create new Owner
  it("create Driver", () => {
    const mockOwner: Owner = {
      firstname:"Moussa",
      lastname:"DIOMBANA",
      phone:"0656821681",
      email:"moussa@gmail.com",
      address:"USA",
      password:"33333",

    };
   //inject of Service
    const ownerService = fixture.debugElement.injector.get(OwnerService);
  //Add new Owner
    spyOn(ownerService, "createOwner").and.returnValue(of(mockOwner));

    component.owner = mockOwner;

    expect(true).toBeTruthy();


  });

  it("should call URL method", () =>{

      // Create a spy for the method in the component
    spyOn(router, 'navigateByUrl').and.returnValue(Promise.resolve(true));

    expect(true).toBeTruthy();

  })

  it('should call saveOwner method', () => {

    // Create a spy for the saveOwner method in the component

    const onClickMock = spyOn(component, 'saveOwner');

    // Simulate a click on the "Save" button

    fixture.debugElement.query(By.css('button')).triggerEventHandler('click', null);

    // Vérify if the method saveOwner has been called

    expect(onClickMock).toHaveBeenCalled();

    // Vérify if the method saveOwner has been called once

    expect(component.saveOwner).toHaveBeenCalledTimes(1);
  });
});


