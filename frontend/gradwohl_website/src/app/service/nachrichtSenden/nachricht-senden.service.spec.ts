import { TestBed } from '@angular/core/testing';

import { NachrichtSendenService } from './nachricht-senden.service';

describe('NachrichtSendenService', () => {
  let service: NachrichtSendenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NachrichtSendenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
