import { TestBed } from '@angular/core/testing';

import { VorlageService } from './vorlage.service';

describe('VorlageService', () => {
  let service: VorlageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VorlageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
