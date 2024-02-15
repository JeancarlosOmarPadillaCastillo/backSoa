import { TestBed } from '@angular/core/testing';

import { AsignationActTeenService } from './asignation-act-teen.service';

describe('AsignationActTeenService', () => {
  let service: AsignationActTeenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AsignationActTeenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
