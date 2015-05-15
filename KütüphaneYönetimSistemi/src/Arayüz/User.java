package Arayüz;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.w3c.dom.NameList;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

import description.ControlGui;
import description.LibraryStore;
import description.OntologyConstants;

import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.AbstractListModel;

import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class User extends JFrame {

	/**
	 * 
	 */
	//private static final long serialVersionUID = -3716616696273284642L;
	private JPanel contentPane;
	private static long id;
     List<Resource> friendList = new ArrayList<Resource>();
     List<Resource> allFriendList = new ArrayList<Resource>();
     int index=0;
	public long getId() {
		return id;
	}

	private JTextField nameTextField;
	private JTextField familyNameTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param id2
	 */
	public User(long id2) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				AnaPencere ap = new AnaPencere();
				ap.main(null);
				
			}
		});
		User.id=id2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel HeaderLabel = new JLabel("New label");
		Image img = new ImageIcon(this.getClass().getResource("/Header.jpg"))
				.getImage();
		HeaderLabel.setIcon(new ImageIcon(img));
		HeaderLabel.setBounds(0, 0, 784, 42);
		contentPane.add(HeaderLabel);

		JLabel KullaniciAdiLabel = new JLabel("Ho\u015Fgeldiniz say\u0131n....");
		KullaniciAdiLabel.setBounds(0, 539, 590, 23);
		contentPane.add(KullaniciAdiLabel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 44, 590, 21);
		contentPane.add(menuBar);

		JMenu userItem = new JMenu("Kullan\u0131c\u0131");
		menuBar.add(userItem);

		JMenu mnNewMenu_1 = new JMenu("Kullan\u0131c\u0131 Bilgileri");
		userItem.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("G\u00F6r\u00FCnt\u00FCle/D\u00FCzenle");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				ShowInfo showi = new ShowInfo(id);
				showi.setVisible(true);
				
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Al\u0131nan Kitaplar");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReadBook rb = new ReadBook(id);
				rb.setVisible(true);
				
			}
		});
		userItem.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem = new JMenuItem("\u00C7\u0131k\u0131\u015F");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				AnaPencere ana = new AnaPencere();
				ana.main(null);
				
			}
		});
		userItem.add(mntmNewMenuItem);
		
				JMenu processItem = new JMenu("\u0130\u015Flemler");
				menuBar.add(processItem);
				
						JMenuItem mntmNewMenuItem_1 = new JMenuItem("Kitap Ara");
						mntmNewMenuItem_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {

								setVisible(false);
								FindBook kitapAra = new FindBook(id);
								kitapAra.setVisible(true);

							}
						});
						mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {

						});
						processItem.add(mntmNewMenuItem_1);
						
								JMenuItem mntmNewMenuItem_2 = new JMenuItem("Yazar Ara");
								mntmNewMenuItem_2.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										setVisible(false);
										FindBook kitapAra = new FindBook(3);
										kitapAra.setVisible(true);

									}
								});
								processItem.add(mntmNewMenuItem_2);
								
										JMenuItem mntmNewMenuItem_3 = new JMenuItem("Yay\u0131nevi Ara");
										mntmNewMenuItem_3.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {

												setVisible(false);
												FindBook kitapAra = new FindBook(3);
												kitapAra.setVisible(true);

											}
										});
										processItem.add(mntmNewMenuItem_3);

		nameTextField = new JTextField();
		nameTextField.setBounds(629, 103, 145, 23);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		familyNameTextField = new JTextField();
		familyNameTextField.setBounds(629, 137, 145, 23);
		contentPane.add(familyNameTextField);
		familyNameTextField.setColumns(10);
		// friend ekleme için
		
		JList namelist = new JList();
		namelist.addMouseListener(new MouseAdapter() {
			
			
			
			public void mouseClicked(MouseEvent arg0) { //Seçilen elemanýn indeksini bulur.
		
				index =namelist.locationToIndex(arg0.getPoint());
				
			}
		});
		namelist.setBounds(539, 221, 235, 77);
		contentPane.add(namelist);
		
		
		
		
		//Resource friend = null;

		JButton searchButton = new JButton("Ara"); 
		searchButton.addActionListener(new ActionListener() {  // Adý ve Soyadý textfield lardan alýr eve aramak çin ilgili methota yollar.
			public void actionPerformed(ActionEvent arg0) {

				String name = nameTextField.getText();
				String surname = familyNameTextField.getText();
				Vector<Resource> users = new ControlGui().queryForFriends(name,
						surname);
				
				
			//	 list = new JList<Resource>(users);
				Resource userRsc=null;
				for (int i = 0; i < users.size(); i++) {
					 userRsc = users.get(i);
					// friend eklemek için düzeltilecek
					
					
					
					friendList.add(userRsc);
					System.out.println(userRsc
							.getProperty(
									ResourceFactory
											.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
													+ "tc")).getObject()
							.asLiteral().getLong());
					
					
//					friendList.get(0).getProperty(
//							ResourceFactory
//							.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
//									+ "tc")).getObject()
//			.asLiteral().getLong();
	
											
					// list.add(new Checkbox(
					// userRsc.getPropertyResourceValue(FOAF.name)
					// + " "
					// + userRsc
					// .getPropertyResourceValue(FOAF.family_name),
					// false));
					
					
				}
				
			
				
				namelist.setModel(new ControlGui().ListeDoldur(friendList));
		
				
			}
			
		});
  
		searchButton.setBounds(685, 171, 89, 23);
		contentPane.add(searchButton);

		JButton addButton = new JButton("Ekle");
		addButton.addActionListener(new ActionListener() {
		//TODO: kiþi ekle fonksiyonu yok	
			public void actionPerformed(ActionEvent arg0) { // Seçilen kiþi arkadaþ olarak eklenir
				
				
			//TODO fonksiyon haline getir	
				
				LibraryStore
				.getInstance()
						.addStatement(
								ResourceFactory
										.createResource(OntologyConstants.RESOURCE_BASE_URI
												+ User.id), FOAF.knows,
								friendList.get(index));
				
				JOptionPane.showMessageDialog(null,
						"Arkadaþýnýz takibe alýnmýþtýr.");
				
				nameTextField.setText("");
				familyNameTextField.setText("");
				//TODO: listeyi sil
		        				
			}
			
			
			
			
		});
		addButton.setBounds(685, 339, 89, 23);
		contentPane.add(addButton);
		
		JLabel lblKullancAd = new JLabel("Ad\u0131:");
		lblKullancAd.setBounds(571, 107, 36, 14);
		contentPane.add(lblKullancAd);
		
		JLabel lblSoyad = new JLabel("Soyad\u0131:");
		lblSoyad.setBounds(573, 141, 46, 14);
		contentPane.add(lblSoyad);
		
		JList list = new JList();
		list.setBounds(10, 193, 152, 217);
		contentPane.add(list);
		
		JLabel lblArkadaListesi = new JLabel("Arkada\u015F Listesi");
		lblArkadaListesi.setBounds(10, 175, 152, 14);
		contentPane.add(lblArkadaListesi);
		
		JButton btnNewButton = new JButton("");
		
		
		btnNewButton.setBounds(126, 424, 25, 23);
		Image imgbutton = new ImageIcon(this.getClass().getResource("/refresh.jpg"))
		.getImage();
		btnNewButton.setIcon(new ImageIcon(imgbutton));
		
		contentPane.add(btnNewButton);
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Vector<Resource> allFriends = new ControlGui().queryForAllFriends(id);
				
				
				
					Resource friendsRsc=null;
					for (int i = 0; i < allFriends.size(); i++) {
						friendsRsc = allFriends.get(i);
						// friend eklemek için düzeltilecek
						allFriendList.add(friendsRsc);
						
					}
						
						
						namelist.setModel(new ControlGui().allFriendsListFill(allFriendList));

			}
		});
		
		
		/*setVisible(false);
		ShowInf admn = new ShowInf(id);
		admn.setVisible(true);*/
		
		
		/*btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Vector<Resource> allFriends = new ControlGui().queryForAllFriends(id);
				
				
			//	 list = new JList<Resource>(users);
				Resource friendsRsc=null;
				for (int i = 0; i < allFriends.size(); i++) {
					friendsRsc = allFriends.get(i);
					// friend eklemek için düzeltilecek
					
					
					
					friendList.add(friendsRsc);
				
				
			}
		});*/
		
	
		

		
	}
}
