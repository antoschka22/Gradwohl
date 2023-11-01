import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DienstplanModelsComponent } from './dienstplan-models.component';

describe('DienstplanModelsComponent', () => {
  let component: DienstplanModelsComponent;
  let fixture: ComponentFixture<DienstplanModelsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DienstplanModelsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DienstplanModelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
