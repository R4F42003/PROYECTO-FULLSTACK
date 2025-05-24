import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pago, PagoStatus, TypePago } from '../model/Pago';

@Injectable({
  providedIn: 'root'
})
export class PagoService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  /**
   * Subir nuevo pago (con archivo PDF, tipo, fecha, cantidad y código de estudiante).
   * POST /pagos
   */
  subirPago(file: File, cantidad: number, type: TypePago, fecha: string, codigoEstudiante: string): Observable<Pago> {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('cantidad', cantidad.toString());
    formData.append('type', type);
    formData.append('date', fecha); // backend espera "date"
    formData.append('codigoEstudiante', codigoEstudiante);
    return this.http.post<Pago>(`${this.baseUrl}/pagos`, formData);
  }

  /**
   * Descargar archivo PDF de un pago por ID.
   * GET /pagoFile/{pagoId}
   */
  descargarArchivo(pagoId: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/pagoFile/${pagoId}`, { responseType: 'blob' });
  }

  /**
   * Actualizar el estado de un pago.
   * PUT /pagos/{pagoId}/actualizarPago?status=VALIDADO
   */
  actualizarEstado(pagoId: number, status: PagoStatus): Observable<Pago> {
    const params = new HttpParams().set('status', status);
    return this.http.put<Pago>(`${this.baseUrl}/pagos/${pagoId}/actualizarPago`, null, { params });
  }

  /**
   * Listar todos los pagos.
   * GET /pagos
   */
  listarPagos(): Observable<Pago[]> {
    return this.http.get<Pago[]>(`${this.baseUrl}/pagos`);
  }

  /**
   * Obtener un pago por ID.
   * GET /pagos/{id}
   */
  obtenerPagoPorId(id: number): Observable<Pago> {
    return this.http.get<Pago>(`${this.baseUrl}/pagos/${id}`);
  }

  /**
   * Listar pagos por código de estudiante.
   * GET /estudiantes/{codigo}/pagos
   */
  listarPagosPorCodigoEstudiante(codigo: string): Observable<Pago[]> {
    return this.http.get<Pago[]>(`${this.baseUrl}/estudiantes/${codigo}/pagos`);
  }

  /**
   * Listar pagos por estado.
   * GET /pagosPorStatus?status=CREADO
   */
  listarPagosPorStatus(status: PagoStatus): Observable<Pago[]> {
    const params = new HttpParams().set('status', status);
    return this.http.get<Pago[]>(`${this.baseUrl}/pagosPorStatus`, { params });
  }

  /**
   * Listar pagos por tipo.
   * GET /pagos/porTipo?type=EFECTIVO
   */
  listarPagosPorTipo(type: TypePago): Observable<Pago[]> {
    const params = new HttpParams().set('type', type);
    return this.http.get<Pago[]>(`${this.baseUrl}/pagos/porTipo`, { params });
  }
}
