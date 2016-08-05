package project;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lotto.dao.MemberDAO;
/*수정되어라 제발*/
public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel lbl_title;
	private JLabel lbl_id;
	private JLabel lbl_pwd;
	private JTextField txt_id;
	private JTextField txt_pwd;
	
	public JTextField getTxt_id() {
		return txt_id;
	}

	public void setTxt_id(JTextField txt_id) {
		this.txt_id = txt_id;
	}
	

	public JTextField getTxt_pwd() {
		return txt_pwd;
	}

	public void setTxt_pwd(JTextField txt_pwd) {
		this.txt_pwd = txt_pwd;
	}
	
	private JButton btn_login;
	private JButton btn_join;
	private JButton btn_close;
	private final Action action = new SwingAction();
	
	MemberDAO dao = new MemberDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		try{
			dao.dbConnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 256);
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
		
		lbl_title = new JLabel("IT_Lotto");
		lbl_title.setFont(new Font("굴림", Font.BOLD, 30));
		lbl_title.setBounds(76, 10, 165, 54);
		contentPane.add(lbl_title);
		
		lbl_id = new JLabel("ID");
		lbl_id.setBounds(43, 80, 67, 15);
		contentPane.add(lbl_id);
		
		lbl_pwd = new JLabel("Password");
		lbl_pwd.setBounds(43, 133, 78, 15);
		contentPane.add(lbl_pwd);
		
		txt_id = new JTextField();
		txt_id.setBounds(133, 80, 116, 21);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		txt_pwd = new JPasswordField();
		txt_pwd.setBounds(133, 130, 116, 21);
		contentPane.add(txt_pwd);
		txt_pwd.setColumns(10);
		
		btn_login = new JButton("로그인");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_login_actionPerformed(e);
			}
		});
		btn_login.setBounds(24, 172, 75, 23);
		contentPane.add(btn_login);
		
		btn_join = new JButton("가입");
		btn_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_join_actionPerformed(e);
			}
		});
		btn_join.setBounds(111, 172, 75, 23);
		contentPane.add(btn_join);
		
		btn_close = new JButton("닫기");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btn_close_actionPerformed(e);
			}
		});
		btn_close.setBounds(198, 172, 75, 23);
		contentPane.add(btn_close);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	protected void do_btn_close_actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	protected void do_btn_join_actionPerformed(ActionEvent e) {
		JoinGUI joinGUI = new JoinGUI();
	}
	protected void do_btn_login_actionPerformed(ActionEvent e) {
		String login_id = txt_id.getText();
		String pwd = txt_pwd.getText();
		
		if(login_id.equals("")){
			JOptionPane.showMessageDialog(null, "아이디를 입력하여 주세요");
		}else if(pwd.equals("")){
			JOptionPane.showMessageDialog(null, "비밀번호를 입력하여 주세요");
		}else{
			System.out.println("출력확인용");
			String id = dao.login(login_id, pwd);
			if(id != null && id.equals("nopass")==false && id.equals("noid")==false){
				System.out.println(id+"님 환영합니다");
				
				int k = dao.div_admin(id);
				if(k ==1){
					Admin_main am = new Admin_main(id);
				}else if(k==0){
					User_main um = new User_main(id);
				}else{
					System.out.println("로그인 오류입니다.");
				}
				
			}
			else if(id.equals("nopass")){
				txt_pwd.setText("");
				System.out.println("다시입력해주세요");
				JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
			}else if(id.equals("noid")){
				txt_id.setText("");
				txt_pwd.setText("");
				System.out.println("다시입력해주세요");
				JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다.");
			}
			
			
			
		}
		
			
	}
}
