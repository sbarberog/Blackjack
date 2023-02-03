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
		setTitle("Blackjack");
		setMinimumSize(new Dimension(1360, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 1410, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[221.00][grow][150]", "[][48.00][center][top][250,center][top][63.00]"));
		
		btnNuevoJuego = new JButton("Nueva Partida");
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Juego.setTurnoJugador(true);
			}
		});
		contentPane.add(btnNuevoJuego, "cell 0 0 2 1,alignx center");
		
		JButton btnSalir = new JButton("Salir del juego");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnSalir, "cell 2 0,alignx center");
		
		JLabel lblNewLabel = new JLabel("Mazo (testeo)");
		contentPane.add(lblNewLabel, "cell 2 1,alignx center,aligny bottom");
		
		panelJ = new JPanel();
		panelJ.setMinimumSize(new Dimension(600, 250));
		panelJ.setBackground(new Color(0, 255, 127));
		panelJ.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), 
				"Tu mano", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		contentPane.add(panelJ, "cell 0 1 2 2,grow");
		panelJ.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
//		panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		contentPane.add(scrollPane, "cell 2 2 1 4,grow");
		
		txtMazo = new JTextArea();
		txtMazo.setEditable(false);
		scrollPane.setViewportView(txtMazo);
		
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
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(160, 30));
		panel.setMaximumSize(new Dimension(160, 30));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel, "cell 1 3,alignx left,growy");
		panel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		JLabel lblNewLabel_1 = new JLabel("Puntos de tu mano:");
		panel.add(lblNewLabel_1, "flowx,cell 0 0");
		
		lblPuntosJ = new JLabel("");
		panel.add(lblPuntosJ, "cell 0 0");
		
		panelB = new JPanel();
		panelB.setMinimumSize(new Dimension(600, 250));
		panelB.setBackground(new Color(0, 255, 127));
		panelB.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), 
				"Mano de la banca", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 0, 0)));
		contentPane.add(panelB, "cell 0 4 2 1,grow");
		
		panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setPreferredSize(new Dimension(160, 30));
		panel_1.setMaximumSize(new Dimension(160, 30));
		contentPane.add(panel_1, "cell 1 5,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[]"));
		
		JLabel lblNewLabel_2 = new JLabel("Puntos: de la banca:");
		panel_1.add(lblNewLabel_2, "flowx,cell 0 0");
		
		lblPuntosB = new JLabel("");
		panel_1.add(lblPuntosB, "cell 0 0");
		contentPane.add(btnPedirCarta, "flowx,cell 0 6");
		FlowLayout fl_panelB = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panelB.setLayout(fl_panelB);
		
		btnPlantarse = new JButton("Plantarse");
		btnPlantarse.setEnabled(false);
		btnPlantarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Juego.setTurnoJugador(false);
			}
		});
		contentPane.add(btnPlantarse, "flowx,cell 0 6");
		
		lblEstado = new JLabel("");
		contentPane.add(lblEstado, "flowx,cell 1 6");
		
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
		contentPane.add(chkMazo, "cell 2 6");
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
		lblEstado.setText("¿Pides carta o te plantas? (No te puedes pasar de 21)");
		actualizaMazo();
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
		JOptionPane.showMessageDialog(this, "Lo sentimos, gana la banca...", "Has perdido", JOptionPane.ERROR_MESSAGE);
	}

	public void ganasTu() {
		lblEstado.setText("¡Has ganado! :)");
		JOptionPane.showMessageDialog(this, "¡Enhorabuena!\n\nEstás en racha :)", "¡Has ganado!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void ganaNadie() {
		lblEstado.setText("No hay ganador");
		JOptionPane.showMessageDialog(this, "No hay ganador, así que se te devolverá la apuesta", "Empate", JOptionPane.WARNING_MESSAGE);
	}
}
