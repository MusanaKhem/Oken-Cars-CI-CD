import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderCarComponent } from './header-car.component';

describe('HeaderCarComponent', () => {
  let component: HeaderCarComponent;
  let fixture: ComponentFixture<HeaderCarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderCarComponent]
    });
    fixture = TestBed.createComponent(HeaderCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
