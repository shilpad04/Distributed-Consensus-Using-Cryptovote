package Logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Forms.FollowerNode;
import Forms.MasterNode;

public class follower_listener extends Thread {
	
	public follower_listener(){
		
		start();
	}
	
	public void run(){
		
		try {
			ServerSocket ss=new ServerSocket(FollowerNode.port);
			System.out.println(FollowerNode.obj_id+" is listening at "+FollowerNode.port);
			while(true)
			{
				Socket soc=ss.accept();
				ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
				String task=oin.readUTF();
				if(task.equals("fwd"))
				{
					String tid=oin.readUTF();
					String data=oin.readUTF();
					FollowerNode.textArea.append("Data received from master");
					Socket soc1=new Socket("localhost",5000);
					ObjectOutputStream out=new ObjectOutputStream(soc1.getOutputStream());
					out.writeUTF("ack");
					out.writeUTF(tid);
					out.close();
					soc1.close();
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
