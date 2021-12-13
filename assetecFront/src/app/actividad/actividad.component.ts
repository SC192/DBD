import { Component, OnInit } from '@angular/core';
import { ApiService} from "../apiService";
import { Proyecto} from "../model";
import { Actividad} from "../model";
import { Perfil} from "../model";
import { Trabajador} from "../model";
import { TrabajadorActividad} from "../model";
import { TrabajadorPerfilActividad} from "../model";
import { ProyectoTrabajador} from "../model";
import { Cliente} from "../model";
import { Gasto} from "../model";
import { Moneda} from "../model";
import { ObjetivoActividad} from "../model";
import { Presupuesto} from "../model";
import { ComprobantePago} from "../model";
import { TipoComprobante} from "../model";
import { PracticaActividad} from "../model";
import { TipoGasto} from "../model";
import { Practica} from "../model";
import { ProyectoContacto} from "../model";

@Component({
  selector: 'app-actividad',
  templateUrl: './actividad.component.html',
  styleUrls: ['./actividad.component.scss']
})
export class ActividadComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
