/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MONOPOLYY;

/**
 *
 * @author juang
 */
abstract class Tarjeta {
    private String Información;

    public Tarjeta(String descripcion) {
        this.Información = descripcion;
    }

    public abstract void ejecutar(Jugador jugador, Juego juego);

    public String getDescripcion() {
        return Información;
    }
}

class DineroTarjeta extends Tarjeta {
    private int monto;

    public DineroTarjeta(String descripcion, int monto) {
        super(descripcion);
        this.monto = monto;
    }

    @Override
    public void ejecutar(Jugador jugador, Juego juego) {
        jugador.obtenerFondos(monto);
        System.out.println(getDescripcion());
    }
}

class MoverTarjeta extends Tarjeta {
    private int posiciones;

    public MoverTarjeta(String descripcion, int posiciones) {
        super(descripcion);
        this.posiciones = posiciones;
    }

    @Override
    public void ejecutar(Jugador jugador, Juego juego) {
        jugador.desplazarFicha(posiciones);
        Casilla casillaActual = juego.getTablero().getCasilla(jugador.getPosicion());
        casillaActual.ejecutarAccion(jugador, juego);
        System.out.println(getDescripcion());
    }
}

class CarcelTarjeta extends Tarjeta {
    public CarcelTarjeta(String descripcion) {
        super(descripcion);
    }

    @Override
    public void ejecutar(Jugador jugador, Juego juego) {
        jugador.colocarEnPrisión();
        System.out.println(getDescripcion());
    }
}