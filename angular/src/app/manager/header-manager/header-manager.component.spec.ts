import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HeaderManagerComponent } from './header-manger.component';



describe('HeaderManagerComponent', () => {
  let component: HeaderManagerComponent;
  let fixture: ComponentFixture<HeaderManagerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderManagerComponent]
    });
    fixture = TestBed.createComponent(HeaderManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
