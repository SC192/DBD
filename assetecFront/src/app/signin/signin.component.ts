import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Correo, Direccion, Persona, Telefono} from '../model';
import {ApiService} from '../apiService';
import {Router} from '@angular/router';
import {DataService} from "../services/data.service";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {
  mensaje: string = null;
  constructor(private apiservice: ApiService, private route: Router, private dataService: DataService) { }

  ngOnInit(): void {
  }
  verificar(): void{
    this.apiservice.esCliente(this.dataService.persona).subscribe((respuesta) =>{
      this.dataService.contacto = respuesta;
    });
  }
  /*
  validar(): void{
    this.mensaje = null;
    this.apiservice.validar(this.dataService.persona).subscribe((data) => {
      console.log(data);
      if (data.primerNombre !== ''){
        this.dataService.persona = data;
        this.verificar();
        this.route.navigateByUrl('buscador');
      }
      else{
        this.mensaje = 'DNI o contrasenia incorrecta';
      }
    });
  }
  */

}
