import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KundenbestellungSpeichernComponent } from './kundenbestellung-speichern.component';

describe('KundenbestellungSpeichernComponent', () => {
  let component: KundenbestellungSpeichernComponent;
  let fixture: ComponentFixture<KundenbestellungSpeichernComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KundenbestellungSpeichernComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KundenbestellungSpeichernComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
