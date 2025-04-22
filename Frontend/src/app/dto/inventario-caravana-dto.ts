export interface InventarioCaravanaDto {
nombre: any;
    id: number;
    producto: {
      id: number;
      nombre: string;
      tipo: string;
    };
    cantidad: number;
  }
  