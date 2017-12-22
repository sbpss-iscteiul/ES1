package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.io.FileUtils;

import Analise_de_Emails.Emails_Processing;
import txtreader.Leitor;

public class Interface{
	
	private JFrame frame;
	private Leitor leitor;
	private TableModel ruleModel;
	private JTable ruleTable;
	private Box container;
	//cenas para controlo de configura��es
	private JPanel centerPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel leftButtonPanel;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private ArrayList<String> ruleList;
	private ArrayList<Double> weights;
	private ArrayList<Double> autoWeights;
	private JScrollPane ruleScroll;
	private String[] columnNames = {"Rule", "Weight"};

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
		//centerLeftRight
		leftButtonPanel = new JPanel();
		leftButtonPanel.setLayout(new GridLayout(3,1));
		//centerRight
		rightPanel = new JPanel();
		//--------------------------
		container = new Box(2);
		
		leitor = new Leitor();
		
		text1= new JTextField(25);
		text2= new JTextField(25);
		text3= new JTextField(25);
		ruleList = new ArrayList<String>();
		weights = new ArrayList<Double>();
		autoWeights = new ArrayList<Double>();
		addFrameContent();
	}
	
	public void open(){
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	
	public Object[][] tableUpdater(){
		leitor.read_Rules(text1.getText());
		ruleList = leitor.getRules();
		weights = leitor.getWeights();
		Object[][] data = new Object[ruleList.size()][2];
		for(int i=0;i<ruleList.size();i++){
			data[i][0] = ruleList.get(i);
			data[i][1] = weights.get(i);

		}
		return data;
	}
	public void refresh() {
		ruleModel = new DefaultTableModel(tableUpdater(), columnNames);
		ruleTable.setModel(ruleModel);
		ruleTable.revalidate();
		ruleScroll.repaint();
	}
	public void addFrameContent (){

		Border border = BorderFactory.createLineBorder(Color.black, 1);
		container.setAlignmentX(Box.LEFT_ALIGNMENT);		
		ruleModel = new DefaultTableModel(tableUpdater(), columnNames);
		ruleTable = new JTable(ruleModel);
		ruleTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		ruleScroll = new JScrollPane(ruleTable);

		
		Emails_Processing tmp = new Emails_Processing(leitor.getSpam(), leitor.getHam(), weights);
		JButton loadButton = new JButton("Load Rules");
		loadButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//adicionado if para que so seja feito o carregamento quando houver um path para este
				//caso contr�rio � feito o prompt de uma mensagem de erro 
					refresh();
			}
		});
		
		JButton manualEvaluateButton = new JButton("Evaluate Manually");
		JPanel manualSubPanel = new JPanel();
		manualSubPanel.setLayout(new GridLayout(1,2));
		JTextField manualFN = new JTextField("Falsos Negativos");
		manualFN.setEnabled(false);
		JTextField manualFP = new JTextField("Falsos Positivos");
		manualFP.setEnabled(false);
		manualSubPanel.add(manualFN);
		manualSubPanel.add(manualFP);
		JPanel manualButtonPanel = new JPanel();
		manualButtonPanel.setLayout(new GridLayout(2,1));
		manualButtonPanel.add(manualEvaluateButton);
		manualButtonPanel.add(manualSubPanel);
		manualEvaluateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!leitor.getRules().isEmpty()) {
					leitor.getSpam().clear();
					leitor.getHam().clear(); 
					leitor.read_Rules(text1.getText());
					leitor.read_Email(text2.getText());
					leitor.read_Email(text3.getText());
	//				manualTextArea.setText(tmp.avaliar());
					tmp.setWeights(weights);
					manualFN.setText(""+tmp.calcFN());
					manualFP.setText(""+tmp.calcFP());
				}
				
			}		
		});
		
		JPanel autoButtonPanel = new JPanel();
		JButton autoSaveButton = new JButton("Save Config");
		JButton autoEvaluateButton = new JButton("Evaluate Automatically");
		JPanel autoSubPanel = new JPanel();
		autoSubPanel.setLayout(new GridLayout(1,2));
		JTextField autoFN = new JTextField("Falsos Negativos");
		autoFN.setEnabled(false);
		JTextField autoFP = new JTextField("Falsos Positivos");
		autoFP.setEnabled(false);
		autoSubPanel.add(autoFN);
		autoSubPanel.add(autoFP);
		autoButtonPanel.setLayout(new FlowLayout());
		autoButtonPanel.add(autoEvaluateButton);
		autoButtonPanel.add(autoSaveButton);
		autoEvaluateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				ArrayList<Double> autoWeights = new ArrayList<Double>();
				String source1= text1.getText().replaceAll("rules.cf", "");
				try{
					Scanner scannerVar = new Scanner(new File(source1+"experimentBaseDirectory/AntiSpamStudy/data/NSGAII/AntiSpamFilterProblem/VAR0.tsv"));
					Scanner scannerFun = new Scanner(new File(source1+"experimentBaseDirectory/AntiSpamStudy/data/NSGAII/AntiSpamFilterProblem/FUN0.tsv"));
					ArrayList<Double> funLinesFP = new ArrayList<Double>();
					while(scannerFun.hasNextLine()){
						String[] tempFun = scannerFun.nextLine().split(" ");
						funLinesFP.add(Double.parseDouble(tempFun[0]));
					}
					scannerFun.close();
					int minFunValue = funLinesFP.indexOf(Collections.min(funLinesFP));
					int i=0;
					while(scannerVar.hasNextLine()){
						if(i==minFunValue){
							String[] tempVar = scannerVar.nextLine().split(" ");
							System.out.println("temVar    "+tempVar.length);
							for(int j=0; j<tempVar.length; j++){
								autoWeights.add(Double.parseDouble(tempVar[j]));
							}
							scannerVar.close();
							break;
						}
						i++;
					}
					
				}catch(FileNotFoundException e1){
					System.out.println("FICHEIRO TESTE NÃO ECNCONTRADO");
				}
					try {
						FileUtils.cleanDirectory(new File(source1+"experimentBaseDirectory/AntiSpamStudy/data/NSGAII/AntiSpamFilterProblem"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					while((new File(source1+"experimentBaseDirectory/AntiSpamStudy/data/NSGAII/AntiSpamFilterProblem").listFiles().length)<3) {
								try {
									AntiSpamFilterAutomaticConfiguration.main(source1);									
									System.out.println("imprimi");
									
								} catch (IOException e1) {
								} catch (org.uma.jmetal.util.JMetalException e1) {
								}
					}
					System.out.println("sucesso");

					tmp.setWeights(autoWeights);
					autoFN.setText(""+tmp.calcFN());
					autoFP.setText(""+tmp.calcFP());
					}
					});

		autoSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leitor.write_Rules(text1.getText(), ruleList, autoWeights);
				loadButton.doClick();
