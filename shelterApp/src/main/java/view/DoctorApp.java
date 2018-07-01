package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import model.Basket;
import model.Pet;
import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoctorApp {

	private JFrame frame;
	private JTable table;
	private BenefiterService benefiterService;
	private ShelterService shelterService;
	private PetService petService;
	private BasketService basketService;
	private JTextArea txtrHealthInfo;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorApp window = new DoctorApp();
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
	public DoctorApp(BenefiterService benefiterService, ShelterService shelterService, PetService petService,BasketService basketService) {
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
		petService = new PetService();
		basketService = new BasketService();
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 29, 580, 173);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		populatePetTable();
		

		 txtrHealthInfo = new JTextArea();
		txtrHealthInfo.setText("health info");
		txtrHealthInfo.setBounds(643, 25, 308, 225);
		frame.getContentPane().add(txtrHealthInfo);
		
		JButton btnUpdateHealth = new JButton("Update health");
		btnUpdateHealth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newHealth = txtrHealthInfo.getText();
				
				String petName = (String) table.getValueAt(table.getSelectedRow(), 0);
				//Pet pet =petService.findPetByName(petName);
				
				petService.updateHealth(petName, newHealth);
				populatePetTable();
				
			}
		});
		btnUpdateHealth.setBounds(643, 277, 196, 23);
		frame.getContentPane().add(btnUpdateHealth);
		
		
		
	}
	
	void populatePetTable()
	{
		DefaultTableModel m = new DefaultTableModel();
		
		String[] columnNames = {"NAME",
		        "AGE",
		        "INFO",
		        "HEALTH",
		   "ANIMAL"};
		m.setColumnIdentifiers(columnNames);
		
		
			List<Pet> pets = petService.findAll();
			List<Basket>baskets = basketService.findAll();
			
			for(Pet p:pets)
			{
				int appears=0;
				
				for(Basket b:baskets)
				{
					if(p.getName().equals(b.getPet().getName()))
						appears = 1;
				}
				
			if(appears == 0) {
		
			String[] o = new String[5];
			o[0] = p.getName();
			o[1] = p.getAge();
			o[2] = p.getInfo();
			o[3]= p.getHealth();
			o[4]=p.getAnimal();
			
			m.addRow(o);
			}
			}
			table.setModel(m);
	}
}
