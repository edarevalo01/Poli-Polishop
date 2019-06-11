import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PantallaVenderComponent } from './pantalla-vender.component';

describe('PantallaVenderComponent', () => {
  let component: PantallaVenderComponent;
  let fixture: ComponentFixture<PantallaVenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PantallaVenderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PantallaVenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
