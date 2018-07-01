package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Basket;
import model.Benefiter;
import model.Pet;
import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ClientApp {

	private JFrame frame;
	private JTable table;
	private PetService petService;
	private BasketService basketService;
	private BenefiterService benefiterService;
	private ShelterService shelterService  ;
	private String token;
	private String email;
	private JTextField txtAddress;
	private JTextField txtPhoneNumber;
	private JButton btnChangePassword;
	private JScrollPane scrollPane_1;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					ClientApp window = new ClientApp( "ihzPk7V/)0o6XzEwyTi4E$LLW%%JV^FzlBS;4aA2`r9lll/N;gZp5.5$g|8lHw>Usex^0dc?P5y`v[B?ncQ@p]>%Gm?)pHqV)'w0sGPiA,}w3x^Ff{i?2bLC?Lw6e:$p",
"admin@as.com");
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
	public ClientApp(String email, BenefiterService benefiterService, ShelterService shelterService, PetService petService,BasketService basketService) {
		this.token=token;
		this.email = email;
		
		
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
		frame = new JFrame("Hello, "+ email+"!");
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 62, 441, 260);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		populatePetTable();
		
		JButton btnAdopt = new JButton("Adopt");
		btnAdopt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String petName = (String) table.getValueAt(table.getSelectedRow(), 0);
				Pet pet =petService.findPetByName(petName);
				
				//Benefiter benefiter = benefiterService.getBenefiterByEmail(email);
				
			    String address = txtAddress.getText();
			    String phone = txtPhoneNumber.getText();
			 
			    Basket basket = basketService.saveBasket(email, address, phone, petName);
			    //petService.delete(pet.getIdpet());
			    populatePetTable();
			    table.setVisible(true);
			    populateOrders();
			    table_1.setVisible(true);
				
				
			}
		});
		btnAdopt.setBounds(498, 127, 89, 23);
		frame.getContentPane().add(btnAdopt);
		
		JButton btnDonate = new JButton("Donate!");
		btnDonate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Donate donate = new Donate(benefiterService, shelterService, petService, basketService);
			}
		});
		btnDonate.setBounds(498, 177, 89, 23);
		frame.getContentPane().add(btnDonate);
		
		txtAddress = new JTextField();
		txtAddress.setText("address");
		txtAddress.setBounds(501, 60, 182, 20);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setText("phone number");
		txtPhoneNumber.setBounds(501, 91, 182, 20);
		frame.getContentPane().add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		
		btnChangePassword = new JButton("Change password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			
			}
		});
		btnChangePassword.setBounds(26, 390, 202, 23);
	//	frame.getContentPane().add(btnChangePassword);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(527, 352, 321, 134);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		populateOrders();
		
		JLabel lblOrders = new JLabel("Orders");
		lblOrders.setBounds(527, 327, 46, 14);
		frame.getContentPane().add(lblOrders);
	}
	
	
	
	void populateOrders()
	{
DefaultTableModel m = new DefaultTableModel();
		
		String[] columnNames = {"PET NAME",
		        "ADDRESS",
		        "PHONE"};
		m.setColumnIdentifiers(columnNames);
		
		
			List<Basket>baskets = basketService.findAll();
			
			
				
				for(Basket b:baskets)
				{
				
					if(b.getBenefiter().getEmail().equals(this.email))
				
					{
		
			String[] o = new String[3];
			o[0] = b.getPet().getName();
			o[1] = b.getAddress();
			o[2] = b.getPhone();
		
			
			m.addRow(o);
			}
			}
			table_1.setModel(m);
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
