package clases;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.NoHayCartasException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextArea txtMano;
	private JTextArea txtMazo;
	private JButton btnPedirCarta;
	private JButton btnPlantarse;
	private Mazo mazo;
	private Mano mano;
	private JLabel lblOpcion;
	private JButton btnNuevo;
	private Mano mBanca;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
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
	public Gui() {
		setTitle("Blackjack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow][200px][][100px:n,grow]", "[][][][grow][grow][]"));

		btnNuevo = new JButton("Nuevo Juego");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoJuego();
			}
		});
		contentPane.add(btnNuevo, "cell 0 0 4 1,alignx center");

		JLabel lblNewLabel = new JLabel("Mano actual:");
		contentPane.add(lblNewLabel, "cell 0 2,alignx center");

		lblOpcion = new JLabel("");
		contentPane.add(lblOpcion, "cell 1 2 2 1,alignx left");

		JLabel lblNewLabel_2 = new JLabel("Mazo:");
		contentPane.add(lblNewLabel_2, "cell 3 2,alignx center");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 3 1 2,grow");

		txtMano = new JTextArea();
		txtMano.setEditable(false);
		scrollPane.setViewportView(txtMano);

		btnPedirCarta = new JButton("Pedir Carta");
		btnPedirCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pedirCarta();
			}
		});
		btnPedirCarta.setEnabled(false);
		contentPane.add(btnPedirCarta, "flowx,cell 1 3 1 2,aligny center");

		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 3 3 1 2,grow");

		txtMazo = new JTextArea();
		txtMazo.setEditable(false);
		scrollPane_1.setViewportView(txtMazo);

		btnPlantarse = new JButton("Plantarse");
		btnPlantarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plantarse();
			}
		});
		btnPlantarse.setEnabled(false);
		contentPane.add(btnPlantarse, "cell 1 4");

		btnSalir = new JButton("Mejor me voy");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnSalir, "cell 1 5,alignx center");
	}

	protected void plantarse() {
		lblOpcion.setText("Te has plantado");
//		JOptionPane.showMessageDialog(btnPlantarse, "Puntuación final: " + mano.valorMano(), "Te has plantado",
//				JOptionPane.INFORMATION_MESSAGE);
		juegaBanca();
	}

	private void juegaBanca() {
		mBanca = new Mano();
		do {
			try {
				mBanca.pedirCarta(mazo);
			} catch (NoHayCartasException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (mano.valorMano() > 21) {
				ganaBanca();
				return;
			}
		} while (mBanca.valorMano() <= mano.valorMano());

		if (mBanca.valorMano() <= 21) {
			ganaBanca();
		} else {
			ganasTu();
		}
	}

	private void ganasTu() {
		JOptionPane.showMessageDialog(btnPedirCarta,
				"Mano de la banca:\n" + mBanca + "\nTu puntuación: " + mano.valorMano()
						+ "\n¡Has ganado! Sigue jugando, que estás en racha",
				"¡Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);
		lblOpcion.setText("¡Has ganado!");
		this.finDeJuego();
	}

	private void ganaBanca() {
		JOptionPane
				.showMessageDialog(btnPedirCarta,
						"Mano de la banca:\n" + mBanca + "\nTu puntuación: " + mano.valorMano()
								+ "\nHas perdido, mejor vete a casa...",
						"No ha habido suerte", JOptionPane.ERROR_MESSAGE);
		lblOpcion.setText("Has perdido...");
		this.finDeJuego();
	}

	protected void pedirCarta() {
		try {
			mano.pedirCarta(mazo);
		} catch (NoHayCartasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actualizaMano();
		actualizaMazo();

		if (mano.finDeJuego()) {
			juegaBanca();
		}
	}

	private void finDeJuego() {
		int continuar = JOptionPane.showConfirmDialog(btnPlantarse, "¿Seguir jugando? Piénsalo bien...", "¿Continuar?",
				JOptionPane.YES_NO_OPTION);

		switch (continuar) {
		case JOptionPane.YES_OPTION:
			nuevoJuego();
			break;
		default: {
			this.btnNuevo.setEnabled(true);
			this.btnPedirCarta.setEnabled(false);
			this.btnPlantarse.setEnabled(false);
		}

		}
	}

	protected void nuevoJuego() {
		mazo = new Mazo();
		mano = new Mano();
		mazo.barajar();
		this.actualizaMano();
		this.actualizaMazo();
		this.btnNuevo.setEnabled(false);

		btnPedirCarta.setEnabled(true);
		btnPlantarse.setEnabled(true);
		lblOpcion.setText("¿Pedir una carta o plantarse?");

	}

	protected void actualizaMazo() {
		txtMazo.setText("" + mazo);
		txtMazo.setCaretPosition(0);
	}

	protected void actualizaMano() {
		txtMano.setText("" + mano);
	}

}
