import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { JsonPipe } from '@angular/common';
import { HeaderComponent } from "../../components/header/header.component";

interface ProductoDto {
  id: number;
  tipo: string;
  nombre: string;
}

interface InventarioCiudadDto {
  id: number;
  producto: ProductoDto;
  cantidad: number;
}

interface InventarioCaravanaDto {
  id: number;
  producto: ProductoDto;
  cantidad: number;
}

interface ServicioOfrecidoDto {
  id: number;
  nombreServicio: string;
  precio: number;
}

@Component({
  selector: 'app-ciudad-vista',
  standalone: true,
  imports: [CommonModule, JsonPipe, HeaderComponent],
  templateUrl: './ciudad-vista.component.html',
  styleUrls: ['./ciudad-vista.component.css']
})
export class CiudadVistaComponent implements OnInit {
  ciudadNombre = 'Valdruna';
  productos: InventarioCiudadDto[] = [];
  servicios: ServicioOfrecidoDto[] = [];
  inventarioCaravana: InventarioCaravanaDto[] = [];
  estado: any = { dinero: 0, salud: 0 };
  panels = { products: true, services: true, inventory: true };

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<InventarioCiudadDto[]>('http://localhost:8080/ciudad/1/inventario')
      .subscribe(data => this.productos = data);

    this.http.get<ServicioOfrecidoDto[]>('http://localhost:8080/ciudad/1/servicios')
      .subscribe(data => this.servicios = data);

    this.http.get<InventarioCaravanaDto[]>('http://localhost:8080/caravana/1/inventario')
      .subscribe(data => this.inventarioCaravana = data);

    this.http.get<any>('http://localhost:8080/caravana/1/estado-ciudad')
      .subscribe(data => {
        this.estado.dinero = data.dinero ?? 0;
        this.estado.salud = data.salud ?? 0;
        this.ciudadNombre = data.ciudadNombre ?? 'Valdruna';
      });
  }

  calcularPrecioCompra(producto: InventarioCiudadDto): number {
    // Ajusta la lógica según tu modelo real
    return 100 / (1 + producto.cantidad);
  }

  calcularPrecioVenta(producto: InventarioCaravanaDto): number {
    // Ajusta la lógica según tu modelo real
    return 80 / (1 + producto.cantidad);
  }

  comprarProducto(producto: InventarioCiudadDto) {
    alert(`Comprar ${producto.producto.nombre}`);
  }

  comprarServicio(servicio: ServicioOfrecidoDto) {
    alert(`Comprar servicio ${servicio.nombreServicio}`);
  }

  venderProducto(producto: InventarioCaravanaDto) {
    alert(`Vender ${producto.producto.nombre}`);
  }

  toggle(panel: 'products' | 'services' | 'inventory') {
    this.panels[panel] = !this.panels[panel];
  }
}
