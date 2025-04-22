import { ServicioDto } from "./servicio-dto";

// src/app/dto/servicio-ofrecido.dto.ts
export interface ServicioOfrecidoDto {
    id: number;
    servicio: ServicioDto;
    precio: number;
  }