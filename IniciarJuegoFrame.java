/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MONOPOLYY;

/**
 *
 * @author juang
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IniciarJuegoFrame extends JFrame {
    private List<JTextField> espacioNombres;
    private JButton botondeIniciar;
    private int numJugadores;

    public IniciarJuegoFrame(int numJugadores) {
        super("Ingresar nombres de los jugadores");
        this.numJugadores = numJugadores;
        espacioNombres = new ArrayList<>();
        inicializarUI();
    }

    private void inicializarUI() {
        setLayout(new GridLayout(numJugadores + 1, 2)); // +1 por el botón
        setSize(400, 30 * numJugadores + 70);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for (int i = 0; i < numJugadores; i++) {
            add(new JLabel("Nombre del Jugador " + (i + 1) + ":"));
            JTextField campoTexto = new JTextField();
            espacioNombres.add(campoTexto);
            add(campoTexto);
        }

        botondeIniciar = new JButton("Iniciar Partida");
        botondeIniciar.addActionListener(e -> iniciarJuego());
        add(botondeIniciar);

        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);
    }

    private void iniciarJuego() {
        List<String> nombresJugadores = new ArrayList<>();
        for (JTextField campo : espacioNombres) {
            nombresJugadores.add(campo.getText().trim());
        }

        // Cerrar el formulario actual
        dispose();

        // Iniciar la interfaz principal del juego con los nombres recogidos
        InterfazMonopoly juegoGUI = new InterfazMonopoly(nombresJugadores);
        juegoGUI.setVisible(true);
    }

    public static void main(String[] args) {
        new IniciarJuegoFrame(4); // Asumiendo que queremos 4 jugadores
    }
}
