package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import txtreader.Rule;
import txtreader.leitor;

public class Interface{
	
	private JFrame frame;
	private leitor leitor;
	private TableModel ruleModel;
	private JTable ruleTable;
	private Box container;
	
	public Interface(){
		container = new Box(2);
		leitor = new leitor();
		leitor.ler_Regras();
		addFrameContent();
	}
	
	public void open(){
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	
	public void addFrameContent (){
		Border border = BorderFactory.createLineBorder(Color.black, 1);
		container.setAlignmentX(Box.LEFT_ALIGNMENT);
		ArrayList<Rule> tmpRuleList = leitor.get_Regras();
		String[] columnNames = {"Rule", "Weight"};
		Object[][] data = new Object[tmpRuleList.size()][2];
		for(int i=0; i<tmpRuleList.size(); i++){
			data[i][0] = tmpRuleList.get(i).getName();
		}
		for(int i=0; i<tmpRuleList.size(); i++){
			data[i][1] = tmpRuleList.get(i).getPeso();
		}
		ruleModel = new DefaultTableModel(data, columnNames);
		ruleTable = new JTable(ruleModel);
		ruleTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane ruleScroll = new JScrollPane(ruleTable);
		
		JFileChooser fc = new JFileChooser();
		frame = new JFrame();
		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLayout(new BorderLayout());
//		panel = new JPanel();
//		JPanel panelN = new JPanel();
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.setBorder(border);
//		frame.add(panel);
//		panel.add(panelN);
		frame.add(northPanel, BorderLayout.NORTH);
		
		
		JTextArea text1= new JTextArea("path_________________________________");
		JTextArea text2= new JTextArea("path_________________________________");
		JTextArea text3= new JTextArea("path_________________________________");
		
		JLabel label1= new JLabel("label1:");
		JPanel panelN1= new JPanel();
		panelN1.setBorder(border);
//		panelN.add(panelN1);
		northPanel.add(panelN1);
		panelN1.add(label1);
		panelN1.add(text1);
		
		JLabel label2= new JLabel("label2:");
		JPanel panelN2= new JPanel();
		panelN2.setBorder(border);
//		panelN.add(panelN2);
		northPanel.add(panelN2);
		panelN2.add(label2);
		panelN2.add(text2);
		
		JLabel label3= new JLabel("label3:");
		JPanel panelN3= new JPanel();
		panelN3.setBorder(border);
//		panelN.add(panelN3);
		northPanel.add(panelN3);
		panelN3.add(label3);
		panelN3.add(text3);
		
		JButton button1 = new JButton(new Action() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fc.showOpenDialog(text1);
				text1.setText(fc.getSelectedFile().toString());
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
				text2.setText(fc.getSelectedFile().toString());
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
				text3.setText(fc.getSelectedFile().toString());
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
		panelN1.add(button1);
		panelN2.add(button2);
		panelN3.add(button3);
		
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(ruleScroll);
		leftPanel.setBorder(border);
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(border);
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		frame.add(centerPanel);
	}
}
