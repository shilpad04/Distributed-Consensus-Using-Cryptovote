package Forms;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logic.follower_listener;
import Logic.info;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FollowerNode extends JFrame {
    public static String grp_id="",obj_id="";
	private JPanel contentPane;
	public static int port=0;
	JComboBox comboBox;
	public static JTextArea textArea;
	/**
	 * Create the frame.
	 */
	public FollowerNode(String oid,int port,ArrayList al) {
		this.obj_id=oid;
		
		this.port=port;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 537);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFollowerNode = new JLabel("Follower Node");
		lblFollowerNode.setBounds(220, 13, 96, 16);
		contentPane.add(lblFollowerNode);
		
		JLabel lblGroupId = new JLabel("Group ID  : ");
		lblGroupId.setBounds(22, 59, 106, 16);
		contentPane.add(lblGroupId);
		
		JLabel lblObjectId = new JLabel("Object ID :");
		lblObjectId.setBounds(22, 93, 106, 16);
		contentPane.add(lblObjectId);
		
		JLabel lbl_objid = new JLabel("New label");
		lbl_objid.setBounds(130, 93, 138, 16);
		contentPane.add(lbl_objid);
		lbl_objid.setText(obj_id);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(130, 56, 139, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Select");
		
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				grp_id=comboBox.getSelectedItem().toString();
				System.out.println("grp_id="+grp_id);
				String data="";
				File file1 = new File(info.path_py+grp_id+".txt"); 

		        BufferedReader br1 = new BufferedReader(new FileReader(file1)); 

		        String st1; 
		        while ((st1 = br1.readLine()) != null) {
		        System.out.println(st1); 
		        data=st1;
		        }
		        String arr[]=data.split("#");
		        Socket soc=new Socket("localhost",Integer.parseInt(arr[1]));
				ObjectOutputStream out=new ObjectOutputStream(soc.getOutputStream());
				out.writeUTF("join");
				out.writeUTF(obj_id);
				out.writeInt(port);
				out.close();
				soc.close();
				
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
			}
		});
		btnSet.setBounds(290, 55, 97, 25);
		contentPane.add(btnSet);
		
		JButton btnFaultNode = new JButton("Fault Node");
		btnFaultNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Node.false_node_count++;
				try{
					    File f2=new File(info.path_py+"task.txt");
						FileWriter fw_new2=new FileWriter(f2);
				        fw_new2.write("false");
				        fw_new2.close();
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnFaultNode.setBounds(452, 9, 97, 25);
		contentPane.add(btnFaultNode);
		
		JLabel lblActivities = new JLabel("Activities");
		lblActivities.setBounds(22, 133, 138, 16);
		contentPane.add(lblActivities);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 175, 527, 265);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		for(int i=0;i<al.size();i++)
		{
			if(!al.get(i).toString().equals("")){
			comboBox.addItem(al.get(i).toString());
			}
		}
		new follower_listener();
	}
	public static void main(String a[])
	{
		char[] alpha = new char[26];
		for(int i = 0; i < 26; i++){
		    alpha[i] = (char)(65 + i);
		}
		Random r=new Random();
		String obj_id="";
		int i1=r.nextInt(26);
		int i2=r.nextInt(10);
		
		obj_id=alpha[i1]+""+i2;
		boolean f=check_obj_id(obj_id);
		System.out.println(f);
		if(f==true)
		{
			JOptionPane.showMessageDialog(null, "Object ID already present");
		}
		else{
			
			
			port=get_port();
			System.out.println("port="+port);
			
			
			
			
			
			FollowerNode mn=new FollowerNode(obj_id,port,get_grp_ids());
			mn.setVisible(true);
			try{
				File f1=new File(info.path_py+"follower.txt");
				FileWriter fw_new=new FileWriter(f1);
		        fw_new.write(obj_id+"#"+port);
		        fw_new.close();
		        
		        File f2=new File(info.path_py+"task.txt");
				FileWriter fw_new2=new FileWriter(f2);
		        fw_new2.write("follower");
		        fw_new2.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			
		}
		
		
		
	}
	public static boolean check_obj_id(String gid){
		boolean f=false;
		try{
		System.out.println("check obj id "+gid);
		ArrayList ids=new ArrayList();
		String old_data=""; 
		File file = new File(info.path_py+"obj_ids.txt"); 

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
	        fw_new.write(old_data);
	        fw_new.close();
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return f;
	}
	
	public static ArrayList get_grp_ids(){
		String grp_id="";
		ArrayList ids=new ArrayList();
		try{
		
		
		
		File file = new File(info.path_py+"grp_ids.txt"); 

        BufferedReader br = new BufferedReader(new FileReader(file)); 

        String st; 
        while ((st = br.readLine()) != null) {
        System.out.println(st); 
        if(!st.equals("")||st!=null)
        {
        ids.add(st);
        }
       
        }
        
      //  int index=ids.size();
      //  grp_id=ids.get(index).toString();
		
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ids;
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
