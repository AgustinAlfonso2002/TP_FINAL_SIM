package org.example.Controlador;

import org.apache.poi.ss.usermodel.Row;
import org.example.Modelo.Comidas;
import org.example.Modelo.Objetos.Cliente;

public class ColumnaPreparacion {
    private static int ultimoId = 1;
    private final int id;
    private int idComidaObjeto;
    private int indiceIdComidaCliente;
    private int indiceDemora;
    private int indiceDemoraEfectiva;
    private int indiceFinPreparacion;
    private String estado;

    public int getIdComidaObjeto() {
        return idComidaObjeto;
    }

    public ColumnaPreparacion(int indiceIdComidaCliente, int indiceDemora, int indiceDemoraEfectiva, int indiceFinPreparacion) {
        this.id = ultimoId++;
        this.indiceIdComidaCliente = indiceIdComidaCliente;
        this.indiceDemora = indiceDemora;
        this.indiceDemoraEfectiva = indiceDemoraEfectiva;
        this.indiceFinPreparacion = indiceFinPreparacion;
        this.estado = "Libre";
    }


    public void setBlanco(Row row) {
        this.idComidaObjeto = 0;
        row.createCell(this.indiceIdComidaCliente).setBlank();
        row.createCell(this.indiceDemora).setBlank();
        row.createCell(this.indiceDemoraEfectiva).setBlank();
        row.createCell(this.indiceFinPreparacion).setBlank();
        this.estado = "Libre";
    }

    public void asignarObjeto(Comidas comida, Row row) {
        this.idComidaObjeto = comida.getIdComidaCliente();
        row.createCell(this.indiceIdComidaCliente).setCellValue(comida.getIdComidaCliente());
        row.createCell(this.indiceDemora).setCellValue(comida.getDemora());
        row.createCell(this.indiceDemoraEfectiva).setCellValue(comida.getDemoraEfectiva());
        row.createCell(this.indiceFinPreparacion).setCellValue(comida.getFinPreparacionComida());
        this.estado = "Ocupado";
    }

    public boolean isLibre() {
        return this.estado.equals("Libre");
    }
}
