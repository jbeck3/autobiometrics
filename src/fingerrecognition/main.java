package fingerrecognition;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import fingerrecognition.main;

public class main {
	//Java type usages
	private JFrame mainframe;
	private JPanel actionpanel;
	private JMenuBar menubar;
	private JMenuItem quit;
	private JTabbedPane oneonetabpane;
	private JTable table, temptable;
	private JTextField field;
	private ButtonGroup buttongroup = new ButtonGroup();
	private JButton button;

	boolean end = false;

	//gui
	public main(){
mainframe = new JFrame();		
		
		/*
		 * 
		 * 
		 * Action Panel
		 * 
		 * 
		 * */
		actionpanel = new JPanel();
		actionpanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		actionpanel.add(button = new JButton("	"));
		button.setActionCommand("	");
		buttongroup.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("button pressed");				
			}
		});


		/*
		 * 
		 * 
		 * Create Tabs
		 * 
		 * 
		 * */
		oneonetabpane = new JTabbedPane();
		oneonetabpane.addTab("Actions", actionpanel);		
		mainframe.add(oneonetabpane);



		/*
		 * 
		 * 
		 * frame details
		 * 
		 * 
		 * */
		mainframe.setLayout(new GridLayout(2,2));
		menubar = new JMenuBar();
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent e) { 
				{
					System.out.println("------------------");
					System.exit(0);
				} 
			}
		});
		menubar.add(quit);
		mainframe.setJMenuBar(menubar);
		mainframe.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainframe.setSize(1000,800);
		mainframe.setVisible(true);
		int i=1;
		
		while(end==false){
			percentDifferent(i++);
			
		}
		if(end==true){
			JLabel grant = new JLabel("ACCESS GRANTED");			
			grant.setForeground(Color.green);
			
			mainframe.add(grant);
			grant = new JLabel("Welcome Joseph:");
			mainframe.add(grant);
			grant = new JLabel("Preferred Temperature: 71 degrees");
			mainframe.add(grant);
			grant = new JLabel("Preferred seat(lower): 53");
			mainframe.add(grant);
			grant = new JLabel("Preferred seat(middle): 37");
			mainframe.add(grant);
			grant = new JLabel("Preferred seat(upper): 78");
			mainframe.add(grant);
	    	mainframe.setVisible(true);
		}
	}

	public static File lastFileModified(String dir) {
	    File fl = new File(dir);
	    File[] files = fl.listFiles();
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
	    return choice;
	}
	// Load an image from an embedded resource.
		private  BufferedImage loadImage(String path) {
			InputStream in = loadResource(path);
			try {
				return ImageIO.read(in);
			} catch (IOException e) {
				throw new IllegalStateException("Couldn't load image " + path, e);
			}
		}
		// Load an embedded resource as an input stream.
		public  InputStream loadResource(String path) {
			InputStream in = getClass().getClassLoader().getResourceAsStream(path);
			if (in == null) {
				throw new IllegalStateException("No such resource: " + path);
			}
			return in;
		}
	public  void percentDifferent(int iiii){
		 	BufferedImage img1 = null;
		    BufferedImage img2 = null;
		    try {
		      img1 = ImageIO.read(lastFileModified("C:\\Images"));
		      img2 =  loadImage("imgs/identify_2016-09-18_04-12-20_00.bmp");
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    int width1 = img1.getWidth(null);
		    int width2 = img2.getWidth(null);
		    int height1 = img1.getHeight(null);
		    int height2 = img2.getHeight(null);
		    if ((width1 != width2) || (height1 != height2)) {
		      System.err.println("Error: Images dimensions mismatch");
		      System.exit(1);
		    }
		    long diff = 0;
		    for (int y = 0; y < height1; y++) {
		      for (int x = 0; x < width1; x++) {
		        int rgb1 = img1.getRGB(x, y);
		        int rgb2 = img2.getRGB(x, y);
		        int r1 = (rgb1 >> 16) & 0xff;
		        int g1 = (rgb1 >>  8) & 0xff;
		        int b1 = (rgb1      ) & 0xff;
		        int r2 = (rgb2 >> 16) & 0xff;
		        int g2 = (rgb2 >>  8) & 0xff;
		        int b2 = (rgb2      ) & 0xff;
		        diff += Math.abs(r1 - r2);
		        diff += Math.abs(g1 - g2);
		        diff += Math.abs(b1 - b2);
		      }
		    }
		    double n = width1 * height1 * 3;
		    double p = diff / n / 255.0;
		    System.out.println("diff percent: " + (p * 100.0));
		    if(p>.35){
		    	System.out.println("ACCESS GRANTED");
		    	
		    	end=true;
		    }
	}

	public static void main(String[] args){
		System.out.println("------------------");
		
		new main();
	}
}
