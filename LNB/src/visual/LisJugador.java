package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

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
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class LisJugador<IconImage> extends JDialog {

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
	private JButton btnDesempeo;
	private JButton btnLesion;
	private int selectedIndexJugador;
	private String cedulaSelectedJugador = "";
	private JButton btnModificar;
	private JLabel lblFotojugador;

	public LisJugador() {
		setResizable(false);
		setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LisJugador.class.getResource("/imagenes/basketball.png")));
		setTitle("Listar Jugadores");
		setBounds(100, 100, 808, 490);
		setLocationRelativeTo(null);
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
					//ImageIcon iconoEquipo = new ImageIcon(nuevo.getFotoJugador().getImage().getScaledInstance(lblFotojugador.getWidth(), lblFotojugador.getHeight(), Image.SCALE_DEFAULT));
					ImageIcon iconoEquipo = new ImageIcon(Seleccionado.getLogo().getImage().getScaledInstance(lblFotoequipo.getWidth(), lblFotoequipo.getHeight(), Image.SCALE_DEFAULT));
					lblFotoequipo.setIcon(iconoEquipo);
					loadJugador();
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
		scrollPane.setBounds(10, 121, 778, 286);
		contentPanel.add(scrollPane);
		
		model = new DefaultTableModel();
		String[] columnNames = {"Cedula","Nombre","Nacionalidad","Posici�n","Edad","Dorsal","Equipo","Altura"};
		table = new JTable();
		table.setSelectionBackground(Color.BLUE);
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()>=0) {
					selectedIndexJugador = table.getSelectedRow();
					cedulaSelectedJugador = model.getValueAt(selectedIndexJugador, 0).toString();
					Jugador nuevo = Liga.getInstance().buscarJugadorId(cedulaSelectedJugador);
					ImageIcon imageicon = new ImageIcon(nuevo.getFotoJugador().getImage().getScaledInstance(lblFotojugador.getWidth(), lblFotojugador.getHeight(), Image.SCALE_DEFAULT));
					lblFotojugador.setIcon(imageicon);
					btnDesempeo.setEnabled(true);
					btnLesion.setEnabled(true);
					btnModificar.setEnabled(true);
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new CellRenderer());
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
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				    if(Character.isLetter(c) || Character.isISOControl(c))
				    {
				        e = e;			
				    }	
				    else
				        e.consume();
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
				loadJugador();
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
				loadJugador();
			}
		});
		rdbtnPosicion.setBounds(88, 58, 88, 23);
		contentPanel.add(rdbtnPosicion);
		
		lblFotojugador = new JLabel("");
		lblFotojugador.setBounds(583, 11, 116, 97);
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
				loadJugador();
				
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
			
			btnLesion = new JButton("Lesion");
			btnLesion.setEnabled(false);
			btnLesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Equipo equipo = Liga.getInstance().buscarEquipo(cbxEquipo.getSelectedItem().toString());
					Jugador jugador = Liga.getInstance().buscarJugadorId(model.getValueAt(table.getSelectedRow(), 0).toString());
					RegLesion nuevo = new RegLesion(jugador,equipo);
					nuevo.setModal(true);
					nuevo.setVisible(true);
				}
			});
			buttonPane.add(btnLesion);
			
			btnDesempeo = new JButton("Desempe\u00F1o");
			btnDesempeo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Jugador jugador = Liga.getInstance().buscarJugadorId(cedulaSelectedJugador);
					LisEstadisticas nuevo = new LisEstadisticas(jugador);
					nuevo.setModal(true);
					nuevo.setVisible(true);
				}
			});
			btnDesempeo.setEnabled(false);
			buttonPane.add(btnDesempeo);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Jugador jugador = Liga.getInstance().buscarJugadorId(cedulaSelectedJugador);
						RegJugador nuevo;
						try {
							nuevo = new RegJugador(jugador);
							nuevo.setModal(true);
							nuevo.setVisible(true);
						} catch (FileNotFoundException | ParseException e1) {
							e1.printStackTrace();
						}
						
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		loadEquipos();
		loadJugador();
		table.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
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
			if((rdbtnNombre.isSelected() && txtBuscador.getText().equalsIgnoreCase(""))||(rdbtnNacionalidad.isSelected() && txtBuscador.getText().equalsIgnoreCase("") )||(rdbtnPosicion.isSelected() && cbxPosicion.getSelectedIndex()==0)) {
				if(cbxEquipo.getSelectedIndex()==0)
					model.addRow(fila);
				else {
					if(fila[6].toString().equalsIgnoreCase(cbxEquipo.getSelectedItem().toString())) {
						model.addRow(fila);
					}
				}
			}
			else if(rdbtnNombre.isSelected() && txtBuscador.getText().length()>0) {
				if(cbxEquipo.getSelectedIndex() == 0 && fila[1].toString().toLowerCase().contains(txtBuscador.getText().toLowerCase())) {
					model.addRow(fila);
				}
				else if(cbxEquipo.getSelectedIndex()>0 && fila[1].toString().toLowerCase().contains(txtBuscador.getText().toLowerCase()) && cbxEquipo.getSelectedItem().toString().equalsIgnoreCase(actual.getEquipo().getNombre())) {
					model.addRow(fila);
				}
			}else if( rdbtnPosicion.isSelected()) {
				if(cbxEquipo.getSelectedIndex()== 0 && fila[3].toString().equalsIgnoreCase(cbxPosicion.getSelectedItem().toString())) {
					model.addRow(fila);
				}
				else if( cbxEquipo.getSelectedIndex()> 0 && fila[3].toString().equalsIgnoreCase(cbxPosicion.getSelectedItem().toString()) && cbxEquipo.getSelectedItem().toString().equalsIgnoreCase(actual.getEquipo().getNombre())) {
					model.addRow(fila);
				}
			}else if(rdbtnNacionalidad.isSelected()) {
				if(cbxEquipo.getSelectedIndex() == 0 && fila[2].toString().toLowerCase().contains(txtBuscador.getText().toLowerCase())) {
					model.addRow(fila);
				}else if(cbxEquipo.getSelectedIndex()>0 && txtBuscador.getText().toLowerCase().contains(fila[2].toString().toLowerCase())) {
					model.addRow(fila);
				}
			}
		}
		
	}
	

	
}
