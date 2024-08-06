package org.example.Vista;

import org.example.Controlador.Controller;
import org.example.Vista.utilsVista.CharacterLimitDocumentFilter;
import org.example.Vista.utilsVista.NumericDocumentFilter;
import org.example.Vista.utilsVista.DoubleDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ui {

    private final Controller controller;

    public Ui(Controller controller) {
        this.controller = controller;
    }

    public void iniciar() {
        // Crear el frame
        JFrame frame = new JFrame("Trabajo Final de Simulacion - Ejercicio 108");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Crear un panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Hacer visible el frame
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Cantidad de iteraciones
        JLabel cantidadIteracionesLabel = new JLabel("Cantidad de iteraciones:");
        cantidadIteracionesLabel.setBounds(10, 20, 200, 25);
        panel.add(cantidadIteracionesLabel);

        JTextField cantidadIteracionesInput = new JTextField(20);
        cantidadIteracionesInput.setBounds(220, 20, 132, 25);
        cantidadIteracionesInput.setText("100");
        panel.add(cantidadIteracionesInput);

        ((AbstractDocument) cantidadIteracionesInput.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) cantidadIteracionesInput.getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(17));

        // Mostrar desde
        JLabel mostrarDesdeLabel = new JLabel("Mostrar desde:");
        mostrarDesdeLabel.setBounds(10, 60, 200, 25);
        panel.add(mostrarDesdeLabel);

        JTextField mostrarDesdeInput = new JTextField(20);
        mostrarDesdeInput.setBounds(220, 60, 132, 25);
        mostrarDesdeInput.setText("0");
        panel.add(mostrarDesdeInput);

        ((AbstractDocument) mostrarDesdeInput.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) mostrarDesdeInput.getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(17));

        // Mostrar hasta
        JLabel mostrarHastaLabel = new JLabel("Mostrar hasta:");
        mostrarHastaLabel.setBounds(10, 100, 200, 25);
        panel.add(mostrarHastaLabel);

        JTextField mostrarHastaInput = new JTextField(20);
        mostrarHastaInput.setBounds(220, 100, 132, 25);
        mostrarHastaInput.setText("20");
        panel.add(mostrarHastaInput);

        ((AbstractDocument) mostrarHastaInput.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        ((AbstractDocument) mostrarHastaInput.getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(17));

        // Llegada cliente (media)
        JLabel llegadaClienteLabel = new JLabel("Llegada cliente (media):");
        llegadaClienteLabel.setBounds(10, 140, 250, 25);
        panel.add(llegadaClienteLabel);

        JTextField llegadaClienteInput = new JTextField(20);
        llegadaClienteInput.setBounds(260, 140, 132, 25);
        llegadaClienteInput.setText("5");
        panel.add(llegadaClienteInput);

        ((AbstractDocument) llegadaClienteInput.getDocument()).setDocumentFilter(new DoubleDocumentFilter());
        ((AbstractDocument) llegadaClienteInput.getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(17));

        // Limite superior para la probabilidad de pedidos de golosinas
        JLabel limiteProbabilidadLabel = new JLabel("Limite superior para la probabilidad de pedidos de golosinas:");
        limiteProbabilidadLabel.setBounds(10, 180, 400, 25);
        panel.add(limiteProbabilidadLabel);

        JTextField limiteProbabilidadInput = new JTextField(20);
        limiteProbabilidadInput.setBounds(410, 180, 132, 25);
        limiteProbabilidadInput.setText("80");
        panel.add(limiteProbabilidadInput);

        ((AbstractDocument) limiteProbabilidadInput.getDocument()).setDocumentFilter(new DoubleDocumentFilter());
        ((AbstractDocument) limiteProbabilidadInput.getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(3));

        // A y B para el fin de Atencion de Cliente
        JLabel finAtencionLabel = new JLabel("A y B para el fin de Atencion de Cliente:");
        finAtencionLabel.setBounds(10, 220, 300, 25);
        panel.add(finAtencionLabel);

        JTextField finAtencionAInput = new JTextField(20);
        finAtencionAInput.setBounds(310, 220, 132, 25);
        finAtencionAInput.setText("0.5");
        panel.add(finAtencionAInput);

        ((AbstractDocument) finAtencionAInput.getDocument()).setDocumentFilter(new DoubleDocumentFilter());
        ((AbstractDocument) finAtencionAInput.getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(17));

        JTextField finAtencionBInput = new JTextField(20);
        finAtencionBInput.setBounds(450, 220, 132, 25);
        finAtencionBInput.setText("2");
        panel.add(finAtencionBInput);

        ((AbstractDocument) finAtencionBInput.getDocument()).setDocumentFilter(new DoubleDocumentFilter());
        ((AbstractDocument) finAtencionBInput.getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(17));

        // A y B para el fin de Preparacion de Comidas Rapidas
        JLabel finPreparacionLabel = new JLabel("A y B para el fin de Preparacion de Comidas Rapidas:");
        finPreparacionLabel.setBounds(10, 260, 400, 25);
        panel.add(finPreparacionLabel);

        JTextField finPreparacionAInput = new JTextField(20);
        finPreparacionAInput.setBounds(410, 260, 132, 25);
        finPreparacionAInput.setText("5");
        panel.add(finPreparacionAInput);

        ((AbstractDocument) finPreparacionAInput.getDocument()).setDocumentFilter(new DoubleDocumentFilter());
        ((AbstractDocument) finPreparacionAInput.getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(17));

        JTextField finPreparacionBInput = new JTextField(20);
        finPreparacionBInput.setBounds(550, 260, 132, 25);
        finPreparacionBInput.setText("10");
        panel.add(finPreparacionBInput);

        ((AbstractDocument) finPreparacionBInput.getDocument()).setDocumentFilter(new DoubleDocumentFilter());
        ((AbstractDocument) finPreparacionBInput.getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(17));

        // Crear un botón de simular
        JButton simularButton = new JButton("Simular");
        simularButton.setBounds(10, 300, 150, 25);
        panel.add(simularButton);

        // Añadir un ActionListener al botón
        simularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Boolean> comprobacion = new ArrayList<>();
                comprobacion.add(processInput(cantidadIteracionesInput, "Cantidad de iteraciones", true));
                comprobacion.add(processInput(mostrarDesdeInput, "Mostrar desde", true));
                comprobacion.add(processInput(mostrarHastaInput, "Mostrar hasta", true));
                comprobacion.add(processInput(llegadaClienteInput, "Llegada cliente (media)", false));
                comprobacion.add(processInput(limiteProbabilidadInput, "Limite superior para la probabilidad de pedidos de golosinas", false));
                comprobacion.add(processInput(finAtencionAInput, "A para el fin de Atencion de Cliente", false));
                comprobacion.add(processInput(finAtencionBInput, "B para el fin de Atencion de Cliente", false));
                comprobacion.add(processInput(finPreparacionAInput, "A para el fin de Preparacion de Comidas Rapidas", false));
                comprobacion.add(processInput(finPreparacionBInput, "B para el fin de Preparacion de Comidas Rapidas", false));

                if (!comprobacion.contains(false)) {
                    long cantidadIteraciones = Long.parseLong(cantidadIteracionesInput.getText());
                    long mostrarDesde = Long.parseLong(mostrarDesdeInput.getText());
                    long mostrarHasta = Long.parseLong(mostrarHastaInput.getText());
                    double limiteProbabilidad = Double.parseDouble(limiteProbabilidadInput.getText());
                    double llegadaCliente = Double.parseDouble(llegadaClienteInput.getText());
                    double finAtencionA = Double.parseDouble(finAtencionAInput.getText());
                    double finAtencionB = Double.parseDouble(finAtencionBInput.getText());
                    double finPreparacionA = Double.parseDouble(finPreparacionAInput.getText());
                    double finPreparacionB = Double.parseDouble(finPreparacionBInput.getText());

                    if (cantidadIteraciones > 0 && mostrarDesde >= 0 && mostrarHasta >= 0 && mostrarDesde < mostrarHasta && mostrarHasta <= cantidadIteraciones) {
                        if (llegadaCliente > 0 && limiteProbabilidad > 0 && limiteProbabilidad <= 100) {
                            if (finAtencionA >= 0 && finAtencionB >= 0 && finPreparacionA >= 0 && finPreparacionB >= 0) {
                                if (finAtencionA < finAtencionB && finPreparacionA < finPreparacionB) {
                                    try {
                                        controller.iniciar(
                                                cantidadIteraciones,
                                                mostrarDesde,
                                                mostrarHasta,
                                                llegadaCliente,
                                                limiteProbabilidad / 100.0,
                                                finAtencionA,
                                                finAtencionB,
                                                finPreparacionA,
                                                finPreparacionB
                                        );
                                        simularButton.setEnabled(false);
                                    } catch (CloneNotSupportedException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El valor de A debe ser menor que el valor de B para ambos 'fin de Atención de Cliente' y 'fin de Preparación de Comidas Rápidas'.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Los valores de A y B para el fin de Atención de Cliente y el fin de Preparación de Comidas Rápidas deben ser mayores o iguales a 0.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El valor de 'Llegada cliente (media)' debe ser mayor a 0 y el 'Límite superior para la probabilidad de pedidos de golosinas' debe ser mayor a 0 y menor o igual a 100.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Los valores de 'Mostrar desde' y 'Mostrar hasta' deben ser válidos y 'Mostrar hasta' debe ser menor o igual a 'Cantidad de iteraciones'.\n 'Cantidad de Iteraciones debe ser mayor a 0'");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos en todos los campos.");
                }
            }
        });
    }

    private boolean processInput(JTextField inputField, String fieldName, boolean isInteger) {
        String inputText = inputField.getText();
        try {
            if (isInteger) {
                Long.parseLong(inputText); // Check for integers
            } else {
                Double.parseDouble(inputText); // Check for doubles
            }
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Se ingresó un carácter inválido en el campo '" + fieldName + "', por favor ingrese un número válido.");
            return false;
        }
    }
}
