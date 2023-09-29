import { TestBed } from '@angular/core/testing';

import { LieferbarService } from './lieferbar.service';

describe('LieferbarService', () => {
  let service: LieferbarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LieferbarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
