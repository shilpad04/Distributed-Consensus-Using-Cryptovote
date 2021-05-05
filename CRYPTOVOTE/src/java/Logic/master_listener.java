package Logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Forms.MasterNode;
import Forms.Node;

public class master_listener extends Thread {
	MasterNode mn;
	String master_name="";
	public master_listener(MasterNode mn,String grp_id){
		this.mn=mn;
		this.master_name=grp_id;
		
		start();
	}
	
	public void run(){
		
		try {
			ServerSocket ss=new ServerSocket(mn.port);
			System.out.println(MasterNode.grp_id+" is listening at "+MasterNode.port);
			while(true)
			{
				Socket soc=ss.accept();
				ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
				String task=oin.readUTF();
				System.out.println("Received by "+master_name);
				if(task.equals("join"))
				{
				
				String obj_id=oin.readUTF();
				int po=oin.readInt();
				String arr[]={obj_id,po+""};
				mn.dtab_fnodes.addRow(arr);
				}
				else if(task.equals("fwd"))
				{
					String tid=oin.readUTF();
					String data=oin.readUTF();
					for(int i=0;i<mn.dtab_fnodes.getRowCount();i++)
					{
						System.out.println("Fwd data to follwers node "+MasterNode.dtab_fnodes.getValueAt(i, 0).toString());
						mn.textArea.append("Fwd data to follwers node "+MasterNode.dtab_fnodes.getValueAt(i, 0).toString());
						Socket soc1=new Socket("localhost",Integer.parseInt(mn.dtab_fnodes.getValueAt(i, 1).toString()));
						ObjectOutputStream out=new ObjectOutputStream(soc1.getOutputStream());
						out.writeUTF("fwd");
						out.writeUTF(tid);
						out.writeUTF(data);
						out.close();
						soc1.close();
						
					}
					
					
					
					
				}
				soc.close();
				oin.close();
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
