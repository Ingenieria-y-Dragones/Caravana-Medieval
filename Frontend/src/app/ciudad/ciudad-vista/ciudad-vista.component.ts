import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { CaravanaService } from '../../caravana/caravana.service';
import { CiudadService } from '../../ciudad/ciudad.service';
// Importar los DTOs
import { InventarioCiudadDto } from '../../dto/inventarioCiudad-dto';
import { ServicioOfrecidoDto } from '../../dto/servicioOfrecio-dto';

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
  idCiudad: number = 0;

  panels = {
    products: true,
    services: true,
    inventory: true
  };

  constructor(
    private caravanaService: CaravanaService,
    private ciudadService: CiudadService
  ) {}

  ngOnInit(): void {
    const idCaravana = 1; // O el ID que corresponda
    
    // Obtener el estado y nombre de la ciudad
    this.caravanaService.obtenerEstadoCiudad(idCaravana).subscribe(data => {
      this.ciudadNombre = data.ciudadNombre;
      
      // Asumiendo que obtienes también el ID de la ciudad en la respuesta
      // Si no, necesitarás otra forma de obtener el ID de la ciudad actual
      this.idCiudad = data.ciudadId || 1;
      
      // Cargar productos y servicios una vez que tengamos el ID de la ciudad
      this.cargarProductosYServicios();
    });
  }
  
  cargarProductosYServicios(): void {
    // Cargar productos de la ciudad usando CiudadService
    this.ciudadService.obtenerProductosCiudad(this.idCiudad).subscribe(productos => {
      this.productos = productos;
    });
    
    // Cargar servicios de la ciudad usando CiudadService
    this.ciudadService.obtenerServiciosCiudad(this.idCiudad).subscribe(servicios => {
      this.servicios = servicios;
    });
  }

  toggle(section: 'products' | 'services' | 'inventory') {
    this.panels[section] = !this.panels[section];
  }
}
