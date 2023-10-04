import { TestBed } from '@angular/core/testing';

import { MasterGuardService } from './master-guard.service';

describe('MasterGuardService', () => {
  let service: MasterGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MasterGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
