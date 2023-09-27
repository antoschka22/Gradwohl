import { TestBed } from '@angular/core/testing';

import { MitabeiterService } from './mitabeiter.service';

describe('MitabeiterService', () => {
  let service: MitabeiterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MitabeiterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
