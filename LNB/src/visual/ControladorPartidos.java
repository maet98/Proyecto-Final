package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

public class ControladorPartidos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMarcador;
	private JTable tablaLocal;
	private JTable Tablavisitante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ControladorPartidos dialog = new ControladorPartidos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ControladorPartidos() {
		setTitle("Controlador Partido");
		setBounds(100, 100, 805, 580);
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
			lblMarcador.setBounds(368, 432, 58, 16);
			panel.add(lblMarcador);
			
			txtMarcador = new JTextField();
			txtMarcador.setHorizontalAlignment(SwingConstants.CENTER);
			txtMarcador.setEnabled(false);
			txtMarcador.setEditable(false);
			txtMarcador.setBounds(332, 457, 131, 22);
			panel.add(txtMarcador);
			txtMarcador.setColumns(10);
			
			JLabel lblEquipoLocal = new JLabel("Equipo Local");
			lblEquipoLocal.setBounds(120, 13, 84, 16);
			panel.add(lblEquipoLocal);
			
			JLabel lblEquipoVisitante = new JLabel("Equipo Visitante");
			lblEquipoVisitante.setBounds(544, 13, 120, 16);
			panel.add(lblEquipoVisitante);
			
			JScrollPane scrollPaneLocal = new JScrollPane();
			scrollPaneLocal.setBounds(6, 41, 368, 355);
			panel.add(scrollPaneLocal);
			
			tablaLocal = new JTable();
			tablaLocal.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Jugadores", "Tiros Libres", "Tiros de Dos", "Tiros de Tres"
				}
			));
			scrollPaneLocal.setViewportView(tablaLocal);
			
			JScrollPane scrollPaneVisitante = new JScrollPane();
			scrollPaneVisitante.setBounds(415, 41, 368, 355);
			panel.add(scrollPaneVisitante);
			
			Tablavisitante = new JTable();
			Tablavisitante.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Jugadores", "Tiros Libres", "Tiros de Dos", "Tiros de Tres"
				}
			));
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
	}
}
