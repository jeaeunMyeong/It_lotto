package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lotto.dao.AdminDAO;
import lotto.dao.MemberDAO;
import lotto.dao.UserDAO;
import lotto.dto.UserDTO;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin_main extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txt_dropID;
	
	MemberDAO mdao = new MemberDAO();
	UserDAO dao = new UserDAO();
	AdminDAO adao = new AdminDAO();
	
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Admin_main frame = new Admin_main();
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
	public Admin_main(String id) {
		try{
			mdao.dbConnect();
		}catch(Exception e){
			e.printStackTrace();
		}
		initGUI(id);
		admin_showdata();
	}
	public void initGUI(String id){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 532);
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
		
		JLabel lbl_admin = new JLabel("전체 회원 목록");
		lbl_admin.setBounds(29, 20, 97, 15);
		contentPane.add(lbl_admin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 45, 525, 341);
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
		
		JLabel lblNewLabel_1 = new JLabel("회원탈퇴관리메뉴");
		lblNewLabel_1.setBounds(118, 416, 157, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("탈퇴할 ID");
		lblNewLabel_2.setBounds(118, 453, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		txt_dropID = new JTextField();
		txt_dropID.setBounds(206, 450, 116, 21);
		contentPane.add(txt_dropID);
		txt_dropID.setColumns(10);
		
		JButton btn_drop = new JButton("탈퇴처리");
		btn_drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String drop_id = txt_dropID.getText();
				if(drop_id.equals("")){
					System.out.println("탈퇴할 ID를 입력해주세요");
				}else{
					int n = adao.dropID(drop_id);
					if(n>0){
						JOptionPane.showMessageDialog(null, "탈퇴완료");
						
					}else{
						JOptionPane.showMessageDialog(null, "탈퇴실패: 존재하지 않는 ID입니다.");
						
					}
					
				}
				admin_showdata();
			}
		});
		btn_drop.setBounds(373, 449, 97, 23);
		contentPane.add(btn_drop);
		
		JLabel lbl_adminID = new JLabel("관리자<"+id+">님 안녕하세요.");
		lbl_adminID.setBounds(150, 20, 141, 15);
		contentPane.add(lbl_adminID);
		
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void admin_showdata(){
		UserDTO arr[]=null;
		arr = adao.selectAll();
		
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
	
		model.setDataVector(data, colName);
		table.setModel(model);	
	}
	
}
