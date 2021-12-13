import { Component, OnInit } from '@angular/core';
import {ApiService} from "../apiService";
import {Router} from "@angular/router";
import {DataService} from "../services/data.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  pagina: string = null;
  constructor(private apiservice: ApiService, private route: Router, public dataService: DataService) { }

  ngOnInit(): void {
  }

  cerrarSesion(): void{
    this.dataService.persona = null;
    this.dataService.contacto = false;
  }
  buscar(): void{
    this.apiservice.traerProyectosUsuario(this.dataService.persona).subscribe((data) => {
      this.dataService.proyectos = data;
    });
  }

}
