/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana1;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author adrianaguilar
 */
public class PalindromoAir {

    private Ticket[] asientos = new Ticket[30];
    private final double PRECIO_BASE = 200.0;

    public int firstAvailable(int index) {
        if (index >= asientos.length) return -1;
        return (asientos[index] == null) ? index : firstAvailable(index + 1);
    }

    public int searchPassenger(String nombre, int index) {
        if (index >= asientos.length) return -1;
        Ticket t = asientos[index];
        return (t != null && t.getNombre().equalsIgnoreCase(nombre)) ? index : searchPassenger(nombre, index + 1);
    }

    public void printPassengers(int index, JTextArea area) {
        if (index >= asientos.length) return;
        if (asientos[index] != null) {
            area.append("Asiento #" + (index + 1) + ":\n" + asientos[index].print() + "\n\n");
        }
        printPassengers(index + 1, area);
    }

    public double income(int index) {
        if (index >= asientos.length) return 0;
        return (asientos[index] != null ? asientos[index].getCostoFinal() : 0) + income(index + 1);
    }

    public void reset(int index) {
        if (index >= asientos.length) return;
        asientos[index] = null;
        reset(index + 1);
    }

    public void sellTicket(String nombre, JButton[] botones, JTextArea mensajes) {
        if (nombre == null || nombre.trim().isEmpty()) {
            mensajes.setText("Ingrese un nombre válido.");
            return;
        }

        int asientoLibre = firstAvailable(0);
        if (asientoLibre == -1) {
            mensajes.setText("Todos los asientos están ocupados");
            return;
        }

        Ticket nuevoTicket = new Ticket(nombre, PRECIO_BASE); 

        asientos[asientoLibre] = nuevoTicket;
        Color color = nuevoTicket.esPalindromo() ? Color.BLUE : Color.RED;
        botones[asientoLibre].setBackground(color);

        mensajes.setText("Ticket vendido exitosamente! \n" +
                         "Pasajero: " + nuevoTicket.getNombre() + "\n" +
                         "Total pagado: $" + String.format("%.2f", nuevoTicket.getCostoFinal()));
    }

    public boolean cancelTicket(String nombre, JButton[] botones, JTextArea mensajes) {
        int pos = searchPassenger(nombre, 0);
        if (pos == -1) {
            mensajes.setText("ERROR Pasajero no encontrado.");
            return false;
        }

        asientos[pos] = null;
        botones[pos].setBackground(Color.GREEN);
        mensajes.setText("Boleto cancelado exitosamente.");
        return true;
    }

    public void dispatch(JButton[] botones, JTextArea mensajes) {
        double total = income(0);
        mensajes.setText("otal recaudado: $" + String.format("%.2f", total) +
                         "\n Reiniciando avión...");

        reset(0);
        for (JButton btn : botones) {
            btn.setBackground(Color.GREEN);
        }
    }
}
