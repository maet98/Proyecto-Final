package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.Desempenno;
import logico.Equipo;
import logico.Jugador;

import javax.swing.ListSelectionModel;

public class ControladorPartidos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMarcador;
	private JTable tablaLocal;
	private JTable Tablavisitante;
	private DefaultTableModel modelLocal;
	private DefaultTableModel modelVisitante;
	private String[] columnNames = {"Numero","Nombre","Tiros Libres","Tiros de Dos","Tiros de Tres"};
	private Equipo Local;
	private Equipo Visitante;
	private ArrayList<Desempenno> DesLocal;
	private ArrayList<Desempenno> DesVisitante;
	
	public ControladorPartidos(Equipo Local, Equipo Visitante) {
		setTitle("Controlador Partido");
		setBounds(100, 100, 951, 581);
		this.Local = Local;
		this.Visitante = Visitante;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Partido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblMarcador = new JLabel("Marcador");
			lblMarcador.setBounds(441, 430, 58, 16);
			panel.add(lblMarcador);
			
			txtMarcador = new JTextField();
			txtMarcador.setHorizontalAlignment(SwingConstants.CENTER);
			txtMarcador.setEnabled(false);
			txtMarcador.setEditable(false);
			txtMarcador.setBounds(398, 457, 131, 22);
			panel.add(txtMarcador);
			txtMarcador.setColumns(10);
			
			modelLocal = new DefaultTableModel();
			modelVisitante = new DefaultTableModel();
			DesLocal = new ArrayList<>();
			DesVisitante = new ArrayList<>();
			JLabel lblEquipoLocal = new JLabel("Equipo Local");
			lblEquipoLocal.setBounds(160, 13, 84, 16);
			panel.add(lblEquipoLocal);
			
			JLabel lblEquipoVisitante = new JLabel("Equipo Visitante");
			lblEquipoVisitante.setBounds(646, 13, 120, 16);
			panel.add(lblEquipoVisitante);
			
			JScrollPane scrollPaneLocal = new JScrollPane();
			scrollPaneLocal.setBounds(6, 41, 425, 355);
			panel.add(scrollPaneLocal);
			
			tablaLocal = new JTable();
			tablaLocal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modelLocal.setColumnIdentifiers(columnNames);
			tablaLocal.setModel(modelLocal);
			scrollPaneLocal.setViewportView(tablaLocal);
			
			JScrollPane scrollPaneVisitante = new JScrollPane();
			scrollPaneVisitante.setBounds(497, 41, 425, 355);
			panel.add(scrollPaneVisitante);
			
			Tablavisitante = new JTable();
			Tablavisitante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modelVisitante.setColumnIdentifiers(columnNames);
			Tablavisitante.setModel(modelVisitante);
			scrollPaneVisitante.setViewportView(Tablavisitante);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnTerminar = new JButton("Finalizar Partido");
				btnTerminar.setActionCommand("OK");
				buttonPane.add(btnTerminar);
				getRootPane().setDefaultButton(btnTerminar);
			}
		}
		loadLocal();
		loadVisitante();
	}
	private void loadLocal() {
		Object[] fila = new Object[modelLocal.getColumnCount()];
		modelLocal.setRowCount(0);
		for (Jugador actual : Local.getJugadores()) {
			fila[0] = actual.getNumero();
			fila[1] = actual.getNombre();
			fila[2] = 0;
			fila[3] = 0;
			fila[4] = 0;
			modelLocal.addRow(fila);
		}
	}
	private void loadVisitante() {
		Object[] fila = new Object[modelVisitante.getColumnCount()];
		modelVisitante.setRowCount(0);
		for (Jugador actual : Visitante.getJugadores()) {
			fila[0] = actual.getNumero();
			fila[1] = actual.getNombre();
			fila[2] = 0;
			fila[3] = 0;
			fila[4] = 0;
			modelVisitante.addRow(fila);
		}
	}
}
