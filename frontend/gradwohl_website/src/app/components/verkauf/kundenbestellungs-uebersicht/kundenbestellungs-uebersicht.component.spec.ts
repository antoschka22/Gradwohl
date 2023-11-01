import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KundenbestellungsUebersichtComponent } from './kundenbestellungs-uebersicht.component';

describe('KundenbestellungsUebersichtComponent', () => {
  let component: KundenbestellungsUebersichtComponent;
  let fixture: ComponentFixture<KundenbestellungsUebersichtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KundenbestellungsUebersichtComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KundenbestellungsUebersichtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
