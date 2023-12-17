import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NachrichtComponent } from './nachricht.component';

describe('NachrichtComponent', () => {
  let component: NachrichtComponent;
  let fixture: ComponentFixture<NachrichtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NachrichtComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NachrichtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
