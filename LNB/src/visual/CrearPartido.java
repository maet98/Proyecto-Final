package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import com.toedter.calendar.JDateChooser;

import logico.Equipo;
import logico.Liga;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrearPartido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String EquipoLocal = "";
	private String EquipoVisitante = "";
	private DefaultListModel<String> modelLocal;
	private DefaultListModel<String> modelVisitante;
	private JList ListLocal;
	private JList ListVisitante;
	
	public CrearPartido() {
		setTitle("Crear Partido");
		setBounds(100, 100, 594, 484);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblEquipoLocal = new JLabel("Equipo Local");
			lblEquipoLocal.setBounds(83, 23, 93, 16);
			panel.add(lblEquipoLocal);
			
			JLabel lblEquipoVisitante = new JLabel("Equipo Visitante");
			lblEquipoVisitante.setBounds(414, 23, 99, 16);
			panel.add(lblEquipoVisitante);
			
			modelLocal = new DefaultListModel<String>();
			modelVisitante = new DefaultListModel<String>();
			
			ListLocal = new JList();
			ListLocal.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					EquipoLocal = ListLocal.getSelectedValue().toString();
					loadVisitante();
				}
			});
			ListLocal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListLocal.setModel(modelLocal);
			ListLocal.setBounds(12, 51, 195, 151);
			panel.add(ListLocal);
			
			ListVisitante = new JList();
			ListVisitante.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					EquipoVisitante = ListVisitante.getSelectedValue().toString();
					loadLocal();
				}
			});
			ListVisitante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListVisitante.setModel(modelVisitante);
			ListVisitante.setBounds(366, 50, 195, 152);
			panel.add(ListVisitante);
			
			JLabel lblFechaDelPartido = new JLabel("Fecha del Partido:");
			lblFechaDelPartido.setBounds(12, 213, 114, 16);
			panel.add(lblFechaDelPartido);
			
			JDateChooser dateChooser = new JDateChooser();
			dateChooser.setBounds(112, 213, 95, 20);
			panel.add(dateChooser);
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
		loadLocal();
		loadVisitante();
	}
	void loadLocal() {
		int i = 1;
		modelLocal.removeAllElements();
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			if(!EquipoVisitante.equalsIgnoreCase(actual.getNombre())) {
				modelLocal.addElement(i+": "+actual.getNombre());
				i++;
			}
		}
	}
	void loadVisitante() {
		int i = 1;
		modelVisitante.removeAllElements();
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			if(!EquipoVisitante.equalsIgnoreCase(actual.getNombre())) {
				modelVisitante.addElement(i+": "+actual.getNombre());
				i++;
			}
		}
	}
}
