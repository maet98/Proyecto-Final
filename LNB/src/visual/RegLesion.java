package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegLesion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;


	/**
	 * Create the dialog.
	 */
	public RegLesion() {
		setTitle("Registrar Lesi�n");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblJugador = new JLabel("Jugador:");
		lblJugador.setBounds(12, 30, 56, 16);
		panel.add(lblJugador);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		lblEquipo.setBounds(12, 59, 56, 16);
		panel.add(lblEquipo);
		
		JLabel lblTipoDeLesin = new JLabel("Tipo de lesi\u00F3n:");
		lblTipoDeLesin.setBounds(12, 88, 125, 16);
		panel.add(lblTipoDeLesin);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(125, 30, 138, 22);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(125, 59, 138, 22);
		panel.add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(125, 88, 138, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio:");
		lblFechaDeInicio.setBounds(12, 117, 109, 16);
		panel.add(lblFechaDeInicio);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
