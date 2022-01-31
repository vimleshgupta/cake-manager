import { TestBed } from '@angular/core/testing';

import { CakeManagerService } from './cake-manager.service';

describe('CakeManagerService', () => {
  let service: CakeManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CakeManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
