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
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;

public class Calendario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private int selectedIndex;
	private JButton btnJugar;
	JRadioButton rdbtnTodos;
	JRadioButton rdbtnJugados;
	JRadioButton rdbtnNoJugados;

	public Calendario() {
		setTitle("Calendario");
		setBounds(100, 100, 587, 445);
		setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 69, 577, 311);
			contentPanel.add(scrollPane);
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(6, 6, 575, 59);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setSelected(true);
		rdbtnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnTodos.isSelected()) {
					rdbtnNoJugados.setSelected(false);
					rdbtnJugados.setSelected(false);
					loadPartidos();
					
				}
			}
		});
		rdbtnTodos.setBounds(6, 17, 83, 23);
		panel.add(rdbtnTodos);
		
		rdbtnJugados = new JRadioButton("Jugados");
		rdbtnJugados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnJugados.isSelected()) {
					rdbtnNoJugados.setSelected(false);
					rdbtnTodos.setSelected(false);
					loadPartidos();
				}
			}
		});
		rdbtnJugados.setBounds(102, 17, 106, 23);
		panel.add(rdbtnJugados);
		
		rdbtnNoJugados = new JRadioButton("No jugados");
		rdbtnNoJugados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNoJugados.isSelected()) {
					rdbtnJugados.setSelected(false);
					rdbtnTodos.setSelected(false);
					loadPartidos();
				}
			}
		});
		rdbtnNoJugados.setBounds(210, 17, 111, 23);
		panel.add(rdbtnNoJugados);
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
						String ac = local.getNombre()+" vs "+visitante.getNombre();
						ControladorPartidos jugar = new ControladorPartidos(Liga.getInstance().BuscarPartido(ac));
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
			if (rdbtnJugados.isSelected() && actual.isJugado()) {
				model.addRow(fila);
			}
			if (rdbtnNoJugados.isSelected() && !actual.isJugado()) {
				model.addRow(fila);
			}
			if (rdbtnTodos.isSelected()) {
				model.addRow(fila);
			}	
		}
	}
}
