import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError} from 'rxjs/operators';
import {Actividad, Alcance, Objetivo, Persona, Proyecto, RegistroActa, Rol} from './model';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  baseurl = 'http://127.0.0.1:8080/';
  // TODO: Falta averiguar si es el mismo o cambio la base url.
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json;charset=utf-8'
    })
  };

  errorHandl(error: any) {
    let mensajeError = '';
    if (error.error instanceof ErrorEvent){
      mensajeError = error.error.message;
    } else {
      mensajeError = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(mensajeError);
    return throwError(mensajeError);
  }

  constructor(private http: HttpClient) {  }

  traerProyectosUsuario(data: Persona): Observable<Proyecto[]> {
    return this.http.post<Proyecto[]>(this.baseurl + 'traer-proyectos-usuario', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  esCliente(data: Persona): Observable<boolean> {
    return this.http.post<boolean>(this.baseurl + 'trabajador', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  obtenerActividadesProyecto(data: Proyecto): Observable<Actividad[]> {
    return this.http.post<Actividad[]>(this.baseurl + 'obtener-actividades-proyecto', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  obtenerListaRolesProyecto(data: Proyecto): Observable<Rol[]> {
    return this.http.post<Rol[]>(this.baseurl + 'obtener-roles-proyecto', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  obtenerAlcancesProyecto(data: Proyecto): Observable<Alcance[]> {
    return this.http.post<Alcance[]>(this.baseurl + 'obtener-alcances-proyecto', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  obtenerObjetivosProyecto(data: Proyecto): Observable<Objetivo[]> {
    return this.http.post<Objetivo[]>(this.baseurl + 'obtener-objetivos-proyecto', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  generarCierre(data: Proyecto): Observable<void> {
    return this.http.post<void>(this.baseurl + 'generar-cierre', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  reporteFechasProyecto(data: Proyecto): Observable<string> {
    return this.http.post<string>(this.baseurl + 'reporte-fechas-proyecto', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  reporteFechasActividadesProyecto(data: Proyecto): Observable<string[]> {
    return this.http.post<string[]>(this.baseurl + 'reporte-fechas-actividad-proyecto', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  crearActa(data: RegistroActa): Observable<void> {
    return this.http.post<void>(this.baseurl + 'crear-acta', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
  reporteGastosActividad(data: Proyecto): Observable<string[]> {
    return this.http.post<string[]>(this.baseurl + 'reporte-gastos-actividad', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl)
      );
  }
}
