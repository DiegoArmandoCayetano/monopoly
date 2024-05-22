/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MONOPOLYY;

import java.util.ArrayList;

/**
 *
 * @author juang
 */
public class Jugador {
    private String nombre;
    private int ubicación;
    private int dinero;
    private ArrayList<Propiedad> propiedades;
    private boolean estanciaEnPrision;
    private int turnosPrision;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.ubicación = 0;  // Todos los jugadores comienzan en la casilla de salida
        this.dinero = 2300; // Monto inicial de dinero; esto puede ajustarse según las reglas personalizadas
        this.propiedades = new ArrayList<>();
    }

    public void desplazarFicha(int cantidadCasillas) {
        this.ubicación = (this.ubicación + cantidadCasillas) % 40; // Asegura que la posición siempre sea válida en el tablero de 40 casillas
        System.out.println(this.nombre + "Se desplaza hacia la posición " + this.ubicación);
    }

    public void pagar(int monto) {
        this.dinero -= monto;
        System.out.println(this.nombre + " Realizó el pago " + monto + " y cuenta con " + this.dinero);
        // Aquí podrías incluir una verificación para ver si el jugador ha caído en bancarrota
    }

    public void obtenerFondos(int monto) {
        this.dinero += monto;
        System.out.println(this.nombre + " recibe " + monto + " y cuenta con " + this.dinero);
    }

    public void adquirirPropiedad(Propiedad propiedad) {
        if (dinero >= propiedad.getCosto()) {
            this.dinero -= propiedad.getCosto();
            this.propiedades.add(propiedad);
            propiedad.setPropietario(this);
            System.out.println(this.nombre + " ha comprado " + propiedad.getNombre());
        } else {
            System.out.println(this.nombre + "  No dispone de fondos suficientes para la compra " + propiedad.getNombre());
        }
    }
    
    public void añadirPropiedad(Propiedad propiedad) {
        propiedades.add(propiedad);
    }
    
    public void colocarEnPrisión() {
        this.estanciaEnPrision = true;
        this.turnosPrision = 0;
        this.ubicación = 10; // Posición de la cárcel en el tablero
        System.out.println(nombre + " Fue enviado a la cárcel.");
    }

    public void IntentarLiberarseDePrisión() {
        turnosPrision++;
        if (turnosPrision >= 3) {
            pagar(50); // Suponemos un pago para salir después de 3 turnos
            estanciaEnPrision = false;
            System.out.println(nombre + " Ha realizado un pago para salir de la cárcel.");
        }
    }

    public boolean enPrisión() {
        return estanciaEnPrision;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return ubicación;
    }

    public int getDinero() {
        return dinero;
    }

    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}
