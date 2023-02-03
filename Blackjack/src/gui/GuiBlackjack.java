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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import javax.swing.border.TitledBorder;

import clases.Carta;
import clases.Juego;
import excepciones.NoHayCartasException;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;

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
		contentPane.setLayout(new MigLayout("", "[221.00][grow][150]", "[][][][][250,grow][][63.00]"));
		
		JButton btnNuevoJuego = new JButton("Nueva Partida");
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Juego.nuevoJuego();
				btnPedirCarta.setEnabled(true);
				btnPlantarse.setEnabled(true);
				lblEstado.setText("¿Pides carta o te plantas?");
				actualizaMazo();
			}
		});
		contentPane.add(btnNuevoJuego, "cell 0 0 2 1,alignx center");
		
		JLabel lblNewLabel = new JLabel("Mazo (testeo)");
		contentPane.add(lblNewLabel, "cell 2 1,alignx center");
		
		panelJ = new JPanel();
		panelJ.setMinimumSize(new Dimension(600, 250));
		panelJ.setBackground(Color.GREEN);
		panelJ.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Tu mano", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelJ, "cell 0 1 2 2,grow");
		panelJ.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
//		panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 2 2 1 3,grow");
		
		txtMazo = new JTextArea();
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
		contentPane.add(panel, "cell 0 3 2 1,grow");
		panel.setLayout(new MigLayout("", "[221.00][grow]", "[]"));
		
		JLabel lblNewLabel_1 = new JLabel("Puntos: ");
		panel.add(lblNewLabel_1, "cell 0 0,alignx right");
		
		lblPuntosJ = new JLabel("");
		panel.add(lblPuntosJ, "cell 1 0");
		
		panelB = new JPanel();
		panelB.setMinimumSize(new Dimension(600, 250));
		panelB.setBackground(Color.GREEN);
		panelB.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Mano de la banca", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelB, "cell 0 4 2 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Puntos: ");
		contentPane.add(lblNewLabel_2, "cell 0 5,alignx right");
		
		lblPuntosB = new JLabel("");
		contentPane.add(lblPuntosB, "cell 1 5");
		contentPane.add(btnPedirCarta, "flowx,cell 0 6");
		FlowLayout fl_panelB = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panelB.setLayout(fl_panelB);
		
		btnPlantarse = new JButton("Plantarse");
		btnPlantarse.setEnabled(false);
		btnPlantarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				turnoBanca();
			}
		});
		contentPane.add(btnPlantarse, "flowx,cell 0 6");
		
		JButton btnSalir = new JButton("Salir del juego");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnSalir, "cell 2 6");
		
		lblEstado = new JLabel("");
		contentPane.add(lblEstado, "cell 1 6");
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
//			JOptionPane.showMessageDialog(btnPedirCarta, "No se ha cargado la imagen", "Error", JOptionPane.ERROR_MESSAGE);
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
			actualizaMazo();
		} catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "No se ha cargado la imagen", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void turnoBanca() {
		JOptionPane.showMessageDialog(this, "Tu turno ha terminado.\n"+ "Valor de la mano: "
				+Juego.jugador.valorMano()+"\nTurno de la banca...", "Fin de tu turno", JOptionPane.INFORMATION_MESSAGE);
		try {
			Juego.juegaBanca();
		} catch (NoHayCartasException e) {
			JOptionPane.showMessageDialog(btnPedirCarta, "No quedan cartas en la baraja", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actualizaPuntos() {
		lblPuntosJ.setText(""+Juego.jugador.valorMano());
		lblPuntosB.setText(""+Juego.banca.valorMano());
		
	}
}
