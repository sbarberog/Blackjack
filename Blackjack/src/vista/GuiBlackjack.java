package vista;

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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import excepciones.NoHayCartasException;
import modelo.Carta;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.border.SoftBevelBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class GuiBlackjack extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JCheckBox chkEfectos;
	private JLabel lblNewLabel;
	private JPanel panel_5;
	private static String pathCarta = "/cartas/";
	private static String pathIcono = "/iconos/";
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenu mnNewMenu_1;
	private JCheckBoxMenuItem rbtMenuSonido;
	private JCheckBoxMenuItem chkMenuMazo;
	private JMenu mnNewMenu_2;
	private JSeparator separator;
	private JMenu mnNewMenu_3;
	private JMenu mnNewMenu_4;
	private JCheckBoxMenuItem chkBancaV;
	private JCheckBoxMenuItem chkBancaR;
	private JCheckBoxMenuItem chkBancaN;
	private JRadioButtonMenuItem chkJugadorV;
	private JRadioButtonMenuItem chkJugadorR;
	private JRadioButtonMenuItem chkJugadorN;
	private final ButtonGroup grupoColorJ = new ButtonGroup();
	private JScrollPane scrollPane;
	private final ButtonGroup grupoColorB = new ButtonGroup();
	private JPanel panel_6;
	private JPanel panel_7;
	private static Color verdeOscuro = new Color(0, 128, 0);
	private static Color rojoOscuro = new Color(128, 0, 0);
	private JCheckBoxMenuItem rbtMenuMusica;
	private JSeparator separator_1;
	private JPanel panelDibujo;
	private JPanel panel_8;
	private JLabel lblpuedesHacerClick;
	private Controlador controlador;
	private JLabel lblPartidasJ;
	private JCheckBox chkMusica;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					try {
//						for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//							if ("Nimbus".equals(info.getName())) {
//								UIManager.setLookAndFeel(info.getClassName());
//								break;
//							}
//						}
//					} catch (Exception e) {
//						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//					}
//					GuiBlackjack frame = new GuiBlackjack();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GuiBlackjack() {
		setPreferredSize(new Dimension(1450, 815));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		setTitle("Blackjack v1.8");
		setMinimumSize(new Dimension(1450, 815));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 10, 1448, 817);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		ImageIcon pedir = null, pasar = null;
		try {
			pedir = new ImageIcon(ImageIO.read(getClass().getResource(pathIcono + "pedir" + ".png")));
			pasar = new ImageIcon(ImageIO.read(getClass().getResource(pathIcono + "pasar" + ".png")));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Salir");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		mnNewMenu_1 = new JMenu("Opciones");
		menuBar.add(mnNewMenu_1);

		rbtMenuSonido = new JCheckBoxMenuItem("Efectos de sonido");
		rbtMenuSonido.setSelected(true);
		rbtMenuSonido.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					controlador.setEfectos(true);
				} else {
					controlador.setEfectos(false);
				}
				chkEfectos.setSelected(controlador.isEfectos());
			}
		});
		mnNewMenu_1.add(rbtMenuSonido);

		chkMenuMazo = new JCheckBoxMenuItem("Mostrar/Ocultar mazo");
		chkMenuMazo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					mostrarMazo(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					mostrarMazo(false);
				}
			}
		});

		rbtMenuMusica = new JCheckBoxMenuItem("Música de fondo");
		rbtMenuMusica.setSelected(true);
		rbtMenuMusica.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (!controlador.isMusica())
						controlador.setMusica(true);
				} else {
					if (controlador.isMusica())
						controlador.setMusica(false);
				}
				chkMusica.setSelected(controlador.isMusica());
			}
		});
		mnNewMenu_1.add(rbtMenuMusica);

		separator_1 = new JSeparator();
		mnNewMenu_1.add(separator_1);
		mnNewMenu_1.add(chkMenuMazo);

		separator = new JSeparator();
		mnNewMenu_1.add(separator);

		mnNewMenu_2 = new JMenu("Cambiar colores");
		mnNewMenu_1.add(mnNewMenu_2);

		mnNewMenu_3 = new JMenu("Mesa de tu mano");
		mnNewMenu_2.add(mnNewMenu_3);

		chkJugadorV = new JRadioButtonMenuItem("Verde");
		chkJugadorV.setSelected(true);
		chkJugadorV.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					panelJ.setBackground(verdeOscuro);
				}
			}
		});
		grupoColorJ.add(chkJugadorV);
		mnNewMenu_3.add(chkJugadorV);

		chkJugadorR = new JRadioButtonMenuItem("Rojo");
		chkJugadorR.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					panelJ.setBackground(rojoOscuro);
				}
			}
		});
		grupoColorJ.add(chkJugadorR);
		mnNewMenu_3.add(chkJugadorR);

		chkJugadorN = new JRadioButtonMenuItem("Ninguno");
		chkJugadorN.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					panelJ.setBackground(null);
				}
			}
		});
		grupoColorJ.add(chkJugadorN);
		mnNewMenu_3.add(chkJugadorN);

		mnNewMenu_4 = new JMenu("Mesa de la banca");
		mnNewMenu_2.add(mnNewMenu_4);

		chkBancaV = new JCheckBoxMenuItem("Verde");
		chkBancaV.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					panelB.setBackground(verdeOscuro);
				}
			}
		});
		grupoColorB.add(chkBancaV);
		mnNewMenu_4.add(chkBancaV);

		chkBancaR = new JCheckBoxMenuItem("Rojo");
		chkBancaR.setSelected(true);
		chkBancaR.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					panelB.setBackground(rojoOscuro);
				}
			}
		});
		grupoColorB.add(chkBancaR);
		mnNewMenu_4.add(chkBancaR);

		chkBancaN = new JCheckBoxMenuItem("Ninguno");
		chkBancaN.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					panelB.setBackground(null);
				}
			}
		});
		grupoColorB.add(chkBancaN);
		mnNewMenu_4.add(chkBancaN);

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[221:221.00][500:n:500][350:350][:150:150][10:200,grow][180:n:180]",
				"[90:n:90,center][::300,top][280:400:400,center][35:35.00,grow,bottom]"));

		btnNuevoJuego = new JButton("Nueva Partida");
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.setTurnoJugador(true);
			}
		});
		contentPane.add(btnNuevoJuego, "flowx,cell 0 0,alignx center,growy");

		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Mensaje",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		contentPane.add(panel_5, "cell 1 0 2 1,grow");
		panel_5.setLayout(new MigLayout("", "[500:n:500][][]", "[]"));

		lblEstado = new JLabel("");
		lblEstado.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel_5.add(lblEstado, "cell 0 0,alignx center,aligny center");

		btnPedirCarta = new JButton(pedir);
		btnPedirCarta.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnPedirCarta.setText(" Pedir Carta");
		panel_5.add(btnPedirCarta, "cell 1 0,aligny top");
		btnPedirCarta.setEnabled(false);

		btnPlantarse = new JButton(pasar);
		btnPlantarse.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnPlantarse.setText(" Plantarse");
		panel_5.add(btnPlantarse, "cell 2 0,aligny top");
		btnPlantarse.setEnabled(false);
		btnPlantarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.setTurnoJugador(false);
			}
		});
		btnPedirCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controlador.pideCarta();
				} catch (NoHayCartasException e1) {
					JOptionPane.showMessageDialog(btnPedirCarta, "No quedan cartas en la baraja", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		chkEfectos = new JCheckBox();
		chkEfectos.setFocusable(false);
		chkEfectos.setSelected(true);
		chkEfectos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					controlador.setEfectos(true);
					chkEfectos.setText(textoSonido(true));
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					controlador.setEfectos(false);
					chkEfectos.setText(textoSonido(false));
				}
				rbtMenuSonido.setSelected(controlador.isEfectos());
				actualizaCheckboxes();
			}
		});
		contentPane.add(chkEfectos, "flowy,cell 3 0,alignx left");

		btnSalir = new JButton("Salir del juego");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnSalir, "cell 5 0,alignx center,growy");

		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 300));
		panel_3.setMaximumSize(new Dimension(32767, 300));
		contentPane.add(panel_3, "cell 0 1 4 1,growx,aligny top");
		panel_3.setLayout(new MigLayout("", "[221.00][grow][grow]", "[48.00][top]"));

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Tu mano", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_6, "cell 0 0 3 1,grow");
		panel_6.setLayout(new MigLayout("", "[221.00][grow][grow]", "[48.00][top][]"));

		panelJ = new JPanel();
		panelJ.setMaximumSize(new Dimension(1180, 205));
		panel_6.add(panelJ, "cell 0 0 3 2,growx,aligny center");
		panelJ.setPreferredSize(new Dimension(600, 230));
		panelJ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					if (controlador.isTurnoJugador())
						controlador.pideCarta();
				} catch (NoHayCartasException e1) {
					JOptionPane.showMessageDialog(btnPedirCarta, "No quedan cartas en la baraja", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelJ.setMinimumSize(new Dimension(1180, 230));
		panelJ.setBackground(new Color(0, 128, 0));
		panelJ.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelJ.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		panel = new JPanel();
		panel_6.add(panel, "cell 1 2");
		panel.setPreferredSize(new Dimension(160, 30));
		panel.setMaximumSize(new Dimension(160, 30));
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setLayout(new MigLayout("", "[grow]", "[]"));

		JLabel lblNewLabel_1 = new JLabel("Puntos de tu mano:");
		panel.add(lblNewLabel_1, "flowx,cell 0 0");

		lblPuntosJ = new JLabel("");
		lblPuntosJ.setFont(new Font("SansSerif", Font.BOLD, 12));
		panel.add(lblPuntosJ, "cell 0 0");

		lblNewLabel = new JLabel("(Puedes hacer click aquí para pedir carta)");
		panel_6.add(lblNewLabel, "cell 2 2");
//		panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		scrollPane = new JScrollPane();
		scrollPane.setOpaque(true);
		scrollPane.setVisible(false);

		panelDibujo = new JPanel();
		panelDibujo.setRequestFocusEnabled(false);
		panelDibujo.setOpaque(false);
		panelDibujo.setFocusable(false);
		contentPane.add(panelDibujo, "cell 4 1,alignx left,aligny center");
		contentPane.add(scrollPane, "cell 5 1 1 2,growx,aligny top");
		ImageIcon arte = null;
		try {
			arte = new ImageIcon(ImageIO.read(getClass().getResource(pathIcono + "cartas4" + ".png")));
		} catch (IOException e1) {
			System.err.println("Arte no encontrado");
		}
		JLabel label = new JLabel(arte);
		label.setRequestFocusEnabled(false);
		label.setFocusable(false);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		panelDibujo.add(label);

		txtMazo = new JTextArea();
		txtMazo.setOpaque(true);
		txtMazo.setEditable(false);
		scrollPane.setViewportView(txtMazo);

		panel_4 = new JPanel();
		contentPane.add(panel_4, "cell 0 2 4 1,growx,aligny center");
		panel_4.setLayout(new MigLayout("", "[221.00][grow]", "[250,center][center]"));

		panel_7 = new JPanel();
		panel_7.setBorder(
				new TitledBorder(null, "Mano de la banca", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.add(panel_7, "cell 0 0 2 1,grow");
		panel_7.setLayout(new MigLayout("", "[221.00][grow][grow]", "[250,center][58.00,grow]"));

		panelB = new JPanel();
		panel_7.add(panelB, "cell 0 0 3 1,growx,aligny center");
		panelB.setPreferredSize(new Dimension(600, 230));
		panelB.setMaximumSize(new Dimension(1180, 205));
		panelB.setMinimumSize(new Dimension(1180, 230));
		panelB.setBackground(new Color(128, 0, 0));
		panelB.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_panelB = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panelB.setLayout(fl_panelB);
		panelB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (controlador.isTurnoJugador())
					controlador.setTurnoJugador(false);
			}
		});

		panel_1 = new JPanel();
		panel_7.add(panel_1, "cell 1 1");
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setPreferredSize(new Dimension(160, 30));
		panel_1.setMaximumSize(new Dimension(160, 30));
		panel_1.setLayout(new MigLayout("", "[grow]", "[top]"));

		JLabel lblNewLabel_2 = new JLabel("Puntos: de la banca:");
		panel_1.add(lblNewLabel_2, "flowx,cell 0 0");

		lblPuntosB = new JLabel("");
		lblPuntosB.setFont(new Font("SansSerif", Font.BOLD, 12));
		panel_1.add(lblPuntosB, "cell 0 0");

		lblpuedesHacerClick = new JLabel("(Puedes hacer click aquí para plantarte)");
		panel_7.add(lblpuedesHacerClick, "cell 2 1");

		panel_8 = new JPanel();
		contentPane.add(panel_8, "cell 1 3,alignx center,aligny bottom");
		panel_8.setLayout(new MigLayout("", "[500:n:500]", "[35:35.00,grow,bottom]"));

		chkMazo = new JCheckBox("Mostrar mazo (testeo)");
		chkMazo.setFocusable(false);
		panel_8.add(chkMazo, "cell 0 0,alignx right,aligny center");
		chkMazo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					mostrarMazo(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					mostrarMazo(false);
				}
			}
		});

		panel_2 = new JPanel();
		contentPane.add(panel_2, "flowx,cell 2 3 2 1,alignx center,aligny bottom");
		panel_2.setLayout(new MigLayout("", "[grow]", "[top]"));

		lblPartidasJ = new JLabel("P");
		lblPartidasJ.setFont(new Font("SansSerif", Font.BOLD, 12));
		panel_2.add(lblPartidasJ, "flowx,cell 0 0");

		lblVictorias = new JLabel("v");
		lblVictorias.setFont(new Font("SansSerif", Font.BOLD, 12));
		panel_2.add(lblVictorias, "cell 0 0");

		lblEmpates = new JLabel("e");
		lblEmpates.setFont(new Font("SansSerif", Font.BOLD, 12));
		panel_2.add(lblEmpates, "cell 0 0");

		lblDerrotas = new JLabel("d");
		lblDerrotas.setFont(new Font("SansSerif", Font.BOLD, 12));
		panel_2.add(lblDerrotas, "cell 0 0");

		chkMusica = new JCheckBox("");
		chkMusica.setFocusable(false);
		chkMusica.setSelected(true);
		chkMusica.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (!controlador.isMusica())
						controlador.setMusica(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					if (controlador.isMusica())
						controlador.setMusica(false);
				}
				rbtMenuMusica.setSelected(controlador.isMusica());
				actualizaCheckboxes();
			}
		});
		contentPane.add(chkMusica, "cell 3 0");

	}

	protected void mostrarMazo(boolean b) {
		if (b) {
//			 lblMazo.setVisible(true);
			scrollPane.setVisible(true);
//			scrollPane
			txtMazo.requestFocusInWindow();
			chkMenuMazo.setSelected(true);
			chkMazo.setSelected(true);
		} else {
			scrollPane.setVisible(false);
//			 lblMazo.setVisible(false);
			chkMenuMazo.setSelected(false);
			chkMazo.setSelected(false);
		}

	}

	public void empiezaJuego() {
		btnNuevoJuego.setEnabled(false);
		btnPedirCarta.setEnabled(true);
//		btnPlantarse.setEnabled(true);
		btnSalir.setEnabled(false);
		panelJ.removeAll();
		panelJ.revalidate();
		panelJ.repaint();
		panelB.removeAll();
		panelB.revalidate();
		panelB.repaint();
		lblEstado.setText("¿Pides carta o te plantas? (intenta no pasarte de 21 puntos)");
		actualizaMazo();
		actualizaPuntos();
		btnPedirCarta.requestFocusInWindow();
	}

	public void actualizaMazo() {
		txtMazo.setText(controlador.getBaraja());
		txtMazo.setCaretPosition(0);
		;
	}

	public void muestraCartaJ(Carta c, int pos) {
		ImageIcon image = null;
		try {
			image = new ImageIcon(ImageIO.read(getClass().getResource(pathCarta + c.imagenCarta() + ".png")));

//			JLabel carta=new JLabel();
//			carta.setSize(10, 10);
//			rsscalelabel.RSScaleLabel.setScaleLabel(carta, pathCarta+c.imagenCarta()+".png");
			panelJ.add(new JLabel(image), pos);
			panelJ.revalidate();
			panelJ.repaint();
//			if(!btnPlantarse.isEnabled()) 
			btnPlantarse.setEnabled(true);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "No se ha cargado la imagen", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void muestraCartaB(Carta c, int pos) {
		ImageIcon image = null;
		try {
			image = new ImageIcon(ImageIO.read(getClass().getResource(pathCarta + c.imagenCarta() + ".png")));
			panelB.add(new JLabel(image), pos);
			panelB.revalidate();
			panelB.repaint();
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "No se ha cargado la imagen", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void turnoBanca() {
		btnPedirCarta.setEnabled(false);
		btnPlantarse.setEnabled(false);
//		btnSalir.setEnabled(false);
		lblEstado.setText("Turno de la banca...");
		JOptionPane.showMessageDialog(this,
				"Tu turno ha terminado.\n" + "Valor de la mano: " + controlador.valorManoJ() + "\nTurno de la banca...",
				"Fin de tu turno", JOptionPane.INFORMATION_MESSAGE);
	}

	public void actualizaPuntos() {
		lblPuntosJ.setText("" + controlador.valorManoJ());
		lblPuntosB.setText("" + controlador.valorManoB());

	}

	public void finDePartida() {
		int seguir = JOptionPane.showConfirmDialog(this, "¿Deseas seguir jugando?\nPiénsatelo bien...",
				"¿Seguir jugando?", JOptionPane.YES_NO_OPTION);

		switch (seguir) {
		case JOptionPane.YES_OPTION:
			controlador.setTurnoJugador(true);
		case JOptionPane.NO_OPTION:
		default:
			btnNuevoJuego.setEnabled(true);
			btnSalir.setEnabled(true);
		}

	}

	public void puntuacionFinal() {
		JOptionPane.showMessageDialog(this, "Puntos de tu mano: " + controlador.valorManoJ()
				+ "\n\nPuntos de la banca: " + controlador.valorManoB(), "Puntuación final",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void ganaBanca() {
		lblEstado.setText("La banca gana :(");
		JOptionPane.showMessageDialog(this, "Lo sentimos, gana la banca...", "Has perdido", JOptionPane.ERROR_MESSAGE);
	}

	public void ganasTu() {
		lblEstado.setText("¡Has ganado! :)");
		JOptionPane.showMessageDialog(this, "¡Enhorabuena!\n\nEstás en racha :)", "¡Has ganado!",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void ganaNadie() {
		lblEstado.setText("No hay ganador");
		JOptionPane.showMessageDialog(this, "No hay ganador, así que se te devolverá la apuesta", "Empate",
				JOptionPane.WARNING_MESSAGE);
	}

	public void actualizaContador() {
		lblPartidasJ.setText("Partidas Jugadas: " + controlador.getPartidasJ() + "    ///    ");
		lblVictorias.setText("Victorias: " + controlador.getVictorias() + " | ");
		lblEmpates.setText("Empates: " + controlador.getEmpates() + " | ");
		lblDerrotas.setText("Derrotas: " + controlador.getDerrotas() + "  ");

	}

	private String textoSonido(boolean b) {
		if (b)
			return "Efectos de sonido ON";
		else
			return "Efectos de sonido OFF";
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void actualizaCheckboxes() {
		chkEfectos.setText(textoSonido(controlador.isEfectos()));
//		chkMusica.setSelected(controlador.isMusica());
		chkMusica.setText(textoMusica(controlador.isMusica()));
//		rbtMenuMusica.setSelected(controlador.isMusica());
	}

	private String textoMusica(boolean b) {
		if (b)
			return "Música ON";
		else
			return "Música OFF";
	}
}
