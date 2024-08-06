package org.example.Modelo.Objetos;

public class Cliente implements Cloneable{

    private static int ultimoId = 0;
    private final int id;

    private String estado;
    private String tipoPedido;

    public Cliente() {
        this.id = ultimoId++;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }
    public void esperandoAtencion() {
        this.estado = "Esperando Atencion";
    }
    public void siendoAtendido() {
        this.estado = "Siendo Atendido";
    }
    public void esperandoComida() {
        this.estado = "Esperando comida";
    }
    public void pidioGolosinas() { this.tipoPedido = "Golosinas";}
    public void pidioComidaRapida() { this.tipoPedido = "Comida Rapida";}
    public String quePidio() {return this.tipoPedido;}

    public int getId() {
        return id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
