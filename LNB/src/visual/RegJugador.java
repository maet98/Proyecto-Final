package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Equipo;
import logico.Jugador;
import logico.Liga;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private Liga miLiga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the dialog.
	 */
	public RegJugador(Liga liga) {
		miLiga = liga;
		setTitle("Registrar Jugador");
		setBounds(100, 100, 350, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(6, 25, 61, 16);
		contentPanel.add(lblCedula);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(6, 55, 61, 16);
		contentPanel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(6, 88, 61, 16);
		contentPanel.add(lblApellido);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setBounds(6, 120, 91, 16);
		contentPanel.add(lblNacionalidad);
		
		JLabel lblPosicion = new JLabel("Posición:");
		lblPosicion.setBounds(6, 250, 61, 16);
		contentPanel.add(lblPosicion);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(6, 186, 61, 16);
		contentPanel.add(lblEdad);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		lblEquipo.setBounds(6, 218, 61, 16);
		contentPanel.add(lblEquipo);
		
		JLabel lblAltura = new JLabel("Altura:");
		lblAltura.setBounds(6, 153, 61, 16);
		contentPanel.add(lblAltura);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(100, 20, 130, 26);
		contentPanel.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(100, 50, 130, 26);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(100, 83, 130, 26);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);
		
		JComboBox cmbxNacionalidad = new JComboBox();
		cmbxNacionalidad.setBounds(100, 116, 130, 27);
		contentPanel.add(cmbxNacionalidad);
		
		JComboBox cmbxPosicion = new JComboBox();
		cmbxPosicion.setBounds(100, 246, 130, 27);
		contentPanel.add(cmbxPosicion);
		
		JSpinner spnEdad = new JSpinner();
		spnEdad.setBounds(100, 181, 33, 26);
		contentPanel.add(spnEdad);
		
		JComboBox cmbxEquipo = new JComboBox();
		cmbxEquipo.setBounds(100, 214, 130, 27);
		contentPanel.add(cmbxEquipo);
		
		JSpinner spnAltura = new JSpinner();
		spnAltura.setBounds(100, 148, 33, 26);
		contentPanel.add(spnAltura);
		
		JLabel lblNmero = new JLabel("Número:");
		lblNmero.setBounds(6, 286, 61, 16);
		contentPanel.add(lblNmero);
		
		JSpinner spnNumero = new JSpinner();
		spnNumero.setBounds(100, 285, 33, 26);
		contentPanel.add(spnNumero);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Equipo equipo = liga.buscarEquipo(cmbxEquipo.getSelectedItem().toString());
						//Jugador jugador = new Jugador(txtCedula.getText(),txtNombre.getText(),txtApellido.getText(),cmbxNacionalidad.getSelectedItem().toString(),cmbxPosicion.getSelectedItem().toString(),spnEdad.getValue(),spnNumero.getValue(),equipo,()spnAltura.getValue());
						//miLiga.addJugador(jugador);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
