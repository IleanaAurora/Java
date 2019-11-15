import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JFrameTempConverter extends JFrame
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
        JFrameTempConverter frame = new JFrameTempConverter();
	}
	private JPanel panel1;
    private TextPanel panel2;
    private JTextField inField1;
    private JTextField inField2;
    private JTextField[] text = {inField1,inField2};
    
	public JFrameTempConverter()
	{
		Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension dim = tools.getScreenSize();
        setSize(dim.width/2,dim.height/2);
        setLocationByPlatform(true);

        setTitle("Temperature Converter");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Font font = new Font("Serif", Font.PLAIN, 30);

        panel1 = new JPanel();
        JLabel label1 = new JLabel("Temperature Converter", SwingConstants.CENTER);
        label1.setFont(font);
        panel1.add(label1);
        add(panel1, "North");
        
    	panel2 = new TextPanel();
        panel2.setLayout(new GridLayout(2,2,50,100));
        for(int i = 0; i < text.length; i++)
        {
        	JLabel label;
			if(i == 0)
			{label = new JLabel("Fahrenheit: ", SwingConstants.LEFT);}
        	else {label = new JLabel("Celsius: ", SwingConstants.LEFT);}
	        label.setFont(font);
	        panel2.add(label);
	        text[i] = new JTextField(10);
	        text[i].setFont(font);
	        ActionListener inListener = new TextListener();
			text[i].addActionListener(inListener);
	        panel2.add(text[i]);
		}
        add(panel2, "Center");
        setVisible(true);
	}
	
    private class TextListener implements ActionListener
    {
		  public void actionPerformed(ActionEvent event)
		  {
			  Object source = event.getSource();
              for(int i = 0; i < text.length; i++)
              {
                 if(source == text[i])
                 {
                	String input = text[i].getText();
                	text[i].setText("");
                	double numMessage = getTemp(input, i);
                	String message = Double.toString(numMessage);
      			  	if(i == 0)
      			  		text[1].setText(message);
      			  	else
      			  		text[0].setText(message);
                 }
              }
		  }

		private double getTemp(String input, int i)
		{
			// TODO Auto-generated method stub
			try
			{
				Double temp1 = Double.parseDouble(input);
				if(i == 0)
				{
					double temp2 = 5.0/9 * (temp1-32);
					return temp2;
				}
				else
				{
					double temp2 = (temp1 * (9.0/5)) + 32;
					return temp2;
				}
			}
			catch(NumberFormatException e)
			{System.out.println("Not a valid number: " + e);}
			catch(NullPointerException e)
			{System.out.println("Null pointer exception: " + e);}
			return 0;
		}
	}
}
class TextPanel extends JPanel
{
      private String message;

      public TextPanel()
      {message = "";}

      public TextPanel(String m)
      {message = "";}
      
      public void paintComponent(Graphics g)
      {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        int width  = getWidth();
        int height = getHeight();
        Font f1 = new Font("Serif", Font.BOLD,height/8);
        g2.setFont(f1);
        FontMetrics fm = g2.getFontMetrics(f1);
        int w1 = fm.stringWidth(message);
        g2.drawString(message,(width-w1)/2, height/2);
      }

      public void setMessage(String message)
      {
    	  this.message = message;
    	  repaint();
      }
}