package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpClient {

	private JFrame frame;
	private JTextField txtEmail;
	private JTextField txtName;
	
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
					SignUpClient window = new SignUpClient();
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
	public SignUpClient(BenefiterService benefiterService, ShelterService shelterService, PetService petService,BasketService basketService) {
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
		txtEmail.setText("email");
		txtEmail.setBounds(100, 60, 110, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(100, 110, 110, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String name = txtName.getText();
				
				benefiterService.saveBenefiter(email, name, (long)4);
				
				RegisterApp regApp = new RegisterApp((long)4, benefiterService, shelterService, petService, basketService);
			}
		});
		btnSignUp.setBounds(100, 170, 110, 20);
		frame.getContentPane().add(btnSignUp);
	}
}
