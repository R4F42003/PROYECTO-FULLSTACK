import { Component, OnInit } from '@angular/core';
import { Estudiante } from '../../model/Estudiante';
import { EstudianteService } from '../../service/estudianteService';

@Component({
  selector: 'app-lista-estudiantes',
  templateUrl: './lista-estudiantes.component.html',
  styleUrls: ['./lista-estudiantes.component.css']
})
export class ListaEstudiantesComponent implements OnInit {
  estudiantes: Estudiante[] = [];

  pageEstudiantes = 1;

  constructor(private estudianteService: EstudianteService) {}

  ngOnInit(): void {
    this.estudianteService.listarEstudiantes().subscribe(data => {
      this.estudiantes = data;
    });
  }

}