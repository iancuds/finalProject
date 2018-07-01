package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Benefiter;
import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

public class SignIn {

	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JButton btnSignIn;
	private JTextArea textArea;
	private BenefiterService benefiterService;
	private ShelterService shelterService;
	private PetService petService;
	private BasketService basketService;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn window = new SignIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	
	public SignIn(BenefiterService benefiterService, ShelterService shelterService, PetService petService,BasketService basketService) {
		super();
		this.benefiterService = benefiterService;
		this.shelterService = shelterService;
		this.petService = petService;
		this.basketService = basketService;
		initialize();
		frame.setVisible(true);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setText("");
		txtEmail.setBounds(100, 50, 86, 30);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("");
		pwdPassword.setBounds(100, 110, 92, 31);
		frame.getContentPane().add(pwdPassword);
		
		lblPassword = new JLabel("password");
		lblPassword.setBounds(99, 95, 46, 14);
		frame.getContentPane().add(lblPassword);
		
		lblEmail = new JLabel("e-mail");
		lblEmail.setBounds(100, 35, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		textArea = new JTextArea();
		textArea.setBounds(100, 196, 311, 54);
		frame.getContentPane().add(textArea);
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = txtEmail.getText();
				char[] passwd = pwdPassword.getPassword();
				String spass = String.valueOf(passwd);
				String encPass = encryptPassword(spass, false);
				String message = null;
				System.out.println(encPass);
				boolean login =  benefiterService.logIn(email,encPass,message);
				
				
				if(login ==  false) textArea.setText("Sign in failed!");
				else {
					System.out.println(message);
					Benefiter b = benefiterService.getBenefiterByEmail(email);
					Long kind = b.getKind();
					System.out.println("kind:" +kind);
					if(kind.equals((long)0)) {
						System.out.println("in admin");
						AdminApp adminwindow = new AdminApp(benefiterService, petService, shelterService, basketService);
					}
					if(kind.equals((long)2)) {
							System.out.println("in doc");
							DoctorApp docwindow = new DoctorApp(benefiterService, shelterService, petService, basketService);}
					if(kind.equals((long)3)) {
							System.out.println("in Worker");
							WorkerApp workwindow = new WorkerApp(b.getEmail(),benefiterService, shelterService, petService, basketService);}
					if(kind.equals((long)4)) {
							System.out.println("in admin");
							ClientApp clientwindow = new ClientApp(b.getEmail(),benefiterService, shelterService, petService, basketService);			
					}
					
					}
							
			
				}});
		btnSignIn.setBounds(97, 162, 89, 23);
		frame.getContentPane().add(btnSignIn);
					
	
				}
	
	   String encryptPassword(String p, boolean way)
	    {
	        if(!way)
	            return p;
	        else
	        {


	            String encpass = p;
	            try {
	                MessageDigest md = MessageDigest.getInstance("MD5");

	                md.update(p.getBytes());
	                //Get the hash's bytes
	                byte[] bytes = md.digest();
	                //This bytes[] has bytes in decimal format;
	                //Convert it to hexadecimal format
	                StringBuilder sb = new StringBuilder();
	                for(int i=0; i< bytes.length ;i++)
	                {
	                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	                }
	                //Get complete hashed password in hex format
	                encpass = sb.toString();
	            } catch (NoSuchAlgorithmException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }

	            return encpass;

	        }
	    }

}
