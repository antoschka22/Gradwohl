import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZentraleNavbarComponent } from './zentrale-navbar.component';

describe('ZentraleNavbarComponent', () => {
  let component: ZentraleNavbarComponent;
  let fixture: ComponentFixture<ZentraleNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZentraleNavbarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZentraleNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
