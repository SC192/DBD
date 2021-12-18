import { Component, OnInit } from '@angular/core';
import { ApiService } from '../apiService';
import {Acta, RegistroActa} from '../model';
import { Persona } from '../model';
import { Perfil } from '../model';
import { Proyecto } from '../model';
import {DataService} from '../services/data.service';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-acta',
  templateUrl: './acta.component.html',
  styleUrls: ['./acta.component.scss']
})
export class ActaComponent implements OnInit {
  listaAcuerdos: string [] = [];
  acuerdo: string = null;
  acta: RegistroActa = new RegistroActa([], '');
  i: number = 0;
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
  enviarActa(): void{
    this.apiservice.crearActa(this.acta).subscribe(() => {
      this.acta.codigoProyecto = this.dataService.proyecto.codigoProyecto;
      this.acta.acuerdos = this.listaAcuerdos
      this.dataService.actaGenerada = true;
    });
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'El acta ha sido enviada',
      showConfirmButton: false,
      timer: 1500
    })
  }
  eliminarAcuerdo(index: number): void{
    for (this.i = index; this.i < this.listaAcuerdos.length; this.i++){
      this.listaAcuerdos[this.i] = this.listaAcuerdos[this.i + 1];
    }
    this.listaAcuerdos.length = this.listaAcuerdos.length - 1;
  }
  buscar(): void{
    this.apiservice.traerProyectosUsuario(this.dataService.persona).subscribe((data) => {
      this.dataService.proyectos = data;
    });
  }
}
