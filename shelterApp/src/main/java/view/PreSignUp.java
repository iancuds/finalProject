package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.Pet;
import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PreSignUp {

	private JFrame frame;
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
					PreSignUp window = new PreSignUp();
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
	public PreSignUp(BenefiterService benefiterService, ShelterService shelterService, PetService petService,BasketService basketService) {
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
		
		JButton btnClient = new JButton("Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpClient suc = new SignUpClient(benefiterService, shelterService, petService, basketService);
				//RegisterApp regApp = new RegisterApp((long)4, benefiterService, shelterService, petService, basketService);
			}
		});
		btnClient.setBounds(145, 69, 89, 23);
		frame.getContentPane().add(btnClient);
		
		JButton btnWorker = new JButton("Worker");
		btnWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpWorker suw = new SignUpWorker(benefiterService, shelterService, petService, basketService);
				//RegisterApp regApp = new RegisterApp((long)3, benefiterService, shelterService, petService, basketService);
			}
		});
		btnWorker.setBounds(145, 123, 89, 23);
		frame.getContentPane().add(btnWorker);
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterApp regApp = new RegisterApp((long)2, benefiterService, shelterService, petService, basketService);
			}
		});
		btnDoctor.setBounds(145, 180, 89, 23);
		frame.getContentPane().add(btnDoctor);
	}

}
