package ChattingApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;



public class Server implements ActionListener {
	JPanel text;
	JTextField text1;
	static Box vertical = Box.createVerticalBox();
	static JFrame j= new JFrame();
	static DataOutputStream dout;
	Server() {
		j.setLayout(null);
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(7, 94, 84));
		p1.setBounds(0, 0, 450, 70);
		p1.setLayout(null);
		j.add(p1);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("iconss/3.png"));
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel back = new JLabel(i3);
		back.setBounds(5, 20, 25, 25);
		p1.add(back);
		
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				super.mouseClicked(e);
			}
		});
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("iconss/salmankhan.jpg"));
		Image i5 = i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon i6 =new ImageIcon(i5);
		JLabel profile = new JLabel(i6);
		profile.setBounds(25, 10, 50, 50);
		p1.add(profile);
		
		ImageIcon i7 =new ImageIcon(ClassLoader.getSystemResource("iconss/video.png"));
		Image i8 = i7.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
		ImageIcon i9 =new ImageIcon(i8);
		JLabel VedioIcon = new JLabel(i9);
		VedioIcon.setBounds(330, 25, 30, 30);
		p1.add(VedioIcon);
		
		ImageIcon i10 =new ImageIcon(ClassLoader.getSystemResource("iconss/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
		ImageIcon i12 =new ImageIcon(i11);
		JLabel calling = new JLabel(i12);
		calling.setBounds(370, 30, 20, 20);
		p1.add(calling);
		
		ImageIcon i13 =new ImageIcon(ClassLoader.getSystemResource("iconss/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(10, 15,Image.SCALE_DEFAULT);
		ImageIcon i15 =new ImageIcon(i14);
		JLabel dots = new JLabel(i15);
		dots.setBounds(410, 30, 10, 15);
		p1.add(dots);
		
		JLabel name = new JLabel("Salman Khan");
		name.setBounds(80, 15, 100, 18);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("SAN_SERIF", Font.BOLD,15));
		p1.add(name);
		
		JLabel status = new JLabel("online...");
		status.setBounds(85, 35,100, 18);
		status.setForeground(Color.WHITE);
		status.setFont(new Font("SAN_SERIF", Font.BOLD, 12));
		p1.add(status);
		
		text = new JPanel();
		text.setBounds(5,75,440,570);
		j.add(text);
		
		text1 = new JTextField();
		text1.setBounds(5,655,310,40);
		text1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		j.add(text1);
		
		JButton send =new JButton("Send");
		send.setBounds(320, 655, 123, 40);
		send.setBackground(new Color(7,94,84));
		send.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
		send.setForeground(Color.white);
		send.addActionListener(this);
		j.add(send);
		
		j.setSize(450, 700);
		j.setLocation(200, 25);
		j.setUndecorated(true);
		j.getContentPane().setBackground(Color.WHITE);
		j.setVisible(true);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		String out =text1.getText();
		JPanel p1 = formatLabel(out);
		text.setLayout(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		right.add(p1,BorderLayout.LINE_END);
		vertical.add(right);
		vertical.add(Box.createVerticalStrut(15));
		text.add(vertical,BorderLayout.PAGE_START);
		dout.writeUTF(out);
		text1.setText(" ");
		j.repaint();
		j.validate();
		j.invalidate();
	}catch(Exception e1) {
		e1.printStackTrace();
	}
		}
	
	
	public static JPanel formatLabel(String out) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel output = new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>");
		output.setFont(new Font("Tahoma", Font.PLAIN, 16));
		output.setBackground(new Color(37, 211, 102));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50));
		panel.add(output);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		JLabel time =new JLabel();
		time.setText(sdf.format(cal.getTime()));
		panel.add(time);
				return panel;
	}

	public static void main(String[] args) {
		new Server();
		
		try {
			
			ServerSocket ser = new ServerSocket(6001);
			while(true) {
				Socket s =ser.accept();
				DataInputStream din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				
				while(true) {
				String msg = din.readUTF();
				JPanel panel = formatLabel(msg);
				JPanel left = new JPanel(new BorderLayout());
				left.add(panel,BorderLayout.LINE_START);
				vertical.add(left);
				j.validate();
				}
				}

			

			}catch(Exception e) {
				e.printStackTrace();
	}

	}}
