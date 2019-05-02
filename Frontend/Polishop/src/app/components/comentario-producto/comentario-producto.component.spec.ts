import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComentarioProductoComponent } from './comentario-producto.component';

describe('ComentarioProductoComponent', () => {
  let component: ComentarioProductoComponent;
  let fixture: ComponentFixture<ComentarioProductoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComentarioProductoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComentarioProductoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
