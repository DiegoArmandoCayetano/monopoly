package MONOPOLYY;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juang
 */
public class Juego {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Tarjeta> tarjetasboxcomunity;
    private ArrayList<Tarjeta> tarjetasLuck;
    private int jugadorActual;
    private boolean juegoTerminado;
    private Scanner scanner;

    public Juego(List<String> nombresJugadores) {
        this.tablero = new Tablero();
        this.jugadores = new ArrayList<>();
        inicializarJugadores(nombresJugadores);
        this.jugadorActual = 0;
        this.juegoTerminado = false;
        this.tarjetasboxcomunity = new ArrayList<>();
        this.tarjetasLuck = new ArrayList<>();
        inicializarTarjetas();
        
        this.scanner = new Scanner(System.in);
    }
    
    private void inicializarTarjetas() {
       
// Ejemplos más representativos de las tarjetas de la Caja de Comunidad.
    tarjetasboxcomunity.add(new DineroTarjeta("El banco te otorga un dividendo de $30.", 30));
    tarjetasboxcomunity.add(new DineroTarjeta("Recibes una devolución de impuestos. Obtienes $25.",25));
    tarjetasboxcomunity.add(new MoverTarjeta("Ve directamente a la cárcel. No pases por la casilla de salida.", 7)); // Este debería mover al jugador directamente a la cárcel.

    // Ejemplos más representativos de las tarjetas de Suerte.
      tarjetasLuck.add(new DineroTarjeta("Has sido penalizado por exceso de velocidad. Debes pagar una multa de $11.", -11));
    tarjetasLuck.add(new MoverTarjeta("Avanza hasta la casilla de 'Salida'.", 0)); // Esto moverá al jugador a la casilla de salida.
    tarjetasLuck.add(new DineroTarjeta("El préstamo de construcción ha llegado a su vencimiento. Recibe $134", 134));

        // Barajar las tarjetas
        Collections.shuffle(tarjetasboxcomunity);
        Collections.shuffle(tarjetasLuck);
    }

    public Tarjeta sacarTarjetaCajaComunidad() {
        Tarjeta tarjeta = tarjetasboxcomunity.remove(0);
        tarjetasboxcomunity.add(tarjeta);  // Vuelve a colocar la tarjeta al final del mazo
        return tarjeta;
    }

    public Tarjeta sacarTarjetaSuerte() {
        Tarjeta tarjeta = tarjetasLuck.remove(0);
        tarjetasLuck.add(tarjeta);  // Vuelve a colocar la tarjeta al final del mazo
        return tarjeta;
    }

    private void inicializarJugadores(List<String> nombresJugadores) {
        for (String nombre : nombresJugadores) {
            this.jugadores.add(new Jugador(nombre));
        }
    }
    
    

    public void iniciarJuego() {
        System.out.println("¡Bienvenidos a 'Monopoly!'");
        while (!juegoTerminado) {
            jugarTurno();
        }
    }

    public void jugarTurno() {
        Jugador jugador = jugadores.get(jugadorActual);
        System.out.println(jugador.getNombre() + ", es tu turno.");

        // Simular lanzamiento de dados
        int dado = lanzarDados();
        System.out.println("Has lanzado un " + dado + ".");

        // Mover ficha del jugador
        jugador.desplazarFicha(dado);
        int posicion = jugador.getPosicion();
        Casilla casillaActual = tablero.getCasilla(posicion);
        
        System.out.println("Has aterrizado en " + casillaActual.getNombre() + ".");
        
        // Ejecutar acción basada en la casilla
        casillaActual.ejecutarAccion(jugador, this);

        // Verificar fin del juego
        if (jugador.getDinero() <= 0) {
            System.out.println(jugador.getNombre() + " Ha agotado sus fondos y ha perdido.");
            finalizarJuego(jugador);
        }

        // Actualizar el jugador actual para el siguiente turno
        jugadorActual = (jugadorActual + 1) % jugadores.size();
    }

    private int lanzarDados() {
        return (int) (Math.random() * 6) + 1; // Lanza un dado que devuelve un número entre 1 y 6
    }

    public void finalizarJuego(Jugador jugadorPerdedor) {
        System.out.println("El juego ha terminado. " + jugadorPerdedor.getNombre() + " ha perdido.");
        jugadores.remove(jugadorPerdedor);
        if (jugadores.size() == 1) {
            System.out.println("El ganador es " + jugadores.get(0).getNombre() + ".");
        }
        juegoTerminado = true;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public void setJuegoTerminado(boolean juegoTerminado) {
        this.juegoTerminado = juegoTerminado;
    }
    
    public Tablero getTablero() {
        return tablero;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(int jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    
    
}
