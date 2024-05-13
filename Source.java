publicclass Source implements ActionListener {

	Container c;
	JLabel l1, img, op, op2;
	JButton b1, b2, b3;
	public Font f6 = new Font("Bell MT", Font.BOLD, 18);
	public Font f4 = new Font("Bell MT", Font.BOLD, 15);
	JTextArea ta;
	JScrollPane sp;
	JFrame jf;
	Border b, bb;

	String fname;
	String mac, mac2, mac3, mac4;
	File field;

	Source() {

		jf = new JFrame("Source::A Network Coding and DES Based Dynamic Encryption Scheme for Moving Target Defense");

		LineBorder border1 = new LineBorder(Color.red);

		c = jf.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.PINK);
		
		ImageIcon banner = new ImageIcon(this.getClass().getResource("Sender.png"));
		JLabel title=new JLabel();
		title.setIcon(banner);
		title.setBounds(0, -15, 850, 100);
		

		Border b11 = BorderFactory.createRaisedBevelBorder();
		Border b0 = BorderFactory.createCompoundBorder(border1, b11);

		op = new JLabel();
		op.setBorder(b0);

		Border bb = BorderFactory.createBevelBorder(0);
		op2 = new JLabel();
		op2.setBorder(bb);

		l1 = new JLabel("UPLOAD FILE");
		l1.setFont(f4);

		l1.setForeground(Color.BLACK);

		img = new JLabel();

		ta = new JTextArea();
		ta.setFont(f6);
		ta.setRows(20);
		ta.setColumns(20);

		sp = new JScrollPane();
		sp.setViewportView(ta);
		sp.setBounds(110, 175, 475, 350);

		b1 = new JButton("S E N D");
		b2 = new JButton("C A N C E L ");
		b3 = new JButton("BROWSE");
		b3.setFont(f4);
		b2.setFont(f4);
		b1.setFont(f4);
		b3.setForeground(Color.blue);
		b3.setBackground(Color.white);

		b1.setForeground(Color.blue);
		b1.setBackground(Color.white);
		b2.setForeground(Color.blue);
		b2.setBackground(Color.white);

		b1.setBounds(175, 575, 120, 40);
		b2.setBounds(375, 575, 120, 40);
		b3.setBounds(300, 120, 125, 35);
		l1.setBounds(100, 120, 150, 30);
		l1.setForeground(Color.blue);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		op.setBounds(40, 100, 600, 450);
		op.setBackground(Color.white);
		op2.setBounds(40, 560, 600, 70);
		op2.setBackground(Color.white);

		c.add(l1);
		c.add(b1);
		c.add(b2);
		c.add(b3);
		c.add(sp);
		c.add(img);
		c.add(op);
		c.add(op2);
		c.add(title);

		jf.setVisible(true);
		jf.setBounds(50, 25, 700, 700);

	}

	@SuppressWarnings("static-access")
	publicvoid actionPerformed(ActionEvent e) {

		if (e.getSource() == b2) {

			System.exit(0);

		}

		if (e.getSource() == b3) {

			JFileChooser jc = new JFileChooser();

			try {
				File f = new File(new File("filename.txt").getCanonicalPath());
				jc.setSelectedFile(f);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			int rt = jc.showOpenDialog(b3);

			if (rt == JFileChooser.APPROVE_OPTION) {

				field = jc.getSelectedFile();

				fname = field.getName();

			}

			File ff = jc.getSelectedFile();

			try {

				FileInputStream fis = new FileInputStream(ff);

				DataInputStream dis = new DataInputStream(fis);

				BufferedReader br = new BufferedReader(new InputStreamReader(
						dis));

				StringBuffer bf = new StringBuffer();

				String s;

				while ((s = br.readLine()) != null) {
					bf.append(s + "\n");
				}

				ta.setText(bf.toString());

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		if (e.getSource() == b1) {

			JOptionPane op = new JOptionPane();

			try {

				Socket socket = new Socket(op.showInputDialog(null,
						"Enter IPAddress"), 9000);

				DataOutputStream dos = new DataOutputStream(
						socket.getOutputStream());

				dos.writeUTF(fname);
				dos.writeUTF(new DES().encrypt(ta
						.getText()));

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	publicstaticvoid main(String[] args) {
		new Source();

	}
}

