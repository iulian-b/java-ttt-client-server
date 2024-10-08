package gioco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;

import java.awt.Font;

public class TrisClient extends JFrame {
		
	// Netcode Socket
	static ServerSocket ServerSocket;
	static Socket Socket;
	public static final int PORTA=1337;
	
	// Netcode I/O
	static BufferedReader ReaderInput;
	static BufferedWriter ReaderOutput;
	static InputStreamReader StreamInput;
	static OutputStreamWriter StreamOutput;

	// Input
	static PrintWriter Writer;
	
	// Variabili
	private static final long serialVersionUID = -4214163155216722740L;
	static String tmp="";
	static String FLAG="";
	
	// Data
    final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    final DateFormat sdf2 = new SimpleDateFormat("mm:ss");
    Date Data = new Date();
    
	// Javax Swing
    static JToggleButton[][] Griglia = new JToggleButton[3][3];
    public JFrame Finestra;
    public JTextArea Console;
    static JPanel contentPane;
    
	// Javax Swing Pulsanti
	public JToggleButton  btnNewButton;
	public JToggleButton  btnNewButton_1;
	public JToggleButton  btnNewButton_2;
	public JToggleButton  btnNewButton_3;
	public JToggleButton  btnNewButton_4;
	public JToggleButton  btnNewButton_5;
	public JToggleButton  btnNewButton_6;
	public JToggleButton  btnNewButton_7;
	public JToggleButton  btnNewButton_8;


	
	public TrisClient() throws IOException {
		grafica();
		connetti();
		ascolta();
	}
	
	public void grafica() throws IOException{
		Finestra=new JFrame();
		Finestra.setTitle("[client@127.0.0.1:1337]");
		contentPane=new JPanel();
		Finestra.setBounds(100, 100, 520, 300);
		Finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Finestra.setContentPane(contentPane);
		contentPane.setLayout(null);
	    JFrameBuild();
		Finestra.setVisible(true);
	}

	public  void JFrameBuild() {
		btnNewButton = new JToggleButton("");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		Griglia[0][0]=btnNewButton;
		btnNewButton.setBounds(10, 11, 80, 80);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JToggleButton ("");
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		Griglia[0][1]=btnNewButton_1;
		btnNewButton_1.setBounds(90, 11, 80, 80);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JToggleButton("");
		btnNewButton_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		Griglia[0][2]=btnNewButton_2;
		btnNewButton_2.setBounds(170, 11, 80, 80);
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JToggleButton("");
		btnNewButton_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		Griglia[1][0]=btnNewButton_3;
		btnNewButton_3.setBounds(10, 91, 80, 80);
		contentPane.add(btnNewButton_3);

		btnNewButton_4 = new JToggleButton("");
		btnNewButton_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		Griglia[1][1]=btnNewButton_4;
		btnNewButton_4.setBounds(90, 91, 80, 80);
		contentPane.add(btnNewButton_4);

		btnNewButton_5 = new JToggleButton("");
		btnNewButton_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		Griglia[1][2]=btnNewButton_5;
		btnNewButton_5.setBounds(170, 91, 80, 80);
		contentPane.add(btnNewButton_5);

		btnNewButton_6 = new  JToggleButton("");
		btnNewButton_6.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		Griglia[2][0]=btnNewButton_6;
		btnNewButton_6.setBounds(10, 171, 80, 80);
		contentPane.add(btnNewButton_6);

		btnNewButton_7 = new JToggleButton("");
		btnNewButton_7.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		Griglia[2][1]=btnNewButton_7;
		btnNewButton_7.setBounds(90, 171, 80, 80);
		contentPane.add(btnNewButton_7);

		btnNewButton_8 = new JToggleButton("");
		btnNewButton_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		Griglia[2][2]=btnNewButton_8;
		btnNewButton_8.setBounds(170, 171, 80, 80);
		contentPane.add(btnNewButton_8);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(260, 12, 234, 238);
		contentPane.add(scrollPane);
		
		Console = new JTextArea();
		Console.setEditable(false);
		Console.setFont(new Font("Consolas", Font.PLAIN, 10));
		scrollPane.setViewportView(Console);
		
		gestisciInput();
	}  
	
