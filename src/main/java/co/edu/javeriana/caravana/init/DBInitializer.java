package co.edu.javeriana.caravana.init;

import co.edu.javeriana.caravana.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

    }
}
