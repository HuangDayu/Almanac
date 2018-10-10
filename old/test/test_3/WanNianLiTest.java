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
	private String[] astr = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
	private DefaultTableModel dtm = new DefaultTableModel(null, astr);
	private JTable table = new JTable(dtm); // 装日期的表格
	private JScrollPane sp = new JScrollPane(table);

	private JButton bLastYear = new JButton("上一年");
	private JButton bNextYear = new JButton("下一年");
	private JButton bLastMonth = new JButton("上月");
	private JButton bNextMonth = new JButton("下月");

	private JTextField jtfYear = new JTextField(5);// jtfYear年份显示和输入文本框
	private JTextField jtfMonth = new JTextField(2);// jtfMonth月份显示文本框

	private JPanel p1 = new JPanel(); // 装入控制日期按钮的模块
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel(new BorderLayout());
	private JPanel p4 = new JPanel(new GridLayout(2, 1));
	private JPanel p5 = new JPanel(new BorderLayout());

	private JButton bAdd = new JButton("保存日志");
	private JButton bDel = new JButton("删除日志");

	private JTextArea jta = new JTextArea(); // jta--JTextArea
	private JScrollPane jsp = new JScrollPane(jta);

	private JLabel l = new JLabel("年份文本框中可直接键入要查找的年份,以提高查询效率");
	private JLabel lt = new JLabel();
	private JLabel ld = new JLabel();
	private int lastTime;

	public WanNianLiTest() {
		super("万年历"); // 框架命名
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗口关闭函数
		this.getContentPane().setLayout(new BorderLayout(10, 0));

		jta.setLineWrap(true);
		table.setGridColor(Color.GRAY); // 星期之间的网格线是灰色的
		table.setColumnSelectionAllowed(true);
		table.setSelectionBackground(Color.BLACK);// 当选定某一天时这一天背景黑色
		table.setSelectionForeground(Color.GREEN);// 选定的日期字体是绿色的
		table.setBackground(new Color(184, 207, 229));// 日期显示表格颜色浅蓝色
		table.setFont(new Font("黑体", Font.BOLD, 24));// 日期数字字体格式
		table.setRowHeight(30);// 表格的高度
		table.addMouseListener(this); // 鼠标监听器

		jtfYear.addActionListener(this);// 可输入年份的文本框
		// 为各个按钮添加监听函数
		bAdd.addActionListener(this);
		bDel.addActionListener(this);
		bLastYear.addActionListener(this);
		bNextYear.addActionListener(this);
		bLastMonth.addActionListener(this);
		bNextMonth.addActionListener(this);
		// 将按钮添加到Jpanel上

		p1.add(bLastYear);
		p1.add(jtfYear);// 年份输入文本框
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

		String[] strDate = DateFormat.getDateInstance().format(new Date()).split("-");// 获得日期
		cld.set(Integer.parseInt(strDate[0]), Integer.parseInt(strDate[1]) - 1, 0);
		showCalendar(Integer.parseInt(strDate[0]), Integer.parseInt(strDate[1]), cld);
		jtfMonth.setEditable(false);// 设置月份的文本框为不可编辑
		jtfYear.setText(strDate[0]);
		jtfMonth.setText(strDate[1]);
		this.showTextArea(strDate[2]);
		ld.setFont(new Font("新宋体", Font.BOLD, 24));
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

	public int getDaysOfMonth(int year, int Month) // 显示所选月份的天数
	{
		if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
			return 31;
		}
		if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
			return 30;
		}
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)// 闰年
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
			try// 控制输入的年份正确，异常控制
			{
				if (jtfYear.getText().length() != 4) {
					throw new NumberFormatException();
				}
				y = Integer.parseInt(jtfYear.getText());
				m = Integer.parseInt(jtfMonth.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "请输入4位0-9的数字！", "年份有误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			ld.setText("没有选择日期");
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
			if (!ld.getText().equals("没有选择日期")) {
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
					jta.setText("日志删除成功");
				} else {
					jta.setText("日志删除失败");
				}
			} else {
				jta.setText("没有找到日志文件");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		jta.setText(null);
		int r = table.getSelectedRow();
		int c = table.getSelectedColumn();
		if (table.getValueAt(r, c) == null) {
			ld.setText("没有选择日期");
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

	private void showTextArea(Object selected) {// 将所选日期显示出来，能不能弄成农历显示
		ld.setText(jtfYear.getText() + "年" + jtfMonth.getText() + "月" + selected + "日");
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

class Timer extends Thread// 显示系统时间
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
