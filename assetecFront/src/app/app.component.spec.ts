import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {ActaComponent} from './acta/acta.component';
import {ProyectoComponent} from './proyecto/proyecto.component';
import {SigninComponent} from './signin/signin.component';
import {SignupComponent} from './signup/signup.component';
import {BuscadorProyectoComponent} from './buscadorProyecto/buscador-proyecto.component';
import {AsistenciaComponent} from './asistencia/asistencia.component';
import {ActividadComponent} from './actividad/actividad.component';
import {PerfilComponent} from './perfil/perfil.component';
import {SolicitudContactoComponent} from './solicitud-contacto/solicitud-contacto.component';
import {SolicitudTrabajadorComponent} from './solicitud-trabajador/solicitud-trabajador.component';
import {ResultadosComponent} from './resultados/resultados.component';
import {GastoComponent} from './gasto/gasto.component';
import {ComprobanteComponent} from './comprobante/comprobante.component';

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        AppComponent,
        HomeComponent,
        ActaComponent,
        ProyectoComponent,
        SigninComponent,
        SignupComponent,
        BuscadorProyectoComponent,
        AsistenciaComponent,
        ActividadComponent,
        PerfilComponent,
        SolicitudContactoComponent,
        SolicitudTrabajadorComponent,
        ResultadosComponent,
        GastoComponent,
        ComprobanteComponent,
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'assetecFront'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('assetecFront');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('assetecFront app is running!');
  });
});
