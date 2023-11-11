import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DienstplanViewComponent } from './dienstplan-view.component';

describe('DienstplanViewComponent', () => {
  let component: DienstplanViewComponent;
  let fixture: ComponentFixture<DienstplanViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DienstplanViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DienstplanViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