//				refresh();
			}
		});
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leitor.reset_Rules(text1.getText());
				refresh();
				loadButton.doClick();
			}
		});
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<ruleModel.getRowCount(); i++){
					if(ruleModel.getValueAt(i, 0) != null && ruleModel.getValueAt(i, 1) != null ) {
						ruleList.set(i, String.valueOf(ruleModel.getValueAt(i, 0)));
						weights.set(i, Double.valueOf(String.valueOf(ruleModel.getValueAt(i, 1))));
					}
				}
				leitor.setRules(ruleList);
				leitor.setWeights(weights);
				leitor.write_Rules(text1.getText());
				loadButton.doClick();
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
		JButton button1 = new JButton("Browse");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fc.showOpenDialog(text1);
				//verifica se path contem o nome do ficehiro que se quer
				if(fc.getSelectedFile().toString().toLowerCase().contains("rules.cf")) {
					text1.setText(fc.getSelectedFile().toString());
				}else {
					JOptionPane.showMessageDialog(frame, "Path selecionado nao contem rules.cf");
				}
			}
		});
		
		JButton button2 = new JButton("Browse");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fc.showOpenDialog(text2);
				//verifica se path contem o nome do ficehiro que se quer
				if(fc.getSelectedFile().toString().toLowerCase().contains("spam.log")) {
					text2.setText(fc.getSelectedFile().toString());
				}else {
					JOptionPane.showMessageDialog(frame, "Path selecionado nao contem spam.log");
				}	
			}
		});
		
		JButton button3 = new JButton("Browse");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fc.showOpenDialog(text3);
				//verifica se path contem o nome do ficehiro que se quer
				if(fc.getSelectedFile().toString().toLowerCase().contains("ham.log")) {
					text3.setText(fc.getSelectedFile().toString());
				}else {
					JOptionPane.showMessageDialog(frame, "Path selecionado nao contem ham.log");
				}	
			} 
		});
		
		//Adicionar os botoes aos paineis de selec��o
		panelN1.add(button1);
		panelN2.add(button2);
		panelN3.add(button3);
		
		//Criar painel esquerda (Lista de Regras)
		leftButtonPanel.setBorder(border);
		leftButtonPanel.add(loadButton);
		leftButtonPanel.add(resetButton);
		leftButtonPanel.add(saveButton);
		leftPanel.add(leftButtonPanel, BorderLayout.EAST);
		leftPanel.add(ruleScroll);
		leftPanel.setBorder(border); 
		
		//Criar painel da direita
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(5,1));
		rightPanel.setBorder(border);
		rightPanel.add(manualButtonPanel);
//		rightPanel.add(manualScroll);
		rightPanel.add(autoButtonPanel);
		rightPanel.add(autoSubPanel);
//		rightPanel.add(autoScroll);
		
		//adicionar paineis esquerdo e direito ao painel central, e o central � frame
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		frame.add(centerPanel);
	}
}
