import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductoPrincipalSliderComponent } from './producto-principal-slider.component';

describe('ProductoPrincipalSliderComponent', () => {
  let component: ProductoPrincipalSliderComponent;
  let fixture: ComponentFixture<ProductoPrincipalSliderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductoPrincipalSliderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductoPrincipalSliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
