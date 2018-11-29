package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Equipo;
import logico.Liga;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Posiciones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Posiciones dialog = new Posiciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Posiciones() {
		setTitle("Posiciones");
		setBounds(100, 100, 600, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		table = new JTable();
		table.setBounds(17, 434, 577, -404);
		contentPanel.add(table);
		String[] columnNames = {"Equipo","Partidos Jugados","Ganados","Perdidos"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		loadEquipos();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void loadEquipos(){
		Object[] fila = new Object[model.getColumnCount()];
		model.setRowCount(0);
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			fila[0] = actual.getNombre();
			fila[1] = actual.getPartidosJugados();
			fila[2] = actual.getPartidosGanados();
			fila[3] = actual.getPartidosGanados();
			model.addRow(fila);
		}
		
	}
}
	
	

