import { Component, OnInit } from '@angular/core';
import { ApiService } from '../apiService';
import { Acta } from '../model';

@Component({
  selector: 'app-acta',
  templateUrl: './acta.component.html',
  styleUrls: ['./acta.component.scss']
})
export class ActaComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
