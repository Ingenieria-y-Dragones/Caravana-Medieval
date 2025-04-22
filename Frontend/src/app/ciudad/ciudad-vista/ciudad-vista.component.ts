import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { CiudadService } from '../../ciudad/ciudad.service';
import { InventarioCiudadDto } from '../../dto/inventarioCiudad-dto';
import { ServicioOfrecidoDto } from '../../dto/servicioOfrecido-dto';
import { CiudadDto } from '../../dto/ciudad-dto'; // Nuevo DTO importado

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
  idCiudad: number = 1; // Usa el ID correcto

  panels = {
    products: true,
    services: true,
    inventory: true
  };

  constructor(private ciudadService: CiudadService) {}

  ngOnInit(): void {
    this.obtenerNombreCiudad();
    this.cargarProductosYServicios();
  }

  // Nuevo método para obtener el nombre de la ciudad
  obtenerNombreCiudad(): void {
    this.ciudadService.obtenerCiudad(this.idCiudad).subscribe(
      (ciudad: CiudadDto) => {
        console.log('Ciudad recibida:', ciudad); // ← Añade esto
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

  toggle(section: 'products' | 'services' | 'inventory') {
    this.panels[section] = !this.panels[section];
  }
}
