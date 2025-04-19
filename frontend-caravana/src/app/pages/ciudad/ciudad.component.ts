import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ciudad',
  standalone: true,
  imports: [CommonModule], // 👈 Necesario para usar *ngFor y otras directivas estructurales
  templateUrl: './ciudad.component.html',
  styleUrls: ['./ciudad.component.css']
})
export class CiudadComponent {}
