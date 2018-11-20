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
import javax.swing.SpinnerNumberModel;

public class RegJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JSpinner spnAltura;

	
	public RegJugador() {
		setLocationRelativeTo(null);
		setTitle("Registrar Jugador");
		setBounds(100, 100, 367, 401);
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
		
		JLabel lblPosicion = new JLabel("Posici\u00F3n:");
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
		spnEdad.setModel(new SpinnerNumberModel(new Integer(16), new Integer(16), null, new Integer(1)));
		spnEdad.setBounds(100, 181, 56, 26);
		contentPanel.add(spnEdad);
		
		JComboBox cmbxEquipo = new JComboBox();
		cmbxEquipo.setBounds(100, 214, 130, 27);
		contentPanel.add(cmbxEquipo);
		
		spnAltura = new JSpinner();
		SpinnerNumberModel model = new SpinnerNumberModel((double)(1.75),(double)(1.70),(double)(2.21),(double)(0.01));
		spnAltura.setModel(model);
		spnAltura.setBounds(100, 148, 56, 26);
		contentPanel.add(spnAltura);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(6, 286, 61, 16);
		contentPanel.add(lblNmero);
		
		JSpinner spnNumero = new JSpinner();
		spnNumero.setBounds(100, 285, 33, 26);
		contentPanel.add(spnNumero);
		
		JLabel lblMt = new JLabel("mt");
		lblMt.setBounds(157, 153, 56, 16);
		contentPanel.add(lblMt);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Equipo equipo = Liga.getInstance().buscarEquipo(cmbxEquipo.getSelectedItem().toString());
						//Jugador jugador = new Jugador(txtCedula.getText(),txtNombre.getText(),txtApellido.getText(),cmbxNacionalidad.getSelectedItem().toString(),cmbxPosicion.getSelectedItem().toString(),spnEdad.getValue(),spnNumero.getValue(),equipo,()spnAltura.getValue());
						//miLiga.addJugador(jugador);
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
