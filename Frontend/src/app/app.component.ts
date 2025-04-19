import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {RutaListaComponent} from './ruta/ruta-lista/ruta-lista.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RutaListaComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Caravana';
}
