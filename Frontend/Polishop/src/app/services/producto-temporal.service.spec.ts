import { TestBed } from '@angular/core/testing';

import { ProductoTemporalService } from './producto-temporal.service';

describe('ProductoTemporalService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProductoTemporalService = TestBed.get(ProductoTemporalService);
    expect(service).toBeTruthy();
  });
});
