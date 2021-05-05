package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import Forms.MasterNode;
import Forms.Node;

public class check_task extends Thread {
	String task="";
	public check_task(){
		start();
	}
	
	public void run(){
		
		while(true)
		{
			try{
			File file = new File(info.path_py+"task.txt"); 

	        BufferedReader br = new BufferedReader(new FileReader(file)); 

	        String st; 
	        while ((st = br.readLine()) != null) {
	        System.out.println(st); 
	        task=st;
	        }
			
			
			if(!task.equals(""))
			{
				if(task.equals("update"))
				{
					ArrayList al=get_grp_ids();
					int size=al.size();
					Random r=new Random();
					int index=r.nextInt(size);
					int tid=r.nextInt(10000);
					Node.al_tid.add(tid+"");
					Node.al_tid_count.add("0");
					String data="";
					File file1 = new File(info.path_py+"data.txt"); 

			        BufferedReader br1 = new BufferedReader(new FileReader(file1)); 

			        String st1; 
			        while ((st1 = br1.readLine()) != null) {
			        System.out.println(st1); 
			        data=st1;
			        }
					String grp_id=al.get(index).toString();
					String data2="";
					File file2 = new File(info.path_py+grp_id+".txt"); 

			        BufferedReader br2 = new BufferedReader(new FileReader(file2)); 

			        String st2; 
			        while ((st2 = br2.readLine()) != null) {
			        System.out.println(st2); 
			        data2=st2;
			        }
			        String arr[]=data2.split("#");
			        Socket soc=new Socket("localhost",Integer.parseInt(arr[1]));
					ObjectOutputStream out=new ObjectOutputStream(soc.getOutputStream());
					out.writeUTF("fwd");
					out.writeUTF(tid+"");
					out.writeUTF(data);
					out.close();
					soc.close();
					
					
				}
				else if(task.equals("master"))
				{
					String data="";
					File file1 = new File(info.path_py+"master.txt"); 

			        BufferedReader br1 = new BufferedReader(new FileReader(file1)); 

			        String st1; 
			        while ((st1 = br1.readLine()) != null) {
			        System.out.println(st1); 
			        data=st1;
			        }
			        
			        String arr[]=data.split("#");
			        //String arr1[]={arr[0],arr[1]};
					Node.dtab_master_nodes.addRow(arr);
				}
				else if(task.equals("follower"))
				{
					String data="";
					File file1 = new File(info.path_py+"follower.txt"); 

			        BufferedReader br1 = new BufferedReader(new FileReader(file1)); 

			        String st1; 
			        while ((st1 = br1.readLine()) != null) {
			        System.out.println(st1); 
			        data=st1;
			        }
			        
			        String arr[]=data.split("#");
			        //String arr1[]={arr[0],arr[1]};
					Node.dtab_follower_nodes.addRow(arr);
				}
				if(task.equals("false"))
				{
					Node.false_node_count++;
					
				}
				
				    File fnew=new File(info.path_py+"task.txt");
			        FileWriter fw_new=new FileWriter(fnew);
			        fw_new.write("");
			        fw_new.close();
			        task="";
			}
			Thread.sleep(3000);
			}catch(Exception e)
			{
				
				e.printStackTrace();
			}
			
		}
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
}
