
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DbConnection;
import Model.Bashekim;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastatc;
	private JTextField fld_hastasifre;
	private JTextField fld_doctortc;
	private JTextField fld_doctorpassword;
	private DbConnection con=new DbConnection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hospital Automation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("logo.png")));
		lbl_logo.setBounds(215, 22, 145, 85);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(128, 117, 319, 22);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabbedpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbedpane.setBounds(10, 171, 566, 382);
		w_pane.add(w_tabbedpane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		w_tabbedpane.addTab("Hasta Girişi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaranız:    ");
		lblTcNumaranz.setHorizontalAlignment(SwingConstants.LEFT);
		lblTcNumaranz.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTcNumaranz.setBounds(44, 45, 345, 76);
		panel.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setHorizontalAlignment(SwingConstants.LEFT);
		lblifre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblifre.setBounds(44, 131, 345, 84);
		panel.add(lblifre);
		
		fld_hastatc = new JTextField();
		fld_hastatc.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		fld_hastatc.setBounds(167, 65, 137, 37);
		panel.add(fld_hastatc);
		fld_hastatc.setColumns(10);
		
		fld_hastasifre = new JTextField();
		fld_hastasifre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		fld_hastasifre.setBounds(167, 151, 137, 37);
		panel.add(fld_hastasifre);
		fld_hastasifre.setColumns(10);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btn_register.setBounds(63, 262, 126, 65);
		panel.add(btn_register);
		
		JButton btn_login = new JButton("Giriş Yap");
		btn_login.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btn_login.setBounds(293, 262, 121, 65);
		panel.add(btn_login);
		
		JPanel w_doctorlogin = new JPanel();
		w_doctorlogin.setBackground(new Color(255, 255, 255));
		w_tabbedpane.addTab("Doktor Girişi", null, w_doctorlogin, null);
		w_doctorlogin.setLayout(null);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaranız: ");
		lblTcNumaranz_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTcNumaranz_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTcNumaranz_1.setBounds(35, 40, 345, 76);
		w_doctorlogin.add(lblTcNumaranz_1);
		
		JLabel lblifre_1 = new JLabel("Şifre:");
		lblifre_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblifre_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblifre_1.setBounds(35, 143, 345, 84);
		w_doctorlogin.add(lblifre_1);
		
		JButton btn_doctorlogin = new JButton("Giriş Yap");
		btn_doctorlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(fld_doctortc.getText().length()==0 || fld_doctorpassword.getText().length()==0)
				{
					Helper.Helper.showMsg("fill");
				}
				
				else
				{
					Connection conn=con.connDb();
					try {
						Statement st=conn.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user");
						while(rs.next())
						{
							if(fld_doctortc.getText().equals(rs.getString("TC")) && fld_doctorpassword.getText().equals(rs.getString("Password")))
							{
								Bashekim bhekim=new Bashekim();
								bhekim.setId(rs.getInt("Id"));
								bhekim.setPassword(rs.getString("Password"));
								bhekim.setTC(rs.getString("TC"));
								bhekim.setName(rs.getString("Name"));
								bhekim.setTC(rs.getString("Type"));
								System.out.println(bhekim.getName());
							}
							
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorlogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btn_doctorlogin.setBounds(146, 254, 246, 65);
		w_doctorlogin.add(btn_doctorlogin);
		
		fld_doctortc = new JTextField();
		fld_doctortc.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		fld_doctortc.setColumns(10);
		fld_doctortc.setBounds(167, 59, 213, 37);
		w_doctorlogin.add(fld_doctortc);
		
		fld_doctorpassword = new JTextField();
		fld_doctorpassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		fld_doctorpassword.setColumns(10);
		fld_doctorpassword.setBounds(167, 165, 213, 37);
		w_doctorlogin.add(fld_doctorpassword);
	}
}
