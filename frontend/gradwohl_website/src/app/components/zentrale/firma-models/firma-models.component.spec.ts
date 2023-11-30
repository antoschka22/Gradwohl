import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FirmaModelsComponent } from './firma-models.component';

describe('FirmaModelsComponent', () => {
  let component: FirmaModelsComponent;
  let fixture: ComponentFixture<FirmaModelsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FirmaModelsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FirmaModelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
