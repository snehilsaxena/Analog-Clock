import java.awt.*;
import java.awt.event.*;
import java.util.Date;

class ClockR extends Canvas implements Runnable{

	Thread th = null;
	Date currentDate;
	int cx = 194, cy = 192, exs = 0, eys = 0, exm = 0, eym = 0, exh = 0,eyh = 0;
	int c = 0;
	static Frame f;
	static int i=0,j=0,k=0;

	public void start()
	{
	    if (th == null) {
	      th = new Thread(this);
	      th.start();
	    }
    }

    public void run()
    {
	     while (th != null)
	     {
	      	try
	      	{
	      	  Thread.sleep(1000);
	      	}
	      	catch (InterruptedException e)
	      	{}

	      	repaint();
	    }
	    th = null;
  	}

  	private void init(Graphics g)
  	{
		g.drawOval(4,4,380,360);
		g.setColor(Color.RED);
		g.fillOval(4,4,380,360);
		g.drawOval(10,10,370,350);
		g.setColor(Color.BLACK);
		g.fillOval(10,10,370,350);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,50));
		g.drawString("12",165,60);
		g.drawString("6",180,350);
		g.drawString("9",20,205);
		g.drawString("3",340,210);
		g.setFont(new Font("Arial",Font.BOLD,40));
		g.drawString("1",280,80);
		g.drawString("2",325,130);
		g.drawString("4",320,280);
		g.drawString("5",265,330);
		g.drawString("7",100,330);
		g.drawString("8",45,280);
		g.drawString("10",37,135);
		g.drawString("11",85,80);

		g.drawOval(192,190,5,5);
		g.fillOval(192,190,5,5);
	}

  	public void paint(Graphics g)
  	{
		/*f.setBackground(new Color(i,j,k));
			      	i+=10;
			      	j++;
	      	k++;*/

		int xh, yh, xm, ym, xs, ys, second, minute, hour;

		init(g);

		Date d = new Date();
		String s = d.toString();
		System.out.println(s);
		second = Integer.parseInt(s.substring(17,19));
		System.out.println(second);
		minute = Integer.parseInt(s.substring(14,16));
		System.out.println(minute);
		hour = Integer.parseInt(s.substring(11,13));
		System.out.println(hour);

		xs = (int) (Math.cos(second * 3.14f / 30 - 3.14f / 2) * 120 + cx);
		ys = (int) (Math.sin(second * 3.14f / 30 - 3.14f / 2) * 120 + cy);
		xm = (int) (Math.cos(minute * 3.14f / 30 - 3.14f / 2) * 100 + cx);
		ym = (int) (Math.sin(minute * 3.14f / 30 - 3.14f / 2) * 100 + cy);
		xh = (int) (Math.cos((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f / 2) * 80 + cx);
		yh = (int) (Math.sin((hour * 30 + minute / 2) * 3.14f / 180 - 3.14f / 2) * 80 + cy);

		g.setColor(Color.blue);
		g.drawLine(cx, cy, xs, ys);

		g.setColor(Color.red);
		g.drawLine(cx, cy , xm, ym);

		g.setColor(Color.green);
		g.drawLine(cx, cy, xh, yh);

		exs = xs;
		eys = ys;
		exm = xm;
		eym = ym;
		exh = xh;
		eyh = yh;
	}


	public static void main(String s[])
	{
			//.........Initializing Frame.............

			f = new Frame("$$ CLOCK");
			f.setSize(400,400);

			//Color c = new Color(118,73,190);
			f.setBackground(Color.cyan);

			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent windowEvent){
					System.exit(0);
				}
		    });

		    //.........Initializing Canvas.............

		   	ClockR c1 = new ClockR();
		   	f.add(c1);
		   	c1.start();

		   	f.setVisible(true);
			f.setResizable(false);
	}

}