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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiApuesta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Controlador controlador;
	private JLabel lblFichas;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			GuiApuesta dialog = new GuiApuesta();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public GuiApuesta() {
		setModal(true);
		setTitle("Introduce tu apuesta");
		setBounds(100, 100, 260, 186);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][][]", "[][][][][]"));
		{
			JLabel lblNewLabel = new JLabel("Tus fichas:");
			contentPanel.add(lblNewLabel, "cell 1 1");
		}
		{
			lblFichas = new JLabel("New label");
			contentPanel.add(lblFichas, "cell 2 1");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Apuesta: ");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			contentPanel.add(lblNewLabel_1, "cell 1 3");
		}
		{
			spinner = new JSpinner();
			spinner.setFont(new Font("Tahoma", Font.BOLD, 13));
			spinner.setModel(new SpinnerNumberModel(10, 10, 100, 10));
			contentPanel.add(spinner, "cell 2 3");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnComenzar = new JButton("Comenzar partida");
				btnComenzar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						introduceApuesta();
					}
				});
				btnComenzar.setActionCommand("OK");
				buttonPane.add(btnComenzar);
				getRootPane().setDefaultButton(btnComenzar);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}

	protected void introduceApuesta() {
		setVisible(false);
		controlador.apuesta((int)spinner.getValue());
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
		
	}

	public void preparaVentana(int fichas) {
		lblFichas.setText(""+fichas);
		spinner.setValue(10);
	}

}
