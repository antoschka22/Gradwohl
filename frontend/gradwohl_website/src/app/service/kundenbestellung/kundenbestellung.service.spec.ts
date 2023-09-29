import { TestBed } from '@angular/core/testing';

import { KundenbestellungService } from './kundenbestellung.service';

describe('KundenbestellungService', () => {
  let service: KundenbestellungService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KundenbestellungService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
