import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginFirstPageComponent } from './login-first-page.component';

describe('LoginFirstPageComponent', () => {
  let component: LoginFirstPageComponent;
  let fixture: ComponentFixture<LoginFirstPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginFirstPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginFirstPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
