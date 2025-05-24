import { Component, EventEmitter, Output } from '@angular/core';
import { PagoService } from '../../service/pagoService';
import { TypePago } from '../../model/Pago';

@Component({
  selector: 'app-pago-crear',
  templateUrl: './pago-crear.component.html',
  styleUrls:['./pago-crear.component.css']
})

export class PagoCrearComponent {
  @Output() pagoGuardado = new EventEmitter<void>();

  archivo!: File;
  cantidad!: number;
  tipo: TypePago = 'EFECTIVO';
  fecha!: string;
  codigoEstudiante!: string;

  constructor(private pagoService: PagoService) {}

  onFileChange(event: any) {
    this.archivo = event.target.files[0];
  }

  enviarPago() {
    if (!this.archivo || !this.fecha || !this.codigoEstudiante || !this.cantidad) {
      alert('Completa todos los campos');
      return;
    }

    this.pagoService.subirPago(this.archivo, this.cantidad, this.tipo, this.fecha, this.codigoEstudiante)
      .subscribe({
        next: () => {
          alert('Pago registrado');
          this.pagoGuardado.emit();
          this.limpiarFormulario();
        },
        error: () => {
          alert('Error al registrar el pago');
        }
      });
  }

  limpiarFormulario() {
    this.archivo = undefined!;
    this.cantidad = 0;
    this.tipo = 'EFECTIVO';
    this.fecha = '';
    this.codigoEstudiante = '';
  }
}