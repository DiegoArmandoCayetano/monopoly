/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MONOPOLYY;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author juang
 */
public class InterfazMonopoly extends JFrame {
    private Juego juego;
    private JPanel panelTablero;
    private JPanel panelInfo;
    private JLabel labelInfo;
    private ArrayList<JLabel> labelsJugadores;
    public JLabel[][] casillasLabels = new JLabel[11][11];

    public InterfazMonopoly(List<String> nombresJugadores) {
        setTitle("Monopoly");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el juego con los nombres de los jugadores
        juego = new Juego(nombresJugadores);
        labelsJugadores = new ArrayList<>();
        
        setupTablero();
        setupPanelInfo();

        setVisible(true);
    }

   
    private void setupTablero() {
    panelTablero = new JPanel();
    // Configura el panelTablero con 10 filas y 4 columnas
    panelTablero.setLayout(new GridLayout(8, 5)); // Ajusta según la visualización deseada

    // Inicializa casillasLabels para almacenar las etiquetas de las casillas
    casillasLabels = new JLabel[10][4]; // Asegúrate de que esto refleja el layout de GridLayout

    // Recorre la lista de casillas para colocar cada casilla en el grid
    List<Casilla> casillasDelJuego = juego.getTablero().getCasillas();
    if (casillasDelJuego.size() != 40) {
        throw new IllegalStateException("Se requiere un total de 40 casillas definidas.");
    }

    int index = 0;
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 4; j++) {
            JLabel labelCasilla = new JLabel();
            labelCasilla.setBorder(BorderFactory.createLineBorder(Color.black));
            labelCasilla.setHorizontalAlignment(JLabel.CENTER);
            labelCasilla.setVerticalAlignment(JLabel.CENTER);
            labelCasilla.setOpaque(true);

            // Configura el texto y el fondo de la casilla
            Casilla casilla = casillasDelJuego.get(index);
            labelCasilla.setText(casilla.getNombre());
            labelCasilla.setBackground(definirColorParaCasilla(casilla));

            casillasLabels[i][j] = labelCasilla;
            panelTablero.add(labelCasilla);

            index++;  
        }
    }

    add(panelTablero, BorderLayout.CENTER);
}

    private void setupPanelInfo() {
        JPanel panelInfo = new JPanel(new BorderLayout());
        JPanel panelJugadores = new JPanel(new GridLayout(juego.getJugadores().size(), 1)); // Un jugador por fila
        
        // Añadir labels para cada jugador con su nombre
        for (Jugador jugador : juego.getJugadores()) {
        JLabel labelJugador = new JLabel(formatoInfoJugador(jugador));
        labelJugador.setBorder(BorderFactory.createLineBorder(Color.black));
        panelJugadores.add(labelJugador);
        labelsJugadores.add(labelJugador); 
    }

        panelInfo.add(new JScrollPane(panelJugadores), BorderLayout.CENTER); 

    JButton botonLanzarDados = new JButton("Lanzar Dados");
    botonLanzarDados.addActionListener(e -> {
        juego.jugarTurno();
        actualizarUI();
    });
    panelInfo.add(botonLanzarDados, BorderLayout.SOUTH);

    add(panelInfo, BorderLayout.EAST);
}
    
    

