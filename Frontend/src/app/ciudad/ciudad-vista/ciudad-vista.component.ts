import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ciudad-vista',
  imports: [CommonModule],
  templateUrl: './ciudad-vista.component.html',
  styleUrls: ['./ciudad-vista.component.css'] // Corrige `styleUrl` a `styleUrls`
})
export class CiudadVistaComponent {
  // Define la propiedad panels
  panels = {
    products: true,
    services: true,
    inventory: true
  };

  // Método para alternar las propiedades de panels
  toggle(section: 'products' | 'services' | 'inventory') {
    this.panels[section] = !this.panels[section];
  }
}