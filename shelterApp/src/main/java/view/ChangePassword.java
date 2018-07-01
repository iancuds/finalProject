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

public class ChangePassword {

	private JFrame frame;
	private JTextField txtPassword;
	private JTextField textField;
	private JButton btnChange;
	
	private BenefiterService benefiterService;
	private ShelterService shelterService;
	private PetService petService;
	private BasketService basketService;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword window = new ChangePassword();
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
	public ChangePassword(BenefiterService benefiterService, ShelterService shelterService, PetService petService,BasketService basketService) {
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
		frame = new JFrame("Change Password");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtPassword = new JTextField();
		txtPassword.setText("password");
		txtPassword.setBounds(35, 70, 189, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		textField = new JTextField("new password");
		textField.setBounds(35, 100, 180, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChange.setBounds(35, 140, 110, 23);
		frame.getContentPane().add(btnChange);
	}

}
