import { Component, OnInit } from '@angular/core';
import {ApiService} from '../apiService';
import {DataService} from '../services/data.service';
import {Rol} from '../model';

@Component({
  selector: 'app-proyecto',
  templateUrl: './proyecto.component.html',
  styleUrls: ['./proyecto.component.scss']
})
export class ProyectoComponent implements OnInit {
  lista: string = null;
  i: number = 0;
  j: number = 0;
  m: number = 0;
  costoTotal: number;
  roles: Rol [] = [];

  constructor(private apiservice: ApiService, public dataService: DataService) { }

  ngOnInit(): void {
  }
  cerrarSesion(): void{
    this.dataService.persona = {
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
    this.dataService.contacto = false;
  }
  listaActividades(): void{
    this.apiservice.obtenerActividadesProyecto(this.dataService.proyecto).subscribe((data) => {
      this.dataService.proyecto.actividades = data;
    });
  }
  infoActividad(index: number): void{
    this.dataService.actividad = this.dataService.proyecto.actividades[index];
  }
  listaRoles(): void{
    this.apiservice.obtenerListaRolesProyecto(this.dataService.proyecto).subscribe((data) => {
      this.roles = data;
    });
  }
  // no funciona
  calcular(): void{
    this.costoTotal = 0;
    for (this.i = 0; this.i < this.roles.length; this.i++){
      this.roles[this.i].costoPerfil = this.roles[this.i].totalHoras * this.roles[this.i].costoHora;
      this.costoTotal = this.costoTotal + this.roles[this.i].costoPerfil;
    }
  }
  // no funciona
  asociarActividades(): void{
    for (this.i = 0; this.i < this.dataService.proyecto.actividades.length; this.i++){
      if (this.dataService.proyecto.actividades[this.i].codActividadPadre != null) {
        this.j = 0;
        while (this.j < this.dataService.proyecto.actividades.length) {
          if (this.dataService.proyecto.actividades[this.j].codigoActividad == this.dataService.proyecto.actividades[this.i].codActividadPadre) {
              this.dataService.proyecto.actividades[this.i].posicionF = this.dataService.proyecto.actividades[this.j].posicion * 10 + this.dataService.proyecto.actividades[this.i].posicion;
            this.m = this.dataService.proyecto.actividades[this.j].actividadesHijas.length;
            this.dataService.proyecto.actividades[this.j].actividadesHijas[this.m] = this.dataService.proyecto.actividades[this.i];
            this.j = this.dataService.proyecto.actividades.length;
          }
          else {
            this.j++;
          }
        }
      }
      else{
        this.dataService.proyecto.actividades[this.i].posicionF = this.dataService.proyecto.actividades[this.i].posicion;
      }
    }
  }
  //no funciona no trae nada
  reporteProyecto(): void{
    this.apiservice.reporteFechasProyecto(this.dataService.proyecto).subscribe((data) => {
      this.dataService.reporte = data;
    });
  }
  reporteActividades(): void{
    this.apiservice.reporteFechasActividadesProyecto(this.dataService.proyecto).subscribe((data) => {
      this.dataService.datos = data;
    });
  }
  reporteGastos(): void{
    this.apiservice.reporteGastosActividad(this.dataService.proyecto).subscribe((data) => {
      this.dataService.gastos = data;
    });
  }
}
