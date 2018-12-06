package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Equipo;
import logico.Liga;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class LisEquipo extends JDialog {
	private JTable table;
	private DefaultTableModel model;
	private String selected = "";
	private JButton btnModificar;
	private JTextField txtNombre;
	private JTextField txtEntrenador;
	private JTextField txtEstadio;
	private JTextField txtCiudad;
	private JLabel lblLogo;


	public LisEquipo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LisEquipo.class.getResource("/imagenes/default-team-logo-500.png")));
		setTitle("Listar Equipos");
		setBounds(100, 100, 692, 431);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(6, 331, 668, 47);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				btnModificar = new JButton("");
				btnModificar.setIcon(new ImageIcon(LisEquipo.class.getResource("/imagenes/edit-file.png")));
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(txtCiudad.getText().equals("") || txtEntrenador.getText().equals("") || txtEstadio.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Debe llenar todos los campos", "Aviso", JOptionPane.WARNING_MESSAGE);
						}else {
							Equipo cambiar = Liga.getInstance().buscarEquipo(selected);
							cambiar.setCiudad(txtCiudad.getText());
							cambiar.setEntrenador(txtEntrenador.getText());
							cambiar.setEstadio(txtEstadio.getText());
							JOptionPane.showMessageDialog(null, "El equipo "+txtNombre.getText()+" ha sido modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							loadEquipos();
						}
						
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				btnModificar.setEnabled(false);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnSalir = new JButton("");
				btnSalir.setIcon(new ImageIcon(LisEquipo.class.getResource("/imagenes/cross-close-or-delete-circular-interface-button-symbol.png")));
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 150, 668, 154);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow>=0) {
					selected = table.getValueAt(selectedRow, 0).toString();
					txtNombre.setText(Liga.getInstance().buscarEquipo(selected).getNombre());
					txtCiudad.setText(Liga.getInstance().buscarEquipo(selected).getCiudad());
					txtEntrenador.setText(Liga.getInstance().buscarEquipo(selected).getEntrenador());
					txtEstadio.setText(Liga.getInstance().buscarEquipo(selected).getEstadio());
					ImageIcon imageicon = new ImageIcon(Liga.getInstance().buscarEquipo(selected).getLogo().getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
					lblLogo.setIcon(imageicon);
					btnModificar.setEnabled(true);
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(table);
		String[] columnNames = {"Nombre","Entrenador","Estadio","Cant. Jugadores"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(6, 11, 61, 16);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(80, 6, 230, 26);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEntrenador = new JLabel("Entrenador:");
		lblEntrenador.setBounds(6, 44, 82, 16);
		getContentPane().add(lblEntrenador);
		
		JLabel lblEstadio = new JLabel("Estadio:");
		lblEstadio.setBounds(6, 81, 61, 16);
		getContentPane().add(lblEstadio);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(6, 114, 61, 16);
		getContentPane().add(lblCiudad);
		
		lblLogo = new JLabel("");
		lblLogo.setBounds(511, 25, 113, 113);
		getContentPane().add(lblLogo);
		
		txtEntrenador = new JTextField();
		txtEntrenador.setBounds(80, 39, 230, 26);
		getContentPane().add(txtEntrenador);
		txtEntrenador.setColumns(10);
		
		txtEstadio = new JTextField();
		txtEstadio.setBounds(80, 76, 230, 26);
		getContentPane().add(txtEstadio);
		txtEstadio.setColumns(10);
		
		txtCiudad = new JTextField();
		txtCiudad.setBounds(80, 109, 230, 26);
		getContentPane().add(txtCiudad);
		txtCiudad.setColumns(10);
		loadEquipos();
	}
	
	
	void loadEquipos() {
		Object[] fila = new Object[model.getColumnCount()];
		model.setRowCount(0);
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			fila[0] = actual.getNombre();
			fila[1] = actual.getEntrenador();
			fila[2] = actual.getEstadio();
			fila[3] = actual.getJugadores().size();
			model.addRow(fila);
		}
	}
}
