import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductoPrincipalPequenoComponent } from './producto-principal-pequeno.component';

describe('ProductoPrincipalPequenoComponent', () => {
  let component: ProductoPrincipalPequenoComponent;
  let fixture: ComponentFixture<ProductoPrincipalPequenoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductoPrincipalPequenoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductoPrincipalPequenoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
