package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;

import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Basket;
import model.Pet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WorkerApp {

	private JFrame frame;
	
	private String email;
	private String token;
	
	private PetService petService;
	private BasketService basketService;
	private BenefiterService benefiterService;
	private ShelterService shelterService  ;
	private JTable table;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtHealth;
	private JTextField txtInfo;
	private JTextField txtAnimal;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerApp window = new WorkerApp();
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
	public WorkerApp(String email, BenefiterService benefiterService, ShelterService shelterService, PetService petService,BasketService basketService) {
		this.token=token;
		this.email = email;
		
		
		this.benefiterService = benefiterService;
		this.shelterService = shelterService;
		this.petService = petService;
		this.basketService = basketService;
		initialize();
		frame.setVisible(true) ;
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Hello, "+email+"!");
		frame.setBounds(100, 100, 532, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 381, 232);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		populatePetTable();
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(401, 50, 86, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setText("age");
		txtAge.setBounds(401, 87, 86, 20);
		frame.getContentPane().add(txtAge);
		txtAge.setColumns(10);
		
		txtHealth = new JTextField();
		txtHealth.setText("health");
		txtHealth.setBounds(401, 128, 86, 20);
		frame.getContentPane().add(txtHealth);
		txtHealth.setColumns(10);
		
		txtInfo = new JTextField();
		txtInfo.setText("info");
		txtInfo.setBounds(401, 168, 86, 20);
		frame.getContentPane().add(txtInfo);
		txtInfo.setColumns(10);
		
		txtAnimal = new JTextField();
		txtAnimal.setText("animal");
		txtAnimal.setBounds(401, 211, 86, 20);
		frame.getContentPane().add(txtAnimal);
		txtAnimal.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String petname = txtName.getText();
				String age = txtAge.getText();
				String info = txtInfo.getText();
				String animal = txtAnimal.getText();
				String health = txtHealth.getText();
				
				System.out.println("res: "+petname);
				System.out.println("res: "+age);
				System.out.println("res: "+info);
				System.out.println("res: "+animal);
						
						
				Pet pet = petService.savePet(petname, age, info, health, animal);
				System.out.println(pet);
				
				
				populatePetTable();
				table.setVisible(true);
				
			}
		});
		btnAdd.setBounds(398, 272, 89, 23);
		frame.getContentPane().add(btnAdd);
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
