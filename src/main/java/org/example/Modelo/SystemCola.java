package org.example.Modelo;

import org.example.Controlador.Controller;
import org.example.Modelo.Eventos.Evento;
import org.example.Modelo.Objetos.Ayudante;
import org.example.Modelo.Objetos.Cliente;
import org.example.Modelo.Objetos.Duenio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;

public class SystemCola {
    private final String inicializacion = "Inicializacion";
    private final String llegadaCliente = "Llegada Cliente";
    private final String finAtencionCliente = "Fin Atencion Cliente";
    private final String finPreparacionComida = "Fin Preparacion comida";
    private final String tipoGolosinas = "Golosinas";
    private final String tipoComidaRapida = "Comida Rapida";

    private final Controller controller;
    private ArrayList<Evento> eventos = new ArrayList<>();

    private VectorEstado vectorActual = new VectorEstado();
    private final Random random = new Random();

    private Double reloj = 0.0;

    private Duenio duenio = new Duenio();
    private Ayudante ayudante = new Ayudante();

    public SystemCola(Controller controller) {
        this.controller = controller;
        Evento init = new Evento(this.inicializacion, 0.0);
        this.eventos.add(init);
    }

    public VectorEstado simular(int i, double media, double lsGolosinas,
                        double aAtencion, double bAtencion,
                        double aPreparacion, double bPreparacion) throws CloneNotSupportedException {

        if (eventos.get(0).getTipo().equals(this.inicializacion)) {
            this.inicializar(i, media);
        } else if (eventos.get(0).getTipo().equals(this.llegadaCliente)) {
            this.llegadaCliente(i, media, lsGolosinas, aAtencion, bAtencion);
        } else if (eventos.get(0).getTipo().equals(this.finAtencionCliente)) {
            this.finAtencion(i, aPreparacion, bPreparacion, aAtencion, bAtencion, lsGolosinas);
        } else if (eventos.get(0).getTipo().equals(this.finPreparacionComida)) {
            this.finPreparacion(i);
        }
        eventos.sort((Comparator.comparing(Evento::getTiempoOcurrencia)));
        this.eventos.remove(0);
        this.reloj = eventos.get(0).getTiempoOcurrencia();
        //vectorActual.print();

        VectorEstado vectorParaGrabar = (VectorEstado) this.vectorActual.clone();
        if (vectorActual.getComidas().size() > controller.getMaxTamanioPreparacion()){
            controller.setMaxTamanioPreparacion(vectorActual.getComidas().size());
        }
        if (vectorActual.getClientes().size() > controller.getMaxTamanioTemporales()) {
            controller.setMaxTamanioTemporales(vectorActual.getClientes().size());
        }

        return vectorParaGrabar;


    }

