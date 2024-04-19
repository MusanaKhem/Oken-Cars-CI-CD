import { ComponentFixture, TestBed,tick, fakeAsync} from '@angular/core/testing';
import { HeaderManagerComponent } from '../header-manager/header-manger.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RegisterManagerComponent } from './register-manager.component';
import { Manager } from '../models/manager';
import { ManagerService } from '../service/manager.service';
import { of } from 'rxjs';
import { Router } from '@angular/router';

describe('RegisterManagerComponent', () => {
  let component: RegisterManagerComponent;
  let fixture: ComponentFixture<RegisterManagerComponent>;
  let managerService: ManagerService;
  let router: Router;
   const mockManager: Manager = {
          id: 1,
          lastname: 'Doe',
          firstname: 'John',
          email: 'johndoe@example.com',
          phone: '123-456-7890',
          address: '123 Main St',
          password: 'password1',
        };

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterManagerComponent,HeaderManagerComponent],
      imports: [HttpClientTestingModule, FormsModule,RouterTestingModule],
      providers: [ManagerService]
    });
    fixture = TestBed.createComponent(RegisterManagerComponent);
    component = fixture.componentInstance;

    //Injection du service
    managerService = TestBed.inject(ManagerService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });



  it('should create', () => {
    expect(component).toBeTruthy();
  });


   it('should add a manager and  call managerService.newManager for navigate to manager page', () => {
      const managerServiceSpy = spyOn(managerService, 'newManager').and.returnValue(of(mockManager));
      const routerSpy = spyOn(router, 'navigateByUrl');

      component.manager = mockManager;
      component.confirmPassword = mockManager.password;

      component.addManager();

      expect(managerServiceSpy).toHaveBeenCalledWith(mockManager);
      expect(routerSpy).toHaveBeenCalledWith(`/manager/${mockManager.id}`);
    });

    //Vérification de la validité du mot de passe saisi
      it('should validate password using regex', () => {
        // Test a valid password
        component.manager.password = 'ValidPassword123!';
        component.validatePassword();
        expect(component.passwordInvalid).toBeFalsy();

        // Test an invalid password
        component.manager.password = 'invalid'; // Does not meet regex requirements
        component.validatePassword();
        expect(component.passwordInvalid).toBeTruthy();
      });

      //Vérifier validité des 2 mots de passe
      it('should validate matching passwords', () => {
          component.manager.password = 'Password123!';
          component.confirmPassword = 'Password123!';

          // Trigger the validation
          component.validatePasswordMatch();

          expect(component.passwordsMatch).toBeTruthy();
        });

        //Vérifier invalidité des 2 mots de passe
        it('should validate non-matching passwords', () => {
          component.manager.password = 'Password123!';
          component.confirmPassword = 'DifferentPassword456@';

          // Trigger the validation
          component.validatePasswordMatch();

          expect(component.passwordsMatch).toBeFalsy();
        });




});
