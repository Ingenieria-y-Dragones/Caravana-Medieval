import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'app-ciudad-vista',
  standalone: true,
  imports: [CommonModule, HeaderComponent], // Importa HeaderComponent aquí
  templateUrl: './ciudad-vista.component.html',
  styleUrls: ['./ciudad-vista.component.css']
})
export class CiudadVistaComponent {
  panels = {
    products: true,
    services: true,
    inventory: true
  };

  toggle(section: 'products' | 'services' | 'inventory') {
    this.panels[section] = !this.panels[section];
  }
}