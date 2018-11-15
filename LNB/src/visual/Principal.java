package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import logico.Liga;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {
	private static Liga LNB;
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				LNB =new Liga();
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 627, 359);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnJugadores = new JMenu("Jugadores");
		menuBar.add(mnJugadores);
		
		JMenuItem mntmRegistrarJugador = new JMenuItem("Registrar Jugador");
		mntmRegistrarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegJugador nuevo = new RegJugador(LNB);
				nuevo.setModal(true);
				nuevo.setVisible(true);
			}
		});
		mnJugadores.add(mntmRegistrarJugador);
		
		JMenu mnEquipos = new JMenu("Equipos");
		menuBar.add(mnEquipos);
		
		JMenuItem mntmRegistrarEquipo = new JMenuItem("Registrar Equipo");
		mntmRegistrarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEquipo nuevo = new RegEquipo(LNB, null);
				nuevo.setModal(true);
				nuevo.setVisible(true);
			}
		});
		mnEquipos.add(mntmRegistrarEquipo);
	}

}
