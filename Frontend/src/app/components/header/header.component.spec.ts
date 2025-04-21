import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HeaderComponent } from './header.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CaravanaService } from '../services/caravana.service';
import { of } from 'rxjs';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let service: CaravanaService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ HeaderComponent, HttpClientTestingModule ],
      providers: [ CaravanaService ]
    }).compileComponents();

    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(CaravanaService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('debería cargar estado de caravana en init', () => {
    const mockEstado = { salud: 80, dinero: 200, tiempoMaximo: 10, inicioJuego: new Date().toISOString() };
    spyOn(service, 'getEstadoCiudadCaravana').and.returnValue(of(mockEstado));

    fixture.detectChanges(); // dispara ngOnInit

    expect(service.getEstadoCiudadCaravana).toHaveBeenCalledWith(1);
    expect(component.estado).toEqual(mockEstado);
    expect(component.tiempoRestante).toMatch(/\d\d:\d\d/);
  });
});
