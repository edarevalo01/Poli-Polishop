import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PantallaVendedorComponent } from './pantalla-vendedor.component';

describe('PantallaVendedorComponent', () => {
  let component: PantallaVendedorComponent;
  let fixture: ComponentFixture<PantallaVendedorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PantallaVendedorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PantallaVendedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
