import { TestBed } from '@angular/core/testing';

import { ProduktgruppeService } from './produktgruppe.service';

describe('ProduktgruppeService', () => {
  let service: ProduktgruppeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProduktgruppeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
