import { Component, OnInit } from '@angular/core';
import { ApiService} from '../apiService';
import {Router} from '@angular/router';
import {DataService} from '../services/data.service';
import {RegistroPago, Rol} from '../model';

@Component({
  selector: 'app-actividad',
  templateUrl: './actividad.component.html',
  styleUrls: ['./actividad.component.scss']
})
export class ActividadComponent implements OnInit {
  costoTotal: number;
  roles: Rol [] = [];
  i: number = 0;
  constructor(private apiservice: ApiService, private route: Router, public dataService: DataService) { }

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
  buscar(): void{
    this.apiservice.traerProyectosUsuario(this.dataService.persona).subscribe((data) => {
      this.dataService.proyectos = data;
    });
  }
  listaRoles(): void{
    this.apiservice.obtenerListaRolesActividad(this.dataService.actividad).subscribe((data) => {
      this.roles = data;
    });
  }
  completar(): void {
    this.apiservice.completarActividad(this.dataService.actividad).subscribe((data) => {
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
}
