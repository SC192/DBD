// Aqui se insertan las clases que se tienen en el back
// import {mkdir} from "fs";

export class Moneda {
  constructor(public codigoMoneda: string,
              public nombre: string) {}
}

export class Rol {
  nombrePerfil: string;
  cantidad: number;
  totalHoras: number;
  costoHora: number;
}

export class ActaAcuerdo {
  codigoAcuerdo: string;
  acuerdo: string;
}

export class Acta {
  codigoActa: string;
  fecha: string;
  estado: string;
  acuerdos: ActaAcuerdo [];
}

export class Solicitud {
  codigoSolicitud: string;
  fechaSolicitud: string;
  fechaModificacion: string;
  estado: string;
  solicitante: Persona;
  tipo: TipoSolicitud;
}

export class Telefono {
  codigoTelefono: string;
  numero: string;
  prefijo: string;
}

export class TipoComprobante {
  codigoTipo: string;
  tipo: string;
  descripcion: string;
}
export class TipoGasto {
  codigoTipo: string;
  tipo: string;
  descripcion: string;
}

export class TipoReporte {
  codigoTipo: string;
  descripcion: string;
  tipo: string;
}

export class TipoSolicitud {
  codigoTipo: string;
  tipo: string;
  descripcion: string;
}

export class Actividad {
  codigoActividad: string;
  nombre: string;
  descripcion: string;
  fechaInicioEstimada: string;
  fechaInicioReal: string;
  fechaFinEstimada: string;
  fechaFinReal: string;
  gasto: Gasto;
  cantidadHoras: number;
  posicion: number;
  actividadesHijas: Actividad [];
  objetivos: ObjetivoActividad [];
}

export class Correo {
  codigoCorreo: string;
  correo: string;
}

export class Direccion {
  codigoDireccion: string;
  codigoPostal: string;
  nombre: string;
  unidad: string;
  distrito: string;
  provincia: string;
  pais: string;
}

export class Alcance {
  codigoAlcance: string;
  alcance: string;
}

export class ComprobantePago {
  numero: string;
  importe: number;
  fecha: string;
  proveedor: string;
  descripcion: string;
  moneda: Moneda;
  tipoComprobante: TipoComprobante;
  tipoGasto: TipoGasto;
}

export class Gasto {
  codigoGasto: string;
  maximaRemuneracion: number;
  moneda: Moneda;
  comprobantes: ComprobantePago [];
}

export class Cliente {
  ruc: string;
  razonSocial: string;
  contactos: Persona [];
}

export class Objetivo {
  posicion: number;
  descripcion: string;
  objetivosHijos: Objetivo;
}

export class Reporte {
  numeroReporte: number;
  fecha: string;
  descripcion: string;
  tipo: TipoReporte;
}
export class ObjetivoActividad {
  codigoObjetivoActividad: string;
  objetivo: string;
}

export class Perfil {
  codigoPerfil: string;
  nombre: string;
  sueldo: number;
  moneda: Moneda;
}
/////////////////////
export class ResumenTrabajador {
  nombreRol: string;
  costoHora: number;
  dni: string;
  nombre: string;
  apellidoP: string;
  apellidoM: string;
  horasTotales: number;
}

export class RegistroAsistencia extends ResumenTrabajador {
  codigoActividad: string;
  horasTrabajadas: number;
  fecha: string;
}

export class TrabajadorActividad {
  codigoProyecto: string;
  dni: string;
  codigoActividad: string;
}

export class ActaPersona {
  codigoActa: string;
  dni: string;
  fecha: string;
}


export class Persona {
  dni: string;
  primerNombre: string;
  apellidoMaterno: string;
  apellidoPaterno: string;
  contrasenia: string;
  firma: string;
  direccion: Direccion;
  telefonos: Telefono [];
  correos: Correo [];
}

export class Practica {
  codigoPractica: string;
  descripcion: string;
  tipoPractica: string;
}

export class PracticaActividad {
  codigoActividad: string;
  codigoPractica: string;
}

export class ProyectoContacto {
  codigoProyecto: string;
  dni: string;
}

export class ProyectoTrabajador {
  codigoProyecto: string;
  dni: string;
  codigoPerfil: string;
}





export class Trabajador {
  dni: string;
  trabajadorPerfil: Perfil [];
}

export class TrabajadorPerfilActividad {
  codigo: number;
  fecha: string;
  cantidadHoras: number;
  codigoActividad: string;
  dni: string;
  codigoPerfil: string;
}

export class Presupuesto {
  constructor(public codigoPresupuesto: string,
              public costoEstimado: number,
              public moneda: Moneda) {}
}

export class Proyecto {
  constructor(public codigoProyecto: string,
              public nombre: string,
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
