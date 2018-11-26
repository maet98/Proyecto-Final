package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Equipo;
import logico.Jugador;
import logico.Liga;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LisJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtBuscador;
	private JComboBox<String> cbxPosicion;
	private DefaultComboBoxModel<String> modelEquipo;
	private JComboBox<String> cbxEquipo;
	private JLabel lblBuscador;
	private JLabel lblPosicion;
	private JLabel lblFotoequipo;
	private JRadioButton rdbtnPosicion;
	private JRadioButton rdbtnNacionalidad;
	private Equipo Seleccionado;
	private JRadioButton rdbtnNombre;

	public LisJugador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LisJugador.class.getResource("/imagenes/basketball.png")));
		setTitle("Listar Jugadores");
		setBounds(100, 100, 752, 491);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		cbxEquipo = new JComboBox<String>();
		cbxEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbxEquipo.getSelectedIndex()>0) {
					String nombre = cbxEquipo.getSelectedItem().toString();
					Seleccionado = Liga.getInstance().buscarEquipo(nombre);
					lblFotoequipo.setIcon(Seleccionado.getLogo());
				}
			}
		});
		modelEquipo = new DefaultComboBoxModel<String>();
		cbxEquipo.setModel(modelEquipo);
		cbxEquipo.setBounds(66, 27, 132, 20);
		contentPanel.add(cbxEquipo);
		
		
		JLabel lblEquipo = new JLabel("Equipo");
		lblEquipo.setBounds(10, 30, 46, 14);
		contentPanel.add(lblEquipo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 119, 726, 300);
		contentPanel.add(scrollPane);
		
		model = new DefaultTableModel();
		String[] columnNames = {"Cedula","Nombre","Nacionalidad","Posición","Edad","Dorsal","Equipo","Altura"};
		table = new JTable();
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		lblFotoequipo = new JLabel("");
		lblFotoequipo.setBounds(442, 11, 110, 97);
		contentPanel.add(lblFotoequipo);
		
		lblBuscador = new JLabel("Buscador:");
		lblBuscador.setBounds(10, 94, 76, 14);
		contentPanel.add(lblBuscador);
		
		txtBuscador = new JTextField();
		txtBuscador.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				loadJugador();
			}
		});
		txtBuscador.setBounds(96, 91, 110, 20);
		contentPanel.add(txtBuscador);
		txtBuscador.setColumns(10);
		
		rdbtnNombre = new JRadioButton("Nombre");
		rdbtnNombre.setSelected(true);
		rdbtnNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNacionalidad.setSelected(false);
				rdbtnNombre.setSelected(true);
				rdbtnPosicion.setSelected(false);
				lblBuscador.setVisible(true);
				txtBuscador.setVisible(true);
				cbxPosicion.setVisible(false);
				lblPosicion.setVisible(false);
				
			}
		});
		rdbtnNombre.setBounds(6, 58, 80, 23);
		contentPanel.add(rdbtnNombre);
		
		rdbtnPosicion = new JRadioButton("Posici\u00F3n");
		rdbtnPosicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNacionalidad.setSelected(false);
				rdbtnNombre.setSelected(false);
				rdbtnPosicion.setSelected(true);
				lblBuscador.setVisible(false);
				txtBuscador.setVisible(false);
				cbxPosicion.setVisible(true);
				lblPosicion.setVisible(true);
			}
		});
		rdbtnPosicion.setBounds(88, 58, 88, 23);
		contentPanel.add(rdbtnPosicion);
		
		JLabel lblFotojugador = new JLabel("");
		lblFotojugador.setBounds(562, 11, 116, 97);
		contentPanel.add(lblFotojugador);
		
		rdbtnNacionalidad = new JRadioButton("Nacionalidad");
		rdbtnNacionalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNacionalidad.setSelected(true);
				rdbtnNombre.setSelected(false);
				rdbtnPosicion.setSelected(false);
				lblBuscador.setVisible(true);
				txtBuscador.setVisible(true);
				cbxPosicion.setVisible(false);
				lblPosicion.setVisible(false);
				
			}
		});
		rdbtnNacionalidad.setBounds(184, 58, 116, 23);
		contentPanel.add(rdbtnNacionalidad);
		
		lblPosicion = new JLabel("Posici\u00F3n:");
		lblPosicion.setVisible(false);
		lblPosicion.setBounds(10, 94, 74, 14);
		contentPanel.add(lblPosicion);
		
		cbxPosicion = new JComboBox<String>();
		cbxPosicion.setVisible(false);
		cbxPosicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadJugador();
			}
		});
		cbxPosicion.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Base", "Escolta Alero", "Ala-p\u00EDvot", "P\u00EDvot"}));
		cbxPosicion.setBounds(96, 88, 110, 20);
		contentPanel.add(cbxPosicion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		loadEquipos();
		loadJugador();
	}
	
	void loadEquipos() {
		modelEquipo.addElement("Todos");
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			modelEquipo.addElement(actual.getNombre());
		}
	}
	
	void loadJugador() {
		model.setRowCount(0);
		Object[] fila = new Object[model.getColumnCount()];
		for (Jugador actual : Liga.getInstance().getJugadores()) {
			fila[0] = actual.getCedula();
			fila[1] = actual.getNombre()+" "+actual.getApellido();
			fila[2] = actual.getNacionalidad();
			fila[3] = actual.getPosicion();
			fila[4] = actual.getEdad();
			fila[5] = actual.getNumero();
			fila[6] = actual.getEquipo().getNombre();
			fila[7] = actual.getAltura();
			if(rdbtnNombre.isSelected() && txtBuscador.getText().equalsIgnoreCase("") && cbxEquipo.getSelectedIndex()==0) {
				model.addRow(fila);
			}else if(cbxEquipo.getSelectedIndex()==0 && rdbtnNombre.isSelected() && txtBuscador.getText().length()>0) {
				if(fila[1].toString().toLowerCase().contains(txtBuscador.getText().toLowerCase())) {
					model.addRow(fila);
				}
			}else if(cbxEquipo.getSelectedIndex()== 0 && rdbtnPosicion.isSelected()) {
				if(fila[3].toString().equalsIgnoreCase(cbxPosicion.getSelectedItem().toString())) {
					model.addRow(fila);
				}
			}
		}
		
	}
	
}