    private void finPreparacion(int n) {
        vectorActual.setN(n);
        vectorActual.setEvento(eventos.get(0).getTipo());
        vectorActual.setReloj(eventos.get(0).getTiempoOcurrencia());
        vectorActual.setRndLLegadaCliente(-1.0);
        vectorActual.setTiempoEntreLLegadaCliente(-1.0);
        vectorActual.setRndTipoPedido(-1.0);
        vectorActual.setTipoPedido("");
        vectorActual.setRndFinAtencion(-1.0);
        vectorActual.setTiempoAtencion(-1.0);
        vectorActual.setRndDemoraPreparacionComida(-1.0);

        //cerrar la preparacion actual y su cliente

        Optional<Comidas> comidaFinalizadaOp = vectorActual.getComidas()
                .stream()
                .filter(comida -> this.reloj == comida.getFinPreparacionComida())
                .findFirst();

        Comidas comidaFinalizada = comidaFinalizadaOp.get();
        vectorActual.finalizarComida(comidaFinalizada.getIdComidaCliente());
        vectorActual.finalizarCliente(comidaFinalizada.getIdComidaCliente());

        if (vectorActual.getComidas().isEmpty()) { // no hay comidas
            this.ayudante.desocupar();
            this.ayudante.iniciarTiempoOscioso(this.reloj);
            vectorActual.setInicioTiempoOscio(this.reloj);

            if (duenio.getEstado().equals("Libre")){
                vectorActual.setDuenioEstado(this.duenio.getEstado());
                vectorActual.setTiempoCocinaDuenioAcumulado(this.duenio.getTiempoEnCocinaAC());
                vectorActual.setInicioTiempoMostrador(this.duenio.getTiempoEnMostrador());
                vectorActual.setInicioTiempoCocina(-1.0);

            } else if (duenio.getEstado().equals("Cocinando")){
                this.duenio.desocupar();
                this.duenio.acumularTiempoCocina(this.reloj);
                this.duenio.iniciarTiempoMostrador(this.reloj);
                vectorActual.setDuenioEstado(this.duenio.getEstado());
                vectorActual.setTiempoCocinaDuenioAcumulado(this.duenio.getTiempoEnCocinaAC());
                vectorActual.setInicioTiempoMostrador(this.duenio.getTiempoEnMostrador());
                vectorActual.setInicioTiempoCocina(-1.0);
            }
        } else {  // hay comidas
            this.ayudante.cocinar();
            vectorActual.setInicioTiempoOscio(-1.0);
        }
        vectorActual.setAyudanteEstado(this.ayudante.getEstado());
    }

    public void llegadaCliente(int n, double media, double lsTipoPedido,
                               double a ,double b) {
        Cliente nuevoCliente = new Cliente();

        vectorActual.setN(n);
        vectorActual.setEvento(eventos.get(0).getTipo());
        vectorActual.setReloj(eventos.get(0).getTiempoOcurrencia());
        this.calcularProximaLlegada(media);
        if (this.duenio.puedeAtender()) {
            nuevoCliente.siendoAtendido();
            this.calcularTipoPedido(lsTipoPedido, nuevoCliente);
            this.calcularDemora(nuevoCliente, a, b);
            if (this.duenio.getEstado().equals("Cocinando")) {
                this.vectorActual.doblarTiempoPreparaciones(this.reloj);
                this.duenio.acumularTiempoCocina(this.reloj);
                vectorActual.setInicioTiempoCocina(-1.0);
                vectorActual.setTiempoCocinaDuenioAcumulado(this.duenio.getTiempoEnCocinaAC());
                this.duenio.iniciarTiempoMostrador(this.reloj);
                vectorActual.setInicioTiempoMostrador(this.duenio.getTiempoEnMostrador());

            }
            this.duenio.atender();

        } else if (!this.duenio.puedeAtender()) {
            nuevoCliente.esperandoAtencion();

            vectorActual.aumentarCola();
            vectorActual.setRndFinAtencion(-1.0);
            vectorActual.setTiempoAtencion(-1.0);
            vectorActual.setRndTipoPedido(-1.0);
            vectorActual.setTipoPedido("");
        }
        vectorActual.agregarClienteTemporal(nuevoCliente);
        vectorActual.setDuenioEstado(this.duenio.getEstado());
        vectorActual.setRndDemoraPreparacionComida(-1.0);

    }

    private void calcularTipoPedido(double ls, Cliente cliente) {
        double rnd = random.nextDouble();
        String tipoPedido;
        if (rnd < ls) {
            tipoPedido = this.tipoGolosinas;
            cliente.pidioGolosinas();
        } else {
            tipoPedido = this.tipoComidaRapida;
            cliente.pidioComidaRapida();
        }

        vectorActual.setRndTipoPedido(rnd);
        vectorActual.setTipoPedido(tipoPedido);
    }



