import {Component, OnInit, Output} from '@angular/core';
import {Correo, Direccion, Persona, Proyecto, Telefono} from '../model';
import {ApiService} from '../apiService';
import {DataService} from '../services/data.service';

@Component({
  selector: 'app-buscador-proyecto',
  templateUrl: './buscador-proyecto.component.html',
  styleUrls: ['./buscador-proyecto.component.scss']
})
export class BuscadorProyectoComponent implements OnInit {
  proyectos: Proyecto [];
  filtro: string = '*';
  i: number = 0;
  j: number = 0;
  m: number = 0;
  constructor(private apiservice: ApiService, public dataService: DataService) { }
  ngOnInit(): void {
  }

  buscar(): void{
    this.apiservice.traerProyectosUsuario(this.dataService.persona).subscribe((data) => {
      this.proyectos = data;
    });
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
  infoProyecto(index: number): void {
    this.dataService.proyecto = this.proyectos[index];
  }
  listaAlcances(): void{
    this.apiservice.obtenerAlcancesProyecto(this.dataService.proyecto).subscribe((data) => {
      this.dataService.proyecto.alcances = data;
    });
  }
  listaObjetivos(): void{
    this.apiservice.obtenerObjetivosProyecto(this.dataService.proyecto).subscribe((data) => {
      this.dataService.proyecto.objetivos = data;
    });
  }
  // No funciona
  asociarObjetivos(): void{
    for (this.i = 0; this.i < this.dataService.proyecto.objetivos.length; this.i++){
      if (this.dataService.proyecto.objetivos[this.i].posicionPadre > 0) {
        this.j = 0;
        while (this.j < this.dataService.proyecto.objetivos.length) {
          if (this.dataService.proyecto.objetivos[this.j].posicion == this.dataService.proyecto.objetivos[this.i].posicionPadre) {
            this.dataService.proyecto.objetivos[this.i].posicionF = this.dataService.proyecto.objetivos[this.j].posicion * 10 + this.dataService.proyecto.objetivos[this.i].posicion;
            this.m = this.dataService.proyecto.objetivos[this.j].objetivosHijos.length;
            this.dataService.proyecto.objetivos[this.j].objetivosHijos[this.m] = this.dataService.proyecto.objetivos[this.i];
            this.j = this.dataService.proyecto.objetivos.length;
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
}
