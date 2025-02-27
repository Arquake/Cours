import { TestBed } from '@angular/core/testing';

import { BokkService } from './bokk.service';

describe('BokkService', () => {
  let service: BokkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BokkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
