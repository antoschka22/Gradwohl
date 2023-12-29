import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BestelluebersichtverwaltenComponent } from './bestelluebersichtverwalten.component';

describe('BestelluebersichtverwaltenComponent', () => {
  let component: BestelluebersichtverwaltenComponent;
  let fixture: ComponentFixture<BestelluebersichtverwaltenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BestelluebersichtverwaltenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BestelluebersichtverwaltenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
