import { Injectable } from '@angular/core';
import {Actividad, Correo, Direccion, Persona, Proyecto, Telefono} from '../model';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  public contacto: boolean = false;
  public actaGenerada: boolean = false;
  public acciones: string = null;
  public persona: Persona = {
    dni : '54684528',
    primerNombre: null,
    apellidoMaterno: null,
    apellidoPaterno: null,
    contrasenia: null,
    firma: null,
    direccion: null,
    telefonos: null,
    correos: null,
    rol: 'GERENTE DE PROYECTOS'
  };
  public proyecto: Proyecto = null;
  public actividad: Actividad = null;
  reporte: string = null;
  datos: string [] = [];
  gastos: string [] = [];
}
