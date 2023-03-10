package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Jugador;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GuiElegirJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Controlador controlador;
	private JComboBox<Jugador> comboBox;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			GuiElegirJugador dialog = new GuiElegirJugador();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public GuiElegirJugador() {
		setModal(true);
		setTitle("Identificación");
		setBounds(100, 100, 736, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][]"));
		{
			JLabel lblNewLabel = new JLabel("Selecciona tu nombre:");
			contentPanel.add(lblNewLabel, "cell 1 1,alignx trailing");
		}
		{
			comboBox = new JComboBox<Jugador>();
			contentPanel.add(comboBox, "cell 2 1 2 1,growx");
		}
		{
			JButton btnNewButton = new JButton("Registrar nuevo jugador");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controlador.ventanaAnadirJugador();
				}
			});
			contentPanel.add(btnNewButton, "cell 1 4");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("<- Click aquí si eres un nuevo jugador");
			contentPanel.add(lblNewLabel_1, "cell 2 4");
		}
		{
			JButton btnSinRegistro = new JButton("Jugar sin registrarse");
			btnSinRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					elegirJugador(false);
				}
			});
			contentPanel.add(btnSinRegistro, "cell 3 4");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						elegirJugador(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void elegirJugador(boolean b) {
		Jugador j= new Jugador();
		if(b) {
			j=(Jugador) comboBox.getSelectedItem();
			if(j==null) {
				JOptionPane.showMessageDialog(contentPanel, "Debe elegir un juador de la lista o crear uno nuevo", "Jugador no seleccionado", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else {
			j.setNombre("(Sin registrar)");
		}
		controlador.setJugador(j);
		setVisible(false);
//		controlador.abreApuesta();
		
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
		
	}

	public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
		comboBox.removeAllItems();
		for (Jugador jugador : listaJugadores) {
			comboBox.addItem(jugador);
		}
		
	}

}
