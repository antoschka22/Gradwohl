import { TestBed } from '@angular/core/testing';

import { NachrichtService } from './nachricht.service';

describe('NachrichtService', () => {
  let service: NachrichtService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NachrichtService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
