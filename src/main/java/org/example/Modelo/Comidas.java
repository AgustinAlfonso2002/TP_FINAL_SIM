package org.example.Modelo;

import org.example.Modelo.Eventos.Evento;

public class Comidas implements Cloneable{
    public Evento getEvento() {
        return evento;
    }

    private int idComidaCliente;
    private double demora;
    private double demoraEfectiva;
    private double finPreparacionComida;
    private Evento evento;

    public Comidas(int idComidaCliente, double demora, double demoraEfectiva, double finPreparacionComida,
                   Evento evento) {
        this.idComidaCliente = idComidaCliente;
        this.demora = demora;
        this.demoraEfectiva = demoraEfectiva;
        this.finPreparacionComida = finPreparacionComida;
        this.evento = evento;
    }

    public int getIdComidaCliente() {
        return idComidaCliente;
    }

    public double getDemora() {
        return demora;
    }

    public double getDemoraEfectiva() {
        return demoraEfectiva;
    }

    public double getFinPreparacionComida() {
        return finPreparacionComida;
    }


    public void setIdComidaCliente(int idComidaCliente) {
        this.idComidaCliente = idComidaCliente;
    }

    public void setDemora(double demora) {
        this.demora = demora;
    }

    public void setDemoraEfectiva(double demoraEfectiva) {
        this.demoraEfectiva = demoraEfectiva;
    }

    public void setFinPreparacionComida(double finPreparacionComida) {
        this.finPreparacionComida = finPreparacionComida;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
