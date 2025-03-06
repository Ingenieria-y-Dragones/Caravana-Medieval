package co.edu.javeriana.caravana.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.model.Mapa;
import co.edu.javeriana.caravana.model.Producto;
import co.edu.javeriana.caravana.model.Ruta;
import co.edu.javeriana.caravana.model.Servicio;
import co.edu.javeriana.caravana.repository.CiudadRepository;
import co.edu.javeriana.caravana.repository.MapaRepository;
import co.edu.javeriana.caravana.repository.ProductoRepository;
import co.edu.javeriana.caravana.repository.RutaRepository;
import co.edu.javeriana.caravana.repository.ServicioRepository;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private MapaRepository mapaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    private final List<String> nombresCiudades = Arrays.asList(
        "Akkadia", "Babiria", "Carthagos", "Damashq", "Elisium", "Farsia", "Gadir", "Heliopolis", "Iskandria", "Jerash",
        "Kushar", "Lidonia", "Memphis", "Nineveh", "Ophir", "Palmyra", "Quirhazar", "Rhodon", "Sidonia", "Tarsos",
        "Urkesh", "Vashtan", "Xandria", "Yamatai", "Zaragoza", "Arbela", "Byblos", "Cyrene", "Dura-Europos", "Ebla",
        "Ferghana", "Gordion", "Hattusa", "Itanos", "Jiroft", "Kalhu", "Laranda", "Mari", "Nisa", "Opis", "Pergamon",
        "Qatna", "Ragae", "Salamis", "Thapsos", "Ugarit", "Volubilis", "Waset", "Xanthi", "Yamkhad", "Zabala",
        "Antioquía", "Bosra", "Castra", "Damasco", "Ecbatana", "Fars", "Gerasa", "Hierápolis", "Iol-Caesarea", "Judea",
        "Karkemish", "Libarna", "Medina", "Naukratis", "Oea", "Patara", "Qadesh", "Roma", "Seleucia", "Tebas",
        "Uqair", "Viminacium", "Xanthos", "Yazd", "Zafar", "Avaris", "Borsippa", "Ctesifonte", "Dion", "Erythrae",
        "Fustat", "Gergovia", "Hatra", "Ilion", "Jaffa", "Kition", "Lampsaco", "Ma'rib", "Neapolis", "Olbia", "Petra",
        "Qom", "Raqqa", "Sais", "Tanais", "Ur", "Vienne", "Xois", "Yabrud", "Zama"
    );

    @Override
    public void run(String... args) throws Exception {
        // Generar productos
        List<Producto> productos = List.of(
            new Producto(null, "Especias", 50),
            new Producto(null, "Seda", 30),
            new Producto(null, "Oro", 10),
            new Producto(null, "Incienso", 40),
            new Producto(null, "Maderas finas", 25),
            new Producto(null, "Sal", 60),
            new Producto(null, "Pergaminos", 15),
            new Producto(null, "Aceite de oliva", 35),
            new Producto(null, "Vino", 20),
            new Producto(null, "Hierbas curativas", 45),
            new Producto(null, "Alfombras", 18),
            new Producto(null, "Bronce", 22),
            new Producto(null, "Vidrio", 12),
            new Producto(null, "Cobre", 28),
            new Producto(null, "Pieles", 38),
            new Producto(null, "Marfil", 8),
            new Producto(null, "Piedras preciosas", 6),
            new Producto(null, "Cueros curtidos", 32),
            new Producto(null, "Armas forjadas", 14),
            new Producto(null, "Perfumes", 50),
            new Producto(null, "Joyería de oro", 11),
            new Producto(null, "Harina de trigo", 55),
            new Producto(null, "Lámparas de aceite", 17),
            new Producto(null, "Miel", 40),
            new Producto(null, "Plata", 9),
            new Producto(null, "Cristalería", 19),
            new Producto(null, "Telas de lino", 33),
            new Producto(null, "Hierro", 16),
            new Producto(null, "Ámbar", 13),
            new Producto(null, "Papiro", 26),
            new Producto(null, "Cera de abejas", 27),
            new Producto(null, "Caballos", 7),
            new Producto(null, "Arcos compuestos", 8),
            new Producto(null, "Higos", 21),
            new Producto(null, "Cereales", 44),
            new Producto(null, "Espejos de bronce", 9),
            new Producto(null, "Instrumentos musicales", 30),
            new Producto(null, "Tintes", 10),
            new Producto(null, "Algodón", 42),
            new Producto(null, "Sables", 11),
            new Producto(null, "Cabras", 24),
            new Producto(null, "Mármol", 14),
            new Producto(null, "Terracota", 31),
            new Producto(null, "Carne salada", 37),
            new Producto(null, "Joyas", 5),
            new Producto(null, "Roca Madre", 54),
            new Producto(null, "Suero costeño", 7),
            new Producto(null, "Frutas secas", 29),
            new Producto(null, "Estatuas", 20),
            new Producto(null, "Marfil tallado", 5)
        );
        productoRepository.saveAll(productos);

        // Generar ciudades
        List<Ciudad> ciudades = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Map<Producto, Integer> stock = new HashMap<>();
            Map<Producto, Double> factoresDemanda = new HashMap<>();
            Map<Producto, Double> factoresOferta = new HashMap<>();

            for (Producto producto : productos) {
                stock.put(producto, random.nextInt(100) + 1);
                factoresDemanda.put(producto, 0.5 + (random.nextDouble() * 1.5));
                factoresOferta.put(producto, 0.5 + (random.nextDouble() * 1.5));
            }

            List<Servicio> servicios = generarServicios();
            servicioRepository.saveAll(servicios);

            Ciudad ciudad = new Ciudad(
                factoresDemanda, 
                factoresOferta, 
                null, 
                nombresCiudades.get(i), // Usa nombres de la lista
                random.nextDouble() * 0.2 + 0.05, 
                stock, 
                servicios
            );
            ciudades.add(ciudad);
        }
        ciudadRepository.saveAll(ciudades);

        // Generar rutas aleatorias entre ciudades
        List<Ruta> rutas = new ArrayList<>();
        for (Ciudad ciudad : ciudades) {
            int rutasPorCiudad = random.nextInt(4) + 2;
            for (int j = 0; j < rutasPorCiudad; j++) {
                Ciudad destino = ciudades.get(random.nextInt(ciudades.size()));
                if (!ciudad.equals(destino)) {
                    Ruta ruta = new Ruta(null, ciudad, destino, 50.0 + random.nextDouble() * 450.0, random.nextBoolean(), random.nextInt(20));
                    rutas.add(ruta);
                    ciudad.agregarRutaSaliente(ruta);
                }
            }
        }
        rutaRepository.saveAll(rutas);
        ciudadRepository.saveAll(ciudades); // Guardar las ciudades con rutas

        // Crear el mapa con las ciudades generadas
        Mapa mapa = new Mapa(null);
        mapa.setCiudades(new HashSet<>(ciudades));
        mapaRepository.save(mapa);
    }
    // Generar servicios
    private List<Servicio> generarServicios() {
        Random random = new Random();
        List<Servicio> servicios = new ArrayList<>();
        for (Servicio.TipoServicio tipo : Servicio.TipoServicio.values()) {
            if (random.nextBoolean()) { // 50% de probabilidad de agregar el servicio
                servicios.add(new Servicio(random.nextDouble() * 100 + 50, null, tipo));
            }
        }
        return servicios;
    }
}
