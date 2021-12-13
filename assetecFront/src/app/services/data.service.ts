import { Injectable } from '@angular/core';
import {Correo, Direccion, Persona, Proyecto, Telefono} from '../model';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  public contacto: boolean = false;
  public persona: Persona = {
    dni : '54684528',
    primerNombre: null,
    apellidoMaterno: null,
    apellidoPaterno: null,
    contrasenia: null,
    firma: null,
    direccion: null,
    telefonos: null,
    correos: null
  };
  public proyecto: Proyecto = null;
}
