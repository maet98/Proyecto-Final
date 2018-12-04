package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

public class LisEquipo extends JDialog {
	private JTable table;
	private DefaultTableModel model;
	private String selected = "";
	private JButton btnModificar;


	public LisEquipo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LisEquipo.class.getResource("/imagenes/basketball.png")));
		setTitle("Listar Equipos");
		setBounds(100, 100, 580, 377);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 305, 564, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Equipo cambiar = Liga.getInstance().buscarEquipo(selected);
						RegEquipo modificar = new RegEquipo(cambiar);
						modificar.setModal(true);
						modificar.setVisible(true);
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				btnModificar.setEnabled(false);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 564, 305);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow>=0) {
					selected = table.getValueAt(selectedRow, 0).toString();
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
