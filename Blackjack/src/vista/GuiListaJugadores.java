package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Jugador;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GuiListaJugadores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Controlador controlador;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			GuiListaJugadores dialog = new GuiListaJugadores();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public GuiListaJugadores() {
		setTitle("Ranking de jugadores");
		setBounds(100, 100, 797, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 0,grow");
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Nombre", "Fecha de registro", "Fichas", "Victorias", "Empates", "Derrotas", "Partidas totales"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(161);
				table.getColumnModel().getColumn(1).setPreferredWidth(99);
				table.getColumnModel().getColumn(2).setPreferredWidth(72);
				table.getColumnModel().getColumn(6).setPreferredWidth(89);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
		
	}

	public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
		DefaultTableModel modelo= (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		
		for (Jugador jugador : listaJugadores) {
			Object fila[]= {
					jugador.getNombre(),jugador.getFechaRegistro(),jugador.getFichas(),jugador.getVictorias(),
					jugador.getEmpates(),jugador.getDerrotas(),jugador.getPartidasTotales()
			};
			modelo.addRow(fila);
		}
		
	}

}
