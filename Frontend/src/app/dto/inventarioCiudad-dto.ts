export interface InventarioCiudadDto {
  id: number;
  producto: {
    id: number;
    nombre: string;
    tipo: string;
  };
  existencias: number;
  factorDemanda: number;
  factorOferta: number;
}