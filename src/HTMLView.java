import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class HTMLView extends JFrame implements HTMLObserverModel{

	/**
	 * 
	 */
	private HTMLController m_Parsengs = new HTMLController();
	private HTMLModel m_Model;
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private	JTextArea txtrSsSS;
	private	JButton btnStart;

	
	/**
	 * Launch the application.
	 */
	
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HTMLView frame = new HTMLView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 

	/**
	 * Create the frame.
	 */
	HTMLView() {
		
		m_Model = m_Parsengs.getModel();
		m_Model.registerObserver(this);
					
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		setTitle("Parser HTML");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Global", null, layeredPane, null);
		
		JLayeredPane layeredPane1 = new JLayeredPane();
		tabbedPane.addTab("Logov", null, layeredPane1, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 419, 207);
		layeredPane1.add(scrollPane);
		
		txtrSsSS = new JTextArea();
		scrollPane.setViewportView(txtrSsSS);
		
		JLabel lblUrlAdress = new JLabel("Url adress");
		lblUrlAdress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUrlAdress.setBounds(10, 31, 90, 14);
		layeredPane.add(lblUrlAdress);
		
		textField = new JTextField();
		textField.setText("1");
		textField.setBounds(115, 28, 280, 20);
		layeredPane.add(textField);
		textField.setColumns(10);
	
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				try {
					m_Model.clearData();
					txtrSsSS.setText("");
					String url = textField.getText();
					m_Parsengs.get(url);				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});	
		btnStart.setBounds(306, 173, 89, 23);
		layeredPane.add(btnStart);
	}
	public void update() {
	txtrSsSS.append(m_Model.get_last_string());	
	}
}
