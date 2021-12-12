import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscadorProyectoComponent } from './buscador-proyecto.component';

describe('BuscadorProyectoComponent', () => {
  let component: BuscadorProyectoComponent;
  let fixture: ComponentFixture<BuscadorProyectoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BuscadorProyectoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BuscadorProyectoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
