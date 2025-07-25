/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana1;

/**
 *
 * @author adrianaguilar
 */
public class Ticket {
    
    private double precioInicial;
    private double costoFinal;
    private String nombre;
    private boolean esPalindromo;

    public Ticket(String nombre, double precioInicial) {
        this.nombre = nombre;
        this.precioInicial = precioInicial; 
        this.esPalindromo = esPalindromo(nombre);
        this.costoFinal = esPalindromo ? precioInicial * 0.8 : precioInicial;
    }

   
    private boolean esPalindromo(String text) {
        text = text.toLowerCase().replaceAll("\\s+", "");
        return verificarPalindromo(text, 0, text.length() - 1);
    }

    private boolean verificarPalindromo(String text, int left, int right) {
        if (left >= right) return true;
        if (text.charAt(left) != text.charAt(right)) return false;
        return verificarPalindromo(text, left + 1, right - 1);
    }

   
    public String getNombre() {
        return nombre;
    }

    public double getCostoFinal() {
        return costoFinal;
    }

    public double getPrecioInicial() {
        return precioInicial;
    }

    public boolean esPalindromo() {
        return esPalindromo;
    }

    public String print() {
        return String.format("Pasajero: %s | Precio original: %.2f | Precio final: %.2f | Palíndromo: %s",
                nombre, precioInicial, costoFinal, esPalindromo ? "Sí" : "No");
    }
}
