import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { ListaPagosComponent } from '../lista-pagos/lista-pagos.component';

@Component({
  selector: 'app-pago-dashboard',
  templateUrl: './pago-dashboard.component.html',
  styleUrls:['./pago-dashboard.component.css']
})

export class PagoDashboardComponent implements AfterViewInit {
  @ViewChild(ListaPagosComponent) listaPagosComp!: ListaPagosComponent;

  ngAfterViewInit(): void {
    // Esperamos a que esté renderizado el componente
    setTimeout(() => this.listaPagosComp?.cargarPagos(), 0);
  }

  refrescarPagos() {
    this.listaPagosComp?.cargarPagos();
  }
}