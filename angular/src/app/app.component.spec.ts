import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';

beforeEach(async () => {
  await TestBed.configureTestingModule({
    imports: [
      RouterTestingModule
    ],
    declarations: [ AppComponent ]
  })
  .compileComponents();
});

it('should create', () => {
  const fixture = TestBed.createComponent(AppComponent);
  const appComponent = fixture.debugElement.componentInstance;
  expect(appComponent).toBeTruthy();
});
