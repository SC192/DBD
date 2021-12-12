import { Component, OnInit } from '@angular/core';
import {ApiService} from "../apiService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  pagina: string = null;
  constructor(private apiservice: ApiService, private route: Router) { }

  ngOnInit(): void {
  }
  cambiar(): void{
    if (this.pagina == 'proyecto'){
      this.route.navigateByUrl('buscadorProyetco');
    }
    else if (this.pagina == 'solicitudContacto'){
      this.route.navigateByUrl('solicitudContacto');
    }
    else if (this.pagina == 'solicitudTrabajador'){
      this.route.navigateByUrl('solicitudTrabajador');
    }
    else if (this.pagina == 'signin'){
      this.route.navigateByUrl('signin');
    }
    else if (this.pagina == 'perfil'){
      this.route.navigateByUrl('perfil');
    }
  }


}
