import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { CiudadService } from '../../ciudad/ciudad.service';
import { CaravanaService } from '../../caravana/caravana.service'; // Nuevo servicio importado
import { InventarioCiudadDto } from '../../dto/inventarioCiudad-dto';
import { ServicioOfrecidoDto } from '../../dto/servicioOfrecido-dto';
import { CiudadDto } from '../../dto/ciudad-dto';
import { InventarioCaravanaDto } from '../../dto/inventario-caravana-dto'; // Nuevo DTO importado

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
  inventarioCaravana: InventarioCaravanaDto[] = []; // Nueva propiedad
  idCiudad: number = 1; // ID ciudad
  idCaravana: number = 1; // ID de la caravana del jugador

  panels = {
    products: true,
    services: true,
    inventory: true
  };

  constructor(
    private ciudadService: CiudadService,
    private caravanaService: CaravanaService // Nuevo servicio inyectado
  ) {}

  ngOnInit(): void {
    this.obtenerNombreCiudad();
    this.cargarProductosYServicios();
    this.cargarInventarioCaravana(); // Nueva llamada
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

  // Nuevo método para cargar el inventario
  cargarInventarioCaravana(): void {
    this.caravanaService.obtenerInventarioCaravana(this.idCaravana).subscribe(
      inventario => this.inventarioCaravana = inventario,
      error => console.error('Error cargando inventario:', error)
    );
  }

  toggle(section: 'products' | 'services' | 'inventory') {
    this.panels[section] = !this.panels[section];
  }
}
