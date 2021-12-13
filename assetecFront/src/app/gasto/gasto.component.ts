import { Component, OnInit } from '@angular/core';
import {ApiService} from '../apiService';
import {Router} from '@angular/router';
import {DataService} from '../services/data.service';
import {RegistroPago} from '../model';

@Component({
  selector: 'app-gasto',
  templateUrl: './gasto.component.html',
  styleUrls: ['./gasto.component.scss']
})
export class GastoComponent implements OnInit {
  remuneracion: RegistroPago;
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
  crearGasto(): void{
    this.apiservice.registrarPago(this.remuneracion).subscribe((data) => {
    });
  }
  buscar(): void{
    this.apiservice.traerProyectosUsuario(this.dataService.persona).subscribe((data) => {
      this.dataService.proyectos = data;
    });
  }
}
