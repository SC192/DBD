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

const routes: Routes = [
  {path: '', component: SignupComponent},
  {path: 'home', component: HomeComponent},
  {path: 'asistencia', component: AsistenciaComponent},
  {path: 'actividad', component: ActividadComponent},
  {path: 'acta', component: ActaComponent},
  {path: 'buscadorProyecto', component: BuscadorProyectoComponent},
  {path: 'signin', component: SigninComponent},
  {path: 'proyecto', component: ProyectoComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
