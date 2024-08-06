package org.example.Modelo;

import org.example.Modelo.Objetos.Cliente;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class VectorEstado implements Cloneable{
    private int n;
    private String evento;
    private Double reloj;
    private double rndLLegadaCliente;
    private double tiempoEntreLLegadaCliente;
    private double proximaLlegadaCliente;
    private double rndTipoPedido;
    private String tipoPedido;
    private double rndFinAtencion;
    private double tiempoAtencion;
    private double finAtencion;
    private double rndDemoraPreparacionComida;
    private ArrayList<Comidas> comidas = new ArrayList<>();
    private String duenioEstado;
    private int duenioCola;
    private double inicioTiempoMostrador;
    private double inicioTiempoCocina;
    private String ayudanteEstado;
    private double inicioTiempoOscio;
    private double tiempoMostradorDuenioAcumulado;
    private double tiempoCocinaDuenioAcumulado;
    private double tiempoOsciosoAyudanteAcumulado;
    private ArrayList<Cliente> clientes = new ArrayList<>();


    private Cliente clienteEliminado;

    public void aumentarCola() {
        this.duenioCola += 1;
    }

    public void disminuirCola() {
        this.duenioCola -= 1;
    }

    public void agregarClienteTemporal(Cliente cliente) {
        this.clientes.add(cliente);
    }
    public void setClienteEliminado(Cliente clienteEliminado) {
        this.clienteEliminado = clienteEliminado;
    }

    public Cliente getClienteEliminado() {
        return clienteEliminado;
    }

    public Cliente finalizarCliente(int id) {
        Optional<Cliente> clienteFinalizado = this.clientes.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst();
        this.clientes.removeIf(cliente -> cliente.getId() == id);
        return clienteFinalizado.get();

    }

    public void print() {
        StringBuilder mostrarComidas = new StringBuilder("");
        for (Comidas comida : comidas) {
            mostrarComidas
                    .append(comida.getDemora()).append("|")
                    .append(comida.getDemoraEfectiva()).append("|")
                    .append(comida.getFinPreparacionComida());
        }

        System.out.println(n + "|" +
                evento + "|" +
                reloj + "|" +
                rndLLegadaCliente + "|" +
                tiempoEntreLLegadaCliente + "|" +
                proximaLlegadaCliente + "|" +
                rndTipoPedido + "|" +
                tipoPedido + "|" +
                rndFinAtencion + "|" +
                tiempoAtencion + "|" +
                finAtencion + "|" +
                rndDemoraPreparacionComida + "|" +
                mostrarComidas + "|" +
                duenioEstado + "|" +
                duenioCola + "|" +
                inicioTiempoMostrador + "|" +
                inicioTiempoCocina + "|" +
                ayudanteEstado + "|" +
                inicioTiempoOscio + "|" +
                tiempoMostradorDuenioAcumulado + "|" +
                tiempoCocinaDuenioAcumulado + "|" +
                tiempoOsciosoAyudanteAcumulado
                );
    }


    public int getN() {
        return n;
    }

    public String getEvento() {
        return evento;
    }

    public Double getReloj() {
        return reloj;
    }

    public double getRndLLegadaCliente() {
        return rndLLegadaCliente;
    }

    public double getTiempoEntreLLegadaCliente() {
        return tiempoEntreLLegadaCliente;
    }

    public double getProximaLlegadaCliente() {
        return proximaLlegadaCliente;
    }

    public double getRndTipoPedido() {
        return rndTipoPedido;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public double getRndFinAtencion() {
        return rndFinAtencion;
    }

    public double getTiempoAtencion() {
        return tiempoAtencion;
    }

    public double getFinAtencion() {
        return finAtencion;
    }

    public ArrayList<Comidas> getComidas() {
        return comidas;
    }

    public String getDuenioEstado() {
        return duenioEstado;
    }

    public int getDuenioCola() {
        return duenioCola;
    }

    public double getInicioTiempoMostrador() {
        return inicioTiempoMostrador;
    }

    public double getInicioTiempoCocina() {
        return inicioTiempoCocina;
    }

    public String getAyudanteEstado() {
        return ayudanteEstado;
    }

    public double getInicioTiempoOscio() {
        return inicioTiempoOscio;
    }

    public double getTiempoMostradorDuenioAcumulado() {
        return tiempoMostradorDuenioAcumulado;
    }

    public double getTiempoCocinaDuenioAcumulado() {
        return tiempoCocinaDuenioAcumulado;
    }

    public double getTiempoOsciosoAyudanteAcumulado() {
        return tiempoOsciosoAyudanteAcumulado;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public void setReloj(Double reloj) {
        this.reloj = reloj;
    }

    public void setRndLLegadaCliente(double rndLLegadaCliente) {
        this.rndLLegadaCliente = rndLLegadaCliente;
    }

    public void setTiempoEntreLLegadaCliente(double tiempoEntreLLegadaCliente) {
        this.tiempoEntreLLegadaCliente = tiempoEntreLLegadaCliente;
    }

    public void setProximaLlegadaCliente(double proximaLlegadaCliente) {
        this.proximaLlegadaCliente = proximaLlegadaCliente;
    }

    public void setRndTipoPedido(double rndTipoPedido) {
        this.rndTipoPedido = rndTipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public void setRndFinAtencion(double rndFinAtencion) {
        this.rndFinAtencion = rndFinAtencion;
    }

    public void setTiempoAtencion(double tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public void setFinAtencion(double finAtencion) {
        this.finAtencion = finAtencion;
    }

    public void setComidas(ArrayList<Comidas> comidas) {
        this.comidas = comidas;
    }

    public void setDuenioEstado(String duenioEstado) {
        this.duenioEstado = duenioEstado;
    }

    public void setDuenioCola(int duenioCola) {
        this.duenioCola = duenioCola;
    }

    public void setInicioTiempoMostrador(double inicioTiempoMostrador) {
        this.inicioTiempoMostrador = inicioTiempoMostrador;
    }

    public void setInicioTiempoCocina(double inicioTiempoCocina) {
        this.inicioTiempoCocina = inicioTiempoCocina;
    }

    public void setAyudanteEstado(String ayudanteEstado) {
        this.ayudanteEstado = ayudanteEstado;
    }

    public void setInicioTiempoOscio(double inicioTiempoOscio) {
        this.inicioTiempoOscio = inicioTiempoOscio;
    }

    public void setTiempoMostradorDuenioAcumulado(double tiempoMostradorDuenioAcumulado) {
        this.tiempoMostradorDuenioAcumulado = tiempoMostradorDuenioAcumulado;
    }

    public void setTiempoCocinaDuenioAcumulado(double tiempoCocinaDuenioAcumulado) {
        this.tiempoCocinaDuenioAcumulado = tiempoCocinaDuenioAcumulado;
    }

    public void setTiempoOsciosoAyudanteAcumulado(double tiempoOsciosoAyudanteAcumulado) {
        this.tiempoOsciosoAyudanteAcumulado = tiempoOsciosoAyudanteAcumulado;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setRndDemoraPreparacionComida(double rndDemoraPreparacionComida) {
        this.rndDemoraPreparacionComida = rndDemoraPreparacionComida;
    }

    public double getRndDemoraPreparacionComida() {
        return rndDemoraPreparacionComida;
    }

    public void agregarComidaEnPreparacion(Comidas nuevaComida) {
        this.comidas.add(nuevaComida);
    }

    public void doblarTiempoPreparaciones(double reloj) {
        for (Comidas comida : this.comidas) {
            comida.setDemora(comida.getFinPreparacionComida() - reloj);
            comida.setDemoraEfectiva(comida.getDemora() * 2);
            comida.setFinPreparacionComida(comida.getDemoraEfectiva() + reloj);
            comida.getEvento().setTiempoOcurrencia(comida.getDemoraEfectiva() + reloj);
        }
    }

    public void dividirTiempoPreparacion(Double reloj) {
        for (Comidas comida : this.comidas) {
            comida.setDemora(comida.getFinPreparacionComida() - reloj);
            comida.setDemoraEfectiva(comida.getDemora() / 2);
            comida.setFinPreparacionComida(comida.getDemoraEfectiva() + reloj);
            comida.getEvento().setTiempoOcurrencia(comida.getDemoraEfectiva() + reloj);
        }
    }

    public void finalizarComida(int id) {
        this.comidas.removeIf(comida -> comida.getIdComidaCliente() == id);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        VectorEstado cloned = (VectorEstado) super.clone();
        cloned.comidas = new ArrayList<>(this.comidas.stream().map(comida -> {
            try {
                return (Comidas) comida.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()));
        cloned.clientes = new ArrayList<>(this.clientes.stream().map(cliente -> {
            try {
                return (Cliente) cliente.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()));
        return cloned;
    }

}