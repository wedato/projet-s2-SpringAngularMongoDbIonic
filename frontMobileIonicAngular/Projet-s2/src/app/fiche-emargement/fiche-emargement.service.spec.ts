import { TestBed } from '@angular/core/testing';

import { FicheEmargementService } from './fiche-emargement.service';

describe('FicheEmargementService', () => {
  let service: FicheEmargementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FicheEmargementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
