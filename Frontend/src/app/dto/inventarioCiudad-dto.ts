import { ProductoDto } from "./producto-dto";

// src/app/dto/inventario-ciudad.dto.ts
export interface InventarioCiudadDto {
    id: number;
    producto: ProductoDto;
    existencias: number;
    factorDemanda: number;
    factorOferta: number;
  }