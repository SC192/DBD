// Aqui se insertan las clases que se tienen en el back
// import {mkdir} from "fs";

export class Moneda {
  constructor(public codigoMoneda: string,
              public nombre: string) {}
}

export class Rol {
  constructor(public cantidad: number,
              public nombrePerfil: string,
              public totalHoras: number,
              public costoHora: number) {}
}

export class ActaAcuerdo {
  constructor(public codigoAcuerdo: string,
              public acuerdo: string) {}
}

export class Acta {
  constructor(public codigoActa: string,
              public fecha: string,
              public estado: string,
              public acuerdos: ActaAcuerdo []) {}
}

export class Solicitud {
  constructor(public codigoSolicitud: string,
              public fechaSolicitud: string,
              public fechaModificacion: string,
              public estado: string,
              public solicitante: Persona,
              public tipo: TipoSolicitud) {}
}

export class Telefono {
  constructor(public codigoTelefono: string,
              public numero: string,
              public prefijo: string) {}
}

export class TipoComprobante {
  constructor(public codigoTipo: string,
              public tipo: string,
              public descripcion: string) {}
}
export class TipoGasto {
  constructor(public codigoTipo: string,
              public tipo: string,
              public descripcion: string) {}
}

export class TipoReporte {
  constructor(public codigoTipo: string,
              public descripcion: string,
              public tipo: string) {}
}

export class TipoSolicitud {
  constructor(public codigoTipo: string,
              public tipo: string,
              public descripcion: string) {}
}

export class Actividad {
  constructor(public codigoActividad: string,
              public nombre: string,
              public descripcion: string,
              public fechaInicioEstimada: string,
              public fechaInicioReal: string,
              public fechaFinEstimada: string,
              public fechaFinReal: string,
              public gasto: RegistroPago,
              public cantidadHoras: number,
              public posicion: number,
              public posicionF: number,
              public codActividadPadre: string,
              public actividadesHijas: Actividad [],
              public objetivos: ObjetivoActividad [],
              public proyecto: Proyecto) {}
}

export class Correo {
  constructor(public codigoCorreo: string,
              public correo: string) {}
}

export class Direccion {
  constructor(public codigoDireccion: string,
              public codigoPostal: string,
              public nombre: string,
              public unidad: string,
              public distrito: string,
              public provincia: string,
              public pais: string) {}
}

export class Alcance {
  constructor(public codigoAlcance: string,
              public alcance: string) {}
}

export class ComprobantePago {
  constructor(public numero: string,
              public importe: number,
              public fecha: string,
              public proveedor: string,
              public descripcion: string,
              public moneda: Moneda,
              public tipoComprobante: TipoComprobante,
              public tipoGasto: TipoGasto) {}
}

export class RegistroPago {
  constructor(public maximaRemuneracion: number,
              public nombreMoneda: string,
              public codigoActividad: string,
              public actividad: Actividad[]) {}
}

export class Cliente {
  constructor(public ruc: string,
              public razonSocial: string) {}
}

export class Objetivo {
  constructor(public posicion: number,
              public descripcion: string,
              public posicionPadre: number,
              public posicionF: number,
              public objetivosHijos: Objetivo []) {}
}

export class Reporte {
  public numeroReporte: number;
  public fecha: string;
  public descripcion: string;
  public tipo: TipoReporte;
}
export class ObjetivoActividad {
  constructor(public codigoObjetivoActividad: string,
              public objetivo: string) {}
}

export class Perfil {
  constructor(public codigoPerfil: string,
              public nombre: string,
              public sueldo: number,
              public moneda: Moneda) {}
}
/////////////////////
export class ResumenTrabajador {
  public nombreRol: string;
  public costoHora: number;
  public dni: string;
  public nombre: string;
  public apellidoP: string;
  public apellidoM: string;
  public horasTotales: number;
}

export class RegistroAsistencia extends ResumenTrabajador {
  public codigoActividad: string;
  public horasTrabajadas: number;
  public fecha: string;
}

export class TrabajadorActividad {
  public codigoProyecto: string;
  public dni: string;
  public codigoActividad: string;
}

export class ActaPersona {
  public codigoActa: string;
  public dni: string;
  public fecha: string;
}


export class Persona {
  public dni: string;
  public primerNombre: string;
  public apellidoMaterno: string;
  public apellidoPaterno: string;
  public contrasenia: string;
  public firma: string;
  public rol: string;
  public direccion: Direccion;
  public telefonos: Telefono [];
  public correos: Correo [];
}

export class Practica {
  public codigoPractica: string;
  public descripcion: string;
  public tipoPractica: string;
}

export class PracticaActividad {
  public codigoActividad: string;
  public codigoPractica: string;
}

export class ProyectoContacto {
  public codigoProyecto: string;
  public dni: string;
}

export class ProyectoTrabajador {
  public codigoProyecto: string;
  public dni: string;
  public codigoPerfil: string;
}





export class Trabajador {
  public dni: string;
  public trabajadorPerfil: Perfil [];
}

export class TrabajadorPerfilActividad {
  public codigo: number;
  public fecha: string;
  public cantidadHoras: number;
  public codigoActividad: string;
  public dni: string;
  public codigoPerfil: string;
}

export class Presupuesto {
  constructor(public codigoPresupuesto: string,
              public costoEstimado: number,
              public moneda: Moneda) {}
}

export class Proyecto {
  constructor(public codigoProyecto: string,
              public nombre: string,
              public descripcion: string,
              public estado: string,
              public fechaFinReal: string,
              public fechaFinEstimada: string,
              public fechaInicioReal: string,
              public fechaInicioEstimada: string,
              public presupuesto: Presupuesto,
              public acta: Acta,
              public actividades: Actividad [],
              public reportes: Reporte [],
              public alcances: Alcance [],
              public objetivos: Objetivo []) {}
}
export class RegistroActa{
  constructor(
    public acuerdos: string [],
    public codigoProyecto: string,
  ) {
  }
}

