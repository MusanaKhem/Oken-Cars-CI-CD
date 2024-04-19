import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { EditManagerComponent } from './edit-manager.component';
import { HeaderManagerComponent } from '../header-manager/header-manger.component';
import { Manager } from '../models/manager';
import { ManagerService } from '../service/manager.service';
import { of } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

fdescribe('EditManagerComponent', () => {
  let component: EditManagerComponent;
  let fixture: ComponentFixture<EditManagerComponent>;
  let activatedRoute: ActivatedRoute;
  let managerService: ManagerService;
   let route: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditManagerComponent,HeaderManagerComponent],
      imports: [HttpClientTestingModule, RouterTestingModule ,FormsModule]
    });
    fixture = TestBed.createComponent(EditManagerComponent);
    component = fixture.componentInstance;

     // Obtenir l'instance réelle d'ActivatedRoute
     const route = TestBed.inject(ActivatedRoute);
     route.snapshot.params = { id: '1' }; // Simuler l'ID

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

/*
   it('should load manager data', () => {
          const managerData: Manager = {
                id: 1,
                lastname: 'Doe',
                firstname: 'John',
                email: 'johndoe@example.com',
                phone: '123-456-7890',
                address: '123 Main St',
                password: 'password1'
          };

           // Espionner la méthode avant de déclencher le changement dans le composant
            spyOn(managerService, 'getManagerById').and.returnValue(of(managerData));

            fixture.detectChanges(); // Déclenche la détection des modifications dans le composant

            // Maintenant, vous pouvez effectuer vos attentes
            expect(managerService.getManagerById).toHaveBeenCalledWith(1);
            expect(component.manager).toEqual(managerData);
    });

      it('should update manager', () => {
        const managerData: Manager = {                 id: 1,
                                                       lastname: 'Doe',
                                                       firstname: 'John',
                                                       email: 'johndoe@example.com',
                                                       phone: '123-456-7890',
                                                       address: '123 Main St',
                                                       password: 'password1' };
        component.manager = managerData;

      //  managerService.updateManager.and.returnValue(of(null));
         // Espionner la méthode avant de déclencher le changement dans le composant
          spyOn(managerService, 'updateManager').and.returnValue(of(managerData));

          fixture.detectChanges(); // Déclenche la détection des modifications dans le composant

          // Maintenant, vous pouvez effectuer vos attentes
          expect(managerService.getManagerById).toHaveBeenCalledWith(1);
          expect(component.manager).toEqual(managerData);
        component.updateManager();

        expect(managerService.updateManager).toHaveBeenCalledWith(1, managerData);
        expect(route.navigateByUrl).toHaveBeenCalledWith('/manager/1');
      });
    */


});
