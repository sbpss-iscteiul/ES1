package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Interface {
	private JFrame frame;
	
	public void start (){
		JPanel panel;
		JFileChooser fc = new JFileChooser();
		frame = new JFrame();
		panel = new JPanel();
		JPanel panelN = new JPanel();
		frame.add(panel);
		panel.add(panelN);
		
		
		JTextArea text1= new JTextArea("path_________________________________");
		JTextArea text2= new JTextArea("path_________________________________");
		JTextArea text3= new JTextArea("path_________________________________");
		
		JLabel label1= new JLabel("label1:");
		JPanel panelN1= new JPanel();
		panelN.add(panelN1);
		panelN1.add(label1);
		panelN1.add(text1);
		
		JLabel label2= new JLabel("label2:");
		JPanel panelN2= new JPanel();
		panelN.add(panelN2);
		panelN2.add(label2);
		panelN2.add(text2);
		
		JLabel label3= new JLabel("label3:");
		JPanel panelN3= new JPanel();
		panelN.add(panelN3);
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
		
		JPanel panelS = new JPanel();
		panel.add(panelS);
		panelS.add(new JLabel("faieufna"));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		frame.setSize(800,400);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
