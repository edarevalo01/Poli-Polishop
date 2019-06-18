import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PantallaCarritoComponent } from './pantalla-carrito.component';

describe('PantallaCarritoComponent', () => {
  let component: PantallaCarritoComponent;
  let fixture: ComponentFixture<PantallaCarritoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PantallaCarritoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PantallaCarritoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
