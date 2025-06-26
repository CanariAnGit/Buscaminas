/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package buscaminas;
import javax.swing.*;  //Para los botones.
import java.awt.event.*;  //Para actionListener
import java.lang.UnsupportedOperationException;  //Para la acción del botón.
import java.util.ArrayList;

/**
 *
 * @author a.gonzalezro.2022
 */
public class FrameTableroBuscaminas extends javax.swing.JFrame {
    
    
    private JButton[][] botonCasilla;  //Definimos una matriz de botones para rellenar nuestro tablero.
    private ArrayList<Casilla> seleccionadas=new ArrayList<Casilla>();  //Almacena las casillas que se van seleccionando.
    private static DatosCompartidosVentanas datos=new DatosCompartidosVentanas();

    /**
     * Creates new form FrameTableroBuscaminas
     */
        
    public FrameTableroBuscaminas() {
        initComponents();
        crearBotones();        
        datos.crearPartida();
        nombreJugador1.setText(datos.getPartida().getJugadorActual().getUsuario() + ":");
        nombreJugador2.setText(datos.getPartida().getJugadorEsperando().getUsuario() + ":");
        turnoJugador.setText(datos.getPartida().getJugadorActual().getUsuario());
        puntosJ1.setText(String.valueOf(datos.getPartida().getMarcador().getMinasJ1()));
        puntosJ2.setText(String.valueOf(datos.getPartida().getMarcador().getMinasJ2()));
    }
    
    private void actualizarMarcador(){  //Cambia el valor de los marcadores si procede.
        puntosJ1.setText(String.valueOf(datos.getPartida().getMarcador().getMinasJ1()));
        puntosJ2.setText(String.valueOf(datos.getPartida().getMarcador().getMinasJ2()));
    }
    
    private void revelarAdyacentes(Casilla aux){
        ArrayList<Casilla> adyacentes;
        adyacentes=datos.getPartida().getTablero().casillasAdyacentes(aux);
        for(Casilla c : adyacentes){
            if(seleccionadas.contains(c)==false){                
                this.seleccionadas.add(c);
                JButton boton=botonCasilla[c.getX()][c.getY()];
                boton.setText(c.getContenido());
                boton.setEnabled(false);
                boton.setForeground(datos.getPartida().getColorCasilla());
                boton.setOpaque(true);
                boton.setContentAreaFilled(false);
                if(c.getContenido().equals("0")){  //Si el contenido vuelve a ser 0.
                    revelarAdyacentes(c);
                }                
            }
        }
    }
    
    private void crearBotones(){  //Coloca todos los botones en el tablero.
        
        botonCasilla=new JButton[16][16];
        
        for(int fila=0; fila<16; fila++){  //Recorre las filas.
            for(int columna=0; columna<16; columna++){  //Recorre las columnas.
                botonCasilla[fila][columna]=new JButton();  //Asigna un botón a cada una de las posiciones de la matriz de botones.
                botonCasilla[fila][columna].setName(fila + "," + columna);  //Le otorga un nombre a cada botón según su coordenada.
                botonCasilla[fila][columna].setBorder(null);
                if(fila==0 && columna==0){   //Coloca el primer botón, a partir del cual se distribuirán los demás.
                    botonCasilla[fila][columna].setBounds(250,25,30,30);
                }else if(fila==0  && columna!=0){  //Coloca la primera fila de botones a partir del anterior.
                    botonCasilla[fila][columna].setBounds(botonCasilla[fila][columna-1].getX()+botonCasilla[fila][columna-1].getWidth(),25,30,30);
                }else{  //El resto de botones. Crea el resto de filas a partir de la anterior.
                    botonCasilla[fila][columna].setBounds(botonCasilla[fila-1][columna].getX(), botonCasilla[fila-1][columna].getY()+botonCasilla[fila-1][columna].getHeight(),30,30);
                }
                ActionListener accion=new ActionListener(){  //Crea un elemento de tipo acción para definir qué hacer al pulsar.
                    @Override
                    public void actionPerformed(ActionEvent evento){  //Define la acción que debe realizar un botón.
                        alClickear(evento);
                    }
                };
                botonCasilla[fila][columna].addActionListener(accion);          
            
                getContentPane().add(botonCasilla[fila][columna]);   //Añade los botones al contentPane.
            }
        }
        
    }
    
