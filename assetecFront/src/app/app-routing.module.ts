import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AsistenciaComponent} from './asistencia/asistencia.component';
import {ActividadComponent} from './actividad/actividad.component';
import {ActaComponent} from './acta/acta.component';
import {BuscadorProyectoComponent} from './buscadorProyecto/buscador-proyecto.component';
import {SignupComponent} from './signup/signup.component';
import {SigninComponent} from './signin/signin.component';
import {ProyectoComponent} from './proyecto/proyecto.component';
import {SolicitudContactoComponent} from './solicitud-contacto/solicitud-contacto.component';
import {SolicitudTrabajadorComponent} from './solicitud-trabajador/solicitud-trabajador.component';
import {PerfilComponent} from './perfil/perfil.component';
import {ResultadosComponent} from './resultados/resultados.component';
import {GastoComponent} from './gasto/gasto.component';
import {ComprobanteComponent} from './comprobante/comprobante.component';

const routes: Routes = [
  {path: '', component: SigninComponent},
  {path: 'home', component: HomeComponent},
  {path: 'asistencia', component: AsistenciaComponent},
  {path: 'actividad', component: ActividadComponent},
  {path: 'acta', component: ActaComponent},
  {path: 'buscador', component: BuscadorProyectoComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'proyecto', component: ProyectoComponent},
  {path: 'solicitudC', component: SolicitudContactoComponent},
  {path: 'solicitudT', component: SolicitudTrabajadorComponent},
  {path: 'perfil', component: PerfilComponent},
  {path: 'resultados', component: ResultadosComponent},
  {path: 'gasto', component: GastoComponent},
  {path: 'comprobante', component: ComprobanteComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
