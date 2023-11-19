import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MitarbeiterModelsComponent } from './mitarbeiter-models.component';

describe('MitarbeiterModelsComponent', () => {
  let component: MitarbeiterModelsComponent;
  let fixture: ComponentFixture<MitarbeiterModelsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MitarbeiterModelsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MitarbeiterModelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
