import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilialeModelsComponent } from './filiale-models.component';

describe('FilialeModelsComponent', () => {
  let component: FilialeModelsComponent;
  let fixture: ComponentFixture<FilialeModelsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilialeModelsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilialeModelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
