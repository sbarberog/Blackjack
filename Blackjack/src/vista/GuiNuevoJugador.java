package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;

public class GuiNuevoJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private Controlador controlador;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			GuiNuevoJugador dialog = new GuiNuevoJugador();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public GuiNuevoJugador() {
		setModal(true);
		setTitle("Introducir nuevo jugador");
		setBounds(100, 100, 436, 136);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][grow]", "[][]"));
		{
			JLabel lblNewLabel = new JLabel("Nombre del jugador:");
			contentPanel.add(lblNewLabel, "cell 1 1,alignx trailing");
		}
		{
			txtNombre = new JTextField();
			contentPanel.add(txtNombre, "cell 2 1,growx");
			txtNombre.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Ok");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						insertar();
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

	protected void insertar() {
		if(txtNombre.getText()==null || txtNombre.getText().isBlank()) {
			JOptionPane.showMessageDialog(txtNombre, "El nombre no puede estar vacío", "Nombre vacío", JOptionPane.ERROR_MESSAGE);
		}else {
			try {
				controlador.insertarNuevoJugador(txtNombre.getText());
//				setVisible(false);
			} catch (SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(txtNombre, "El nombre ya existe. Debe introducir un nombre diferente.", "Nombre inválido", JOptionPane.ERROR_MESSAGE);
				e.getMessage();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(txtNombre, "Error al introducir los datos", "Error de inserción", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(rootPane, "No se ha podido establecer la conexión a la base de datos", "Error de conexión", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
		
	}
	
	public void limpiar() {
		txtNombre.setText("");
	}

}
