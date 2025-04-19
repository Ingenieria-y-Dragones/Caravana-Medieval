import { Routes } from '@angular/router';
import {RutaListaComponent} from './ruta/ruta-lista/ruta-lista.component';
import {RutaVistaComponent} from './ruta/ruta-vista/ruta-vista.component';
import {RutaEditarComponent} from './ruta/ruta-editar/ruta-editar.component';
import {CaravanaVistaComponent} from './caravana/caravana-vista/caravana-vista.component';
import { HomeComponent } from './pages/home/home.component';
import { CiudadComponent } from './pages/ciudad/ciudad.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'ciudad', component: CiudadComponent },
  { path: '**', redirectTo: '' },
  {path: 'ruta/lista/:id', component: RutaListaComponent}, // Equivalente en Spring Boot: /ruta/lista
  {path: 'ruta/vista/:id', component: RutaVistaComponent}, // Equivalente en Spring Boot: /ruta/vista/{id}
  { path: 'ruta/editar/:id', component: RutaEditarComponent }, // Equivalente en Spring Boot: /ruta/editar/{id}
  {path: 'caravana/vista/:id', component: CaravanaVistaComponent}, // Equivalente en Spring Boot: /caravana/vista/{id}
  {path: '', pathMatch: 'full', redirectTo: 'ruta/lista'} // Equivalente en Spring Boot: /ruta/lista
];