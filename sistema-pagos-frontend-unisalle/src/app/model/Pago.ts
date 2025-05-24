import { Estudiante } from './Estudiante';

// Tipos enumerados
export type TypePago = 'EFECTIVO' | 'CHEQUE' | 'TRANSFERENCIA' | 'DEPOSITO';
export type PagoStatus = 'CREADO' | 'VALIDADO' | 'RECHAZADO';

export interface Pago {
  id?: number;              
  fecha: string;             
  cantidad: number;          
  type: TypePago;            
  status: PagoStatus;        
  file: string;              
  estudiante: Estudiante;    
}