package Aray�z;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {

	private JPanel contentPane;
	private static long id;

	public long getId() {
		return id;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin(id);
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
	public Admin(long id2) {
		Admin.id=id2;
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
		
		JLabel userNameLabel = new JLabel("Ho\u015Fgeldiniz say\u0131n....");
		userNameLabel.setBounds(0, 367, 590, 23);
		contentPane.add(userNameLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 42, 590, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Kullan\u0131c\u0131");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Bilgileri G\u00F6r\u00FCnt\u00FCle/ D\u00FCzenle");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setVisible(false);
				ShowInfoAdmin showi = new ShowInfoAdmin(id);
				showi.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("\u00C7\u0131k\u0131\u015F");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AnaPencere ana = new AnaPencere();
				ana.main(null);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenu mnyeIlemleri = new JMenu("\u00DCye \u0130\u015Flemleri");
		menuBar.add(mnyeIlemleri);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u00DCye Ekle");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//setVisible(false);
				CreateUser cu = new CreateUser(3);
				cu.setVisible(true);
				
				
			}
		});
		mnyeIlemleri.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u00DCye D\u00FCzenle");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				  EditUser eu = new EditUser(id);
				  eu.setVisible(true);
				
			}
		});
		mnyeIlemleri.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u00DCye \u00C7\u0131kar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			  setVisible(false);
			  DeleteUser du = new DeleteUser(id);
			  du.setVisible(true);
				
			}
		});
		mnyeIlemleri.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu = new JMenu("Kitap \u0130\u015Flemleri");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Kitap Ekle");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CreateBook cb = new CreateBook(id);
				cb.setVisible(true);
				
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Kitap \u00C7\u0131kar");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				DeleteBook db = new DeleteBook(id);
				db.setVisible(true);
				
				
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
	}

}
