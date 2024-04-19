import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HeaderDriverComponent } from './header-driver.component';


describe('HeaderDriverComponent', () => {
  let component: HeaderDriverComponent;
  let fixture: ComponentFixture<HeaderDriverComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderDriverComponent]
    });
    fixture = TestBed.createComponent(HeaderDriverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
