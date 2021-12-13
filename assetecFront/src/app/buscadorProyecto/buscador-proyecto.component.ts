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
  proyectos: Proyecto [] = [];
  filtro: string = '*';
  constructor(private apiservice: ApiService, public dataService: DataService) { }
  ngOnInit(): void {
  }

  buscar(): void{
    this.apiservice.treaerProyectosUsuario(this.dataService.persona).subscribe((data) => {
      this.proyectos = data.listaProyecto;
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
      correos: null
    };
    this.dataService.contacto = false;
  }
}
