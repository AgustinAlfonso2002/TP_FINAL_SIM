package org.example.Controlador;

import org.apache.poi.sl.draw.geom.GuideIf;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.Modelo.Comidas;
import org.example.Modelo.Objetos.Cliente;
import org.example.Modelo.VectorEstado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ExcelHandler {
    private final Workbook workbook = new XSSFWorkbook();
    private final Sheet sheet = workbook.createSheet("Simulacion");
    private final CellStyle headerStyle = workbook.createCellStyle();
    private Row encabezado1;
    private Row encabezado2;
    private static int ultimoIndiceColumna = 0;
    private ArrayList<ColumnaPreparacion> columnasPreparacion = new ArrayList<>();
    private ArrayList<ColumnaTemporal> columnasTemporales = new ArrayList<>();
    private int auxPreparacion;


    private int auxTemporales;

    public ExcelHandler() {
        this.encabezado1 = this.sheet.createRow(0);
        this.encabezado2 = this.sheet.createRow(1);

    }
    public void crearCabecera(int maxTamanioPreparacion, int maxTamanioTemporales) {
        this.configurarEstilo();

        if (maxTamanioPreparacion == 0) {
            maxTamanioPreparacion = 1;
        }
        if (maxTamanioTemporales == 0) {
            maxTamanioTemporales = 1;
        }

        this.setAuxPreparacion(4*maxTamanioPreparacion);
        this.setAuxTemporales(2*maxTamanioTemporales - 1);

        //encabezado 1

        Cell llegadaClienteCell = encabezado1.createCell(3);
        Cell tipoPedidoEncabezadoCell = encabezado1.createCell(6);
        Cell finAtencionCLienteCell = encabezado1.createCell(8);
        Cell finPreparacionEncabezadoCell = encabezado1.createCell(11);
        Cell duenioCell = encabezado1.createCell(11 + auxPreparacion + 1);
        Cell ayudanteCell = encabezado1.createCell(11 + auxPreparacion + 5);
        Cell estadisticosCell = encabezado1.createCell(11 + auxPreparacion + 7);
        Cell clientesCell = encabezado1.createCell(11 + auxPreparacion + 10);

        this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));
        this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 7));
        this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 10));
        this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 11, 11 + auxPreparacion));
        this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 12 + auxPreparacion, 15 + auxPreparacion));
        this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 16 + auxPreparacion, 17 + auxPreparacion));
        this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 18 + auxPreparacion, 20 + auxPreparacion));
        this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 21 + auxPreparacion, 21 + auxPreparacion + auxTemporales));


        llegadaClienteCell.setCellValue("Llegada Cliente");
        tipoPedidoEncabezadoCell.setCellValue("Tipo de Pedido");
        finAtencionCLienteCell.setCellValue("Fin Atencion");
        finPreparacionEncabezadoCell.setCellValue("Fin Preparacion (i)");
        duenioCell.setCellValue("Dueño");
        ayudanteCell.setCellValue("Ayudante");
        estadisticosCell.setCellValue("Estadisticos");
        clientesCell.setCellValue("Clientes");


        llegadaClienteCell.setCellStyle(this.headerStyle);
        tipoPedidoEncabezadoCell.setCellStyle(this.headerStyle);
        finAtencionCLienteCell.setCellStyle(this.headerStyle);
        finPreparacionEncabezadoCell.setCellStyle(this.headerStyle);
        duenioCell.setCellStyle(this.headerStyle);
        ayudanteCell.setCellStyle(this.headerStyle);
        estadisticosCell.setCellStyle(this.headerStyle);
        clientesCell.setCellStyle(this.headerStyle);


        //encabezado 2
        Cell nCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell eventoCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell relojCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell rndLlegadaClienteCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell tiempoEntreLlegadasCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell proximaLlegadaCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell rndTipoPedidoCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell tipoPedidoCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell rndFinAtencionCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell tiempoAtencionCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell finAtencionCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell rndDemoraPreparacion = encabezado2.createCell(ultimoIndiceColumna++);

        for (int i = 0; i < maxTamanioPreparacion; i++) {
            Cell idComidaCell = encabezado2.createCell(ultimoIndiceColumna++);
            Cell demoraCell = encabezado2.createCell(ultimoIndiceColumna++);
            Cell demoraEfectivaCell = encabezado2.createCell(ultimoIndiceColumna++);
            Cell finPreparacionCell = encabezado2.createCell(ultimoIndiceColumna++);
            idComidaCell.setCellValue("Id Cliente-Comida");
            demoraCell.setCellValue("Demora");
            demoraEfectivaCell.setCellValue("Demora efectiva");
            finPreparacionCell.setCellValue("Fin Preparacion");
            idComidaCell.setCellStyle(this.headerStyle);
            demoraCell.setCellStyle(this.headerStyle);
            demoraEfectivaCell.setCellStyle(this.headerStyle);
            finPreparacionCell.setCellStyle(this.headerStyle);

            this.crearColumnaPreparacion(idComidaCell.getColumnIndex(), demoraCell.getColumnIndex(), demoraEfectivaCell.getColumnIndex(), finPreparacionCell.getColumnIndex());

        }

        Cell estadoDuenioCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell colaDuenioCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell tiempoMostradorDuenioCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell tiempoCocinaDuenioCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell estadoAyudanteCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell tiempoOsciosoAyudanteCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell tiempoMostradorACCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell tiempoCocinaACCell = encabezado2.createCell(ultimoIndiceColumna++);
        Cell tiempoOsciosoACCell = encabezado2.createCell(ultimoIndiceColumna++);

        for (int i = 0; i < maxTamanioTemporales; i++) {
            Cell idClienteCell = encabezado2.createCell(ultimoIndiceColumna++);
            Cell estadoClienteCell = encabezado2.createCell(ultimoIndiceColumna++);
            idClienteCell.setCellValue("Id");
            estadoClienteCell.setCellValue("Estado");
            idClienteCell.setCellStyle(this.headerStyle);
            estadoClienteCell.setCellStyle(this.headerStyle);

            this.crearColumnaTemporal(idClienteCell.getColumnIndex(),estadoClienteCell.getColumnIndex());

        }


        nCell.setCellValue("N");
        eventoCell.setCellValue("Evento");
        relojCell.setCellValue("Reloj");
        rndLlegadaClienteCell.setCellValue("RND");
        tiempoEntreLlegadasCell.setCellValue("Tiempo Entre Llegadas");
        proximaLlegadaCell.setCellValue("Proxima Llegada");
        rndTipoPedidoCell.setCellValue("RND");
        tipoPedidoCell.setCellValue("Pedido");
        rndFinAtencionCell.setCellValue("RND");
        tiempoAtencionCell.setCellValue("Tiempo Atencion");
        finAtencionCell.setCellValue("Fin Atencion");
        rndDemoraPreparacion.setCellValue("RND");
        estadoDuenioCell.setCellValue("Estado");
        colaDuenioCell.setCellValue("Cola");
        tiempoMostradorDuenioCell.setCellValue("Inicio tiempo en Mostrador");
        tiempoCocinaDuenioCell.setCellValue("Inicio tiempo en Cocina");
        estadoAyudanteCell.setCellValue("Estado");
        tiempoOsciosoAyudanteCell.setCellValue("Inicio tiempo Oscioso");
        tiempoMostradorACCell.setCellValue("Tiempo en mostrador del Dueño acumulado");
        tiempoCocinaACCell.setCellValue("Tiempo en cocina del Dueño acumulado");
        tiempoOsciosoACCell.setCellValue("Tiempo Oscioso del Ayudante acumulado");


        nCell.setCellStyle(this.headerStyle);
        eventoCell.setCellStyle(this.headerStyle);
        relojCell.setCellStyle(this.headerStyle);
        rndLlegadaClienteCell.setCellStyle(this.headerStyle);
        tiempoEntreLlegadasCell.setCellStyle(this.headerStyle);
        proximaLlegadaCell.setCellStyle(this.headerStyle);
        rndTipoPedidoCell.setCellStyle(this.headerStyle);
        tipoPedidoCell.setCellStyle(this.headerStyle);
        rndFinAtencionCell.setCellStyle(this.headerStyle);
        tiempoAtencionCell.setCellStyle(this.headerStyle);
        finAtencionCell.setCellStyle(this.headerStyle);
        rndDemoraPreparacion.setCellStyle(this.headerStyle);
        estadoDuenioCell.setCellStyle(this.headerStyle);
        colaDuenioCell.setCellStyle(this.headerStyle);
        tiempoOsciosoAyudanteCell.setCellStyle(this.headerStyle);
        tiempoCocinaDuenioCell.setCellStyle(this.headerStyle);
        estadoAyudanteCell.setCellStyle(this.headerStyle);
        tiempoOsciosoAyudanteCell.setCellStyle(this.headerStyle);
        tiempoMostradorACCell.setCellStyle(this.headerStyle);
        tiempoCocinaACCell.setCellStyle(this.headerStyle);
        tiempoOsciosoACCell.setCellStyle(this.headerStyle);
        tiempoMostradorDuenioCell.setCellStyle(this.headerStyle);

    }

    private void crearColumnaTemporal(int columnIndex1, int columnIndex2) {
        ColumnaTemporal columna = new ColumnaTemporal(columnIndex1,columnIndex2);
        this.columnasTemporales.add(columna);
    }

    private void crearColumnaPreparacion(int columnIndex1, int columnIndex2, int columnIndex3, int columnIndex4) {
        ColumnaPreparacion columna = new ColumnaPreparacion(columnIndex1, columnIndex2, columnIndex3, columnIndex4);
        this.columnasPreparacion.add(columna);
    }

    public void configurarEstilo() {
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        DataFormat format = workbook.createDataFormat();
        headerStyle.setDataFormat(format.getFormat("0.0000"));
    }

    public void crearExcel(ArrayList<VectorEstado> contenido, int maxTamanioPreparacion, int maxTamanioTemporales,
                           long mostrarDesde, long mostrarHasta) {
        this.crearCabecera(maxTamanioPreparacion, maxTamanioTemporales);
        this.cargarExcel(contenido, mostrarDesde, mostrarHasta);
        this.ajustarCeldas(ultimoIndiceColumna);
        this.grabar();
        this.cerrarWorkBook();
    }

    private void cargarExcel(ArrayList<VectorEstado> contenido, long mostrarDesde, long mostrarHasta) {
        for (VectorEstado vectorActual : contenido) {
            if (vectorActual.getN() >= mostrarDesde && vectorActual.getN() <= mostrarHasta) {
                this.cargarFila(vectorActual);
            }
        }
        if (mostrarHasta < contenido.get(contenido.size() - 1).getN()) {
            this.cargarFila(contenido.get(contenido.size()-1));
        }
        this.calcularPorcentajes(contenido.get(contenido.size()-1));
    }

    private void calcularPorcentajes(VectorEstado vector) {

        int rowNum1 = sheet.getLastRowNum() + 1;
        Row row1 = sheet.createRow(rowNum1);

        row1.createCell(0).setCellValue("Porcentaje de tiempo Oscioso del ayudante");
        row1.createCell(1).setCellValue((vector.getTiempoOsciosoAyudanteAcumulado() / vector.getReloj()) * 100);

        int rowNum2 = sheet.getLastRowNum() + 2;
        Row row2 = sheet.createRow(rowNum2);

        row2.createCell(0).setCellValue("Porcentaje de tiempo del Dueño en la cocina");
        row2.createCell(1).setCellValue((vector.getTiempoCocinaDuenioAcumulado() / vector.getReloj()) * 100);

        int rowNum3 = sheet.getLastRowNum() + 3;
        Row row3 = sheet.createRow(rowNum3);

        row3.createCell(0).setCellValue("Porcentaje de tiempo del Dueño en el mostrador");
        row3.createCell(1).setCellValue((vector.getTiempoMostradorDuenioAcumulado() / vector.getReloj()) * 100);

    }

    private void cargarFila(VectorEstado vector) {
        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(vector.getN());
        row.createCell(1).setCellValue(vector.getEvento());
        row.createCell(2).setCellValue(vector.getReloj());

        Cell cell3 = row.createCell(3);
        if (vector.getRndLLegadaCliente() == -1.0) {cell3.setBlank();} else {cell3.setCellValue(vector.getRndLLegadaCliente());}

        Cell cell4 = row.createCell(4);
        if (vector.getTiempoEntreLLegadaCliente() == -1.0) {cell4.setBlank();} else {cell4.setCellValue(vector.getTiempoEntreLLegadaCliente());}

        row.createCell(5).setCellValue(vector.getProximaLlegadaCliente());

        Cell cell6 = row.createCell(6);
        if (vector.getRndTipoPedido() == -1.0) {cell6.setBlank();} else {cell6.setCellValue(vector.getRndTipoPedido());}

        row.createCell(7).setCellValue(vector.getTipoPedido());

        Cell cell8 =row.createCell(8);
        if (vector.getRndFinAtencion() == -1.0) {cell8.setBlank();} else {cell8.setCellValue(vector.getRndFinAtencion());}

        Cell cell9 =row.createCell(9);
        if (vector.getTiempoAtencion() == -1.0) {cell9.setBlank();} else {cell9.setCellValue(vector.getTiempoAtencion());}

        Cell cell10 =row.createCell(10);
        if (vector.getFinAtencion() == -1.0) {cell10.setBlank();} else {cell10.setCellValue(vector.getFinAtencion());}

        Cell cell11 = row.createCell(11);
        if (vector.getRndDemoraPreparacionComida() == -1.0) {cell11.setBlank();} else {cell11.setCellValue(vector.getRndDemoraPreparacionComida());}


        // Fin Preparacion
        for (ColumnaPreparacion columna : this.columnasPreparacion) {
            boolean ocupada = vector.getComidas()
                    .stream()
                    .anyMatch(comidas -> comidas.getIdComidaCliente() == columna.getIdComidaObjeto());
            if (!ocupada) {
                columna.setBlanco(row);
            }
        }

        for (Comidas comida : vector.getComidas()) {
            Optional<ColumnaPreparacion> columnaPreparacion = this.columnasPreparacion
                    .stream()
                    .filter(c -> c.getIdComidaObjeto() == comida.getIdComidaCliente())
                    .findFirst();
            if (columnaPreparacion.isPresent()) {
                columnaPreparacion.get().asignarObjeto(comida, row);
            } else {
                Optional<ColumnaPreparacion> columnaLibre = this.columnasPreparacion
                        .stream()
                        .filter(c -> c.isLibre())
                        .findFirst();
                columnaLibre.get().asignarObjeto(comida, row);
            }
        }


        row.createCell(12+auxPreparacion).setCellValue(vector.getDuenioEstado());
        row.createCell(13+auxPreparacion).setCellValue(vector.getDuenioCola());

        Cell cell14y8 =row.createCell(14+auxPreparacion);
        if (vector.getInicioTiempoMostrador() == -1.0) {cell14y8.setBlank();} else {cell14y8.setCellValue(vector.getInicioTiempoMostrador());}

        Cell cell15y8 =row.createCell(15+auxPreparacion);
        if (vector.getInicioTiempoCocina() == -1.0) {cell15y8.setBlank();} else {cell15y8.setCellValue(vector.getInicioTiempoCocina());}

        row.createCell(16+auxPreparacion).setCellValue(vector.getAyudanteEstado());

        Cell cell17y8 =row.createCell(17+auxPreparacion);
        if (vector.getInicioTiempoOscio() == -1.0) {cell17y8.setBlank();} else {cell17y8.setCellValue(vector.getInicioTiempoOscio());}

        row.createCell(18+auxPreparacion).setCellValue(vector.getTiempoMostradorDuenioAcumulado());
        row.createCell(19+auxPreparacion).setCellValue(vector.getTiempoCocinaDuenioAcumulado());
        row.createCell(20+auxPreparacion).setCellValue(vector.getTiempoOsciosoAyudanteAcumulado());


        //agregar columnas temporales

        for (ColumnaTemporal columna : this.columnasTemporales) {
            boolean ocupada =  vector.getClientes()
                    .stream()
                    .anyMatch(cliente -> cliente.getId() == columna.getIdCliente());
            if (!ocupada) {
                columna.setBlanco(row);
            }
        }

        for (Cliente cliente : vector.getClientes()) {
            Optional<ColumnaTemporal> columnaTemporal = this.columnasTemporales
                    .stream()
                    .filter(c -> c.getIdCliente() == cliente.getId())
                    .findFirst();
            if (columnaTemporal.isPresent()){
                columnaTemporal.get().asignarObjeto(cliente, row);
            } else {
                Optional<ColumnaTemporal> columnaLibre = this.columnasTemporales
                        .stream()
                        .filter(c -> c.isLibre())
                        .findFirst();
                columnaLibre.get().asignarObjeto(cliente,row);
            }


        }



    }





    public void grabar() {
        try (FileOutputStream fileOut = new FileOutputStream("ejemplo_con_cabecera.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ajustarCeldas(int ultimoIndiceColumna) {
        for (int i = 0; i < ultimoIndiceColumna; i++) {
            sheet.autoSizeColumn(i);
        }
    }
    public void cerrarWorkBook() {
        try {
            workbook.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Archivo de Excel con cabecera creado exitosamente.");

        }
    public void setAuxPreparacion(int auxPreparacion) {
        this.auxPreparacion = auxPreparacion;
    }

    public void setAuxTemporales(int auxTemporales) {
        this.auxTemporales = auxTemporales;
    }

}

