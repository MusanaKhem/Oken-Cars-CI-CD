import { ComponentFixture, TestBed,waitForAsync, fakeAsync, tick  } from '@angular/core/testing';
import { HeaderManagerComponent } from '../header-manager/header-manger.component';import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { Manager } from '../models/manager';
import { ManagerService } from '../service/manager.service';
import { DetailManagerComponent } from './detail-manager.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of, throwError } from 'rxjs';

describe('DetailManagerComponent', () => {
  let component: DetailManagerComponent;
  let fixture: ComponentFixture<DetailManagerComponent>;
  let activatedRoute: ActivatedRoute;
  let managerService: ManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailManagerComponent,HeaderManagerComponent],
      imports: [HttpClientTestingModule,RouterTestingModule],
      providers: [ManagerService]
    });
    fixture = TestBed.createComponent(DetailManagerComponent);
    component = fixture.componentInstance;

    // Obtenir l'instance réelle d'ActivatedRoute
        activatedRoute = TestBed.inject(ActivatedRoute);
        managerService = TestBed.inject(ManagerService);

        // Simuler un manager pour les tests
        const mockManager: Manager = {
          id: 1,
          lastname: 'Doe',
          firstname: 'John',
          email: 'johndoe@example.com',
          phone: '123-456-7890',
          address: '123 Main St',
          password: 'password1'
        };

        // Espionner le service pour retourner un observable contenant le manager simulé
        spyOn(managerService, 'getManagerById').and.returnValue(of(mockManager));

        // Simuler l'ID dans ActivatedRoute
        activatedRoute.snapshot.params = { id: '1' };

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

   it('should retrieve manager data from service', () => {
      expect(component.manager$).toBeDefined();
      expect(component.manager$).toBeTruthy();

      // Vérifier si les données du manager ont été récupérées correctement
      component.manager$.subscribe((manager) => {
        expect(manager.id).toBe(1);
        expect(manager.lastname).toBe('Doe');
        expect(manager.firstname).toBe('John');
        expect(manager.email).toBe('johndoe@example.com');
        expect(manager.phone).toBe('123-456-7890');
        expect(manager.address).toBe('123 Main St');
      });
    });

    it('should display manager data in template', fakeAsync(() => {
      const compiled = fixture.nativeElement;

      // Appeler ngOnInit pour récupérer les données du manager
      component.ngOnInit();

      // Attendre que les observables se résolvent
      tick();
      fixture.detectChanges();

      // Chercher les balises <p> correspondantes pour extraire les informations
      const lastnameParagraph = compiled.querySelector('p:nth-child(1)');
      const firstNameParagraph = compiled.querySelector('p:nth-child(2)');
      const emailParagraph = compiled.querySelector('p:nth-child(3)');
      const phoneNameParagraph = compiled.querySelector('p:nth-child(4)');
      const addressParagraph = compiled.querySelector('p:nth-child(5)');

      expect(lastnameParagraph.textContent).toContain('Name : Doe');
      expect(firstNameParagraph.textContent).toContain('First Name : John');
      expect(emailParagraph.textContent).toContain('Email Adress : johndoe@example.com');
      expect(phoneNameParagraph.textContent).toContain('Phone number : 123-456-7890');
      expect(addressParagraph.textContent).toContain('Address : 123 Main St');
    }));


});
