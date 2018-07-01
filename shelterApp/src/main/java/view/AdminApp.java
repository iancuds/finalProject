package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Basket;
import model.Benefiter;
import model.Pet;
import service.BasketService;
import service.BenefiterService;
import service.PetService;
import service.ShelterService;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminApp {

	private JFrame frame;
	private JTable table;
	private JTextField txtEmail;
	private JTextField txtName;
	private BenefiterService benefiterService;
	private PetService petService;
	private ShelterService shelterService;
	private BasketService basketService;
	

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminApp window = new AdminApp();
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
	public AdminApp(BenefiterService bs, PetService ps, ShelterService ss, BasketService bas) {
	
		this.benefiterService = bs;
		this.petService=ps;
		this.shelterService=ss;
		this.basketService=bas;
		
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Hello, Admin!");
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(180, 71, 368, 254);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnPets = new JButton("Pets");
		btnPets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populatePetTable();
			}
		});
		btnPets.setBounds(180, 21, 90, 30);
		frame.getContentPane().add(btnPets);
		
		JButton btnClients = new JButton("Clients");
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateClientsTable();
			}
		});
		btnClients.setBounds(300, 21, 90, 30);
		frame.getContentPane().add(btnClients);
		
		JButton btnEmployees = new JButton("Employees");
		btnEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateEmployeesTable();
			}
		});
		btnEmployees.setBounds(420, 21, 110, 30);
		frame.getContentPane().add(btnEmployees);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 136, 140, 214);
		frame.getContentPane().add(separator);
		
		txtEmail = new JTextField();
		txtEmail.setText("e-mail");
		txtEmail.setBounds(42, 160, 86, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(42, 212, 86, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String emailDoctor = txtEmail.getText();
				String nameDoctor = txtName.getText();
				
				Benefiter doctor = benefiterService.saveBenefiter(emailDoctor, nameDoctor, (long)2);
				
				System.out.println("saved doctor: " +doctor);
				
			}
		});
		btnAdd.setBounds(39, 266, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblAddDoctor = new JLabel("Add Doctor");
		lblAddDoctor.setBounds(10, 85, 131, 14);
		frame.getContentPane().add(lblAddDoctor);
		
		JButton btnAllPets = new JButton("All pets");
		btnAllPets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateTable();
				table.setVisible(true);
			}
		});
		btnAllPets.setBounds(42, 25, 89, 23);
		frame.getContentPane().add(btnAllPets);
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
	
	
	
	void populateTable()
	{
		DefaultTableModel m = new DefaultTableModel();
		
		String[] columnNames = {"NAME",
		        "AGE",
		        "INFO",
		        "HEALTH",
		   "ANIMAL"};
		m.setColumnIdentifiers(columnNames);
		
			List<Pet> pets = petService.findAll();
			
			for(Pet p:pets)
			{
			
		
			String[] o = new String[5];
			o[0] = p.getName();
			o[1] = p.getAge();
			o[2] = p.getInfo();
			o[3]= p.getHealth();
			o[4]=p.getAnimal();
			
			m.addRow(o);
			}
			
			table.setModel(m);
	}
	
	
	
	void populateClientsTable()
	{
		DefaultTableModel m = new DefaultTableModel();
		
		String[] columnNames = {"EMAIL",
		        "NAME"};
		m.setColumnIdentifiers(columnNames);
		
			List<Benefiter> benefiters = benefiterService.findAll();
			List<Basket>baskets = basketService.findAll();
			
			for(Benefiter b:benefiters)
			{
			
				
			if(b.getKind().equals((long)4)) {
		
			String[] o = new String[2];
			o[0] = b.getEmail();
			o[1] = b.getName();
		
			
			m.addRow(o);
			}
			}
			table.setModel(m);
	}
	

	void populateEmployeesTable()
	{
		DefaultTableModel m = new DefaultTableModel();
		
		String[] columnNames = {"EMAIL",
		        "NAME"};
		m.setColumnIdentifiers(columnNames);
		
			List<Benefiter> benefiters = benefiterService.findAll();
			List<Basket>baskets = basketService.findAll();
			
			for(Benefiter b:benefiters)
			{
			
				
			if(b.getKind().equals((long)3)) {
		
			String[] o = new String[2];
			o[0] = b.getEmail();
			o[1] = b.getName();
		
			
			m.addRow(o);
			}
			}
			table.setModel(m);
	}
	
}
