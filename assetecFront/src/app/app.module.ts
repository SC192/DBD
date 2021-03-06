import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { BuscadorProyectoComponent } from './buscadorProyecto/buscador-proyecto.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './CrearCuenta/signup.component';
import { AsistenciaComponent } from './asistencia/asistencia.component';
import { ProyectoComponent } from './proyecto/proyecto.component';
import { ActividadComponent } from './actividad/actividad.component';
import { ActaComponent } from './LlenarYEnviarActa/acta.component';
import { SolicitudContactoComponent } from './solicitud-contacto/solicitud-contacto.component';
import { SolicitudTrabajadorComponent } from './solicitud-trabajador/solicitud-trabajador.component';
import { PerfilComponent } from './perfil/perfil.component';
import { ResultadosComponent } from './resultados/resultados.component';
import { ComprobanteComponent } from './comprobante/comprobante.component';
import { GastoComponent } from './gasto/gasto.component';
import { ActaEstadoComponent } from './ResponderActa/acta-estado.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BuscadorProyectoComponent,
    SigninComponent,
    SignupComponent,
    AsistenciaComponent,
    ProyectoComponent,
    ActividadComponent,
    ActaComponent,
    SolicitudContactoComponent,
    SolicitudTrabajadorComponent,
    PerfilComponent,
    ResultadosComponent,
    ComprobanteComponent,
    GastoComponent,
    ActaEstadoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