    private void alClickear(ActionEvent e){  //Qué hacer al pulsar el botón.
        JButton boton=(JButton)e.getSource();
        String[] coordenada=boton.getName().split(",");
        //Separa el nombre del botón, que contiene sus coordenadas en un array de dos elementos.
        int coordX=Integer.parseInt(coordenada[0]);
        int coordY=Integer.parseInt(coordenada[1]);  //Guardar las coordenadas como enteros.
        Partida partida=datos.getPartida();      
        
            partida.setMovimiento(coordX, coordY);
            Casilla aux=partida.getTablero().getCasilla(coordX, coordY);
            boton.setText(aux.getContenido()); 
            //Coloca en el botón el símbolo tras ser revelado.
                        
            if(aux.getContenido().equals("0") && this.seleccionadas.contains(aux)==false){  //Si el contenido de la casilla es 0 y no ha sido seleccionada.
                //Se revelan las casillas adyacentes.
                this.seleccionadas.add(aux);
                boton.setEnabled(false);
                boton.setForeground(partida.getColorCasilla());
                boton.setOpaque(true);
                boton.setContentAreaFilled(false);
                revelarAdyacentes(aux);
            }else{
                this.seleccionadas.add(aux);
                boton.setEnabled(false);
                boton.setForeground(partida.getColorCasilla());
                boton.setOpaque(true);
                boton.setContentAreaFilled(false);
            }            
            partida.jugar();
            actualizarMarcador();
            turnoJugador.setText(partida.getJugadorActual().getUsuario());  
            
        if(partida.gameOver()){  //Abre la ventana de fin de juego y muestra quién es el ganador y el marcador.
            partida.setGanador(partida.getJugadorActual());
            datos.rellenarResultadoPartida();/*
            datos.getJ2().añadirPartida(partida);
            datos.getJ2().actualizarEstadisticas(partida, partida.getMarcador().getMinasJ2(), partida.getMarcador().getMinasJ1());*/
            datos.organizarPartidas();  //Coloca las partidas de la lista a los jugadores adecuados.
            for(int i=0;i<16;i++){
                for(int j=0;j<16;j++){
                    JButton pulsador=this.botonCasilla[i][j];
                    if(seleccionadas.contains(partida.getTablero().getCasilla(i, j))==false){                        
                        pulsador.setText(partida.getTablero().getCasilla(i, j).getContenido());
                        pulsador.setEnabled(false);
                    }
                }
            }
            texto.setText("El ganador es: ");            
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nombreJugador1 = new javax.swing.JLabel();
        nombreJugador2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        texto = new javax.swing.JLabel();
        turnoJugador = new javax.swing.JLabel();
        puntosJ1 = new javax.swing.JLabel();
        puntosJ2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 650));
        setResizable(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscaminas/JugadorRojo (1).png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscaminas/JugadorAzul (1).png"))); // NOI18N

        nombreJugador1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        nombreJugador1.setText(":");

        nombreJugador2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        nombreJugador2.setText(":");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscaminas/PortadaBuscamins (1).png"))); // NOI18N

        texto.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        texto.setText("Es el turno de:");

        turnoJugador.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        turnoJugador.setText(":");

        puntosJ1.setFont(new java.awt.Font("Bookman Old Style", 0, 90)); // NOI18N
        puntosJ1.setText(".");

        puntosJ2.setFont(new java.awt.Font("Bookman Old Style", 0, 90)); // NOI18N
        puntosJ2.setText(".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 1561, Short.MAX_VALUE)
                .addComponent(texto)
                .addGap(18, 18, 18)
                .addComponent(turnoJugador)
                .addGap(100, 100, 100)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreJugador1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(nombreJugador2))
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(puntosJ1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(puntosJ2)
                .addGap(105, 105, 105))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreJugador1)
                    .addComponent(nombreJugador2))
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(puntosJ1)
                    .addComponent(puntosJ2))
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 442, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(texto)
                            .addComponent(turnoJugador))
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameTableroBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameTableroBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameTableroBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameTableroBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                FrameTableroBuscaminas tablerojuego=new FrameTableroBuscaminas();
                tablerojuego.setVisible(true);
                tablerojuego.setLocationRelativeTo(null);  //Eliminamos la referencia a la localización de la ventana en pantalla
                //para que aparezca centrada al abrir el juego.   
                tablerojuego.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel nombreJugador1;
    private javax.swing.JLabel nombreJugador2;
    private javax.swing.JLabel puntosJ1;
    private javax.swing.JLabel puntosJ2;
    private javax.swing.JLabel texto;
    private javax.swing.JLabel turnoJugador;
    // End of variables declaration//GEN-END:variables
}
