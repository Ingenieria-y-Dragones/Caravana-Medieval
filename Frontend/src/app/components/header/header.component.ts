import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Injectable } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CaravanaService } from '../../../service/caravana.service';
import { Subscription, interval } from 'rxjs';
@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  estado: any = {};
  tiempoRestante: string = '15:00';
  private estadoSub!: Subscription;
  private timerSub!: Subscription;
  private idCaravana = 1;

  constructor(private caravanaService: CaravanaService) {}

  ngOnInit(): void {
    this.loadEstado(); // Primera carga del estado
    this.estadoSub = interval(10_000).subscribe(() => this.loadEstado()); // Actualiza cada 10s
  }

  private loadEstado(): void {
    this.caravanaService.getEstadoCiudadCaravana(this.idCaravana)
      .subscribe((data: any) => {
        this.estado = data;
        if (data.tiempoMaximo != null && data.inicioJuego) {
          this.startCountdown(data.tiempoMaximo, data.inicioJuego);
        }
      });
  }

  private startCountdown(tiempoMinutos: number, inicioStr: string) {
    const inicio = new Date(inicioStr).getTime();
    const fin = inicio + tiempoMinutos * 60_000;

    // Detener timer anterior si existe
    if (this.timerSub) {
      this.timerSub.unsubscribe();
    }

    this.tiempoRestante = this.formatTime(fin - Date.now());

    this.timerSub = interval(1_000).subscribe(() => {
      const diff = fin - Date.now();
      this.tiempoRestante = this.formatTime(diff);
    });
  }

  private formatTime(ms: number): string {
    if (ms <= 0) { return '00:00'; }
    const m = Math.floor(ms / 60_000);
    const s = Math.floor((ms % 60_000) / 1_000);
    return `${m < 10 ? '0' : ''}${m}:${s < 10 ? '0' : ''}${s}`;
  }

  ngOnDestroy(): void {
    if (this.estadoSub) this.estadoSub.unsubscribe();
    if (this.timerSub) this.timerSub.unsubscribe();
  }
}
