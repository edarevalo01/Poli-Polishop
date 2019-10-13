import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LinksPage } from './links.page';

describe('LinksPage', () => {
  let component: LinksPage;
  let fixture: ComponentFixture<LinksPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LinksPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LinksPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
