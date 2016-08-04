package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import lotto.dao.MemberDAO;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lbl_joinId;
	private JLabel lbl_joinpwd;
	private JLabel lbl_admin;
	private JTextField txt_joinId;
	private JTextField txt_joinpwd;
	private JLabel lblNewLabel_1;
	private JRadioButton rdbtn_user;
	private JRadioButton rdbtn_admin;
	private JButton btn_submit;
	private JButton btn_exit;
	private JDialog dialog;

	
	MemberDAO dao = new MemberDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinGUI frame = new JoinGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JoinGUI() {
		try{
			dao.dbConnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		initGUI();
		
		
	}


	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 282, 288);
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
		
		lbl_joinId = new JLabel("ID");
		lbl_joinId.setBounds(30, 76, 57, 15);
		contentPane.add(lbl_joinId);
		
		lbl_joinpwd = new JLabel("password");
		lbl_joinpwd.setBounds(30, 115, 57, 15);
		contentPane.add(lbl_joinpwd);
		
		lbl_admin = new JLabel("admin");
		lbl_admin.setBounds(30, 158, 57, 15);
		contentPane.add(lbl_admin);
		
		txt_joinId = new JTextField();
		txt_joinId.setBounds(109, 73, 116, 21);
		contentPane.add(txt_joinId);
		txt_joinId.setColumns(10);
		
		txt_joinpwd = new JPasswordField();
		txt_joinpwd.setColumns(10);
		txt_joinpwd.setBounds(109, 112, 116, 21);
		contentPane.add(txt_joinpwd);
		
		lblNewLabel_1 = new JLabel("회원가입");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel_1.setBounds(63, 20, 136, 41);
		contentPane.add(lblNewLabel_1);
		
		rdbtn_user = new JRadioButton("User");
		rdbtn_user.setBounds(104, 154, 121, 23);
		contentPane.add(rdbtn_user);
		
		rdbtn_admin = new JRadioButton("Admin");
		rdbtn_admin.setBounds(104, 182, 121, 23);
		contentPane.add(rdbtn_admin);
		
		ButtonGroup bg = new ButtonGroup();//다중 선택 방지를 위하여 그룹을 지어줌
		bg.add(rdbtn_admin);
		bg.add(rdbtn_user);
				
		btn_submit = new JButton("완료");
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_submit_actionPerformed(e);
			}
		});
		btn_submit.setBounds(30, 211, 97, 23);
		contentPane.add(btn_submit);
		
		btn_exit = new JButton("종료");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_exit_actionPerformed(e);
			}
		});
		btn_exit.setBounds(139, 211, 97, 23);
		contentPane.add(btn_exit);
		
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	protected void do_btn_exit_actionPerformed(ActionEvent e) {
//		System.exit(0);
//		JOptionPane.showMessageDialog(null, "종료합니다");// 팝업창으로 알려주는 기능 
		setVisible(false);
	}
	protected void do_btn_submit_actionPerformed(ActionEvent e) {
		String id = txt_joinId.getText();
		String pwd = txt_joinpwd.getText();
		int admin = 0;
		if(rdbtn_admin.isSelected()){
			admin = 1;
		}else if(rdbtn_user.isSelected()){
			admin = 0;
		}
//		System.out.println(admin);  //라디오 버튼값 확인용
		int n = dao.insertMember(id, pwd, admin);
		if(n > 0){
//			System.out.println("가입성공");
			JOptionPane.showMessageDialog(null,"가입성공","축하합니다!!",JOptionPane.NO_OPTION); 
			setVisible(false);
		}
		else{ 
//			System.out.println("가입실패 : ID중복");
			JOptionPane.showMessageDialog(null,"가입실패 : ID중복","Message",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
