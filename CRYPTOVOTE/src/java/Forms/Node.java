package Forms;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logic.check_task;
import Logic.node_listener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Node extends JFrame {
    public static String type="";
	private JPanel contentPane;
	public static ArrayList al_grp_ids=new ArrayList();
	public static ArrayList al_obj_ids=new ArrayList();
	public static ArrayList al_master_node=new ArrayList();
	public static int port=5000;
	public static ArrayList al_master_nodes_obj=new ArrayList();
	public static ArrayList al_follower_node_obj=new ArrayList();
	private JTable table_master_nodes;
	public static DefaultTableModel dtab_master_nodes,dtab_follower_nodes;
	private JTable table_follower_nodes;
	public static ArrayList al_tid=new ArrayList();
	public static ArrayList al_tid_count=new ArrayList();
	public static int false_node_count=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Node frame = new Node();
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
	public Node() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 575);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectNodeType = new JLabel("Select Node Type ");
		lblSelectNodeType.setBounds(12, 13, 129, 16);
		contentPane.add(lblSelectNodeType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(165, 10, 186, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Select");
		comboBox.addItem("Master");
		comboBox.addItem("Follower");
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type=comboBox.getSelectedItem().toString();
				char[] alpha = new char[26];
				for(int i = 0; i < 26; i++){
				    alpha[i] = (char)(65 + i);
				}
				Random r=new Random();
				String grp_id="",obj_id="";
				int i1=r.nextInt(26);
				int i2=r.nextInt(10);
				if(type.equalsIgnoreCase("select"))
				{
					JOptionPane.showMessageDialog(null, "Please select the node type");
				}
				else if(type.equalsIgnoreCase("master"))
				{
					
					grp_id=alpha[i1]+""+i2;
					boolean f=check_grp_id(grp_id);
					if(f==true)
					{
						JOptionPane.showMessageDialog(null, "Group ID already present");
					}
					else{
						//close();
						al_master_node.add(grp_id);
						
						port=port+1;
						System.out.println("port="+port);
						
						
						
						
						
						MasterNode mn=new MasterNode(grp_id,port);
						mn.setVisible(true);
						String arr[]={grp_id,port+""};
						dtab_master_nodes.addRow(arr);
						al_master_nodes_obj.add(mn);
						for(int i=0;i<al_follower_node_obj.size();i++)
						{
							FollowerNode fnn=	(FollowerNode)al_follower_node_obj.get(i);
							fnn.comboBox.removeAllItems();
							fnn.comboBox.addItem("Select");
							for(int j=0;j<al_master_node.size();j++)
							{
								fnn.comboBox.addItem(al_master_node.get(j).toString());
							}
							
						}
					}
					
					
				}
				else{
					obj_id=alpha[i1]+""+i2;
					boolean f=check_obj_id(obj_id);
					if(f==true)
					{
						JOptionPane.showMessageDialog(null, "Object ID already present");
					}
					else{
					
					//close();
						port=port+1;
						FollowerNode fn=	new FollowerNode(obj_id,port,al_master_node);
						fn.setVisible(true);
						
						String arr[]={obj_id,port+""};
						dtab_follower_nodes.addRow(arr);
						
						al_follower_node_obj.add(fn);
					}
				}
				
			}
		});
		btnSet.setBounds(361, 9, 97, 25);
		contentPane.add(btnSet);
		
		JLabel lblMasterNodes = new JLabel("Master Nodes");
		lblMasterNodes.setBounds(12, 66, 208, 16);
		contentPane.add(lblMasterNodes);
		
		JScrollPane sp_master_nodes = new JScrollPane();
		sp_master_nodes.setBounds(12, 95, 648, 173);
		contentPane.add(sp_master_nodes);
		
		table_master_nodes = new JTable();
		sp_master_nodes.setViewportView(table_master_nodes);
		dtab_master_nodes=new DefaultTableModel();
		table_master_nodes.setModel(dtab_master_nodes);
		dtab_master_nodes.addColumn("Group ID");
		dtab_master_nodes.addColumn("Port");
		
		
		JLabel lblFollowerNodes = new JLabel("Follower Nodes");
		lblFollowerNodes.setBounds(22, 281, 147, 16);
		contentPane.add(lblFollowerNodes);
		
		
		JScrollPane sp_follower_nodes = new JScrollPane();
		sp_follower_nodes.setBounds(12, 312, 648, 179);
		contentPane.add(sp_follower_nodes);
		
		table_follower_nodes = new JTable();
		sp_follower_nodes.setViewportView(table_follower_nodes);
		dtab_follower_nodes=new DefaultTableModel();
		table_follower_nodes.setModel(dtab_follower_nodes);
		
		dtab_follower_nodes.addColumn("Object ID");
		dtab_follower_nodes.addColumn("Port");
		new check_task();
		new node_listener();
	}
	public void close(){
		this.setVisible(false);
	}
	public boolean check_grp_id(String gid){
		boolean f=false;
		System.out.println("check grp id "+gid);
		for(int i=0;i<al_grp_ids.size();i++)
		{
			if(gid.equals(al_grp_ids.get(i).toString()))
			{
				f=true;
				break;
			}
		}
		
		
		return f;
	}
	public boolean check_obj_id(String oid){
		boolean f=false;
		System.out.println("check grp id "+oid);
		for(int i=0;i<al_obj_ids.size();i++)
		{
			if(oid.equals(al_obj_ids.get(i).toString()))
			{
				f=true;
				break;
			}
		}
		
		return f;
	}
}
