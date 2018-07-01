package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Benefiter;
import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

public class RegisterApp {

	private JFrame frame;
	private JTextField txtToken;
	private JTextField txtPassword;
	
	private BenefiterService benefiterService;
	private ShelterService shelterService;
	private PetService petService;
	private BasketService basketService;
	private Long kind;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterApp window = new RegisterApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public RegisterApp(Long kind,BenefiterService benefiterService, ShelterService shelterService, PetService petService,BasketService basketService) {
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
		
		txtToken = new JTextField();
		txtToken.setText("token");
		txtToken.setBounds(100, 140, 130, 20);
		frame.getContentPane().add(txtToken);
		txtToken.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		txtPassword.setBounds(100, 170, 130, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		String token = txtToken.getText();
		String passwd = txtPassword.getText();
		
		String encp=encryptPassword(passwd,false);
		
		
				
				Benefiter b = benefiterService.register(token, encp);
		
				System.out.println(b);
				
		
			}
		});
		btnRegister.setBounds(100, 200, 130, 20);
		frame.getContentPane().add(btnRegister);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 11, 414, 109);
		frame.getContentPane().add(textArea);
		
		
		
		textArea.setText("You need to verify your e-mail address.\nUse the token that you received to do this\nas well as to set a password!");
		textArea.setEditable(false);
	
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
