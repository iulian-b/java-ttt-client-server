package gioco;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Bocse Iulian 5AII
 * Tris Client-Server
 * --------------------------------------------------------------------------
 * Il gioco del tris implementato con un server ed un client come giocatori
 * Versione 1.0
 * Build 17.1.2019-11:41
 * --------------------------------------------------------------------------
 */

public class Main {
	
	// Data
    final static DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    static Date Data = new Date();
    
	public static void main(String[] args) throws IOException, InterruptedException {
		Thread ThreadServer = new Thread(new Runnable() {
		    public void run() {
				try {
					new TrisServer();
				} catch (IOException e) {
					System.out.println(sdf.format(Data)+" ERR: eccezzione catturata in Main:main()\n");
					e.printStackTrace();
				}
		    }
		});
		
		Thread ThreadClient= new Thread(new Runnable() {
		    public void run() {
				try {
					new TrisClient();
				} catch (IOException e) {
					System.out.println(sdf.format(Data)+" ERR: eccezzione catturata in Main:main()\n");
					e.printStackTrace();
				}
		    }
		});

		ThreadServer.start();
		ThreadClient.start();
	}
}