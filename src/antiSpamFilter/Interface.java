package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Interface {
	private JFrame frame;
//	private JPanel panel;
	
	public void start (){
		JPanel panel;
		JFileChooser fc = new JFileChooser();
		frame = new JFrame();
		panel = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelN = new JPanel();
		frame.add(panel);
		panel.add(panelS);
		panel.add(panelN);
		
		
		JTextArea text1= new JTextArea("path_________________________________");
		JTextArea text2= new JTextArea("path_________________________________");
		JTextArea text3= new JTextArea("path_________________________________");
		panelN.add(text1);
		panelN.add(text2);
		panelN.add(text3);
		panelS.add(new JLabel("faieufna"));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(800,400);
		frame.setResizable(true);
	
		panelN.setLayout(new GridLayout(50,1));
		panelS.setLayout(new GridLayout(20,1));
//		panel.add(fc);
//		System.out.println(fc.getSelectedFile());
		//https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
//		frame.setLayout(new BorderLayout());
		
		JButton button = new JButton(new Action() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("oi");
				fc.showOpenDialog(null);
				System.out.println(fc.getSelectedFile());
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
		
		panelS.add(button);
		frame.setVisible(true);
	}
}
