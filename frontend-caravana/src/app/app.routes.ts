import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CiudadComponent } from './pages/ciudad/ciudad.component'; // 👈 Importa el componente de la ciudad

export const routes: Routes = [
  { path: '', component: HomeComponent }, // 👈 Ruta principal vacía redirige al Home
  { path: 'ciudad', component: CiudadComponent }, // 👈 Define la ruta para la ciudad
  // otras rutas
];