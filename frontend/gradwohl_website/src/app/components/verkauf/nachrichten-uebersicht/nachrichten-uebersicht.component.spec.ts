import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NachrichtenUebersichtComponent } from './nachrichten-uebersicht.component';

describe('NachrichtenUebersichtComponent', () => {
  let component: NachrichtenUebersichtComponent;
  let fixture: ComponentFixture<NachrichtenUebersichtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NachrichtenUebersichtComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NachrichtenUebersichtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
