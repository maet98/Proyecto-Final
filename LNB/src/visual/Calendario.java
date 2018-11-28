package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import logico.Equipo;
import logico.Liga;
import logico.Partido;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calendario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private int selectedIndex;
	private JButton btnJugar;

	public Calendario() {
		setTitle("Calendario");
		setBounds(100, 100, 587, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(table.getSelectedRow()>=0) {
							selectedIndex = table.getSelectedRow();
							btnJugar.setEnabled(true);
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Equipo Local","Equipo Visitante","Fecha del juego","Tanteo Local","Tanteo Visitante"};
				table.setModel(model);
				model.setColumnIdentifiers(columnNames);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnJugar = new JButton("Jugar Partido");
				btnJugar.setEnabled(false);
				btnJugar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Equipo local = Liga.getInstance().buscarEquipo(table.getValueAt(selectedIndex, 0).toString());
						Equipo visitante = Liga.getInstance().buscarEquipo(table.getValueAt(selectedIndex, 1).toString());
						ControladorPartidos jugar = new ControladorPartidos(local,visitante);
						jugar.setModal(true);
						jugar.setVisible(true);
					}
				});
				btnJugar.setActionCommand("OK");
				buttonPane.add(btnJugar);
				getRootPane().setDefaultButton(btnJugar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadPartidos();
	}
	private void loadPartidos() {
		Object[] fila = new Object[model.getColumnCount()];
		model.setRowCount(0);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		for (Partido actual : Liga.getInstance().getPartidos()) {
			fila[0] = actual.getLocal().getNombre();
			fila[1] = actual.getVisitante().getNombre();
			fila[2] = format.format(actual.getFecha());
			if(actual.getMarcador()!= null) {
				fila[3] = actual.getMarcador().getLocal();
				fila[4] = actual.getMarcador().getVisitante();
			}
			else {
				fila[3] = 0;
				fila[4] = 0;
			}
			model.addRow(fila);
		}
	}

}
