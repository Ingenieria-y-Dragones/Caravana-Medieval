import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {CaravanaDto} from '../dto/caravana-dto';
import {JugadorDto} from '../dto/jugador-dto';
import {CiudadDto} from '../dto/ciudad-dto';

@Injectable({
  providedIn: 'root'
})
export class CaravanaService {

  private httpOptions = {
    headers: new HttpHeaders(
      {
        "Content-Type": "application/json"
      }
    )
  }

  constructor(
    private http: HttpClient
  ) { }

  recuperarCaravana(id: number): Observable<CaravanaDto> {
    return this.http.get<CaravanaDto>(`${environment.serverUrl}/caravana/${id}`);
  }

  listarJugadores(id: number): Observable<JugadorDto[]> {
    return this.http.get<JugadorDto[]>(`${environment.serverUrl}/caravana/${id}/jugadores`);
  }

  recuperarCiudad(id: number): Observable<CiudadDto>{
    return this.http.get<CiudadDto>(`${environment.serverUrl}/caravana/${id}/ciudad`);
  }

  modificarCaravana(caravana: CaravanaDto): Observable<CaravanaDto> {
    return this.http.put<CaravanaDto>(
      `${environment.serverUrl}/caravana`,
      caravana,
      this.httpOptions
    )
  }
  obtenerEstadoCiudad(id: number): Observable<any> {
    return this.http.get<any>(`${environment.serverUrl}/caravana/${id}/estado-ciudad`);
  }
  
}
