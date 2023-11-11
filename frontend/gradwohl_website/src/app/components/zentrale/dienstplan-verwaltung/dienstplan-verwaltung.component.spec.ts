import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DienstplanVerwaltungComponent } from './dienstplan-verwaltung.component';

describe('DienstplanVerwaltungComponent', () => {
  let component: DienstplanVerwaltungComponent;
  let fixture: ComponentFixture<DienstplanVerwaltungComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DienstplanVerwaltungComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DienstplanVerwaltungComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
