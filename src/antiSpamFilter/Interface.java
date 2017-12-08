package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Analise_de_Emails.Emails_Processing;
import txtreader.Rule;
import txtreader.Writer;
import txtreader.Leitor;

public class Interface{
	
	private JFrame frame;
	private Leitor leitor;
	private TableModel ruleModel;
	private JTable ruleTable;
	private Box container;
	//cenas para controlo de configura��es
	private boolean rulesStatus;
	private boolean hamStatus;
	private boolean spamStatus;

	private Writer fileWriter;
	private JPanel centerPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private ArrayList<Rule> ruleList;
	
	public Interface(){
		//Adicionado por sergio
		//criar frame
		frame = new JFrame();
		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLayout(new BorderLayout());
		//----------------------------
		//inicializar e adicionar paineis
		//center
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		//centerLeft
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		//centerright
		rightPanel = new JPanel();
		//--------------------------
		container = new Box(2);
		rulesStatus=false;
		hamStatus=false;
		spamStatus=false;
		leitor = new Leitor();

		text1= new JTextField(25);
		text2= new JTextField(25);
		text3= new JTextField(25);
		ruleList = new ArrayList<Rule>();
		addFrameContent();
	}
	
	public void open(){
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	public Object[][] tableUpdater(){
		leitor.ler_Regras(text1.getText());
		ruleList = leitor.get_Regras();
		Object[][] data = new Object[500][2];
		for(int i=0;i<ruleList.size();i++){
			data[i][0] = ruleList.get(i).getName();
			data[i][1] = ruleList.get(i).getPeso();

		}
		return data;
	}
	public void addFrameContent (){

		Border border = BorderFactory.createLineBorder(Color.black, 1);
		container.setAlignmentX(Box.LEFT_ALIGNMENT);
		//lista de regras
		ArrayList<Rule> ruleList = leitor.get_Regras();
		String[] columnNames = {"Rule", "Weight"};
		
		ruleModel = new DefaultTableModel(tableUpdater(), columnNames);
		ruleTable = new JTable(ruleModel);
		ruleTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		ruleModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				
				System.out.println("uiii");
				//possivelmente utilizar o event e
				for(int i=0; i<ruleModel.getRowCount(); i++){
					Rule tmpRule = new Rule(String.valueOf(ruleModel.getValueAt(i, 0)), Double.valueOf(String.valueOf(ruleModel.getValueAt(i, 1))));
					ruleList.set(i, tmpRule);
				}
				leitor.setRegras(ruleList);
				leitor.write_Rules();
			}
		});
		JScrollPane ruleScroll = new JScrollPane(ruleTable);


//		fileWriter = new Writer(ruleList);
		
		JButton loadButton = new JButton("Load Rules");
		loadButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//adicionado if para que so seja feito o carregamento quando houver um path para este
				//caso contr�rio � feito o prompt de uma mensagem de erro 
				if(rulesStatus) {
					ruleModel = new DefaultTableModel(tableUpdater(), columnNames);
					ruleTable.setModel(ruleModel);
					ruleTable.revalidate();
					ruleScroll.repaint();
				}else {
					JOptionPane.showMessageDialog(frame, "nao ha um ficheiro regras selecionado");
				}
				
				
				
				//Duarte- guardar contudo da tabela num segundo ficheiro.
//				if(rulesStatus)
//					fileWriter.write();
//				else {
//					//Teste by sergio
//					JOptionPane.showMessageDialog(frame, "nao ha um ficheiro regras selecionado");
//				}
//				leitor.ler_Regras(text1.getText());



			}
		});
		
		JButton evaluateButton = new JButton(new Action() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Emails_Processing tmp = new Emails_Processing(text3.getText(),text2.getText(), text1.getText());
				tmp.avaliar();
				
			}		
			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}
			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
			}
		});
		JButton resetButton = new JButton(new Action() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leitor.resetFileRules();
				loadButton.doClick();
				
			}		
			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}
			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
			}
		});
		
		//painel Norte (aka file chooser)
		JFileChooser fc = new JFileChooser();
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.setBorder(border);
		frame.add(northPanel, BorderLayout.NORTH);

		JLabel label1= new JLabel("Rules");

		JPanel panelN1= new JPanel();
		panelN1.setBorder(border);
		northPanel.add(panelN1);
		panelN1.add(label1);
		panelN1.add(text1);
		

		//criar segundo painel de selec��o

		JLabel label2= new JLabel("Spam");

		JPanel panelN2= new JPanel();
		panelN2.setBorder(border);
		northPanel.add(panelN2);
		panelN2.add(label2);
		panelN2.add(text2);
		
		//criar terceiro painel de selec��o

		JLabel label3= new JLabel("Ham");

		JPanel panelN3= new JPanel();
		panelN3.setBorder(border);
		northPanel.add(panelN3);
		panelN3.add(label3);
		panelN3.add(text3);
		
		//criar os botoes para cada painel de selecionado
		JButton button1 = new JButton(new Action() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fc.showOpenDialog(text1);
				//verifica se path contem o nome do ficehiro que se quer
				if(fc.getSelectedFile().toString().toLowerCase().contains("rules.cf")) {
					text1.setText(fc.getSelectedFile().toString());
					rulesStatus=true;
				}else {
					JOptionPane.showMessageDialog(frame, "Path selecionado nao contem rules.cf");
				}
			}
			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton button2 = new JButton(new Action() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fc.showOpenDialog(text2);
				//verifica se path contem o nome do ficehiro que se quer
				if(fc.getSelectedFile().toString().toLowerCase().contains("spam.log")) {
					text2.setText(fc.getSelectedFile().toString());
					spamStatus=true;
				}else {
					JOptionPane.showMessageDialog(frame, "Path selecionado nao contem spam.log");
				}
				
			}	
			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}
			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
		});
		JButton button3 = new JButton(new Action() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fc.showOpenDialog(text3);
				//verifica se path contem o nome do ficehiro que se quer
				if(fc.getSelectedFile().toString().toLowerCase().contains("ham.log")) {
					text3.setText(fc.getSelectedFile().toString());
					hamStatus=true;
				}else {
					JOptionPane.showMessageDialog(frame, "Path selecionado nao contem ham.log");
				}
				
			}		
			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}
			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
			}
		});
		
		//Colocar texto nos botoes 
		button1.setText("Browse");
		button2.setText("Browse");
		button3.setText("Browse");
		evaluateButton.setText("EvaluateConfig");
		resetButton.setText("Reset Fields");
		
		//Adicionar os botoes aos paineis de selec��o
		panelN1.add(button1);
		panelN2.add(button2);
		panelN3.add(button3);
		
		//Criar painel esquerda (Lista de Regras)
		leftPanel.add(ruleScroll);
		leftPanel.setBorder(border);
		
		//Criar painel da direita
		rightPanel = new JPanel();
		rightPanel.setBorder(border);
		rightPanel.add(loadButton);
		rightPanel.add(evaluateButton);
		rightPanel.add(resetButton);
		
		//adicionar paineis esquerdo e direito ao painel central, e o central � frame
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		frame.add(centerPanel);
	}
}
