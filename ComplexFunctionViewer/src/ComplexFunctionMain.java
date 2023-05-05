import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ComplexFunctionMain extends JFrame{
	public static void main(String[] args) throws Exception {
		ComplexFunctionMain window = new ComplexFunctionMain();
	    JPanel p = new JPanel();
	    p.add(new GraphicsPanel()); 
	    window.setTitle("Complex Function Visualizer");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setContentPane(p);
	    window.pack();
	    window.setLocationRelativeTo(null);
	    window.setVisible(true);
//		
//		JFrame frame = new JFrame("Test");
//		Container contentPane = frame.getContentPane();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		JButton closeButton = new JButton("Close");
//		contentPane.add(closeButton);
//		
//		frame.setLocationRelativeTo(null);
//		frame.setSize(200, 200);
//		frame.setVisible(true);
//	    
//		closeButton.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
//			}
//		});
//		
	}
}