	public void gestisciInput() {
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tmp.equals("x")) btnNewButton.setText("X");
				if(tmp.equals("o")) btnNewButton.setText("O");
				
				checkInput();
				try {
					inserisci("0","0");
				} catch (IOException e) {
					e.printStackTrace();
					Console.append(sdf2.format(Data)+" ERR: eccezzione catturata in TrisClient:gestisciInput()\n");
				}
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tmp.equals("x")) btnNewButton_1.setText("X");
				if(tmp.equals("o")) btnNewButton_1.setText("O");
				
				checkInput();
				try {
					inserisci("0","1");
				} catch (IOException a) {
					a.printStackTrace();
					Console.append(sdf2.format(Data)+" ERR: eccezzione catturata in TrisClient:gestisciInput()\n");
				}
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(tmp.equals("x")) btnNewButton_2.setText("X");
				if(tmp.equals("o")) btnNewButton_2.setText("O");
				
				checkInput();
				try {
					inserisci("0","2");
				} catch (IOException a) {
					a.printStackTrace();
					Console.append(sdf2.format(Data)+" ERR: eccezzione catturata in TrisClient:gestisciInput()\n");
				}
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tmp.equals("x")) btnNewButton_3.setText("X");
				if(tmp.equals("o")) btnNewButton_3.setText("O");
				
				checkInput();
				try {
					inserisci("1","0");
				} catch (IOException a) {
						a.printStackTrace();
						Console.append(sdf2.format(Data)+" ERR: eccezzione catturata in TrisClient:gestisciInput()\n");
				}
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(tmp.equals("x")) btnNewButton_4.setText("X");
				if(tmp.equals("o")) btnNewButton_4.setText("O");	
				
				checkInput();
				try {
					inserisci("1","1");
				} catch (IOException a) {
					a.printStackTrace();
					Console.append(sdf2.format(Data)+" ERR: eccezzione catturata in TrisClient:gestisciInput()\n");
				}
			}
		});
		
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(tmp.equals("x")) btnNewButton_5.setText("X");
				if(tmp.equals("o")) btnNewButton_5.setText("O");
				
				checkInput();
				try {
					inserisci("1","2");
				} catch (IOException a) {
					a.printStackTrace();
					Console.append(sdf2.format(Data)+" ERR: eccezzione catturata in TrisClient:gestisciInput()\n");	
				}
			}
		});
		
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(tmp.equals("x")) btnNewButton_6.setText("X");
				if(tmp.equals("o")) btnNewButton_6.setText("O");

				checkInput();
				try {
					inserisci("2","0");
				} catch (IOException a) {
					a.printStackTrace();
					Console.append(sdf2.format(Data)+" ERR: eccezzione catturata in TrisClient:gestisciInput()\n");
				}
			}
		});
		
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tmp.equals("x")) btnNewButton_7.setText("X");
				if(tmp.equals("o")) btnNewButton_7.setText("O");
				
				checkInput();
				try {
					inserisci("2","1");
				} catch (IOException a) {
					a.printStackTrace();
					Console.append(sdf2.format(Data)+" ERR: eccezzione catturata in TrisClient:gestisciInput()\n");	
				}
			}
		});
		
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if(tmp.equals("x")) btnNewButton_8.setText("X");
				if(tmp.equals("o")) btnNewButton_8.setText("O");
				
				checkInput();
				try {
					inserisci("2","2");
				} catch (IOException a) {
					a.printStackTrace();
					Console.append(sdf2.format(Data)+" ERR: eccezzione catturata in TrisClient:gestisciInput()\n");
				}
			}
		});
	}
	
	public void checkInput() {
		// Check Orizzontale
		for(int i=0;i<3;i++) {
			if(Griglia[i][0].isSelected() && Griglia[i][1].isSelected() && Griglia[i][2].isSelected()) {
				JOptionPane.showMessageDialog(Finestra,"PARTITA FINITA");
				FLAG="s";
			}			
		}

		// Ceck Verticale
		for(int j=0;j<3;j++) {
			if(Griglia[0][j].isSelected() && Griglia[1][j].isSelected() && Griglia[2][j].isSelected()) {
				JOptionPane.showMessageDialog(Finestra,"PARTITA FINITA");
				FLAG="s";
			}
		}
		
		// Check Diagonale	
		if(Griglia[0][0].isSelected() && Griglia[1][1].isSelected() && Griglia[2][2].isSelected()) {
			JOptionPane.showMessageDialog(Finestra,"PARTITA FINITA");
			FLAG="s";
		}		
		if(Griglia[0][2].isSelected() && Griglia[1][1].isSelected() && Griglia[2][0].isSelected()) {
			JOptionPane.showMessageDialog(Finestra,"PARTITA FINITA");
			FLAG="s";
		}
	}
	
	public String switchSimbolo() throws IOException {
		String str="";
		str=ReaderInput.readLine();
		if(str.equals("x")) {
			str="o";
			return str;
		}
		else {
			str="x";
		      return str;
		}
	}
	
	public void reset() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				Griglia[i][j].setSelected(false);
				Griglia[i][j].setEnabled(true);
				Griglia[i][j].setText("");
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void startPartita() throws IOException {
		String s="";
		s=ReaderInput.readLine();
		
		if(s.equals("s")) Finestra.enable(false);
		else Finestra.enable(true);
	}
	
	@SuppressWarnings("deprecation")
	public void turno() throws IOException{
		String s = "s";
		if(s.equals("c")) {
			Writer.println(s);
			Writer.flush();
		}
		else{
			JOptionPane.showMessageDialog(Finestra," non puoi interaggire inizia il server ");
			Writer.println(s);
			Finestra.enable(false);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void focusFinestra() throws IOException {
		String s=ReaderInput.readLine();
		if(s.equals("client")) Finestra.enable(true);
	}
	
	@SuppressWarnings("deprecation")
	public void switchGiocatore() {
		Finestra.enable(false);
		Writer.println("client");
		Writer.flush();
	}
	
	public void connetti() throws IOException{
		Console.append("Client avviato \n");
		Console.append(sdf.format(Data)+'\n');
		Console.append(" ____  ____  __  ____ \n");
		Console.append("(_  _)(  _ |(  )/ ___)\n");
		Console.append("  )(   )   / )( |___ |\n");
		Console.append(" (__) (__|_)(__)(____/\n");
		Console.append("\n\n");

		Console.append(sdf2.format(Data)+" Connessione al server \n");			
		Socket= new Socket(InetAddress.getLocalHost(),PORTA);
		Console.append(sdf2.format(Data)+" Connesso con successo \n");
		Console.append(sdf2.format(Data)+" Comincia il server \n");		
		
		connettiInput();
		connettiOutput();
           
		tmp=switchSimbolo();
		startPartita();
	}
		
	public void connettiInput() throws IOException {
		StreamInput = new InputStreamReader(Socket.getInputStream());
		ReaderInput = new BufferedReader(StreamInput);
	}
	
	public void connettiOutput() throws IOException {
		StreamOutput = new OutputStreamWriter(Socket.getOutputStream());
		ReaderOutput = new BufferedWriter(StreamOutput);
		Writer = new PrintWriter(ReaderOutput, true);
	}
	
	public void ascolta() throws IOException {
		while (true) {	
			String stringa = ReaderInput.readLine();
			if(stringa.equals("s")) {
				reset();
				startPartita();
			} else {
				String[] arrOfStr = stringa.split(",", 0); 
				String a=arrOfStr[0];
				String b=arrOfStr[1];
				String c=arrOfStr[2];
				
				if(c.equals("x")) {
					Griglia[Integer.parseInt(a)][Integer.parseInt(b)].setText("X");
					Griglia[Integer.parseInt(a)][Integer.parseInt(b)].setEnabled(false);
				}
				else
					Griglia[Integer.parseInt(a)][Integer.parseInt(b)].setText("O");
					Griglia[Integer.parseInt(a)][Integer.parseInt(b)].setEnabled(false);
				focusFinestra();  
			}
		} 
	}

	public void inserisci(String rig,String colonna) throws IOException {		
		if(FLAG=="s") System.exit(1);
		else {
			String userInput;
			userInput =rig+","+colonna+","+tmp;
			Writer.println(userInput);
			Writer.flush();
			switchGiocatore();
		}
	}	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		TrisClient GiocoClient=new TrisClient();
	}
}
