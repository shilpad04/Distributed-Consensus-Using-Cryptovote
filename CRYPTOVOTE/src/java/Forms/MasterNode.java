package Forms;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logic.info;
import Logic.master_listener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class MasterNode extends JFrame {
	 public static String grp_id="";
	 public static JTextArea textArea;
	private JPanel contentPane;
	public static int port=0;
	private JTable table_fnodes;
	public static DefaultTableModel dtab_fnodes;
	/**
	 * Create the frame.
	 */
	public MasterNode(String gid,int port) {
		this.grp_id=gid;
		this.port=port;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 636);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMasterNode = new JLabel("Master Node");
		lblMasterNode.setBounds(246, 13, 93, 16);
		contentPane.add(lblMasterNode);
		
		JLabel lblGroupId = new JLabel("Group ID ");
		lblGroupId.setBounds(40, 66, 56, 16);
		contentPane.add(lblGroupId);
		
		JLabel lbl_grpid = new JLabel("New label");
		lbl_grpid.setBounds(131, 66, 116, 16);
		contentPane.add(lbl_grpid);
		lbl_grpid.setText(grp_id);
		
		JLabel lblFollowerNodes = new JLabel("Follower Nodes");
		lblFollowerNodes.setBounds(40, 99, 207, 16);
		contentPane.add(lblFollowerNodes);
		
		JScrollPane sp_fnodes = new JScrollPane();
		sp_fnodes.setBounds(50, 134, 495, 150);
		contentPane.add(sp_fnodes);
		
		table_fnodes = new JTable();
		sp_fnodes.setViewportView(table_fnodes);
		dtab_fnodes=new DefaultTableModel();
		table_fnodes.setModel(dtab_fnodes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 323, 492, 212);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblActivities = new JLabel("Activities");
		lblActivities.setBounds(60, 294, 127, 16);
		contentPane.add(lblActivities);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(329, 66, 56, 16);
		contentPane.add(lblPort);
		
		
		JLabel lblLblport = new JLabel("lbl_port");
		lblLblport.setBounds(437, 66, 56, 16);
		contentPane.add(lblLblport);
		lblLblport.setText(port+"");
		dtab_fnodes.addColumn("Object ID");
		
		dtab_fnodes.addColumn("Port");
		
		new master_listener(this,grp_id);
	}
	public static void main(String a[])
	{
		char[] alpha = new char[26];
		for(int i = 0; i < 26; i++){
		    alpha[i] = (char)(65 + i);
		}
		Random r=new Random();
		String grp_id="";
		int i1=r.nextInt(26);
		int i2=r.nextInt(10);
		
		grp_id=alpha[i1]+""+i2;
		boolean f=check_grp_id(grp_id);
		System.out.println(f);
		if(f==true)
		{
			JOptionPane.showMessageDialog(null, "Group ID already present");
		}
		else{
			
			
			port=get_port();
			System.out.println("port="+port);
			
			
			
			
			
			MasterNode mn=new MasterNode(grp_id,port);
			mn.setVisible(true);
			//String arr[]={grp_id,port+""};
			try{
			File f1=new File(info.path_py+"master.txt");
			FileWriter fw_new=new FileWriter(f1);
	        fw_new.write(grp_id+"#"+port);
	        fw_new.close();
	        
	        
	        File f2=new File(info.path_py+"task.txt");
			FileWriter fw_new2=new FileWriter(f2);
	        fw_new2.write("master");
	        fw_new2.close();
	        
	        File f3=new File(info.path_py+grp_id+".txt");
			FileWriter fw_new3=new FileWriter(f3);
	        fw_new3.write(grp_id+"#"+port);
	        fw_new3.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	public static boolean check_grp_id(String gid){
		boolean f=false;
		try{
		System.out.println("check grp id "+gid);
		ArrayList ids=new ArrayList();
		String old_data=""; 
		File file = new File(info.path_py+"grp_ids.txt"); 

        BufferedReader br = new BufferedReader(new FileReader(file)); 

        String st; 
        while ((st = br.readLine()) != null) {
        System.out.println(st); 
        ids.add(st);
        old_data=st+"\n"+old_data;
        }
        
        
		
		
		for(int i=0;i<ids.size();i++)
		{
			if(gid.equals(ids.get(i).toString()))
			{
				f=true;
				break;
			}
		}
		if(f==false)
		{
			
	        FileWriter fw_new=new FileWriter(file);
	        fw_new.write(old_data+"\n"+gid);
	        fw_new.close();
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return f;
	}
	public static int get_port(){
		int port=0;
		try{
		
		String old_port=""; 
		File file = new File(info.path_py+"port.txt"); 

        BufferedReader br = new BufferedReader(new FileReader(file)); 

        String st; 
        while ((st = br.readLine()) != null) {
        System.out.println(st); 
        
        old_port=st;
        }
        
        port=Integer.parseInt(old_port);
        port=port+1;
		
		
		
			
        FileWriter fw_new=new FileWriter(file);
        fw_new.write(port+"");
        fw_new.close();
			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return port;
	}
}
