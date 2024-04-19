import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EditDriverComponent } from './edit-driver.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { DriverService } from '../service/driver.service';
import { HeaderDriverComponent } from '../header-driver/header-driver.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Driver } from '../models/driver';
import { of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';


describe('EditDriverComponent', () => {
  let fixture: ComponentFixture<EditDriverComponent>;
  let component: EditDriverComponent;
  let driverService: DriverService;
  let router: Router;
  let route: ActivatedRoute;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule,FormsModule],
      declarations: [ EditDriverComponent,HeaderDriverComponent ],
      providers: [DriverService]
    })
    .compileComponents();
    fixture = TestBed.createComponent(EditDriverComponent);
    component = fixture.componentInstance;
    driverService = TestBed.inject(DriverService);
    router = TestBed.inject(Router);
    route = TestBed.inject(ActivatedRoute);
    fixture.detectChanges();
  });

  it('should create', () => {

    expect(component).toBeTruthy();

  });

  it('should goDriverEdit', () => {

        // Create a spy for the goList method in the component

        const navigateByUrlSpy = spyOn(router, 'navigateByUrl').and.returnValue(Promise.resolve(true));

        //Call method goDriverEdit

        component.goDriverEdit();

        //Call driver by Id

        expect(navigateByUrlSpy).toHaveBeenCalledWith('/driver/' + component.id);

        expect(component).toBeTruthy();
  });

  it('should call editDriver method', () => {

    // Create a spy for the editDriver method in the component

    const onClickMock = spyOn(component, 'editDriver');

    // Simulate a click on the "Update" button

    fixture.debugElement.query(By.css('button')).triggerEventHandler('click', null);

    // Vérify if the method editDriver has been called

    expect(onClickMock).toHaveBeenCalled();

    // Vérify if the method editDriver has been called once

    expect(component.editDriver).toHaveBeenCalledTimes(1);
  });


});
