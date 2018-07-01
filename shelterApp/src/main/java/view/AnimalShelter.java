package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnimalShelter {

	private JFrame frame;
	private BenefiterService benefiterService;

	private PetService petService;
	private ShelterService shelterService;
	private BasketService basketService;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimalShelter window = new AnimalShelter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	


	public AnimalShelter() {
		this.petService = new PetService();
		this.shelterService=new ShelterService();
		this.benefiterService = new BenefiterService();
		this.basketService = new BasketService();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Animal Shelter");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SignIn signinwindow = new SignIn(benefiterService, shelterService, petService, basketService);
			}
		});
		btnSignIn.setBounds(155, 80, 90, 25);
		frame.getContentPane().add(btnSignIn);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreSignUp psuwindow = new PreSignUp(benefiterService, shelterService, petService, basketService);
				
			}
		});
		btnSignUp.setBounds(155, 140, 90, 25);
		frame.getContentPane().add(btnSignUp);
	}
}
