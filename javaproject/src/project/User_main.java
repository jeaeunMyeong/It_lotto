package project;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import lotto.dao.MemberDAO;
import lotto.dao.UserDAO;
import lotto.dto.UserDTO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class User_main extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable table;
	
	MemberDAO mdao = new MemberDAO();
	UserDAO dao = new UserDAO();
	
	DefaultTableModel model;
	private JTextField txt_times;
	private JLabel lblNewLabel_1;
	private JButton btn_check;
	
	String save_id="";//ID저장용 공간
	private JTextField txt_chkTimes;
	private JTextField txt_chk1;
	private JTextField txt_chk2;
	private JTextField txt_chk3;
	private JTextField txt_chk4;
	private JTextField txt_chk5;
	private JTextField txt_chk6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					User_main frame = new User_main();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
//	public User_main() {
//		
//	}
	public User_main(String id){
		try{
			mdao.dbConnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		initGUI(id);
		showData(id);
		save_id = id;
	}
	private void initGUI(String id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-8, 100, 524, 372);
		
		//화면 중앙 출력을 위한 코드-----
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
	    Dimension frameSize = this.getSize();  
	        
	    int xpos = (int)(screenSize.getWidth() - frameSize.getWidth()) / 2; 
	    int ypos = (int)(screenSize.getHeight() - frameSize.getHeight()) / 2; 
	        
	    this.setLocation(xpos, ypos); 
	    //--------------------------------
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel(id+"님 환영합니다.");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(24, 10, 184, 15);
		contentPane.add(lblNewLabel);
		
		setVisible(true);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 74, 467, 175);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\uD68C\uCC28", "\uBC88\uD6381", "\uBC88\uD6382", "\uBC88\uD6383", "\uBC88\uD6384", "\uBC88\uD6385", "\uBC88\uD6386", "\uB2F9\uCCA8\uC5EC\uBD80"
			}
		));
		String colNames[] ={
				"NO","ID,","\uC774\uB984", "\uC804\uD654\uBC88\uD638", "\uC8FC\uC18C"
		};
		model = new DefaultTableModel(colNames,0);
		scrollPane.setViewportView(table);
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btn_logout.setBounds(392, 6, 97, 23);
		contentPane.add(btn_logout);
		
		JButton btnNewButton = new JButton("당첨예상번호받기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg="";
				if(txt_times.getText().equals("")){
					JOptionPane.showMessageDialog(null, "회차를 입력해주세요");
					return;
				}
				int times = Integer.parseInt(txt_times.getText());
				int no = dao.count_no(id);
				int n = dao.add_game(id,times,no);
				if(n>0){
					msg="당첨번호전송완료^^";
				}else{
					msg="당첨번호전송실패!!!!";
				}
				JOptionPane.showMessageDialog(null, msg);
				showData(id);
			}
		});
		btnNewButton.setBounds(241, 39, 153, 23);
		contentPane.add(btnNewButton);
		
		txt_times = new JTextField();
		txt_times.setBounds(112, 39, 81, 21);
		contentPane.add(txt_times);
		txt_times.setColumns(10);
		
		lblNewLabel_1 = new JLabel("회차");
		lblNewLabel_1.setBounds(205, 42, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		btn_check = new JButton("당첨번호 저장 및 확인");
		btn_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_check_actionPerformed(e);
			}
		});
		btn_check.setBounds(167, 301, 166, 23);
		contentPane.add(btn_check);
		
		txt_chkTimes = new JTextField();
		txt_chkTimes.setBounds(92, 271, 57, 21);
		contentPane.add(txt_chkTimes);
		txt_chkTimes.setColumns(10);
		
		JLabel label = new JLabel("회차");
		label.setBounds(157, 274, 24, 15);
		contentPane.add(label);
		
		txt_chk1 = new JTextField();
		txt_chk1.setColumns(10);
		txt_chk1.setBounds(189, 271, 29, 21);
		contentPane.add(txt_chk1);
		
		txt_chk2 = new JTextField();
		txt_chk2.setColumns(10);
		txt_chk2.setBounds(230, 271, 29, 21);
		contentPane.add(txt_chk2);
		
		txt_chk3 = new JTextField();
		txt_chk3.setColumns(10);
		txt_chk3.setBounds(273, 271, 29, 21);
		contentPane.add(txt_chk3);
		
		txt_chk4 = new JTextField();
		txt_chk4.setColumns(10);
		txt_chk4.setBounds(314, 271, 29, 21);
		contentPane.add(txt_chk4);
		
		txt_chk5 = new JTextField();
		txt_chk5.setColumns(10);
		txt_chk5.setBounds(355, 271, 29, 21);
		contentPane.add(txt_chk5);
		
		txt_chk6 = new JTextField();
		txt_chk6.setColumns(10);
		txt_chk6.setBounds(396, 271, 29, 21);
		contentPane.add(txt_chk6);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JTextField getTxt_times() {
		return txt_times;
	}

	public void setTxt_times(JTextField txt_times) {
		this.txt_times = txt_times;
	}

	public void showData(String id){
		
	
		UserDTO arr[]=null;
			arr = dao.user_selectAll(id);
			
		if(arr==null||arr.length==0){
			JOptionPane.showMessageDialog(this, "현재 등록된 데이터가없음");
			return;
		}
		String colName[]={"ID","회차","번호1","번호2","번호3","번호4","번호5","번호6","당첨여부",};
		String [][] data = new String[arr.length][9];
		for(int i=0;i<data.length;i++){
			data[i][0] = arr[i].getId()+"";
			data[i][1] = arr[i].getTimes()+"";
			data[i][2] = arr[i].getNum1()+"";
			data[i][3] = arr[i].getNum2()+"";
			data[i][4] = arr[i].getNum3()+"";
			data[i][5] = arr[i].getNum4()+"";
			data[i][6] = arr[i].getNum5()+"";
			data[i][7] = arr[i].getNum6()+"";
			data[i][8] = arr[i].getResult()+"";
		}
//		System.out.println(colName[0]+" "+colName[1]+" "+colName[2]); //일단 값은 정상적 출력됨
//		System.out.println(data[0][0]+" "+data[0][1]+" "+data[0][2]); 
		model.setDataVector(data, colName);// 여기서 문제 -->해결 : model을 초기화 하지 않고 사용하여 오류발생
		table.setModel(model);									
	
	}
	
	
	protected void do_btn_check_actionPerformed(ActionEvent e) {
		
		
				
		if(txt_chkTimes.getText().equals("") || txt_chk1.getText().equals("")|| txt_chk2.getText().equals("")|| txt_chk3.getText().equals("")|| 
				txt_chk4.getText().equals("")|| txt_chk5.getText().equals("")|| txt_chk6.getText().equals("")){
			JOptionPane.showMessageDialog(this, "데이터를 완전하게 입력해주세요");
			return;
		}
		int times = Integer.parseInt(txt_chkTimes.getText());
		int num1 = Integer.parseInt(txt_chk1.getText());
		int num2 = Integer.parseInt(txt_chk2.getText());
		int num3 = Integer.parseInt(txt_chk3.getText());
		int num4 = Integer.parseInt(txt_chk4.getText());
		int num5 = Integer.parseInt(txt_chk5.getText());
		int num6 = Integer.parseInt(txt_chk6.getText());
		String id = save_id;
		
//		System.out.println(id);  //출력환인용이엿음
		
		dao.update_result(id,times,num1,num2,num3,num4,num5,num6);
		showData(id);
	
	}
	
}
