package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Equipo;
import logico.Liga;

import java.awt.event.ActionListener;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Posiciones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private JTable table;
	
	public Posiciones() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Posiciones.class.getResource("/imagenes/podium.png")));
		setBackground(Color.decode("#d8c1aa"));
		setTitle("Posiciones");
		setBounds(100, 100, 597, 447);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 6, 570, 340);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
				String[] columnNames = {"Posicion","Equipo","Partidos Jugados","Ganados","Perdidos"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
			}
		}
		
		loadEquipos();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("");
				cancelButton.setIcon(new ImageIcon(Posiciones.class.getResource("/imagenes/cross-close-or-delete-circular-interface-button-symbol.png")));
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
		Collections.sort(Liga.getInstance().getEquipos());
		Object[] fila = new Object[model.getColumnCount()];
		model.setRowCount(0);
		int i =1;
		for (int j = Liga.getInstance().getEquipos().size()-1; j >=0; j--) {
			fila[0] = i;
			fila[1] = Liga.getInstance().getEquipos().get(i).getNombre();
			fila[2] = Liga.getInstance().getEquipos().get(i).getPartidosJugados();
			fila[3] = Liga.getInstance().getEquipos().get(i).getPartidosGanados();
			fila[4] = Liga.getInstance().getEquipos().get(i).getPartidosPerdidos();
			model.addRow(fila);
			i++;
		}
		
	}
}
	
	


