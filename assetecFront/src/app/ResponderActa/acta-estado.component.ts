import { Component, OnInit } from '@angular/core';
import {ApiService} from '../apiService';
import {DataService} from '../services/data.service';

@Component({
  selector: 'app-acta-estado',
  templateUrl: './acta-estado.component.html',
  styleUrls: ['./acta-estado.component.scss']
})
export class ActaEstadoComponent implements OnInit {

  constructor(private apiservice: ApiService, public dataService: DataService) { }

  ngOnInit(): void {
  }

}
