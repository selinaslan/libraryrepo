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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JScrollPane;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

import description.ControlGui;
import description.OntologyConstants;

public class KitapAra extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static int id;
	 List<Resource> bookList = new ArrayList<Resource>();
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
		panel.setBounds(33, 75, 265, 274);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblKitapAdnaGre = new JLabel("Kitap ad\u0131na g\u00F6re arama:");
		lblKitapAdnaGre.setBounds(10, 11, 159, 14);
		panel.add(lblKitapAdnaGre);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 196, 20);
		panel.add(textField);
		textField.setColumns(10);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(322, 75, 258, 274);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 238, 172);
		panel_1.add(scrollPane);
		
		JList bookList2 = new JList();
		scrollPane.setViewportView(bookList2);
		
		JLabel lblYazarAdnaGre = new JLabel("Yazar ad\u0131na g\u00F6re arama:");
		lblYazarAdnaGre.setBounds(10, 67, 169, 14);
		panel.add(lblYazarAdnaGre);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 92, 196, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAra = new JButton("Ara");
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(!(textField.getText().equals(""))){
				if(textField_1.getText().equals("")){
				
					System.out.print("   $$$$$$$$$$$$$");
					String title = textField.getText();
					
					Vector<Resource> books =  new ControlGui().searchBookbyName(title);
					Resource bookRsc=null;
					for (int i = 0; i < books.size(); i++) {
						bookRsc = books.get(i);
						System.out.println(bookRsc
								.getProperty(
										ResourceFactory
												.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
														+ "title")).getObject()
								.asLiteral().getString());
						
						
						 bookList.add(bookRsc);
						 
						 }
					bookList2.setModel(new ControlGui().BookListFill(bookList));
				}else{} // kitap + yazar
					
					
				}else {// yazar
					}
				
				
				
				
				
				
			}
		});
		btnAra.setBounds(157, 118, 89, 23);
		panel.add(btnAra);
		
		JLabel lblYaynevineGreArama = new JLabel("Yay\u0131nevine g\u00F6re arama:");
		lblYaynevineGreArama.setBounds(7, 159, 199, 14);
		panel.add(lblYaynevineGreArama);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 178, 189, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAra_1 = new JButton("Ara");
		btnAra_1.setBounds(157, 209, 89, 23);
		panel.add(btnAra_1);
		
	
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.setBounds(159, 210, 89, 23);
		panel_1.add(btnEkle);
		

}
}
