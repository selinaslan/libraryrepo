package Arayüz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;

public class KitapAra extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static int id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapAra frame = new KitapAra(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public KitapAra(int id2) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel HeaderLabel = new JLabel("New label");
		Image img= new ImageIcon(this.getClass().getResource("/Header.jpg")).getImage();
		HeaderLabel.setIcon(new ImageIcon(img));
		HeaderLabel.setBounds(0, 0, 590, 42);
		contentPane.add(HeaderLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(33, 75, 256, 232);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblKitapAdnaGre = new JLabel("Kitap ad\u0131na g\u00F6re arama:");
		lblKitapAdnaGre.setBounds(10, 11, 159, 14);
		panel.add(lblKitapAdnaGre);
		
		textField = new JTextField();
		textField.setBounds(10, 51, 196, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblYazarAdnaGre = new JLabel("Yazar ad\u0131na g\u00F6re arama:");
		lblYazarAdnaGre.setBounds(10, 102, 169, 14);
		panel.add(lblYazarAdnaGre);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 127, 196, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAra = new JButton("Ara");
		btnAra.setBounds(117, 177, 89, 23);
		panel.add(btnAra);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(331, 75, 232, 232);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblYaynevineGreArama = new JLabel("Yay\u0131nevine g\u00F6re arama:");
		lblYaynevineGreArama.setBounds(10, 11, 199, 14);
		panel_1.add(lblYaynevineGreArama);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 50, 189, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAra_1 = new JButton("Ara");
		btnAra_1.setBounds(110, 185, 89, 23);
		panel_1.add(btnAra_1);
		

}
}
