import { Component } from '@angular/core';
import { PagoService } from '../../service/pagoService';

@Component({
  selector: 'app-pago-archivo',
  templateUrl: './pago-archivo.component.html',
  styleUrls:['./pago-archivo.component.css']
})
export class PagoArchivoComponent {
  idPago!: number;

  constructor(private pagoService: PagoService) {}

  descargar() {
    this.pagoService.descargarArchivo(this.idPago).subscribe(blob => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'archivo.pdf';
      a.click();
    });
  }
}
