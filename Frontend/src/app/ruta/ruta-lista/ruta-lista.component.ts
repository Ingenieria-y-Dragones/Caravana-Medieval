import {Component, Input} from '@angular/core';
import {RutaService} from '../ruta.service';
import {RutaDto} from '../../dto/ruta-dto';
import {NgForOf} from '@angular/common';
import {RutaVistaComponent} from '../ruta-vista/ruta-vista.component';
import {Router, RouterLink} from '@angular/router';
import {CaravanaDto} from '../../dto/caravana-dto';
import {CaravanaService} from '../../caravana/caravana.service';

@Component({
  selector: 'app-ruta-lista',
  imports: [
    NgForOf,
    RutaVistaComponent,
    RouterLink
  ],
  templateUrl: './ruta-lista.component.html',
  styleUrl: './ruta-lista.component.css'
})
export class RutaListaComponent {
  @Input()
  parametroCaravana:CaravanaDto | undefined;

  @Input()
  parametroRutas:RutaDto[] | undefined;

  @Input()
  selectedRuta: RutaDto | undefined;

  constructor(
    private rutaService: RutaService,
    private caravanaService: CaravanaService,
    private router: Router
  ) { }

  @Input()
  set id(id: number) {
    this.caravanaService.recuperarCaravana(id).subscribe(caravana => this.parametroCaravana = caravana);
    this.rutaService.listarRutasDesdeCiudadDeCaravana(id).subscribe(
      listaRutasDesdeCiudadDeCaravana => this.parametroRutas = listaRutasDesdeCiudadDeCaravana
    );
  }

  seleccionarRuta(rutaSeleccionada: RutaDto) {
    this.selectedRuta = rutaSeleccionada;
  }

  back() {
    this.router.navigate(["/caravana/vista"]);
  }

  viajar(id: number) {
    this.router.navigate(["/caravana/vista"]);
  }
}
