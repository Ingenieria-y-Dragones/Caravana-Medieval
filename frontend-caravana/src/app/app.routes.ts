import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CiudadComponent } from './pages/ciudad/ciudad.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'ciudad', component: CiudadComponent },
  { path: '**', redirectTo: '' }
];