import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { CaravanaService } from '../../caravana/caravana.service'; // Ajusta la ruta si es necesario

@Component({
  selector: 'app-ciudad-vista',
  standalone: true,
  imports: [CommonModule, HeaderComponent],
  templateUrl: './ciudad-vista.component.html',
  styleUrls: ['./ciudad-vista.component.css']
})
export class CiudadVistaComponent implements OnInit {
  ciudadNombre: string = '';

  panels = {
    products: true,
    services: true,
    inventory: true
  };

  constructor(private caravanaService: CaravanaService) {}

  ngOnInit(): void {
    const idCaravana = 1; // Cambia esto por el id real si es necesario
    this.caravanaService.obtenerEstadoCiudad(idCaravana).subscribe(data => {
      this.ciudadNombre = data.ciudadNombre;
    });
  }

  toggle(section: 'products' | 'services' | 'inventory') {
    this.panels[section] = !this.panels[section];
  }
}
