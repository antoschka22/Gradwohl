import { TestBed } from '@angular/core/testing';

import { WarenbestellungService } from './warenbestellung.service';

describe('WarenbestellungService', () => {
  let service: WarenbestellungService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WarenbestellungService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
