import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { EditOwnerComponent } from './edit-owner.component';
import { OwnerService } from '../service/owner.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HeaderOwnerComponent } from '../header-owner/header-owner.component';


fdescribe('EditOwnerComponent', () => {
  let fixture: ComponentFixture<EditOwnerComponent>;
  let component: EditOwnerComponent;
  let ownerService: OwnerService;
  let router: Router;
  let route: ActivatedRoute;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule,FormsModule],
      declarations: [ EditOwnerComponent, HeaderOwnerComponent ],
      providers: [OwnerService]
    })
    .compileComponents();
    fixture = TestBed.createComponent(EditOwnerComponent);
    component = fixture.componentInstance;
    ownerService = TestBed.inject(OwnerService);
    router = TestBed.inject(Router);
    route = TestBed.inject(ActivatedRoute);
    fixture.detectChanges();
  });

  it('should create', () => {

    expect(component).toBeTruthy();

  });

  it('should goEditOwner', () => {

        // Create a spy for the goList method in the component

        const navigateByUrlSpy = spyOn(router, 'navigateByUrl').and.returnValue(Promise.resolve(true));

        //Call method goEditOwner

        component.goEditOwner();

        //Call owner by Id

        expect(navigateByUrlSpy).toHaveBeenCalledWith('/owner/' + component.id);

        expect(component).toBeTruthy();
  });

  it('should call  method', () => {

    // Create a spy for the goEditOwner method in the component

    const onClickMock = spyOn(component, 'editOwner');

    // Simulate a click on the "Update" button

    fixture.debugElement.query(By.css('button')).triggerEventHandler('click', null);

    // Vérify if the method editOwner has been called

    expect(onClickMock).toHaveBeenCalled();

    // Vérify if the method editOwner has been called once

    expect(component.editOwner).toHaveBeenCalledTimes(1);
  });


});
