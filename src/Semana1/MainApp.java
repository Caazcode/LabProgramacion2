/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana1;

/**
 *
 * @author adrianaguilar
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApp extends JFrame {

    private JButton[] asientosBotones = new JButton[30];
    private JTextField campoNombre;
    private JTextArea areaMensajes;
    private PalindromoAir sistema = new PalindromoAir();

    public MainApp() {
        setTitle("Palindromo Airlines - Paginia Incial");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel panelPrincipal = new JPanel(new BorderLayout());


        JPanel panelAsientos = new JPanel(new GridLayout(6, 5, 5, 5));
        for (int i = 0; i < 30; i++) {
            JButton boton = new JButton("A" + (i + 1));
            boton.setBackground(Color.GREEN);
            boton.setOpaque(true);
            boton.setBorderPainted(false);
            asientosBotones[i] = boton;
            panelAsientos.add(boton);
        }


        JPanel panelSuperior = new JPanel();
        panelSuperior.add(new JLabel("Nombre:"));
        campoNombre = new JTextField(15);
        panelSuperior.add(campoNombre);

        String[] acciones = {
                "Vender Ticket", "Cancelar Ticket", "Despachar",
                "Ver Ingresos", "Imprimir Pasajeros", "Buscar Pasajero"
        };

        for (String texto : acciones) {
            JButton btn = new JButton(texto);
            btn.addActionListener(this::accionesBotones);
            panelSuperior.add(btn);
        }

        
        areaMensajes = new JTextArea(10, 50);
        areaMensajes.setEditable(false);
        JScrollPane scrollMensajes = new JScrollPane(areaMensajes);

        
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelAsientos, BorderLayout.CENTER);
        panelPrincipal.add(scrollMensajes, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);
    }

    private void accionesBotones(ActionEvent e) {
        String accion = e.getActionCommand();
        String nombre = campoNombre.getText().trim();

        switch (accion) {
            case "Vender Ticket":
                sistema.sellTicket(nombre, asientosBotones, areaMensajes);
                break;
            case "Cancelar Ticket":
                sistema.cancelTicket(nombre, asientosBotones, areaMensajes);
                break;
            case "Despachar":
                sistema.dispatch(asientosBotones, areaMensajes);
                break;
            case "Ver Ingresos":
                double ingresos = sistema.income(0);
                areaMensajes.setText("Ingresos totales: $" + String.format("%.2f", ingresos));
                break;
            case "Imprimir Pasajeros":
                areaMensajes.setText(""); 
                sistema.printPassengers(0, areaMensajes);
                break;
            case "Buscar Pasajero":
                int pos = sistema.searchPassenger(nombre, 0);
                if (pos == -1) {
                    areaMensajes.setText("Pasajero no encontrado.");
                } else {
                    areaMensajes.setText("Pasajero encontrado en asiento #" + (pos + 1));
                }
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}

