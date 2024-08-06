package org.example.Modelo.Eventos;

public class Evento {
    private String tipo;

    public void setTiempoOcurrencia(Double tiempoOcurrencia) {
        this.tiempoOcurrencia = tiempoOcurrencia;
    }

    private Double tiempoOcurrencia;

    public Evento(String tipo, double tiempoOcurrencia) {
        this.tipo = tipo;
        this.tiempoOcurrencia = tiempoOcurrencia;
    }
    public String getTipo() {
        return tipo;
    }
    public Double getTiempoOcurrencia() {
        return tiempoOcurrencia;
    }

    public boolean isEstadoEspecifico(String estado) {
        return this.tipo.equals(estado);
    }
}
