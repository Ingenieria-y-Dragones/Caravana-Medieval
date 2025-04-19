export class RutaDto {
    constructor(
      public id: number,
      public nombre: string,
      public distancia: number,
      public danio: number,
      public peligro: string
      //public ciudadOrigen: string,
      //public ciudadDestino: string
    ){ }
}
