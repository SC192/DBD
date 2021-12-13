import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError} from 'rxjs/operators';
import {BusquedaProyectoResponse, Persona, Proyecto} from './model';


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

  treaerProyectosUsuario(data: Persona): Observable<BusquedaProyectoResponse> {
    return this.http.post<BusquedaProyectoResponse>(this.baseurl + 'traer-proyectos-usuario', data, this.httpOptions)
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
}
