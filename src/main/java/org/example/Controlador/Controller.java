package org.example.Controlador;
import org.example.Modelo.SystemCola;
import org.example.Modelo.VectorEstado;
import org.example.Vista.Ui;

import java.util.ArrayList;


public class Controller {

    private final Ui ui;
    private final SystemCola systemCola;

    private final ExcelHandler excelHandler;

    private int maxTamanioPreparacion = 0;

    private int maxTamanioTemporales = 0;

    public Controller() {
        this.ui = new Ui(this);
        this.systemCola = new SystemCola(this);
        this.excelHandler = new ExcelHandler();

    }

    // Main
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.ui.iniciar();

    }

    public void iniciar(long cantidadIteraciones,
                        long mostrarDesde,
                        long mostrarHasta,
                        double llegadaCLienteMedia,
                        double lsGolosinas,
                        double finAtencionA,
                        double finAtencionB,
                        double finPreparacionA,
                        double finPreparacionB) throws CloneNotSupportedException {

        ArrayList<VectorEstado> contenido = new ArrayList<>();
        for (int i = 0;i < cantidadIteraciones; i++) {
            VectorEstado vectorParaGrabar = systemCola.simular(i, llegadaCLienteMedia,lsGolosinas,finAtencionA,
                    finAtencionB, finPreparacionA, finPreparacionB);
            contenido.add(vectorParaGrabar);

        }
        System.out.println("Listo");
        System.out.println(this.maxTamanioPreparacion);
        System.out.println(this.maxTamanioTemporales);

        this.excelHandler.crearExcel(contenido, this.maxTamanioPreparacion, this.maxTamanioTemporales,
                mostrarDesde, mostrarHasta);
    }
    public void setMaxTamanioPreparacion(int maxTamanioPreparacion) {
        this.maxTamanioPreparacion = maxTamanioPreparacion;
    }

    public void setMaxTamanioTemporales(int maxTamanioTemporales) {
        this.maxTamanioTemporales = maxTamanioTemporales;
    }


    public int getMaxTamanioPreparacion() {
        return maxTamanioPreparacion;
    }

    public int getMaxTamanioTemporales() {
        return maxTamanioTemporales;
    }
}
