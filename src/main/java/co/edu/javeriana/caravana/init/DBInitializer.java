package co.edu.javeriana.caravana.init;

import co.edu.javeriana.caravana.model.*;
import co.edu.javeriana.caravana.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DBInitializer implements CommandLineRunner {
    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CompraServicioRepository compraServicioRepository;

    @Autowired
    private IventarioCaravanaRepository iventarioCaravanaRepository;

    @Autowired
    private IventarioCiudadRepository iventarioCiudadRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private ServicioOfrecidoRepository servicioOfrecidoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info("Iniciando carga de datos iniciales...");

        // Inicializar juego
        crearJuego();

        // Inicializar productos
        crearProductos();

        // Inicializar servicios
        crearServicios();

        // Inicializar ciudades
        crearCiudades();

        // Inicializar rutas entre ciudades
        crearRutas();

        // Inicializar inventarios de ciudades
        crearInventariosCiudades();

        // Inicializar servicios ofrecidos en ciudades
        crearServiciosOfrecidos();

        // Inicializar caravanas
        crearCaravanas();

        // Inicializar jugadores
        crearJugadores();

        // Inicializar inventarios de caravanas
        crearInventariosCaravanas();

        // Inicializar compras de servicios
        crearComprasServicios();

        logger.info("Carga de datos iniciales completada con éxito");
    }

    private void crearJuego() {
        if (juegoRepository.count() == 0) {
            Juego juego = new Juego(1.0F, 1000.0F); // Tiempo límite 1 hora, ganancias mínimas 1000
            juegoRepository.save(juego);
            logger.info("Juego creado: tiempo límite={}, ganancias mínimas={}", juego.getTiempoLimite(), juego.getGananciasMinimas());
        }
    }

    private void crearProductos() {
        if (productoRepository.count() == 0) {
            List<Producto> productos = new ArrayList<>();

            for (TipoProducto tipo : TipoProducto.values()) {
                Producto producto = new Producto(tipo);
                productos.add(producto);
            }

            productoRepository.saveAll(productos);
            logger.info("Productos creados: {}", productos.size());
        }
    }

    private void crearServicios() {
        if (servicioRepository.count() == 0) {
            List<Servicio> servicios = new ArrayList<>();

            for (TipoServicio tipo : TipoServicio.values()) {
                Servicio servicio = new Servicio(tipo);
                servicios.add(servicio);
            }

            servicioRepository.saveAll(servicios);
            logger.info("Servicios creados: {}", servicios.size());
        }
    }

    private void crearCiudades() {
        if (ciudadRepository.count() == 0) {
            List<Ciudad> ciudades = Arrays.asList(
                    new Ciudad("Sevilla", 0.05F),
                    new Ciudad("Granada", 0.07F),
                    new Ciudad("Toledo", 0.06F),
                    new Ciudad("Valencia", 0.08F),
                    new Ciudad("Barcelona", 0.1F)
            );

            ciudadRepository.saveAll(ciudades);
            logger.info("Ciudades creadas: {}", ciudades.size());
        }
    }

    private void crearRutas() {
        if (rutaRepository.count() == 0) {
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<Ruta> rutas = new ArrayList<>();

            // Crear conexiones entre ciudades
            if (ciudades.size() >= 2) {
                // Sevilla - Granada
                Ruta rutaSevillaGranada = new Ruta("Camino Real", 250.0F, 10.0F, TipoPeligro.BANDIDOS);
                rutaSevillaGranada.setCiudadOrigen(ciudades.get(0));
                rutaSevillaGranada.setCiudadDestino(ciudades.get(1));
                rutas.add(rutaSevillaGranada);

                // Granada - Sevilla (ruta inversa)
                Ruta rutaGranadaSevilla = new Ruta("Camino Real (vuelta)", 250.0F, 5.0F, TipoPeligro.NINGUNO);
                rutaGranadaSevilla.setCiudadOrigen(ciudades.get(1));
                rutaGranadaSevilla.setCiudadDestino(ciudades.get(0));
                rutas.add(rutaGranadaSevilla);

                // Granada - Toledo
                Ruta rutaGranadaToledo = new Ruta("Ruta de la Seda", 350.0F, 15.0F, TipoPeligro.DESASTRES_NATURALES);
                rutaGranadaToledo.setCiudadOrigen(ciudades.get(1));
                rutaGranadaToledo.setCiudadDestino(ciudades.get(2));
                rutas.add(rutaGranadaToledo);

                // Toledo - Valencia
                Ruta rutaToledoValencia = new Ruta("Camino del Este", 300.0F, 8.0F, TipoPeligro.BANDIDOS);
                rutaToledoValencia.setCiudadOrigen(ciudades.get(2));
                rutaToledoValencia.setCiudadDestino(ciudades.get(3));
                rutas.add(rutaToledoValencia);

                // Valencia - Barcelona
                Ruta rutaValenciaBarcelona = new Ruta("Ruta Mediterránea", 350.0F, 5.0F, TipoPeligro.NINGUNO);
                rutaValenciaBarcelona.setCiudadOrigen(ciudades.get(3));
                rutaValenciaBarcelona.setCiudadDestino(ciudades.get(4));
                rutas.add(rutaValenciaBarcelona);

                // Barcelona - Valencia (ruta inversa)
                Ruta rutaBarcelonaValencia = new Ruta("Ruta Mediterránea (vuelta)", 350.0F, 7.0F, TipoPeligro.BANDIDOS);
                rutaBarcelonaValencia.setCiudadOrigen(ciudades.get(4));
                rutaBarcelonaValencia.setCiudadDestino(ciudades.get(3));
                rutas.add(rutaBarcelonaValencia);
            }

            rutaRepository.saveAll(rutas);
            logger.info("Rutas creadas: {}", rutas.size());
        }
    }

    private void crearInventariosCiudades() {
        if (iventarioCiudadRepository.count() == 0) {
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<Producto> productos = productoRepository.findAll();
            List<InventarioCiudad> inventarios = new ArrayList<>();

            // Para cada ciudad y producto, crear un inventario con valores aleatorios pero coherentes
            for (Ciudad ciudad : ciudades) {
                for (Producto producto : productos) {
                    Float factorDemanda = 0.5F + new Random().nextFloat() * 1.5F; // Entre 0.5 y 2.0
                    Float factorOferta = 0.5F + new Random().nextFloat() * 1.5F; // Entre 0.5 y 2.0
                    Long existencias = 50L + Math.round(new Random().nextFloat() * 200); // Entre 50 y 250

                    InventarioCiudad inventario = new InventarioCiudad(existencias, factorDemanda, factorOferta);
                    inventario.setCiudad(ciudad);
                    inventario.setProducto(producto);
                    inventarios.add(inventario);
                }
            }

            iventarioCiudadRepository.saveAll(inventarios);
            logger.info("Inventarios de ciudades creados: {}", inventarios.size());
        }
    }

    private void crearServiciosOfrecidos() {
        if (servicioOfrecidoRepository.count() == 0) {
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<Servicio> servicios = servicioRepository.findAll();
            List<ServicioOfrecido> serviciosOfrecidos = new ArrayList<>();

            // Para cada ciudad, ofrecer algunos servicios con precios variados
            for (Ciudad ciudad : ciudades) {
                for (Servicio servicio : servicios) {
                    // Precio base según el tipo de servicio
                    float precioBase = switch (servicio.getTipo()) {
                        case REPARAR -> 50.0F;
                        case MEJORAR_CAPACIDAD -> 150.0F;
                        case MEJORAR_VELOCIDAD -> 200.0F;
                        case GUARDIAS -> 100.0F;
                        default -> 75.0F;
                    };

                    // Ajuste según el impuesto de la ciudad
                    float precio = precioBase * (1 + ciudad.getImpuesto());

                    ServicioOfrecido servicioOfrecido = new ServicioOfrecido(precio);
                    servicioOfrecido.setCiudad(ciudad);
                    servicioOfrecido.setServicio(servicio);
                    serviciosOfrecidos.add(servicioOfrecido);
                }
            }

            servicioOfrecidoRepository.saveAll(serviciosOfrecidos);
            logger.info("Servicios ofrecidos creados: {}", serviciosOfrecidos.size());
        }
    }

    private void crearCaravanas() {
        if (caravanaRepository.count() == 0) {
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<Caravana> caravanas = new ArrayList<>();

            if (!ciudades.isEmpty()) {
                // Caravana 1 en la primera ciudad
                Caravana caravana1 = new Caravana(
                        "Caravana del Desierto",
                        30.0F,  // velocidad
                        500.0F, // capacidad máxima de carga
                        1000.0F, // dinero inicial
                        100,    // puntos de vida
                        false,  // no tiene guardias inicialmente
                        0.0F    // tiempo transcurrido
                );
                caravana1.setCiudad(ciudades.get(0));
                caravanas.add(caravana1);

                // Caravana 2 en otra ciudad
                Caravana caravana2 = new Caravana(
                        "Mercaderes del Sur",
                        25.0F,  // velocidad
                        700.0F, // capacidad máxima de carga
                        1200.0F, // dinero inicial
                        100,    // puntos de vida
                        true,   // tiene guardias
                        0.0F    // tiempo transcurrido
                );

                int indiceSegundaCiudad = Math.min(1, ciudades.size() - 1);
                caravana2.setCiudad(ciudades.get(indiceSegundaCiudad));
                caravanas.add(caravana2);
            }

            caravanaRepository.saveAll(caravanas);
            logger.info("Caravanas creadas: {}", caravanas.size());
        }
    }

    private void crearJugadores() {
        if (jugadorRepository.count() == 0) {
            List<Caravana> caravanas = caravanaRepository.findAll();
            List<Jugador> jugadores = new ArrayList<>();

            if (!caravanas.isEmpty()) {
                // Jugadores para la primera caravana
                Jugador jugador1 = new Jugador("Ahmed", TipoJugador.CARAVANERO);
                jugador1.setCaravana(caravanas.get(0));
                jugadores.add(jugador1);

                Jugador jugador2 = new Jugador("Isabella", TipoJugador.COMERCIANTE);
                jugador2.setCaravana(caravanas.get(0));
                jugadores.add(jugador2);

                // Jugadores para la segunda caravana si existe
                if (caravanas.size() > 1) {
                    Jugador jugador3 = new Jugador("Carlos", TipoJugador.CARAVANERO);
                    jugador3.setCaravana(caravanas.get(1));
                    jugadores.add(jugador3);

                    Jugador jugador4 = new Jugador("Fatima", TipoJugador.COMERCIANTE);
                    jugador4.setCaravana(caravanas.get(1));
                    jugadores.add(jugador4);
                }
            }

            jugadorRepository.saveAll(jugadores);
            logger.info("Jugadores creados: {}", jugadores.size());
        }
    }

    private void crearInventariosCaravanas() {
        if (iventarioCaravanaRepository.count() == 0) {
            List<Caravana> caravanas = caravanaRepository.findAll();
            List<Producto> productos = productoRepository.findAll();
            List<InventarioCaravana> inventarios = new ArrayList<>();

            for (Caravana caravana : caravanas) {
                for (Producto producto : productos) {
                    // Asignamos existencias iniciales aleatorias pero razonables
                    Long existencias = Long.valueOf(Math.round(new Random().nextFloat() * 50)); // Entre 0 y 50

                    // Aseguramos que no exceda la capacidad máxima de la caravana
                    // (Asumiendo que cada unidad ocupa 1 unidad de espacio)
                    float capacidadUsada = existencias;
                    if (capacidadUsada <= caravana.getCapacidadMaximaCarga()) {
                        InventarioCaravana inventario = new InventarioCaravana(existencias);
                        inventario.setCaravana(caravana);
                        inventario.setProducto(producto);
                        inventarios.add(inventario);
                    }
                }
            }

            iventarioCaravanaRepository.saveAll(inventarios);
            logger.info("Inventarios de caravanas creados: {}", inventarios.size());
        }
    }

    private void crearComprasServicios() {
        if (compraServicioRepository.count() == 0) {
            List<Caravana> caravanas = caravanaRepository.findAll();
            List<Servicio> servicios = servicioRepository.findAll();
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<CompraServicio> compras = new ArrayList<>();

            // Solo creamos algunas compras de ejemplo
            if (!caravanas.isEmpty() && !servicios.isEmpty() && !ciudades.isEmpty()) {
                // La primera caravana compra un servicio en su ciudad actual
                CompraServicio compra1 = new CompraServicio();
                compra1.setCaravana(caravanas.get(0));
                compra1.setCiudad(caravanas.get(0).getCiudad());

                // Buscamos un servicio, por ejemplo GUARDIAS
                Servicio servicioGuardias = servicios.stream()
                        .filter(s -> s.getTipo() == TipoServicio.GUARDIAS)
                        .findFirst()
                        .orElse(servicios.get(0));

                compra1.setServicio(servicioGuardias);
                compras.add(compra1);

                // Si hay una segunda caravana, también compra un servicio
                if (caravanas.size() > 1) {
                    CompraServicio compra2 = new CompraServicio();
                    compra2.setCaravana(caravanas.get(1));
                    compra2.setCiudad(caravanas.get(1).getCiudad());

                    // Buscamos otro servicio, por ejemplo MEJORAR_VELOCIDAD
                    Servicio servicioVelocidad = servicios.stream()
                            .filter(s -> s.getTipo() == TipoServicio.MEJORAR_VELOCIDAD)
                            .findFirst()
                            .orElse(servicios.get(0));

                    compra2.setServicio(servicioVelocidad);
                    compras.add(compra2);
                }
            }

            compraServicioRepository.saveAll(compras);
            logger.info("Compras de servicios creadas: {}", compras.size());
        }
    }
}