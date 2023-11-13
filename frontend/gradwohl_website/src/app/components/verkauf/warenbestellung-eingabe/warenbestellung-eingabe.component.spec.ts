import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarenbestellungEingabeComponent } from './warenbestellung-eingabe.component';

describe('WarenbestellungEingabeComponent', () => {
  let component: WarenbestellungEingabeComponent;
  let fixture: ComponentFixture<WarenbestellungEingabeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarenbestellungEingabeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WarenbestellungEingabeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
