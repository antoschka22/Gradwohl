import { TestBed } from '@angular/core/testing';

import { FirmenurlaubService } from './firmenurlaub.service';

describe('FirmenurlaubService', () => {
  let service: FirmenurlaubService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FirmenurlaubService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
