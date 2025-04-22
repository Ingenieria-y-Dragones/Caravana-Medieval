import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { CiudadService } from '../../ciudad/ciudad.service';
import { CaravanaService } from '../../caravana/caravana.service';
import { InventarioCiudadDto } from '../../dto/inventarioCiudad-dto';
import { ServicioOfrecidoDto } from '../../dto/servicioOfrecido-dto';
import { CiudadDto } from '../../dto/ciudad-dto';
import { InventarioCaravanaDto } from '../../dto/inventario-caravana-dto';
import { CaravanaDto } from '../../dto/caravana-dto';

@Component({
  selector: 'app-ciudad-vista',
  standalone: true,
  imports: [CommonModule, HeaderComponent],
  templateUrl: './ciudad-vista.component.html',
  styleUrls: ['./ciudad-vista.component.css']
})
export class CiudadVistaComponent implements OnInit {
  ciudadNombre: string = '';
  productos: InventarioCiudadDto[] = [];
  servicios: ServicioOfrecidoDto[] = [];
  inventarioCaravana: InventarioCaravanaDto[] = [];
  idCiudad: number = 1;
  idCaravana: number = 1;
  dinero: number = 0;

  panels = {
    products: true,
    services: true,
    inventory: true
  };

  constructor(
    private ciudadService: CiudadService,
    private caravanaService: CaravanaService
  ) {}

  ngOnInit(): void {
    this.cargarDatosIniciales();
  }

  private cargarDatosIniciales(): void {
    this.obtenerNombreCiudad();
    this.cargarProductosYServicios();
    this.cargarInventarioCaravana();
    this.actualizarDineroCaravana();
  }

  private obtenerNombreCiudad(): void {
    this.ciudadService.obtenerCiudad(this.idCiudad).subscribe({
      next: (ciudad: CiudadDto) => this.ciudadNombre = ciudad.nombre,
      error: (err) => this.mostrarError('Error al cargar ciudad', err)
    });
  }

  private cargarProductosYServicios(): void {
    this.ciudadService.obtenerProductosCiudad(this.idCiudad).subscribe({
      next: (productos) => this.productos = productos,
      error: (err) => this.mostrarError('Error cargando productos', err)
    });

    this.ciudadService.obtenerServiciosCiudad(this.idCiudad).subscribe({
      next: (servicios) => this.servicios = servicios,
      error: (err) => this.mostrarError('Error cargando servicios', err)
    });
  }

  private cargarInventarioCaravana(): void {
    this.caravanaService.obtenerInventarioCaravana(this.idCaravana).subscribe({
      next: (inventario) => this.inventarioCaravana = inventario,
      error: (err) => this.mostrarError('Error cargando inventario', err)
    });
  }

  private actualizarDineroCaravana(): void {
    this.caravanaService.recuperarCaravana(this.idCaravana).subscribe({
      next: (caravana: CaravanaDto) => this.dinero = caravana.dinero,
      error: (err) => this.mostrarError('Error actualizando dinero', err)
    });
  }

  comprarProducto(producto: InventarioCiudadDto): void {
    const costo = producto.factorOferta * 100;

    this.caravanaService.comprarProducto(this.idCaravana, producto.producto.id, 1).subscribe({
      next: (response: CaravanaDto) => {
        this.actualizarDatosPostCompra(response);
        alert(`¡Compra exitosa de ${producto.producto.nombre}!`);
      },
      error: (err) => {
        this.mostrarError('Error en compra', err);
      }
    });
  }

  comprarServicio(servicio: ServicioOfrecidoDto): void {
    this.caravanaService.comprarServicio(this.idCaravana, servicio.id).subscribe({
      next: (response: CaravanaDto) => {
        this.actualizarDatosPostCompra(response);
        alert(`¡Servicio ${servicio.servicio.nombre} contratado!`);
      },
      error: (err) => {
        this.mostrarError('Error en servicio', err);
      }
    });
  }

  private actualizarDatosPostCompra(caravana: CaravanaDto): void {
    this.dinero = caravana.dinero;
    this.cargarProductosYServicios();
    this.cargarInventarioCaravana();
  }

  private mostrarError(contexto: string, error: any): void {
    console.error(`${contexto}:`, error);
    const mensaje = error.error?.message || error.message || 'Error desconocido';
    alert(`${contexto}: ${mensaje}`);
  }

  toggle(section: 'products' | 'services' | 'inventory'): void {
    this.panels[section] = !this.panels[section];
  }
}
