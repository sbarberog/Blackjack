package gui;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
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
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.FlowLayout;

public class GuiBlackjack extends JFrame {

	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel2;

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
		contentPane.setLayout(new MigLayout("", "[221.00][grow][150]", "[][][][250,grow][][250,grow][][63.00]"));
		
		JButton btnNuevoJuego = new JButton("Nueva Partida");
		contentPane.add(btnNuevoJuego, "cell 0 0 2 1,alignx center");
		
		JLabel lblNewLabel = new JLabel("Mazo (testeo)");
		contentPane.add(lblNewLabel, "cell 2 1,alignx center");
		
		panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Tu mano", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel1, "cell 0 1 2 3,grow");
		FlowLayout fl_panel1 = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panel1.setLayout(fl_panel1);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 2 2 1 4,grow");
		
		JTextArea txtMazo = new JTextArea();
		scrollPane.setViewportView(txtMazo);
		
		JButton btnPedirCarta = new JButton("Pedir Carta");
		btnPedirCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pruebaCarta();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Puntos: ");
		contentPane.add(lblNewLabel_1, "cell 0 4,alignx right");
		
		JLabel lblPuntos1 = new JLabel("");
		contentPane.add(lblPuntos1, "cell 1 4");
		
		panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Mano de la banca", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel2, "cell 0 5 2 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Puntos: ");
		contentPane.add(lblNewLabel_2, "cell 0 6,alignx right");
		
		JLabel lblPuntos2 = new JLabel("");
		contentPane.add(lblPuntos2, "cell 1 6");
		contentPane.add(btnPedirCarta, "flowx,cell 0 7 2 1");
		FlowLayout fl_panel2 = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panel2.setLayout(fl_panel2);
		
		JButton btnPlantarse = new JButton("Plantarse");
		btnPlantarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnPlantarse, "cell 0 7 2 1");
		
		JButton btnSalir = new JButton("Salir del juego");
		contentPane.add(btnSalir, "cell 2 7");
	}
	
	private void pruebaCarta() {
		// temporal, para testeo
		ImageIcon image = null;
		try {
			image = new ImageIcon(
			        ImageIO.read(getClass().getResource("/poker/"+"ace_of_hearts.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel1.add(new JLabel(image), FlowLayout.LEFT);
		panel1.revalidate();
		panel1.repaint();
		panel2.add(new JLabel(image), FlowLayout.LEFT);
		panel2.revalidate();
		panel2.repaint();
	}

}
