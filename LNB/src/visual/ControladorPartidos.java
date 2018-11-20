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

public class ControladorPartidos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMarcador;

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
		setBounds(100, 100, 956, 511);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Partido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(58, 124, 2, 2);
			panel.add(scrollPane);
			
			JPanel panelLocal = new JPanel();
			panelLocal.setBounds(12, 31, 368, 375);
			panel.add(panelLocal);
			
			JLabel lblMarcador = new JLabel("Marcador");
			lblMarcador.setBounds(422, 13, 56, 16);
			panel.add(lblMarcador);
			
			txtMarcador = new JTextField();
			txtMarcador.setHorizontalAlignment(SwingConstants.CENTER);
			txtMarcador.setEnabled(false);
			txtMarcador.setEditable(false);
			txtMarcador.setBounds(391, 41, 131, 22);
			panel.add(txtMarcador);
			txtMarcador.setColumns(10);
			
			JPanel PanelVisitante = new JPanel();
			PanelVisitante.setBounds(548, 31, 368, 375);
			panel.add(PanelVisitante);
			
			JLabel lblEquipoLocal = new JLabel("Equipo Local");
			lblEquipoLocal.setBounds(120, 13, 84, 16);
			panel.add(lblEquipoLocal);
			
			JLabel lblEquipoVisitante = new JLabel("Equipo Visitante");
			lblEquipoVisitante.setBounds(678, 13, 91, 16);
			panel.add(lblEquipoVisitante);
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