    // analizar bien el cambio de estado del duenio y la acumulacion de los tiempos.
    public void finAtencion(int n, double aPreparacion, double bPreparacion,
                            double aAtencion, double bAtencion,
                            double lsGolosinas) {

        vectorActual.setN(n);
        vectorActual.setEvento(eventos.get(0).getTipo());
        vectorActual.setReloj(eventos.get(0).getTiempoOcurrencia());
        vectorActual.setRndLLegadaCliente(-1.0);
        vectorActual.setTiempoEntreLLegadaCliente(-1.0);
        vectorActual.setRndTipoPedido(-1.0);
        vectorActual.setTipoPedido("");
        vectorActual.setRndFinAtencion(-1.0);
        vectorActual.setTiempoAtencion(-1.0);
        vectorActual.setFinAtencion(-1.0);

        // cerrar el cliente anterior
        Optional<Cliente> clienteFinalizadoOp = vectorActual.getClientes()
                .stream()
                .filter(cliente -> "Siendo Atendido".equals(cliente.getEstado()))
                .findFirst();
        Cliente clienteFinalizado = clienteFinalizadoOp.get();
        if (clienteFinalizado.getTipoPedido().equals(this.tipoGolosinas)){
            vectorActual.finalizarCliente(clienteFinalizado.getId());
        } else {
            this.calcularDemoraPreparacion(clienteFinalizado, aPreparacion, bPreparacion);
            clienteFinalizado.esperandoComida();
            if (this.ayudante.getEstado().equals("Libre")) {
                this.ayudante.cocinar();
                this.ayudante.acumularTiempoOscioso(this.reloj);
            }
            vectorActual.setInicioTiempoOscio(-1.0);
            vectorActual.setAyudanteEstado(this.ayudante.getEstado());
            vectorActual.setTiempoOsciosoAyudanteAcumulado(this.ayudante.getTiempoOsciosoAC());
        }
            this.duenio.desocupar();

        if (vectorActual.getDuenioCola() != 0) {
            //Iniciar el cliente actual
            Optional<Cliente> clienteProximoOp = vectorActual.getClientes()
                    .stream()
                    .filter(c -> "Esperando Atencion".equals(c.getEstado()))
                    .findFirst();

            Cliente clienteProximo = clienteProximoOp.get();
            clienteProximo.siendoAtendido();
            this.duenio.atender();
            this.calcularTipoPedido(lsGolosinas, clienteProximo);
            this.calcularDemora(clienteProximo,aAtencion ,bAtencion);
            this.vectorActual.disminuirCola();

        } else if (vectorActual.getDuenioCola() == 0) {
            if (vectorActual.getComidas().isEmpty()){
                this.duenio.desocupar();
                vectorActual.setInicioTiempoCocina(-1.0);

            } else {
                if (this.duenio.getEstado().equals("Libre")){
                    this.duenio.cocinar();
                    this.duenio.acumularTiempoMostrador(this.reloj);
                    this.duenio.iniciarTiempoCocina(this.reloj);
                    this.vectorActual.dividirTiempoPreparacion(this.reloj);
                    vectorActual.setInicioTiempoMostrador(-1.0);
                    vectorActual.setInicioTiempoCocina(this.duenio.getTiempoEnCocina());
                    vectorActual.setTiempoMostradorDuenioAcumulado(this.duenio.getTiempoEnMostradorAC());

                } else {
                    vectorActual.setInicioTiempoMostrador(-1.0);
                    vectorActual.setTiempoMostradorDuenioAcumulado(this.duenio.getTiempoEnMostradorAC());
                }
            }
        }

        vectorActual.setDuenioEstado(this.duenio.getEstado());
        //vectorActual.setInicioTiempoMostrador(-1.0);
        //vectorActual.setTiempoMostradorDuenioAcumulado(this.duenio.getTiempoEnMostradorAC());
    }

