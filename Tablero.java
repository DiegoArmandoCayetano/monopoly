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
public class Tablero {
    private ArrayList<Casilla> casillas;

    public Tablero() {
        casillas = new ArrayList<>();
        inicializarCasillas();
    }

    private void inicializarCasillas() {
    // Casilla de inicio
    casillas.add(new Salida("Inicio", 300)); // Los jugadores reciben 300 al pasar por el inicio

    // Primer grupo de propiedades y otras casillas
    casillas.add(new Propiedad("Avenida del Parque", 50));
    casillas.add(new CajaComunidad("Caja de sorpresas"));
    casillas.add(new Propiedad("Avenida de la Suerte", 50));
    casillas.add(new Impuestos("Impuesto sobre ganancias", 300));
    casillas.add(new Estacion("Estación de Ferrocarril Central", 250));
    casillas.add(new Propiedad("Avenida de la Fortuna", 120));
    casillas.add(new Suerte("Tarjeta de suerte"));
    casillas.add(new Propiedad("Avenida del Triunfo", 100));
    casillas.add(new Propiedad("Avenida de la Prosperidad", 120));

    // Cárcel
    casillas.add(new Carcel("Cárcel/Visita", 0));

    // Segundo grupo de propiedades
    casillas.add(new Propiedad("Plaza de la Victoria", 160));
    casillas.add(new ServicioPublico("Compañía de Teléfonos", 130));
    casillas.add(new Propiedad("Avenida de los Sueños", 180));
    casillas.add(new Propiedad("Avenida del Éxito", 135));
    casillas.add(new Estacion("Estación de Ferrocarril del Sur", 162));
    casillas.add(new Propiedad("Plaza de la Alegría", 184));
    casillas.add(new CajaComunidad("Caja de sorpresas"));
    casillas.add(new Propiedad("Avenida de los Logros", 104));
    casillas.add(new Propiedad("Avenida del Progreso", 134));

    // Estacionamiento gratuito
    casillas.add(new Libre("Estacionamiento gratuito"));

    // Tercer grupo de propiedades
    casillas.add(new Propiedad("Avenida del Triunfo", 354));
    casillas.add(new Suerte("Tarjeta de suerte"));
    casillas.add(new Propiedad("Avenida del Avance", 125));
    casillas.add(new Propiedad("Avenida del Futuro", 251));
    casillas.add(new Estacion("Estación de Ferrocarril del Este", 261));
    casillas.add(new Propiedad("Avenida del Océano", 273));
    casillas.add(new Propiedad("Avenida de la Montaña", 237));
    casillas.add(new ServicioPublico("Compañía de Electricidad", 132));
    casillas.add(new Propiedad("Jardines de la Esperanza", 258));

    // Ve a la cárcel
    casillas.add(new Carcel("Ve a la cárcel", -1)); // -1 como bandera para enviar al jugador a la cárcel

    // Cuarto grupo de propiedades
    casillas.add(new Propiedad("Avenida del Sol", 200));
    casillas.add(new Propiedad("Avenida de la Luna", 200));
    casillas.add(new CajaComunidad("Caja de sorpresas"));
    casillas.add(new Propiedad("Avenida del Trabajo", 340));
    casillas.add(new Estacion("Estación de Ferrocarril del Oeste", 189));
    casillas.add(new Suerte("Tarjeta de suerte"));
    casillas.add(new Propiedad("Plaza de la Diversión", 333));
    casillas.add(new Impuestos("Impuesto de lujo", 92));
    casillas.add(new Propiedad("El Boulevard", 510));
}

    
    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public Casilla getCasilla(int posicion) {
        return casillas.get(posicion % casillas.size());
    }
}
