import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JFrameLoan extends JFrame
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		JFrameLoan frame = new JFrameLoan();
	}
	private JPanel panel1;
    private TextPanel panel2;
    private JTextField amount, years, interest, rate;
    private JTextField[] text = {amount, years, interest, rate};
    private String[] labels = {"Amount Borrowed:", "Number of Years:", "Annual Interest Rate (%):", "Monthly Rate:"};
    private String[] values = new String[3];
    
    public JFrameLoan()
    {
    	Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension dim = tools.getScreenSize();
        setSize(dim.width*3/4,dim.height*3/4);
        setLocationByPlatform(true);

        setTitle("Loan");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Font font = new Font("Serif", Font.PLAIN, 30);
        
        panel1 = new JPanel();
        JLabel label1 = new JLabel("Loan", SwingConstants.CENTER);
        label1.setFont(font);
        panel1.add(label1);
        add(panel1, "North");
        
        panel2 = new TextPanel();
        panel2.setLayout(new GridLayout(4,2,50,100));
        for(int i = 0; i < text.length; i++)
        {
        	JLabel label = new JLabel(labels[i], SwingConstants.LEFT);
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
				  for(int i = 0; i < (text.length)-1; i++)
		          {
			            values[i] = text[i].getText();
		          }
		          boolean notvalid = false;
		          for(int i = 0; i < values.length; i++)
		        	{
		        	    if(values[i] == null)
		        	    	notvalid = true;
		        	}
		          if(!notvalid)
		          {
			          double numRate = getRate(values);
			          String rate = Double.toString(numRate);
		  			  text[3].setText(rate);
		          }
		          else
		        	  System.out.println("Missing information.");
			  }

			private double getRate(String[] values)
			{
				// TODO Auto-generated method stub
				try
				{
					double amount = Double.parseDouble(values[0]);
					double years = Double.parseDouble(values[1]);
					double interest = Double.parseDouble(values[2]);
					if(amount < 0 || years <= 0 || interest <= 0)
						System.out.println("Invalid information.");
					else
					{
						interest=(interest/100.0)/12;
						years*=12;
						double monthlypayment = amount*interest/(1-Math.pow((1.0 + interest), -years));
						return monthlypayment;
					}
				}
				catch(NumberFormatException e)
				{System.out.println("Not a valid number: " + e);}
				return 0;
			}
		}
}