import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialComprasComponent } from './historial-compras.component';

describe('HistorialComprasComponent', () => {
  let component: HistorialComprasComponent;
  let fixture: ComponentFixture<HistorialComprasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistorialComprasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistorialComprasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
