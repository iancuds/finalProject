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

public class Donate {

	private JFrame frame;
	private JTextField txtAmount;
	private JTextField txtCreditCard;
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
					
					ShelterService ss = new ShelterService();
					Donate window = new Donate(ss);
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
	public Donate(BenefiterService benefiterService, ShelterService shelterService, PetService petService,BasketService basketService) {
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
		
		txtAmount = new JTextField();
		txtAmount.setText("amount");
		txtAmount.setBounds(35, 55, 86, 20);
		frame.getContentPane().add(txtAmount);
		txtAmount.setColumns(10);
		
		txtCreditCard = new JTextField();
		txtCreditCard.setText("credit card ");
		txtCreditCard.setBounds(35, 103, 86, 20);
		frame.getContentPane().add(txtCreditCard);
		txtCreditCard.setColumns(10);
		
		JButton btnDonate = new JButton("Donate");
		btnDonate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Float amount = (float) Float.parseFloat(txtAmount.getText());
				shelterService.updateCapital((long)1, amount);
			}
		});
		btnDonate.setBounds(32, 164, 89, 23);
		frame.getContentPane().add(btnDonate);
	}
}
