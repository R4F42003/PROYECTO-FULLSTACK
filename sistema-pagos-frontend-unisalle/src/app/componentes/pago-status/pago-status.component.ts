import { Component, Output, EventEmitter } from '@angular/core';
import { PagoService } from '../../service/pagoService';
import { PagoStatus } from '../../model/Pago';

@Component({
  selector: 'app-pago-status',
  templateUrl: './pago-status.component.html',
  styleUrls:['./pago-status.component.css']
})

export class PagoStatusComponent {
  @Output() pagoActualizado = new EventEmitter<void>();

  idPago!: number;
  status: PagoStatus = 'VALIDADO';

  constructor(private pagoService: PagoService) {}

  actualizar() {
    if (!this.idPago) {
      alert('Ingresa un ID válido');
      return;
    }

    this.pagoService.actualizarEstado(this.idPago, this.status).subscribe({
      next: () => {
        alert('Estado actualizado');
        this.pagoActualizado.emit();
      },
      error: () => {
        alert('Error al actualizar estado');
      }
    });
  }
}