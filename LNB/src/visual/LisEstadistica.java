package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import logico.Jugador;
import logico.Liga;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LisEstadistica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private TableRowSorter<TableModel>modeloOrdenado;
	private DefaultTableModel model;

	public LisEstadistica() {
		setTitle("Listar Estadisticas");
		setBounds(100, 100, 871, 440);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				model = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Nombre", "Equipo", "Posicion", "Tiros de Tres", "Tiros de Dos", "Tiros Libres", "Rebotes", "Faltas", "Asistencia"
						}
					) {
						Class[] columnTypes = new Class[] {
							String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					};
				modeloOrdenado = new TableRowSorter<TableModel>(model);
				table.setRowSorter(modeloOrdenado);
				table.setModel(model);
				table.getColumnModel().getColumn(0).setPreferredWidth(150);
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(3).setResizable(false);
				table.getColumnModel().getColumn(4).setResizable(false);
				table.getColumnModel().getColumn(5).setResizable(false);
				table.getColumnModel().getColumn(6).setResizable(false);
				table.getColumnModel().getColumn(7).setResizable(false);
				table.getColumnModel().getColumn(8).setResizable(false);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("modificar");
				buttonPane.add(btnModificar);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		loadEstadisticas();
	}
	
	private void loadEstadisticas() {
		model.setRowCount(0);
		Object[] fila = new Object[model.getColumnCount()];
		for (Jugador jugador : Liga.getInstance().getJugadores()) {
			fila[0] = jugador.getNombre()+" "+jugador.getApellido();
			fila[1] = jugador.getEquipo().getNombre();
			fila[2] = jugador.getPosicion();
			fila[3] = jugador.getDesempenno().getTirosDeTres();
			fila[4] = jugador.getDesempenno().getTirosDeDos();
			fila[5] = jugador.getDesempenno().getTirosLibres();
			fila[6] = jugador.getDesempenno().getRebotes();
			fila[7] = jugador.getDesempenno().getFaltas();
			fila[8] = jugador.getDesempenno().getAsistencias();
			model.addRow(fila);
		}
	}

}
