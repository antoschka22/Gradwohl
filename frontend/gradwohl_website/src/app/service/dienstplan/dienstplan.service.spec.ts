import { TestBed } from '@angular/core/testing';

import { DienstplanService } from './dienstplan.service';

describe('DienstplanService', () => {
  let service: DienstplanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DienstplanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
