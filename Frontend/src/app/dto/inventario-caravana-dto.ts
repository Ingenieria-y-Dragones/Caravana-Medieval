export interface InventarioCaravanaDto {
    id: number;
    producto: {
      id: number;
      nombre: string;
      tipo: string;
    };
    cantidad: number;
  }
  