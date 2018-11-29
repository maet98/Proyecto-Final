package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import logico.Equipo;
import logico.Liga;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class RegEquipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Equipo miEquipo;
	private JTextField txtNombre;
	private JTextField txtCiudad;
	private JTextField txtEntrenador;
	private JTextField txtEstadio;
	private JLabel lblLogo;
	private ImageIcon logoEquipo;

	public RegEquipo(Equipo equipo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegEquipo.class.getResource("/imagenes/basketball.png")));
		logoEquipo = new ImageIcon(RegEquipo.class.getResource("/imagenes/equipo1.png"));
		setTitle("Registrar Equipo");
		this.miEquipo = equipo;
		setBounds(100, 100, 603, 354);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel pnlEquipo = new JPanel();
			pnlEquipo.setBorder(new TitledBorder(null, "Informaci\u00F3n del Equipo:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlEquipo.setBounds(0, 0, 637, 268);
			panel.add(pnlEquipo);
			pnlEquipo.setLayout(null);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 34, 56, 16);
			pnlEquipo.add(lblNombre);
			{
				JLabel lblEntrenador = new JLabel("Entrenador:");
				lblEntrenador.setBounds(12, 133, 68, 16);
				pnlEquipo.add(lblEntrenador);
			}
			{
				JLabel lblEstadio = new JLabel("Estadio:");
				lblEstadio.setBounds(12, 100, 56, 16);
				pnlEquipo.add(lblEstadio);
			}
			{
				JLabel lblCiudad = new JLabel("Ciudad:");
				lblCiudad.setBounds(12, 67, 56, 16);
				pnlEquipo.add(lblCiudad);
			}
			
			txtNombre = new JTextField();
			txtNombre.setBounds(92, 31, 167, 22);
			pnlEquipo.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtCiudad = new JTextField();
			txtCiudad.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char validar = e.getKeyChar();
					if(!Character.isWhitespace(validar) && !Character.isLetter(validar) && validar!=8) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			txtCiudad.setBounds(92, 64, 167, 22);
			pnlEquipo.add(txtCiudad);
			txtCiudad.setColumns(10);
			
			txtEntrenador = new JTextField();
			txtEntrenador.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char validar = e.getKeyChar();
					if(!Character.isWhitespace(validar) && !Character.isLetter(validar) && validar!=8) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			txtEntrenador.setBounds(92, 130, 167, 22);
			pnlEquipo.add(txtEntrenador);
			txtEntrenador.setColumns(10);
			
			txtEstadio = new JTextField();
			txtEstadio.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char validar = e.getKeyChar();
					if(!Character.isWhitespace(validar) && !Character.isLetter(validar) && validar!=8) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			txtEstadio.setBounds(92, 97, 167, 22);
			pnlEquipo.add(txtEstadio);
			txtEstadio.setColumns(10);
			{
				lblLogo = new JLabel("");
				lblLogo.setIcon(logoEquipo);
				lblLogo.setBounds(306, 11, 246, 167);
				pnlEquipo.add(lblLogo);
			}
			
			JButton btnIngresarLogo = new JButton("Ingresar Logo");
			btnIngresarLogo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser nuevo = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
					nuevo.addChoosableFileFilter(filter);
					int selectedFile = nuevo.showOpenDialog(null);
					if(selectedFile == JFileChooser.APPROVE_OPTION) {
						File file = nuevo.getSelectedFile();
						lblLogo.setIcon(ResizeImage(file.getPath()));
					}
				}
			});
			btnIngresarLogo.setBounds(380, 191, 132, 23);
			pnlEquipo.add(btnIngresarLogo);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(equipo== null) {
							String entrenador = txtEntrenador.getText();
							String nombre = txtNombre.getText();
							String estadio = txtEstadio.getText();
							String ciudad = txtCiudad.getText();
							if(!nombre.equalsIgnoreCase("")&&!entrenador.equalsIgnoreCase("")&&!estadio.equalsIgnoreCase("") && !ciudad.equalsIgnoreCase("")) {
								Equipo nuevo = new Equipo(entrenador, nombre, estadio, ciudad,logoEquipo);
								Liga.getInstance().addEquipo(nuevo);
								Limpiar();
								JOptionPane.showMessageDialog(null, "El equipo "+nombre+" ha sido ingresado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Aviso", JOptionPane.WARNING_MESSAGE);
							}
						}else {
							if(!txtEntrenador.getText().equalsIgnoreCase("")&&!txtCiudad.getText().equalsIgnoreCase("")&& !txtEstadio.getText().equalsIgnoreCase("")) {
								equipo.setEntrenador(txtEntrenador.getText());
								equipo.setCiudad(txtCiudad.getText());
								equipo.setEstadio(txtEstadio.getText());
								JOptionPane.showMessageDialog(null, "El equipo "+txtNombre.getText()+" ha sido modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Aviso", JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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
		if(miEquipo!= null) {
			loadEquipo();
		}
	}
	

	public ImageIcon ResizeImage(String path)
    {
        ImageIcon MyImage = new ImageIcon(path);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
	void loadEquipo() {
		txtCiudad.setText(miEquipo.getCiudad());
		txtEntrenador.setText(miEquipo.getEntrenador());
		txtNombre.setEnabled(false);
		txtNombre.setText(miEquipo.getNombre());
		txtEstadio.setText(miEquipo.getEstadio());
		System.out.println(miEquipo.getLogo());
		lblLogo.setIcon(miEquipo.getLogo());
	}
	void Limpiar() {
		txtCiudad.setText("");
		txtEntrenador.setText("");
		txtNombre.setText("");
		txtEstadio.setText("");
	}
}
