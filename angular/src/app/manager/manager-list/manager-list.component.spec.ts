import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HeaderManagerComponent } from '../header-manager/header-manger.component';
import { of } from 'rxjs';
import { By } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ManagerService } from '../service/manager.service';
import { ManagerListComponent } from './manager-list.component';
import { FormsModule } from '@angular/forms';

describe('ManagerListComponent', () => {
  let component: ManagerListComponent;
  let fixture: ComponentFixture<ManagerListComponent>;
  let managerService: ManagerService;
  let router: Router;
  const buttonHTML = ` <button type="button" id="viewButton" class="btn btn-outline-success" (click)="onViewManager(manager.id)">See</button>`;
  managerService = jasmine.createSpyObj('ManagerService', [ 'deleteManagerById']);
  //Affichage des infos des managers
      const mockManagers = [
    		{ id: 1, lastname: 'Manager 1', firstname: 'First 1', email: 'email1@example.com', phone: '123456789', address: 'Address 1' ,password: 'password1'},
    		{ id: 2, lastname: 'Manager 2', firstname: 'First 2', email: 'email2@example.com', phone: '987654321', address: 'Address 2' ,password: 'password2'},
    		{ id: 3, lastname: 'Manager 3', firstname: 'First 3', email: 'email3@example.com', phone: '124576836', address: 'Address 3' ,password: 'password3'},
        { id: 4, lastname: 'Manager 4', firstname: 'First 4', email: 'email4@example.com', phone: '523467687', address: 'Address 4' ,password: 'password4'},
        { id: 5, lastname: 'Manager 5', firstname: 'First 5', email: 'email5@example.com', phone: '435257890', address: 'Address 5' ,password: 'password5'}

    		// ... other mock managers ...
      ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManagerListComponent, HeaderManagerComponent],
       imports: [HttpClientTestingModule, FormsModule ],
       providers: [ ManagerService, Router ]
    });
    fixture = TestBed.createComponent(ManagerListComponent);
    component = fixture.componentInstance;

    //Simulation de l'injection du ManagerService
    managerService = TestBed.inject(ManagerService);
    router = TestBed.inject(Router);

    fixture.detectChanges();
  });

  it('should create', () => {
    spyOn(managerService, 'getAllManagers').and.returnValue(of(mockManagers));
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });

  //Vérifier que les données des managers sont correctement affichées dans le tableau.
     it('should display manager data', () => {
    		spyOn(managerService, 'getAllManagers').and.returnValue(of(mockManagers));
    		fixture.detectChanges();
    		const managerRows = fixture.debugElement.queryAll(By.css('.table-container tbody tr td'));
    		expect(5).toBe(mockManagers.length);
    });


    //Vérifier que la navigation vers les détails du manager fonctionne correctement lors du clic sur le bouton "View".
     it('should navigate to manager details', () => {
        const routerSpy = spyOn(router, 'navigateByUrl');

        const managerId = 4;
        component.onViewManager(managerId);

        // Vérifiez que la méthode navigateByUrl du router a été appelée avec le bon URL
        expect(routerSpy).toHaveBeenCalledWith(`/manager/${managerId}`);
      });


      //Vérifier que la navigation vers le formulaire de modification des détails du manager fonctionne correctement lors du clic sur le bouton "Upd".
        it('should navigate to edit details on Upd button click', () => {
                const routerSpy = spyOn(router, 'navigateByUrl');

                const managerId = 4;
                component.onManagerUpdate(managerId);

                // Vérifiez que la méthode navigateByUrl du router a été appelée avec le bon URL
                expect(routerSpy).toHaveBeenCalledWith(`/edit-manager/${managerId}`);
        });

        //Vérifier bonne suppression du manager via le boutton
           it('should call onDeleteManager and reload the page', () => {
             const managerId = 1;
             const spy = spyOn(managerService, 'deleteManagerById').and.callThrough();

             component.onDeleteManager(managerId);

             expect(spy).toHaveBeenCalledWith(managerId);
             //expect(window.location.reload).toHaveBeenCalled(); // Checking if window.location.reload was called
           });


});
