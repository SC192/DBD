import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudContactoComponent } from './solicitud-contacto.component';

describe('SolicitudContactoComponent', () => {
  let component: SolicitudContactoComponent;
  let fixture: ComponentFixture<SolicitudContactoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitudContactoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitudContactoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
