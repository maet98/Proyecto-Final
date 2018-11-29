package visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource.TitledBorderUIResource;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.Desempenno;
import logico.Equipo;
import logico.Jugador;

import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private int TanteoLocal;
	private int TanteoVisitante;
	
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
			tablaLocal.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					int selectedIndex = tablaLocal.getSelectedRow();
					if(selectedIndex>=0) {
						System.out.println(event.getClickCount());
						TanteoLocal = calcLocal();
						cambiarMarcardor();
					}
				}
			});
			tablaLocal.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent event) {
					if(Character.isDigit(event.getKeyChar())){
						TanteoLocal = calcLocal();
						System.out.println(TanteoLocal+" "+TanteoVisitante);
						cambiarMarcardor();
					}
					else {
						event.consume();
					}
				}
			});
			tablaLocal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modelLocal.setColumnIdentifiers(columnNames);
			tablaLocal.setModel(modelLocal);
			scrollPaneLocal.setViewportView(tablaLocal);
			
			JScrollPane scrollPaneVisitante = new JScrollPane();
			scrollPaneVisitante.setBounds(497, 41, 425, 355);
			panel.add(scrollPaneVisitante);
			
			Tablavisitante = new JTable();
			Tablavisitante.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(Character.isDigit(e.getKeyChar())){
						TanteoVisitante = calcVisitante();
						cambiarMarcardor();
					}
					else {
						e.consume();
					}
				}
			});
			Tablavisitante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modelVisitante.setColumnIdentifiers(columnNames);
			Tablavisitante.setModel(modelVisitante);
			scrollPaneVisitante.setViewportView(Tablavisitante);
			
			JButton btnInsertarTiros = new JButton("Insertar Tiros");
			btnInsertarTiros.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int selectedRow = tablaLocal.getSelectedRow();
					if(selectedRow>=0) {
						int tirosLibres = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de tiros libres", "Tiros libres", JOptionPane.INFORMATION_MESSAGE));
						int tiroDe2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de tiros de 2", "Tiros de 2", JOptionPane.INFORMATION_MESSAGE));
						int tiroDe3 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de tiros de 3", "Tiros de 3", JOptionPane.INFORMATION_MESSAGE));
						modelLocal.setValueAt(tirosLibres, selectedRow, 2);
						modelLocal.setValueAt(tiroDe2, selectedRow, 3);
						modelLocal.setValueAt(tiroDe3, selectedRow, 4);
						TanteoLocal = calcLocal();
						cambiarMarcardor();
					}
				}
			});
			btnInsertarTiros.setBounds(147, 426, 120, 25);
			panel.add(btnInsertarTiros);
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
		calcLocal();
		calcVisitante();
		cambiarMarcardor();
	}
	private int calcLocal() {
		int t = 0;
		for(int i = 0;i < Local.getJugadores().size();i++) {
			t += Integer.parseInt(tablaLocal.getValueAt(i, 2).toString());
			t += Integer.parseInt(tablaLocal.getValueAt(i, 3).toString())*2;
			t += Integer.parseInt(tablaLocal.getValueAt(i, 4).toString())*3;
		}
		return t;
	}
	private int calcVisitante() {
		int t = 0;
		for(int i = 0;i < Visitante.getJugadores().size();i++) {
			t += Integer.parseInt(Tablavisitante.getValueAt(i, 2).toString());
			t += Integer.parseInt(Tablavisitante.getValueAt(i, 3).toString())*2;
			t += Integer.parseInt(Tablavisitante.getValueAt(i, 4).toString())*3;
		}
		return t;
	}
	private void cambiarMarcardor() {
		String marcardor = TanteoLocal+" - "+TanteoVisitante;
		txtMarcador.setText(marcardor);
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



