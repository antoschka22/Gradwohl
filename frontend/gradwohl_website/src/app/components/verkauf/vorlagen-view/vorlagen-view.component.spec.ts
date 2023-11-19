import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VorlagenViewComponent } from './vorlagen-view.component';

describe('VorlagenViewComponent', () => {
  let component: VorlagenViewComponent;
  let fixture: ComponentFixture<VorlagenViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VorlagenViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VorlagenViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
