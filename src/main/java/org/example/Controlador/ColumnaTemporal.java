package org.example.Controlador;

import org.apache.poi.ss.usermodel.Row;
import org.example.Modelo.Objetos.Cliente;

public class ColumnaTemporal {
    private static int ultimoId = 1;
    private final int id;

    private int idCliente;
    private String estado;
    private int indiceIdCliente;
    private int indiceEstadoCliente;

    public int getId() {
        return id;
    }

    public ColumnaTemporal(int indiceIdCliente, int indiceEstadoCliente) {
        this.id = ultimoId++;
        this.estado = "Libre";
        this.indiceIdCliente = indiceIdCliente;
        this.indiceEstadoCliente = indiceEstadoCliente;
    }

    public void setBlanco(Row row) {
        this.idCliente = 0;
        row.createCell(this.indiceIdCliente).setBlank();
        row.createCell(this.indiceEstadoCliente).setBlank();
        this.estado = "Libre";
    }

    public int getIdCliente() {
        return idCliente;
    }

    public boolean isLibre() {
        return this.estado.equals("Libre");
    }

    public void asignarObjeto(Cliente cliente, Row row) {
        this.idCliente = cliente.getId();
        row.createCell(this.indiceIdCliente).setCellValue(cliente.getId());
        row.createCell(this.indiceEstadoCliente).setCellValue(cliente.getEstado());
        this.estado = "Ocupado";
    }

    public void rellenar(Row row) {
        int indiceRowAnterior = row.getRowNum() -1 ;

    }
}
