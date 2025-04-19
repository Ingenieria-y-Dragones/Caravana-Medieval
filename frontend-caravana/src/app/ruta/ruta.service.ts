import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {RutaDto} from '../dto/ruta-dto';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RutaService {

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

  listarRutas(): Observable<RutaDto[]> {
    return this.http.get<RutaDto[]>(`${environment.serverUrl}/ruta/lista`);
  }

  listarRutasDesdeCiudadDeCaravana(id:number): Observable<RutaDto[]> {
    return this.http.get<RutaDto[]>(`${environment.serverUrl}/ruta/${id}/lista`);
  }

  recuperarRuta(id: number): Observable<RutaDto> {
    return this.http.get<RutaDto>(`${environment.serverUrl}/ruta/${id}`);
  }

  modificarRuta(ruta: RutaDto): Observable<RutaDto> {
    return this.http.put<RutaDto>(
      `${environment.serverUrl}/ruta`,
      ruta,
      this.httpOptions
    )
  }
}
