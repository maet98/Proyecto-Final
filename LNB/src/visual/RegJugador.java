package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

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
import javax.swing.DefaultComboBoxModel;
import java.awt.Button;

public class RegJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JSpinner spnAltura;
	private JSpinner spnEdad;

	
	public RegJugador() {
		setLocationRelativeTo(null);
		setTitle("Registrar Jugador");
		setBounds(100, 100, 617, 468);
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
		txtCedula.setBounds(125, 25, 130, 26);
		contentPanel.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(125, 55, 130, 26);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(125, 88, 130, 26);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);
		
		JComboBox cmbxNacionalidad = new JComboBox();
		cmbxNacionalidad.setBounds(125, 121, 130, 27);
		contentPanel.add(cmbxNacionalidad);
		
		JComboBox cmbxPosicion = new JComboBox();
		cmbxPosicion.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Base", "Escolta Alero", "Ala-p\u00EDvot", "P\u00EDvot"}));
		cmbxPosicion.setBounds(125, 251, 130, 27);
		contentPanel.add(cmbxPosicion);
		
		spnEdad = new JSpinner();
		spnEdad.setModel(new SpinnerNumberModel(new Integer(16), new Integer(16), null, new Integer(1)));
		spnEdad.setBounds(125, 186, 56, 26);
		contentPanel.add(spnEdad);
		
		JComboBox cmbxEquipo = new JComboBox();
		cmbxEquipo.setBounds(125, 219, 130, 27);
		contentPanel.add(cmbxEquipo);
		
		spnAltura = new JSpinner();
		SpinnerNumberModel model = new SpinnerNumberModel((double)(1.75),(double)(1.70),(double)(2.21),(double)(0.01));
		spnAltura.setModel(model);
		spnAltura.setBounds(125, 153, 56, 26);
		contentPanel.add(spnAltura);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(6, 286, 61, 16);
		contentPanel.add(lblNmero);
		
		JSpinner spnNumero = new JSpinner();
		spnNumero.setBounds(125, 290, 33, 26);
		contentPanel.add(spnNumero);
		
		JLabel lblMt = new JLabel("mt");
		lblMt.setBounds(157, 153, 56, 16);
		contentPanel.add(lblMt);
		
		JLabel lblFotoDeJugador = new JLabel("Foto de Jugador:");
		lblFotoDeJugador.setBounds(6, 337, 108, 16);
		contentPanel.add(lblFotoDeJugador);
		
		Button btnFoto = new Button("Insertar");
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFoto.setBounds(125, 329, 108, 24);
		contentPanel.add(btnFoto);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Equipo equipo = Liga.getInstance().buscarEquipo(cmbxEquipo.getSelectedItem().toString());
						String cedula = txtCedula.getText();
						String nombre = txtNombre.getText();
						String apellido = txtApellido.getText();
						String nacionalidad = cmbxNacionalidad.getSelectedItem().toString();
						String posicion = cmbxPosicion.getSelectedItem().toString();
						int edad = Integer.parseInt(spnEdad.getValue().toString());
						int dorsal = Integer.parseInt(spnNumero.getValue().toString());
						float altura = Integer.parseInt(spnAltura.getValue().toString());
						Image fotoJugador = null;
						Jugador jugador = new Jugador(cedula, nombre, apellido, nacionalidad, posicion, edad, dorsal, equipo, altura, fotoJugador);
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
