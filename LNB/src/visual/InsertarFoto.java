package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;

public class InsertarFoto extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	public InsertarFoto() {
		setTitle("Insertar foto");
		setBounds(100, 100, 656, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JFileChooser fileChooser = new JFileChooser("C:");
			contentPanel.add(fileChooser, BorderLayout.CENTER);
		}
	}

}
