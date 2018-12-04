package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import logico.Liga;
import logico.Partido;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Principal {
	private JFrame frmLigaDeBaloncesto;
	private JTable TableTiros1;
	private JTable TableTiros2;
	private ObjectOutputStream archivoSalida;
	private JLabel lblLocall1;
	private JLabel lblVisitante1;
	private JLabel lblfecha1;
	private JPanel PnlProximosPartidos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					Principal window = new Principal();
					window.frmLigaDeBaloncesto.setVisible(true);
					window.frmLigaDeBaloncesto.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() throws ClassNotFoundException {
		try {
			initialize();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws FileNotFoundException, ClassNotFoundException {
		frmLigaDeBaloncesto = new JFrame();
		frmLigaDeBaloncesto.addWindowListener(new WindowAdapter() {
			

			public void windowClosing(WindowEvent arg0) {
				File salida = new File("liga.dat");
				FileOutputStream guardar;
				try {
					guardar = new FileOutputStream(salida);
					archivoSalida = new ObjectOutputStream(guardar);
					archivoSalida.writeObject(Liga.getInstance());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		Dimension dim = frmLigaDeBaloncesto.getToolkit().getScreenSize();
		frmLigaDeBaloncesto.setTitle("Liga de baloncesto");
		frmLigaDeBaloncesto.setBounds(100, 100, (int)dim.getWidth(), (int)dim.getHeight());
		frmLigaDeBaloncesto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		File archivoEntrada = new File("liga.dat");
		if(archivoEntrada.exists()) {
			FileInputStream file = new FileInputStream(archivoEntrada);
			try {
				ObjectInputStream entrada = new ObjectInputStream(file);
				Liga.setInstance((Liga) entrada.readObject());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
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
					RegJugador nuevo;
					try {
						nuevo = new RegJugador(null);
						nuevo.setModal(true);
						nuevo.setVisible(true);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		mnJugadores.add(mntmRegistrarJugador);
		
		JMenuItem mntmListarJugadores = new JMenuItem("Listar Jugadores");
		mntmListarJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LisJugador nueva = new LisJugador();
				nueva.setModal(true);
				nueva.setVisible(true);
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
		mntmListarEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LisEquipo nueva = new LisEquipo();
				nueva.setModal(true);
				nueva.setVisible(true);
			}
		});
		mnEquipos.add(mntmListarEquipos);
		
		JMenuItem mntmPosiciones = new JMenuItem("Posiciones");
		mntmPosiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Posiciones posiciones = new Posiciones();
				posiciones.setModal(true);
				posiciones.setVisible(true);
			}
		});
		mnEquipos.add(mntmPosiciones);
		
		JMenu mnPartidos = new JMenu("Partidos");
		menuBar.add(mnPartidos);
		
		JMenuItem mntmCrearPartido = new JMenuItem("Crear Partido");
		mntmCrearPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearPartido nuevo = new CrearPartido();
				nuevo.setModal(true);
				nuevo.setVisible(true);
			}
		});
		mnPartidos.add(mntmCrearPartido);
		
		JMenuItem mntmVerCalendario = new JMenuItem("Ver Calendario");
		mntmVerCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calendario nuevo = new Calendario();
				nuevo.setModal(true);
				nuevo.setVisible(true);
			}
		});
		mnPartidos.add(mntmVerCalendario);
		
		JMenuItem mntmJugarPartido = new JMenuItem("Jugar Partido");
		mnPartidos.add(mntmJugarPartido);
		
		JPanel panel = new JPanel();
		frmLigaDeBaloncesto.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JScrollPane Tiros2 = new JScrollPane();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JScrollPane Tiros1 = new JScrollPane();
		tabbedPane.addTab("Tiros de 1", Tiros1);
		
		TableTiros1 = new JTable();
		TableTiros1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Posicion", "Nombre", "Equipo", "Tiros Anotados"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TableTiros1.getColumnModel().getColumn(0).setResizable(false);
		TableTiros1.getColumnModel().getColumn(0).setPreferredWidth(65);
		TableTiros1.getColumnModel().getColumn(0).setMinWidth(65);
		TableTiros1.getColumnModel().getColumn(0).setMaxWidth(50);
		TableTiros1.getColumnModel().getColumn(1).setResizable(false);
		TableTiros1.getColumnModel().getColumn(1).setPreferredWidth(90);
		TableTiros1.getColumnModel().getColumn(2).setResizable(false);
		TableTiros1.getColumnModel().getColumn(3).setResizable(false);
		TableTiros1.getColumnModel().getColumn(3).setPreferredWidth(84);
		TableTiros1.getColumnModel().getColumn(3).setMinWidth(18);
		Tiros1.setViewportView(TableTiros1);
		tabbedPane.addTab("Tiros de 2", Tiros2);
		
		TableTiros2 = new JTable();
		TableTiros2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Posicion", "Nombre", "Equipo", "Tiros Anotados"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TableTiros2.getColumnModel().getColumn(0).setPreferredWidth(71);
		TableTiros2.getColumnModel().getColumn(0).setMinWidth(71);
		TableTiros2.getColumnModel().getColumn(0).setMaxWidth(71);
		TableTiros2.getColumnModel().getColumn(3).setPreferredWidth(87);
		Tiros2.setViewportView(TableTiros2);
		tabbedPane.setBounds(1360, 49, 517, 594);
		panel.add(tabbedPane);
		JScrollPane TiroLibres = new JScrollPane();
		tabbedPane.addTab("Tiros Libres", null, TiroLibres, null);
		
		tableTirosLibres = new JTable();
		tableTirosLibres.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Posicion", "Nombre", "Equipo", "Tiros Anotados"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableTirosLibres.getColumnModel().getColumn(0).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(0).setPreferredWidth(71);
		tableTirosLibres.getColumnModel().getColumn(0).setMinWidth(71);
		tableTirosLibres.getColumnModel().getColumn(0).setMaxWidth(71);
		tableTirosLibres.getColumnModel().getColumn(1).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(1).setPreferredWidth(112);
		tableTirosLibres.getColumnModel().getColumn(2).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(3).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(3).setPreferredWidth(83);
		TiroLibres.setViewportView(tableTirosLibres);
		
		JLabel lblEstadisticas = new JLabel("Estadisticas");
		lblEstadisticas.setBounds(1567, 22, 82, 16);
		panel.add(lblEstadisticas);
		
		JLabel lblProximosPartidos = new JLabel("Proximos Partidos");
		lblProximosPartidos.setBackground(Color.BLACK);
		lblProximosPartidos.setForeground(Color.WHITE);
		lblProximosPartidos.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblProximosPartidos.setBounds(237, 53, 180, 16);
		panel.add(lblProximosPartidos);
		
		JButton btnVerMasEstadisticas = new JButton("Ver mas estadisticas");
		btnVerMasEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnVerMasEstadisticas.setBounds(1567, 674, 152, 23);
		panel.add(btnVerMasEstadisticas);
		
		PnlProximosPartidos = new JPanel();
		PnlProximosPartidos.setForeground(Color.WHITE);
		PnlProximosPartidos.setBackground(Color.BLACK);
		PnlProximosPartidos.setBorder(new LineBorder(new Color(0, 0, 0)));
		PnlProximosPartidos.setBounds(29, 88, 600, 460);
		panel.add(PnlProximosPartidos);
		PnlProximosPartidos.setLayout(null);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setForeground(Color.WHITE);
		lblLocal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLocal.setBounds(100, 42, 59, 17);
		PnlProximosPartidos.add(lblLocal);
		
		JLabel lblVisitante = new JLabel("Visitante");
		lblVisitante.setForeground(Color.WHITE);
		lblVisitante.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVisitante.setBounds(393, 38, 116, 25);
		PnlProximosPartidos.add(lblVisitante);
		
		
		JLabel lblfondo = new JLabel("");
		lblfondo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/principalFondo.jpg")));
		lblfondo.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		panel.add(lblfondo);
		
		loadPartidos();
	}
	public void loadEstadisticas() {
		
	}
	
	public void loadPartidos() {
		ArrayList<Partido>partidosNoJugados = new ArrayList<>();
		for (Partido partido : Liga.getInstance().getPartidos()) {
			if(!partido.isJugado()) {
				partidosNoJugados.add(partido);
			}
		}
		Collections.sort(partidosNoJugados);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JLabel[] lblLocal = new JLabel[4];
		JLabel[] lblVisitante = new JLabel[4];
		JLabel[] lblcosita = new JLabel[4];
		JLabel[] Visitante = new JLabel[4];
		JLabel[] Local = new JLabel[4];
		JLabel[] fecha = new JLabel[4];
		JPanel[] paneles = new JPanel[4];
		int x = 6,y = 70;
		int width = 588,height = 85;
		for(int i = 0;i <4;i++,y+=97) {
			if(i==partidosNoJugados.size())
				break;
			else {
				paneles[i] = new JPanel();
				paneles[i].setBackground(Color.white);
				paneles[i].setBorder(new LineBorder(new Color(0, 0, 0)));
				paneles[i].setBounds(6, y, 588, 85);
				PnlProximosPartidos.add(paneles[i]);
				paneles[i].setLayout(null);
				
				lblcosita[i] = new JLabel();
				lblcosita[i].setText("vs");
				lblcosita[i].setFont(new Font("Dialog", Font.BOLD, 40));
				lblcosita[i].setBounds(290, 20, 50, 50);
				paneles[i].add(lblcosita[i]);
				
				lblLocal[i] = new JLabel();
				lblLocal[i].setBounds(68, 2, 160, 80);
				ImageIcon local = new ImageIcon(Liga.getInstance().getPartidos().get(i).getLocal().getLogo().getImage().getScaledInstance(lblLocal[i].getWidth(), lblLocal[i].getHeight(), Image.SCALE_DEFAULT));
				lblLocal[i].setIcon(local);
				paneles[i].add(lblLocal[i]);
				
				lblVisitante[i] = new JLabel();
				lblVisitante[i].setBounds(380, 2, 150, 80);
				ImageIcon visitante = new ImageIcon(Liga.getInstance().getPartidos().get(i).getVisitante().getLogo().getImage().getScaledInstance(lblLocal[i].getWidth(), lblLocal[i].getHeight(), Image.SCALE_DEFAULT));
				lblVisitante[i].setIcon(visitante);
				paneles[i].add(lblVisitante[i]);
				
				fecha[i] = new JLabel();
				fecha[i].setBounds(280, 63, 82, 16);
				fecha[i].setText(dateFormat.format(Liga.getInstance().getPartidos().get(i).getFecha()));
				paneles[i].add(fecha[i]);
			
				
			}
			
		}
			
			
	}
	private JTable tableTirosLibres;
}
