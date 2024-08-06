package org.example.Modelo.Objetos;

public class Ayudante {
    private String estado;
    private double tiempoOsciosoAC = 0.0;
    private double tiempoOscioso;

    public String getEstado() {
        return estado;
    }

    public double getTiempoOsciosoAC() {
        return tiempoOsciosoAC;
    }

    public double getTiempoOscioso() {
        return tiempoOscioso;
    }

    public void iniciarTiempoOscioso(double t) {
        this.tiempoOscioso = t;
    }
    public void acumularTiempoOscioso(double reloj) {
        this.tiempoOsciosoAC += reloj - this.tiempoOscioso;
    }

    public Ayudante() {
        this.estado = "Libre";
    }
    public void cocinar() {
        this.estado = "Cocinando";
    }
    public void desocupar() {
        this.estado = "Libre";
    }
}
