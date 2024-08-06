package org.example.Modelo.Objetos;

public class Duenio {
    public double getTiempoEnMostradorAC() {
        return tiempoEnMostradorAC;
    }

    public double getTiempoEnMostrador() {
        return tiempoEnMostrador;
    }

    public double getTiempoEnCocinaAC() {
        return tiempoEnCocinaAC;
    }

    public double getTiempoEnCocina() {
        return tiempoEnCocina;
    }

    private String estado;
    private double tiempoEnMostradorAC = 0.0;
    private double tiempoEnMostrador;
    private double tiempoEnCocinaAC = 0.0;
    private double tiempoEnCocina;

    public Duenio() {
        this.estado = "Libre";
    }

    public void iniciarTiempoCocina(double t) {
        this.tiempoEnCocina = t;
    }
    public void iniciarTiempoMostrador(double t) {
        this.tiempoEnMostrador = t;
    }
    public void acumularTiempoCocina(double reloj) {
        this.tiempoEnCocinaAC += reloj - this.tiempoEnCocina;
    }
    public void acumularTiempoMostrador(double reloj) {
        this.tiempoEnMostradorAC += reloj - this.tiempoEnMostrador;
    }
    public void atender() {
        this.estado = "Atendiendo";
    }

    public void cocinar() {
        this.estado = "Cocinando";
    }

    public void desocupar() {
        this.estado = "Libre";
    }

    public boolean puedeAtender() {
        return this.estado.equals("Cocinando") || this.estado.equals("Libre");
    }

    public boolean puedeCocinar() {
        return this.estado.equals("Cocinando") || this.estado.equals("Libre");
    }

    public String getEstado() {
        return estado;
    }
}
