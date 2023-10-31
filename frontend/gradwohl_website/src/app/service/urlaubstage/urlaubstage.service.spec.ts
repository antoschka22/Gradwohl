import { TestBed } from '@angular/core/testing';

import { UrlaubstageService } from './urlaubstage.service';

describe('UrlaubstageService', () => {
  let service: UrlaubstageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UrlaubstageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
