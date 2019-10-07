import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PantallaProductoPage } from './pantalla-producto.page';

describe('PantallaProductoPage', () => {
  let component: PantallaProductoPage;
  let fixture: ComponentFixture<PantallaProductoPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PantallaProductoPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PantallaProductoPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
