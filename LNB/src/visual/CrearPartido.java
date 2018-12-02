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
import logico.Partido;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearPartido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String EquipoLocal = "";
	private String EquipoVisitante = "";
	private DefaultListModel<String> modelLocal;
	private DefaultListModel<String> modelVisitante;
	private JList<String> ListLocal;
	private JList<String> ListVisitante;
	private JDateChooser dateChooser;
	private JButton btnCrear;
	
	public CrearPartido() {
		setTitle("Crear Partido");
		setBounds(100, 100, 594, 346);
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
			
			ListLocal = new JList<String>();
			ListLocal.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					EquipoLocal = ListLocal.getSelectedValue().toString();
					EquipoLocal = EquipoLocal.substring(3, EquipoLocal.length());
					if(ListLocal.getSelectedIndex()>=0 && ListVisitante.getSelectedIndex()>=0) {
						btnCrear.setEnabled(true);
					}
					loadVisitante();
				}
			});
			ListLocal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListLocal.setModel(modelLocal);
			ListLocal.setBounds(12, 51, 195, 151);
			panel.add(ListLocal);
			
			ListVisitante = new JList<String>();
			ListVisitante.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					EquipoVisitante = ListVisitante.getSelectedValue().toString();
					EquipoVisitante = EquipoVisitante.substring(3, EquipoVisitante.length());
					if(ListLocal.getSelectedIndex()>=0 && ListVisitante.getSelectedIndex()>=0) {
						btnCrear.setEnabled(true);
					}
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
			
			dateChooser = new JDateChooser();
			dateChooser.setDate(new Date());
			dateChooser.setBounds(125, 215, 114, 20);
			panel.add(dateChooser);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("Crear");
				btnCrear.setEnabled(false);
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Partido nuevo = new Partido(String.valueOf(Liga.getInstance().getIdsPartidos()+1), dateChooser.getDate(), Liga.getInstance().buscarEquipo(EquipoLocal), Liga.getInstance().buscarEquipo(EquipoVisitante));
						Liga.getInstance().addPartido(nuevo);
						Liga.getInstance().buscarEquipo(EquipoLocal).addPartidos(nuevo);
						Liga.getInstance().buscarEquipo(EquipoVisitante).addPartidos(nuevo);
						limpiar();
					}

					
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadLocal();
		loadVisitante();
	}
	private void loadLocal() {
		int i = 1;
		modelLocal.removeAllElements();
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			for(Partido part: actual.getPartidos()) {
				if(!EquipoVisitante.equalsIgnoreCase(actual.getNombre()) && !part.getVisitante().getNombre().equalsIgnoreCase(EquipoLocal.toString())) {
					modelLocal.addElement(i+": "+actual.getNombre());
					if(EquipoLocal.equalsIgnoreCase(actual.getNombre())) {
						ListLocal.setSelectedIndex(i-1);
					}
					i++;
				}
			}
		}
	}
	private void loadVisitante() {
		int i = 1;
		modelVisitante.removeAllElements();
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			for (Partido part: actual.getPartidos()) {
				if(!EquipoLocal.equalsIgnoreCase(actual.getNombre()) && !part.getLocal().getNombre().equalsIgnoreCase(EquipoVisitante.toString())) {
					modelVisitante.addElement(i+": "+actual.getNombre());
					if(EquipoVisitante.equalsIgnoreCase(actual.getNombre())) {
						ListVisitante.setSelectedIndex(i-1);
					}
					i++;
				}
				
			}
		}
	}
	private void limpiar() {
		dateChooser.setDate(new Date());
		EquipoLocal = "";
		EquipoVisitante = "";
		loadLocal();
		loadVisitante();
		btnCrear.setEnabled(false);
	}
}
