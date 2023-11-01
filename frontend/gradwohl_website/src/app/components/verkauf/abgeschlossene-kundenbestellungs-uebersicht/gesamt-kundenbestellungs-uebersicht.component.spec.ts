import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GesamtKundenbestellungsUebersichtComponent } from './gesamt-kundenbestellungs-uebersicht.component';

describe('GesamtKundenbestellungsUebersichtComponent', () => {
  let component: GesamtKundenbestellungsUebersichtComponent;
  let fixture: ComponentFixture<GesamtKundenbestellungsUebersichtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GesamtKundenbestellungsUebersichtComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GesamtKundenbestellungsUebersichtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
