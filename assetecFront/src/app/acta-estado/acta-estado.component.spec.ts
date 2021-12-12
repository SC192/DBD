import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActaEstadoComponent } from './acta-estado.component';

describe('ActaEstadoComponent', () => {
  let component: ActaEstadoComponent;
  let fixture: ComponentFixture<ActaEstadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActaEstadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActaEstadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
