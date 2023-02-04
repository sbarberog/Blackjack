package gui;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import clases.Carta;
import clases.Juego;
import excepciones.NoHayCartasException;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GuiBlackjack extends JFrame {

	private JPanel contentPane;
	private JPanel panelJ;
	private JPanel panelB;
	private JButton btnPlantarse;
	private JButton btnPedirCarta;
	private JTextArea txtMazo;
	private JLabel lblEstado;
	private JLabel lblPuntosB;
	private JLabel lblPuntosJ;
	private JPanel panel;
	private JPanel panel_1;
	private AbstractButton btnNuevoJuego;
	private JCheckBox chkMazo;
	private JLabel lblVictorias;
	private JLabel lblEmpates;
	private JLabel lblDerrotas;
	private JPanel panel_2;
	private JPanel panel_4;
	private JPanel panel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBlackjack frame = new GuiBlackjack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiBlackjack() {
		setTitle("Blackjack v1.3");
		setMinimumSize(new Dimension(1400, 780));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1450, 830);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[221.00][grow][grow][150]", "[][grow][][][48.00,center][top][250,center][top][35:35.00,grow,top]"));
		
		btnNuevoJuego = new JButton("Nueva Partida");
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Juego.setTurnoJugador(true);
			}
		});
		contentPane.add(btnNuevoJuego, "cell 0 0 3 1,alignx center");
		
		JButton btnSalir = new JButton("Salir del juego");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnSalir, "cell 3 0,alignx center");
		
		btnPedirCarta = new JButton("Pedir Carta");
		btnPedirCarta.setEnabled(false);
		btnPedirCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Juego.pideCarta();
					} catch (NoHayCartasException e1) {
						JOptionPane.showMessageDialog(btnPedirCarta, "No quedan cartas en la baraja", "Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		contentPane.add(btnPedirCarta, "flowx,cell 0 1");
		
		lblEstado = new JLabel("");
		contentPane.add(lblEstado, "flowx,cell 1 1 2 1");
		
		JLabel lblNewLabel = new JLabel("Mazo (testeo)");
		contentPane.add(lblNewLabel, "cell 3 3,alignx center,aligny bottom");
		
		panel_3 = new JPanel();
		contentPane.add(panel_3, "cell 0 4 3 2,growx,aligny center");
		panel_3.setLayout(new MigLayout("", "[221.00][grow][grow]", "[48.00][top][top]"));
		
		panelJ = new JPanel();
		panel_3.add(panelJ, "cell 0 0 3 2,growx,aligny center");
		panelJ.setMinimumSize(new Dimension(600, 250));
		panelJ.setBackground(new Color(127, 255, 0));
		panelJ.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), 
				"Tu mano", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panelJ.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		panel = new JPanel();
		panel_3.add(panel, "cell 1 2,alignx left,aligny top");
		panel.setPreferredSize(new Dimension(160, 30));
		panel.setMaximumSize(new Dimension(160, 30));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		JLabel lblNewLabel_1 = new JLabel("Puntos de tu mano:");
		panel.add(lblNewLabel_1, "flowx,cell 0 0");
		
		lblPuntosJ = new JLabel("");
		panel.add(lblPuntosJ, "cell 0 0");
//		panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		contentPane.add(scrollPane, "cell 3 4 1 3,growx,aligny top");
		
		txtMazo = new JTextArea();
		txtMazo.setEditable(false);
		scrollPane.setViewportView(txtMazo);
		
		panel_4 = new JPanel();
		contentPane.add(panel_4, "cell 0 6 3 2,growx,aligny center");
		panel_4.setLayout(new MigLayout("", "[221.00][grow][grow]", "[250,center][center]"));
		
		panelB = new JPanel();
		panel_4.add(panelB, "cell 0 0 3 1,growx,aligny center");
		panelB.setPreferredSize(new Dimension(600, 250));
		panelB.setMaximumSize(new Dimension(32767, 250));
		panelB.setMinimumSize(new Dimension(600, 250));
		panelB.setBackground(new Color(128, 0, 0));
		panelB.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Mano de la banca", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		FlowLayout fl_panelB = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panelB.setLayout(fl_panelB);
		
		panel_1 = new JPanel();
		panel_4.add(panel_1, "cell 1 1,growx,aligny top");
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setPreferredSize(new Dimension(160, 30));
		panel_1.setMaximumSize(new Dimension(160, 30));
		panel_1.setLayout(new MigLayout("", "[grow]", "[top]"));
		
		JLabel lblNewLabel_2 = new JLabel("Puntos: de la banca:");
		panel_1.add(lblNewLabel_2, "flowx,cell 0 0");
		
		lblPuntosB = new JLabel("");
		panel_1.add(lblPuntosB, "cell 0 0");
		
		btnPlantarse = new JButton("Plantarse");
		btnPlantarse.setEnabled(false);
		btnPlantarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Juego.setTurnoJugador(false);
			}
		});
		contentPane.add(btnPlantarse, "cell 0 1");
		
		chkMazo = new JCheckBox("Mostrar/Ocultar mazo");
		chkMazo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if (e.getStateChange() == ItemEvent.SELECTED) {
					 scrollPane.setVisible(true);
				 } else if (e.getStateChange() == ItemEvent.DESELECTED) {
					 scrollPane.setVisible(false);
				 }
			}
		});
		contentPane.add(chkMazo, "cell 3 7,aligny top");
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, "flowx,cell 2 8,alignx right,aligny top");
		panel_2.setLayout(new MigLayout("", "[grow]", "[top]"));
		
		lblVictorias = new JLabel("v");
		panel_2.add(lblVictorias, "flowx,cell 0 0");
		
		lblEmpates = new JLabel("e");
		panel_2.add(lblEmpates, "cell 0 0");
		
		lblDerrotas = new JLabel("d");
		panel_2.add(lblDerrotas, "cell 0 0");
	}
	
	public void empiezaJuego() {
		btnNuevoJuego.setEnabled(false);
		btnPedirCarta.setEnabled(true);
		btnPlantarse.setEnabled(true);
		panelJ.removeAll();
		panelJ.revalidate();
		panelJ.repaint();
		panelB.removeAll();
		panelB.revalidate();
		panelB.repaint();
		lblEstado.setText("¿Pides carta o te plantas? (No te puedes pasar de 21 puntos)");
		actualizaMazo();
		actualizaPuntos();
		Sonido.sonidoBarajar();
	}
	
	protected void actualizaMazo() {
		txtMazo.setText(Juego.baraja.toString());
		txtMazo.setCaretPosition(0);;
	}

	public void muestraCartaJ(Carta c, int pos) {
		ImageIcon image = null;
		try {
			image = new ImageIcon(
			        ImageIO.read(getClass().getResource("/poker/"+c.imagenCarta()+".png")));
			panelJ.add(new JLabel(image), pos);
			panelJ.revalidate();
			panelJ.repaint();
			actualizaMazo();
			Sonido.sonidoNaipe();
		} catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "No se ha cargado la imagen", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void muestraCartaB(Carta c, int pos) {
		ImageIcon image = null;
		try {
			image = new ImageIcon(
			        ImageIO.read(getClass().getResource("/poker/"+c.imagenCarta()+".png")));
			panelB.add(new JLabel(image), pos);
			panelB.revalidate();
			panelB.repaint();
			actualizaPuntos();
			actualizaMazo();
			Sonido.sonidoNaipe();
		} catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "No se ha cargado la imagen", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void turnoBanca() {
		btnPedirCarta.setEnabled(false);
		btnPlantarse.setEnabled(false);
		lblEstado.setText("Turno de la banca...");
		JOptionPane.showMessageDialog(this, "Tu turno ha terminado.\n"+ "Valor de la mano: "
				+Juego.jugador.valorMano()+"\nTurno de la banca...", "Fin de tu turno", JOptionPane.INFORMATION_MESSAGE);
	}

	public void actualizaPuntos() {
		lblPuntosJ.setText(""+Juego.jugador.valorMano());
		lblPuntosB.setText(""+Juego.banca.valorMano());
		
	}

	public void finDePartida() {
		actualizaContador();
		int seguir=JOptionPane.showConfirmDialog(this, "¿Deseas seguir jugando?\nPiénsatelo bien...", "¿Seguir jugando?", JOptionPane.YES_NO_OPTION);
		
		switch (seguir) {
		case JOptionPane.YES_OPTION: Juego.setTurnoJugador(true);
		case JOptionPane.NO_OPTION:
		default: btnNuevoJuego.setEnabled(true);
		}
		
	}

	public void puntuacionFinal() {
		JOptionPane.showMessageDialog(this, "Puntos de tu mano: "+Juego.jugador.valorMano()
			+"\n\nPuntos de la banca: "+Juego.banca.valorMano(), "Puntuación final", JOptionPane.INFORMATION_MESSAGE);
	}
	public void ganaBanca() {
		lblEstado.setText("La banca gana :(");
		Sonido.sonidoDerrota();
		JOptionPane.showMessageDialog(this, "Lo sentimos, gana la banca...", "Has perdido", JOptionPane.ERROR_MESSAGE);
	}

	public void ganasTu() {
		lblEstado.setText("¡Has ganado! :)");
		Sonido.sonidoVictoria();
		JOptionPane.showMessageDialog(this, "¡Enhorabuena!\n\nEstás en racha :)", "¡Has ganado!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void ganaNadie() {
		lblEstado.setText("No hay ganador");
		Sonido.sonidoEmpate();
		JOptionPane.showMessageDialog(this, "No hay ganador, así que se te devolverá la apuesta", "Empate", JOptionPane.WARNING_MESSAGE);
	}

	public void actualizaContador() {
		lblVictorias.setText("Victorias: "+Juego.victorias+" | ");
		lblEmpates.setText("Empates: "+Juego.empates+" | ");
		lblDerrotas.setText("Derrotas: "+Juego.derrotas+"  ");
		
	}
}
