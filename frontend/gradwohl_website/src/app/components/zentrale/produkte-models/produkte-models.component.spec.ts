import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdukteModelsComponent } from './produkte-models.component';

describe('ProdukteModelsComponent', () => {
  let component: ProdukteModelsComponent;
  let fixture: ComponentFixture<ProdukteModelsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProdukteModelsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProdukteModelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
