import { Component, OnInit } from '@angular/core';
import { Pago } from '../../model/Pago';
import { PagoService } from '../../service/pagoService';

@Component({
  selector: 'app-lista-pagos',
  templateUrl: './lista-pagos.component.html',
  styleUrls: ['./lista-pagos.component.css']
})

export class ListaPagosComponent implements OnInit {
  pagos: Pago[] = [];
  pagePagos = 1;

  constructor(private pagoService: PagoService) {}

  ngOnInit(): void {
    this.cargarPagos();
  }

 cargarPagos(): void {
  this.pagoService.listarPagos().subscribe(data => {
    // Ordenar por ID descendente (más reciente primero)
  this.pagos = data.sort((a, b) => {
    const idA = a.id ?? 0;
    const idB = b.id ?? 0;
    return idA - idB; // orden descendente
  });

  });
}

}
