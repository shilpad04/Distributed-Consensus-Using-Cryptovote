package Logic;

import java.io.File;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Forms.Node;

public class node_listener extends Thread {
	
	public node_listener(){
		
		start();
	}
	
	public void run()
	{
		try{
		ServerSocket ss=new ServerSocket(5000);
		while(true)
		{
			Socket soc=ss.accept();
			ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
			String task=oin.readUTF();
			if(task.equals("ack"))
			{
				String tid=oin.readUTF();
				for(int i=0;i<Node.al_tid.size();i++)
				{
					if(Node.al_tid.get(i).toString().equals(tid))
					{
						int count=Integer.parseInt(Node.al_tid_count.get(i).toString());
						count=count+1;
						System.out.println("False Node "+Node.false_node_count+" Response "+count);
						
						Node.al_tid_count.set(i, count+"");
						if(count+1>Node.false_node_count)
						{
							System.out.println("Verified");
							Node.al_tid.remove(i);
							Node.al_tid_count.remove(i);
							
							File fnew=new File(info.path_py+"status.txt");
					        FileWriter fw_new=new FileWriter(fnew);
					        fw_new.write("update");
					        fw_new.close();
							
							
						}
						else{
							System.out.println("Not Verified");
						}
						break;
						
					}
				}
				
			}
			oin.close();
			soc.close();
			
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
