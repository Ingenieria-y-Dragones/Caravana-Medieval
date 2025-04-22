import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { CiudadService } from '../../ciudad/ciudad.service';
import { CaravanaService } from '../../caravana/caravana.service';
import { InventarioCiudadDto } from '../../dto/inventarioCiudad-dto';
import { ServicioOfrecidoDto } from '../../dto/servicioOfrecido-dto';
import { CiudadDto } from '../../dto/ciudad-dto';
import { InventarioCaravanaDto } from '../../dto/inventario-caravana-dto';

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
  estado: any = {}; // Para almacenar dinero y salud

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
    this.obtenerNombreCiudad();
    this.cargarProductosYServicios();
    this.cargarInventarioCaravana();
    this.cargarEstadoCaravana(); // Obtener estado inicial (dinero, salud)
  }

  obtenerNombreCiudad(): void {
    this.ciudadService.obtenerCiudad(this.idCiudad).subscribe(
      (ciudad: CiudadDto) => {
        this.ciudadNombre = ciudad.nombre;
      },
      (error) => console.error('Error al cargar ciudad:', error)
    );
  }

  cargarProductosYServicios(): void {
    this.ciudadService.obtenerProductosCiudad(this.idCiudad).subscribe(
      productos => this.productos = productos,
      error => console.error('Error productos:', error)
    );
    this.ciudadService.obtenerServiciosCiudad(this.idCiudad).subscribe(
      servicios => this.servicios = servicios,
      error => console.error('Error servicios:', error)
    );
  }

  cargarInventarioCaravana(): void {
    this.caravanaService.obtenerInventarioCaravana(this.idCaravana).subscribe(
      inventario => this.inventarioCaravana = inventario,
      error => console.error('Error cargando inventario:', error)
    );
  }

  cargarEstadoCaravana(): void {
    this.caravanaService.obtenerEstadoCiudad(this.idCaravana).subscribe(
      data => {
        this.estado = data;
      },
      error => console.error('Error al cargar estado:', error)
    );
  }

  toggle(section: 'products' | 'services' | 'inventory') {
    this.panels[section] = !this.panels[section];
  }

  // Nuevos métodos para las transacciones
  comprarProducto(producto: InventarioCiudadDto): void {
    if (this.estado.dinero >= producto.precio && producto.cantidad > 0) {
      this.caravanaService.comprarProducto(this.idCiudad, this.idCaravana, producto.id, 1).subscribe(
        () => {
          // Actualizamos listas y estado
          this.cargarProductosYServicios();
          this.cargarInventarioCaravana();
          // El estado se actualizará automáticamente en la nav bar gracias al sistema de notificaciones
        },
        error => console.error('Error al comprar producto:', error)
      );
    } else {
      console.error('No tienes suficientes monedas o no hay stock disponible');
    }
  }

  comprarServicio(servicio: ServicioOfrecidoDto): void {
    if (this.estado.dinero >= servicio.precio) {
      this.caravanaService.comprarServicio(this.idCiudad, this.idCaravana, servicio.id).subscribe(
        () => {
          // El sistema de notificaciones actualizará la nav bar
        },
        error => console.error('Error al comprar servicio:', error)
      );
    } else {
      console.error('No tienes suficientes monedas para este servicio');
    }
  }

  venderProducto(producto: InventarioCaravanaDto): void {
    if (producto.cantidad > 0) {
      this.caravanaService.venderProducto(this.idCiudad, this.idCaravana, producto.id, 1).subscribe(
        () => {
          // Actualizamos listas y estado
          this.cargarProductosYServicios();
          this.cargarInventarioCaravana();
          // El sistema de notificaciones actualizará la nav bar
        },
        error => console.error('Error al vender producto:', error)
      );
    } else {
      console.error('No tienes unidades disponibles para vender');
    }
  }

  calcularPrecioVenta(producto: InventarioCaravanaDto): number {
    // Obtener los datos necesarios de la ciudad actual
    const inventarioCiudad = this.productos.find(p => p.idProducto === producto.id);
    
    if (!inventarioCiudad) return 0;
    
    // Implementar la fórmula: PV = FD/(1+S)
    const factorDemanda = inventarioCiudad.factorDemanda || 1.0;
    const stockCiudad = inventarioCiudad.cantidad || 0;
    
    return factorDemanda / (1 + stockCiudad);
  }

}
