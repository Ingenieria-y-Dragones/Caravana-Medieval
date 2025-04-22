import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { CiudadService } from '../../ciudad/ciudad.service';
import { InventarioCiudadDto } from '../../dto/inventarioCiudad-dto';
import { ServicioOfrecidoDto } from '../../dto/servicioOfrecido-dto';

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
  idCiudad: number = 1; // O el ID que corresponda
  
  panels = {
    products: true,
    services: true,
    inventory: true
  };

  constructor(private ciudadService: CiudadService) {}

  ngOnInit(): void {
    this.cargarProductosYServicios();
  }

  cargarProductosYServicios(): void {
    // Usar el pipe async o un indicador de carga mientras se esperan los datos
    this.ciudadService.obtenerProductosCiudad(this.idCiudad).subscribe(
      (productos) => {
        console.log('Productos cargados:', productos);
        this.productos = productos;
      },
      (error) => console.error('Error al cargar productos:', error)
    );

    this.ciudadService.obtenerServiciosCiudad(this.idCiudad).subscribe(
      (servicios) => {
        console.log('Servicios cargados:', servicios);
        this.servicios = servicios;
      },
      (error) => console.error('Error al cargar servicios:', error)
    );
  }

  toggle(section: 'products' | 'services' | 'inventory') {
    this.panels[section] = !this.panels[section];
  }
}
