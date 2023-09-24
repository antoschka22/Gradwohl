import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffRosterMainComponent } from './staff-roster-main.component';

describe('StaffRosterMainComponent', () => {
  let component: StaffRosterMainComponent;
  let fixture: ComponentFixture<StaffRosterMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaffRosterMainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StaffRosterMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