private String formatoInfoJugador(Jugador jugador) {
    
    return "<html>Jugador: " + jugador.getNombre() +
           "<br/>Dinero: $" + jugador.getDinero() +
           "<br/>Propiedades: " + jugador.getPropiedades().size() + "</html>";
}
    
    private void actualizarUI() {
    SwingUtilities.invokeLater(() -> {
        
        int index = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel labelCasilla = casillasLabels[i][j];
                Casilla casilla = juego.getTablero().getCasillas().get(index);
                labelCasilla.setText(casilla.getNombre());   
                labelCasilla.setBackground(definirColorParaCasilla(casilla));   
                
                // Limpia cualquier texto de jugador previamente mostrado
                labelCasilla.setText("<html>" + casilla.getNombre() + "<br/></html>");

                index++; 
            }
        }

        // Actualizar la posición de los jugadores en el tablero
        for (Jugador jugador : juego.getJugadores()) {
            int posicion = jugador.getPosicion();
            int fila = posicion / 4;
            int columna = posicion % 4;
            JLabel casillaLabel = casillasLabels[fila][columna];
            String textoCasilla = "<html>" + juego.getTablero().getCasillas().get(posicion).getNombre() +
                                  "<br/>" + jugador.getNombre() + " aquí</html>";
            casillaLabel.setText(textoCasilla);
            casillaLabel.setBackground(Color.CYAN);   
        }

        // Actualiza la información de los jugadores en la interfaz
        for (int i = 0; i < juego.getJugadores().size(); i++) {
            Jugador jugador = juego.getJugadores().get(i);
            JLabel labelJugador = labelsJugadores.get(i);
            labelJugador.setText(formatoInfoJugador(jugador));
        }
    });
}

    
    private int[] convertirPosicionACoordenadas(int posicion) {
    if (posicion < 10) {
         
        return new int[] {10, posicion};   
    } else if (posicion < 20) {
         
        return new int[] {20 - posicion, 0}; 
    } else if (posicion < 30) {
         
        return new int[] {0, posicion - 20};  
    } else {
        
        return new int[] {posicion - 30, 10};  // fila, columna
    }
}
    
    private int transformarCoordenadasEnÍndice(int fila, int columna) {
    // Recorrido antihorario desde 'Go'
    if (fila == 10) {
        // Borde inferior, de izquierda a derecha desde 'Go' hasta 'Just Visiting'
        return columna; // 0 a 9
    } else if (columna == 0) {
         
        return 10 + (10 - fila); // 10 a 19
    } else if (fila == 0) {
        
        return 20 + columna;  
    } else if (columna == 10) {
         
        return 30 + (10 - fila);  
    }
    return -1;  // Coordenada no válida para casillas jugables
}

private int[] asignarCoordenadasDesdePosicion(int posicion) {
    if (posicion < 10) {  // Borde inferior
        return new int[] {10, posicion};
    } else if (posicion < 20) {  // Borde derecho
        return new int[] {20 - posicion, 10};
    } else if (posicion < 30) {  // Borde superior
        return new int[] {0, 30 - posicion};
    } else if (posicion < 40) {  // Borde izquierdo
        return new int[] {posicion - 30, 0};
    }
    return null;
    // Por si la posición no es válida
}
    
    

private Color definirColorParaCasilla(Casilla casilla) {
    if (casilla instanceof Propiedad) {
        return Color.GREEN;
    } else if (casilla instanceof Carcel) {
        return Color.RED;
    } else if (casilla instanceof Impuestos) {
        return Color.blue;
    } else if (casilla instanceof Estacion) {
        return Color.LIGHT_GRAY;
    } else if (casilla instanceof ServicioPublico) {
        return Color.yellow;
    } else if (casilla instanceof CajaComunidad || casilla instanceof Suerte) {
        return Color.CYAN;
    } else if (casilla instanceof Salida) {
        return Color.PINK;
    } else if (casilla instanceof Libre) {
        return Color.white;
    } else {
        return Color.RED;
    }
}


    private void modificarEtiquetaJugador(Jugador jugador, JLabel labelJugador) {
        String texto = "<html>Jugador: " + jugador.getNombre() +
                       "<br/>Dinero: $" + jugador.getDinero() +
                       "<br/>Propiedades: " + jugador.getPropiedades().size() + "</html>";
        labelJugador.setText(texto);
    }
    private boolean hayJugadorEnCasilla(int index) {
    for (Jugador jugador : juego.getJugadores()) {
        if (jugador.getPosicion() == index) {
            return true;  // Hay un jugador ubicado en la casilla especificada
        }
    }
    return false;  // No hay ningún jugador presente en esa casilla
}

    public static void main(String[] args) {
        
        List<String> nombresJugadores = Arrays.asList("diego", "carlos", "jimy", "jhonatan");
        new InterfazMonopoly(nombresJugadores);
    }
}
