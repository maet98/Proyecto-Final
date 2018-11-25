package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import logico.Equipo;
import logico.Jugador;
import logico.Liga;
import nu.zoom.swing.desktop.component.filechooser.FileChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Formatter;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Button;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class RegJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JSpinner spnAltura;
	private JFormattedTextField ftxtCedula;
	private JSpinner spnEdad;
	private JLabel lblFoto;
	private JComboBox<String> cmbxEquipo;
	private ImageIcon fotoJugador;
	private JComboBox<String> cmbxPosicion;
	private JTextField txtNumero;
	private JTextField txtNacionalidad;
	private int dorsal = 0;
	
	public RegJugador() throws FileNotFoundException, ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegJugador.class.getResource("/imagenes/basketball.png")));
		setLocationRelativeTo(null);
		setTitle("Registrar Jugador");
		setBounds(100, 100, 608, 407);
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
		lblPosicion.setBounds(6, 202, 61, 16);
		contentPanel.add(lblPosicion);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(6, 269, 61, 16);
		contentPanel.add(lblEdad);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		lblEquipo.setBounds(6, 161, 61, 16);
		contentPanel.add(lblEquipo);
		
		JLabel lblAltura = new JLabel("Altura:");
		lblAltura.setBounds(6, 240, 61, 16);
		contentPanel.add(lblAltura);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if(!Character.isAlphabetic(letra) && !Character.isWhitespace(letra) && letra != 8) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtNombre.setBounds(125, 55, 129, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if(!Character.isAlphabetic(letra) && !Character.isWhitespace(letra) && letra != 8) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtApellido.setBounds(125, 88, 129, 20);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);
		
		cmbxPosicion = new JComboBox();
		cmbxPosicion.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Base", "Escolta Alero", "Ala-p\u00EDvot", "P\u00EDvot"}));
		cmbxPosicion.setBounds(125, 197, 130, 20);
		contentPanel.add(cmbxPosicion);
		
		spnEdad = new JSpinner();
		spnEdad.setModel(new SpinnerNumberModel(new Integer(16), new Integer(16), null, new Integer(1)));
		spnEdad.setBounds(125, 269, 56, 20);
		contentPanel.add(spnEdad);
		
		cmbxEquipo = new JComboBox();
		cmbxEquipo.setBounds(125, 159, 129, 20);
		contentPanel.add(cmbxEquipo);
		
		spnAltura = new JSpinner();
		SpinnerNumberModel model = new SpinnerNumberModel((double)(1.75),(double)(1.70),(double)(2.21),(double)(0.01));
		spnAltura.setModel(model);
		spnAltura.setBounds(125, 235, 56, 20);
		contentPanel.add(spnAltura);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(6, 296, 61, 16);
		contentPanel.add(lblNmero);
		
		JLabel lblMt = new JLabel("mt");
		lblMt.setBounds(191, 240, 56, 16);
		contentPanel.add(lblMt);
		
		MaskFormatter cedula = new MaskFormatter("###-#######-#");
		ftxtCedula = new JFormattedTextField(cedula);
		ftxtCedula.setBounds(126, 21, 129, 20);
		contentPanel.add(ftxtCedula);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(313, 26, 203, 200);
		ImageIcon defaultfoto = new ImageIcon("ICONS/persona.png");
		lblFoto.setIcon(new ImageIcon(RegJugador.class.getResource("/imagenes/persona.png")));
		contentPanel.add(lblFoto);
		
		JButton btnInsertarFoto = new JButton("Insertar Foto");
		btnInsertarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser nuevo = new JFileChooser();
				int selectedFile = nuevo.showOpenDialog(null);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
				nuevo.addChoosableFileFilter(filter);
				if(selectedFile == JFileChooser.APPROVE_OPTION) {
					File file = nuevo.getSelectedFile();
					fotoJugador = new ImageIcon(file.getPath());
					lblFoto.setIcon(ResizeImage(file.getPath()));
				}
			}
		});
		btnInsertarFoto.setBounds(354, 237, 118, 23);
		contentPanel.add(btnInsertarFoto);
		
		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if(!Character.isDigit(letra)) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtNumero.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				int actual = Integer.parseInt(txtNumero.getText());
				Equipo equipo = Liga.getInstance().buscarEquipo(cmbxEquipo.getSelectedItem().toString());
				if(equipo!=null) {
					if(!equipo.estaOcupado(actual)) {
						dorsal = actual;
						System.out.println(dorsal);
					}
				}
				
			}
		});
		txtNumero.setBounds(125, 296, 56, 20);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			}
		});
		txtNacionalidad.setBounds(125, 118, 129, 20);
		contentPanel.add(txtNacionalidad);
		txtNacionalidad.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cmbxEquipo.getSelectedIndex()!=0 && cmbxPosicion.getSelectedIndex()!=0 && !txtApellido.getText().equalsIgnoreCase("") && !txtNombre.getText().equalsIgnoreCase("")&&
								!txtNacionalidad.getText().equalsIgnoreCase("")&& ftxtCedula.getText().length() ==13  && !txtNumero.getText().equalsIgnoreCase("")) {
							Equipo equipo = Liga.getInstance().buscarEquipo(cmbxEquipo.getSelectedItem().toString());
							int actual = Integer.parseInt(txtNumero.getText());
							if(!equipo.estaOcupado(actual)) {
								dorsal = actual;
								String cedula = ftxtCedula.getText();
								String nombre = txtNombre.getText();
								String apellido = txtApellido.getText();
								String nacionalidad = txtNacionalidad.getText();
								String posicion = cmbxPosicion.getSelectedItem().toString();
								int edad = Integer.parseInt(spnEdad.getValue().toString());
								float altura = Float.parseFloat(spnAltura.getValue().toString());
								Jugador nuevo = new Jugador(cedula, nombre, apellido, nacionalidad, posicion, edad, dorsal, equipo, altura, fotoJugador);
								Liga.getInstance().addJugador(nuevo);
								JOptionPane.showMessageDialog(null, "El jugador "+nombre+" "+apellido+" ha sido ingresado", "Información", JOptionPane.INFORMATION_MESSAGE);
								limpiar();
							}
							else {
								JOptionPane.showMessageDialog(null, "El numero "+actual+" esta cogido por otro jugador", "Aviso", JOptionPane.WARNING_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenados", "Aviso", JOptionPane.WARNING_MESSAGE);
						}
						
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
		loadEquipos();
	}
	public void loadEquipos() {
		int i = 1;
		cmbxEquipo.insertItemAt("<Seleccione>", 0);
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			cmbxEquipo.insertItemAt(actual.getNombre(), i);
			i++;
		}
		cmbxEquipo.setSelectedIndex(0);
	}
	public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	private void limpiar() {
		spnAltura.setValue((float)(1.75));
		txtApellido.setText("");
		txtNombre.setText("");
		ftxtCedula.setText("");
		spnEdad.setValue((int)(16));
		lblFoto.setIcon(new ImageIcon("ICONS/persona.png"));
		cmbxEquipo.setSelectedIndex(0);
		txtNacionalidad.setText("");
		txtNumero.setText("");
		cmbxPosicion.setSelectedIndex(0);
	}
}
