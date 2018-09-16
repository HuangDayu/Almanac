package test_3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
//import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class WanNianLiTest extends JFrame implements ActionListener, MouseListener {
	private Calendar cld = Calendar.getInstance();
	private String[] astr = { "����һ", "���ڶ�", "������", "������", "������", "������", "������" };
	private DefaultTableModel dtm = new DefaultTableModel(null, astr);
	private JTable table = new JTable(dtm); // װ���ڵı��
	private JScrollPane sp = new JScrollPane(table);

	private JButton bLastYear = new JButton("��һ��");
	private JButton bNextYear = new JButton("��һ��");
	private JButton bLastMonth = new JButton("����");
	private JButton bNextMonth = new JButton("����");

	private JTextField jtfYear = new JTextField(5);// jtfYear�����ʾ�������ı���
	private JTextField jtfMonth = new JTextField(2);// jtfMonth�·���ʾ�ı���

	private JPanel p1 = new JPanel(); // װ��������ڰ�ť��ģ��
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel(new BorderLayout());
	private JPanel p4 = new JPanel(new GridLayout(2, 1));
	private JPanel p5 = new JPanel(new BorderLayout());

	private JButton bAdd = new JButton("������־");
	private JButton bDel = new JButton("ɾ����־");

	private JTextArea jta = new JTextArea(); // jta--JTextArea
	private JScrollPane jsp = new JScrollPane(jta);

	private JLabel l = new JLabel("����ı����п�ֱ�Ӽ���Ҫ���ҵ����,����߲�ѯЧ��");
	private JLabel lt = new JLabel();
	private JLabel ld = new JLabel();
	private int lastTime;

	public WanNianLiTest() {
		super("������"); // �������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ڹرպ���
		this.getContentPane().setLayout(new BorderLayout(10, 0));

		jta.setLineWrap(true);
		table.setGridColor(Color.GRAY); // ����֮����������ǻ�ɫ��
		table.setColumnSelectionAllowed(true);
		table.setSelectionBackground(Color.BLACK);// ��ѡ��ĳһ��ʱ��һ�챳����ɫ
		table.setSelectionForeground(Color.GREEN);// ѡ����������������ɫ��
		table.setBackground(new Color(184, 207, 229));// ������ʾ�����ɫǳ��ɫ
		table.setFont(new Font("����", Font.BOLD, 24));// �������������ʽ
		table.setRowHeight(30);// ���ĸ߶�
		table.addMouseListener(this); // ��������

		jtfYear.addActionListener(this);// ��������ݵ��ı���
		// Ϊ������ť��Ӽ�������
		bAdd.addActionListener(this);
		bDel.addActionListener(this);
		bLastYear.addActionListener(this);
		bNextYear.addActionListener(this);
		bLastMonth.addActionListener(this);
		bNextMonth.addActionListener(this);
		// ����ť��ӵ�Jpanel��

		p1.add(bLastYear);
		p1.add(jtfYear);// ��������ı���
		p1.add(bNextYear);
		p1.add(bLastMonth);
		p1.add(jtfMonth);
		p1.add(bNextMonth);
		p2.add(bAdd);
		p2.add(bDel);

		p3.add(jsp, BorderLayout.CENTER);
		p3.add(p2, BorderLayout.SOUTH);
		p3.add(ld, BorderLayout.NORTH);
		p4.add(l);
		p4.add(lt);
		p5.add(p4, BorderLayout.SOUTH);
		p5.add(sp, BorderLayout.CENTER);
		p5.add(p1, BorderLayout.NORTH);

		this.getContentPane().add(p5, BorderLayout.CENTER);
		this.getContentPane().add(p3, BorderLayout.EAST);

		String[] strDate = DateFormat.getDateInstance().format(new Date()).split("-");// �������
		cld.set(Integer.parseInt(strDate[0]), Integer.parseInt(strDate[1]) - 1, 0);
		showCalendar(Integer.parseInt(strDate[0]), Integer.parseInt(strDate[1]), cld);
		jtfMonth.setEditable(false);// �����·ݵ��ı���Ϊ���ɱ༭
		jtfYear.setText(strDate[0]);
		jtfMonth.setText(strDate[1]);
		this.showTextArea(strDate[2]);
		ld.setFont(new Font("������", Font.BOLD, 24));
		new Timer(lt).start();
		this.setBounds(200, 200, 600, 320);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void showCalendar(int localYear, int localMonth, Calendar cld) {
		int Days = getDaysOfMonth(localYear, localMonth) + cld.get(Calendar.DAY_OF_WEEK) - 2;
		Object[] ai = new Object[7];
		lastTime = 0;
		for (int i = cld.get(Calendar.DAY_OF_WEEK) - 1; i <= Days; i++) {
			ai[i % 7] = String.valueOf(i - (cld.get(Calendar.DAY_OF_WEEK) - 2));
			if (i % 7 == 6) {
				dtm.addRow(ai);
				ai = new Object[7];
				lastTime++;
			}
		}
		dtm.addRow(ai);
	}

	public int getDaysOfMonth(int year, int Month) // ��ʾ��ѡ�·ݵ�����
	{
		if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
			return 31;
		}
		if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
			return 30;
		}
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)// ����
		{
			return 29;
		} else {
			return 28;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jtfYear || e.getSource() == bLastYear || e.getSource() == bNextYear
				|| e.getSource() == bLastMonth || e.getSource() == bNextMonth) {
			int m, y;
			try// ��������������ȷ���쳣����
			{
				if (jtfYear.getText().length() != 4) {
					throw new NumberFormatException();
				}
				y = Integer.parseInt(jtfYear.getText());
				m = Integer.parseInt(jtfMonth.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "������4λ0-9�����֣�", "�������", JOptionPane.ERROR_MESSAGE);
				return;
			}
			ld.setText("û��ѡ������");
			for (int i = 0; i < lastTime + 1; i++) {
				dtm.removeRow(0);
			}
			if (e.getSource() == bLastYear) {
				jtfYear.setText(String.valueOf(--y));
			}
			if (e.getSource() == bNextYear) {
				jtfYear.setText(String.valueOf(++y));
			}
			if (e.getSource() == bLastMonth) {
				if (m == 1) {
					jtfYear.setText(String.valueOf(--y));
					m = 12;
					jtfMonth.setText(String.valueOf(m));
				} else {
					jtfMonth.setText(String.valueOf(--m));
				}
			}
			if (e.getSource() == bNextMonth) {
				if (m == 12) {
					jtfYear.setText(String.valueOf(++y));
					m = 1;
					jtfMonth.setText(String.valueOf(m));
				} else {
					jtfMonth.setText(String.valueOf(++m));
				}
			}
			cld.set(y, m - 1, 0);
			showCalendar(y, m, cld);
		}
		if (e.getSource() == bAdd) {
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			if (!ld.getText().equals("û��ѡ������")) {
				try {
					File file = new File(ld.getText() + ".txt");
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
					bw.write(jta.getText());
					bw.close();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		if (e.getSource() == bDel) {
			int r = table.getSelectedRow();
			int c = table.getSelectedColumn();
			File filedel = new File(ld.getText() + ".txt");
			if (filedel.exists()) {
				if (filedel.delete()) {
					jta.setText("��־ɾ���ɹ�");
				} else {
					jta.setText("��־ɾ��ʧ��");
				}
			} else {
				jta.setText("û���ҵ���־�ļ�");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		jta.setText(null);
		int r = table.getSelectedRow();
		int c = table.getSelectedColumn();
		if (table.getValueAt(r, c) == null) {
			ld.setText("û��ѡ������");
		} else {
			this.showTextArea(table.getValueAt(r, c));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	private void showTextArea(Object selected) {// ����ѡ������ʾ�������ܲ���Ū��ũ����ʾ
		ld.setText(jtfYear.getText() + "��" + jtfMonth.getText() + "��" + selected + "��");
		File filein = new File(ld.getText() + ".txt");
		if (filein.exists()) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filein)));
				String strRead = br.readLine();
				jta.setText(null);
				while (strRead != null) {
					jta.append(strRead);
					strRead = br.readLine();
				}
				br.close();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		new WanNianLiTest();
	}
}

class Timer extends Thread// ��ʾϵͳʱ��
{
	private JLabel lt;
	private SimpleDateFormat fy = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
	private SimpleDateFormat fn = new SimpleDateFormat("yyyy.MM.dd G 'at' HH mm ss z");
	private boolean b = true;

	public Timer(JLabel lt) {
		this.lt = lt;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (b) {
					lt.setText(fy.format(new Date()));
				} else {
					lt.setText(fn.format(new Date()));
				}
				b = !b;
				this.sleep(500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
