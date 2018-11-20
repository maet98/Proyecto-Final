package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import logico.Liga;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTable;

public class Principal {
	private JFrame frmLigaDeBaloncesto;
	private JTable TableTiros1;
	private JTable TableTiros2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmLigaDeBaloncesto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		initialize();
	}

	private void initialize() {
		frmLigaDeBaloncesto = new JFrame();
		frmLigaDeBaloncesto.setTitle("Liga de baloncesto");
		frmLigaDeBaloncesto.setBounds(100, 100, 1015, 536);
		frmLigaDeBaloncesto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmLigaDeBaloncesto.setJMenuBar(menuBar);
		
		JMenu mnJugadores = new JMenu("Jugadores");
		menuBar.add(mnJugadores);
		
		JMenuItem mntmRegistrarJugador = new JMenuItem("Registrar Jugador");
		mntmRegistrarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Liga.getInstance().getEquipos().isEmpty()) {
					JOptionPane.showMessageDialog(frmLigaDeBaloncesto, "No existe ningun equipo, registre un equipo.","Error", JOptionPane.INFORMATION_MESSAGE);
				}else {
					RegJugador nuevo = new RegJugador();
					nuevo.setModal(true);
					nuevo.setVisible(true);
					
				}
			}
		});
		mnJugadores.add(mntmRegistrarJugador);
		
		JMenuItem mntmListarJugadores = new JMenuItem("Listar Jugadores");
		mntmListarJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnJugadores.add(mntmListarJugadores);
		
		JMenu mnEquipos = new JMenu("Equipos");
		menuBar.add(mnEquipos);
		
		JMenuItem mntmRegistrarEquipo = new JMenuItem("Registrar Equipo");
		mntmRegistrarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEquipo nuevo = new RegEquipo(null);
				nuevo.setModal(true);
				nuevo.setVisible(true);
			}
		});
		mnEquipos.add(mntmRegistrarEquipo);
		
		JMenuItem mntmListarEquipos = new JMenuItem("Listar Equipos");
		mnEquipos.add(mntmListarEquipos);
		
		JMenu mnCalendario = new JMenu("Calendario");
		menuBar.add(mnCalendario);
		
		JMenuItem mntmVerCalendario = new JMenuItem("Ver Calendario");
		mnCalendario.add(mntmVerCalendario);
		
		JPanel panel = new JPanel();
		frmLigaDeBaloncesto.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JScrollPane Tiros3 = new JScrollPane();
		JScrollPane Tiros2 = new JScrollPane();
		JScrollPane Tiros1 = new JScrollPane();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Tiros de 3", Tiros3);
		tabbedPane.addTab("Tiros de 2", Tiros2);
		
		TableTiros2 = new JTable();
		Tiros2.setViewportView(TableTiros2);
		tabbedPane.addTab("Tiros de 1", Tiros1);
		
		TableTiros1 = new JTable();
		Tiros1.setViewportView(TableTiros1);
		tabbedPane.setBounds(589, 30, 396, 420);
		panel.add(tabbedPane);
		
		JLabel lblEstadisticas = new JLabel("Estadisticas");
		lblEstadisticas.setBounds(740, 13, 82, 16);
		panel.add(lblEstadisticas);
		
		JLabel lblProximosPartidos = new JLabel("Proximos Partidos");
		lblProximosPartidos.setBounds(22, 13, 114, 16);
		panel.add(lblProximosPartidos);
	}
}
