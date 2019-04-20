import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PantallaProductoComponent } from './pantalla-producto.component';

describe('PantallaProductoComponent', () => {
  let component: PantallaProductoComponent;
  let fixture: ComponentFixture<PantallaProductoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PantallaProductoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PantallaProductoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
