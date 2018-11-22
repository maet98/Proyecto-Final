package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class CrearPartido extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearPartido dialog = new CrearPartido();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearPartido() {
		setTitle("Crear Partido");
		setBounds(100, 100, 465, 334);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblEquipoLocal = new JLabel("Equipo Local");
			lblEquipoLocal.setBounds(23, 23, 93, 16);
			panel.add(lblEquipoLocal);
			
			JLabel lblEquipoVisitante = new JLabel("Equipo Visitante");
			lblEquipoVisitante.setBounds(266, 23, 99, 16);
			panel.add(lblEquipoVisitante);
			
			JList ListLocal = new JList();
			ListLocal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListLocal.setBounds(12, 51, 153, 131);
			panel.add(ListLocal);
			
			JList ListVisitante = new JList();
			ListVisitante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListVisitante.setBounds(245, 51, 153, 131);
			panel.add(ListVisitante);
			
			JLabel lblFechaDelPartido = new JLabel("Fecha del Partido");
			lblFechaDelPartido.setBounds(12, 213, 114, 16);
			panel.add(lblFechaDelPartido);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
