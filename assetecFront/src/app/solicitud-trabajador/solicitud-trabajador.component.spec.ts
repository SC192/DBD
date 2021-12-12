import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudTrabajadorComponent } from './solicitud-trabajador.component';

describe('SolicitudTrabajadorComponent', () => {
  let component: SolicitudTrabajadorComponent;
  let fixture: ComponentFixture<SolicitudTrabajadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitudTrabajadorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitudTrabajadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
