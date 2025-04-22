export interface ServicioOfrecidoDto {
  id: number;
  servicio: {
    id: number;
    nombre: string;
    tipo: string;
  };
  precio: number;
}