    private void calcularDemoraPreparacion(Cliente cliente, double a, double b) {
        double rnd = random.nextDouble();
        double demora = a + rnd * (b - a);
        double demoraEfectiva = demora;

        if (duenio.puedeCocinar()) {
            demoraEfectiva = demora / 2;
            duenio.cocinar();
        }
        double finPreparacion = demoraEfectiva + this.reloj;

        Evento nuevoFinPrepracion = new Evento(this.finPreparacionComida, finPreparacion);
        Comidas nuevaComida = new Comidas(cliente.getId(), demora, demoraEfectiva, finPreparacion, nuevoFinPrepracion);
        eventos.add(nuevoFinPrepracion);

        vectorActual.setRndDemoraPreparacionComida(rnd);
        vectorActual.agregarComidaEnPreparacion(nuevaComida);

    }

    public void inicializar(int n, double u) {
        vectorActual.setN(n);
        vectorActual.setEvento(eventos.get(0).getTipo());
        vectorActual.setReloj(eventos.get(0).getTiempoOcurrencia());
        this.calcularProximaLlegada(u);
        vectorActual.setRndTipoPedido(-1.0);
        vectorActual.setTipoPedido("");
        vectorActual.setRndFinAtencion(-1.0);
        vectorActual.setTiempoAtencion(-1.0);
        vectorActual.setFinAtencion(-1.0);
        vectorActual.setRndDemoraPreparacionComida(-1.0);

        for (Comidas comida : vectorActual.getComidas()) {

            comida.setIdComidaCliente(-1);
            comida.setDemora(-1.0);
            comida.setDemoraEfectiva(-1.0);
            comida.setFinPreparacionComida(-1.0);
        }
        //Estadisticos
        this.duenio.iniciarTiempoMostrador(eventos.get(0).getTiempoOcurrencia());
        this.ayudante.iniciarTiempoOscioso(eventos.get(0).getTiempoOcurrencia());

        vectorActual.setDuenioEstado(this.duenio.getEstado());
        vectorActual.setDuenioCola(0);
        vectorActual.setInicioTiempoMostrador(this.duenio.getTiempoEnMostrador());
        vectorActual.setInicioTiempoCocina(-1.0);
        vectorActual.setAyudanteEstado(this.ayudante.getEstado());
        vectorActual.setInicioTiempoOscio(this.ayudante.getTiempoOscioso());
        vectorActual.setTiempoMostradorDuenioAcumulado(0.0);
        vectorActual.setTiempoCocinaDuenioAcumulado(0.0);
        vectorActual.setTiempoOsciosoAyudanteAcumulado(0.0);



    }

    //8 y 18 uniforme
    public void calcularDemora(Cliente cliente, double a, double b) {
        double rnd = random.nextDouble();
        double tiempoAtencion = 0.0;

        if (cliente.getTipoPedido().equals(this.tipoGolosinas)){
            tiempoAtencion = a + rnd * (b - a);
            vectorActual.setRndFinAtencion(rnd);
        } else {
            tiempoAtencion = 0.1;
            vectorActual.setRndFinAtencion(-1.0);
        }

        double finAtencion = this.reloj + tiempoAtencion;
        vectorActual.setTiempoAtencion(tiempoAtencion);
        vectorActual.setFinAtencion(finAtencion);
        Evento eventoFinAtencion = new Evento(this.finAtencionCliente, finAtencion);
        eventos.add(eventoFinAtencion);


    }

    public void calcularProximaLlegada(double u) {
        double rnd = random.nextDouble();
        double tiempoEntreLlegadas = -u * Math.log(1-rnd);
        double proximaLlegada = this.reloj + tiempoEntreLlegadas;
        vectorActual.setRndLLegadaCliente(rnd);
        vectorActual.setTiempoEntreLLegadaCliente(tiempoEntreLlegadas);
        vectorActual.setProximaLlegadaCliente(proximaLlegada);
        Evento nuevaLlegada = new Evento(this.llegadaCliente, proximaLlegada);
        eventos.add(nuevaLlegada);

    }


    public VectorEstado getVectorActual() {
        return vectorActual;
    }
}
