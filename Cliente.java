
import java.io.*;
import java.net.*;
//
//CODIGO REALIZADO POR ENMANUEL MARTINEZ
//DERECHOS RESERVADOS JULIO 2009
//
public class Cliente extends Thread {

	OutputStream out2;
	DataOutputStream escribe;
	InputStream in2;
	DataInputStream lee;
	Socket client;
	Ball bola;
	int vxmanda,vxrecive;
	int vymanda,vyrecive;
	int posymanda,posyrecive;
    boolean control=false;
	//clase para conexion 
	public Cliente() {

	}

	@SuppressWarnings("static-access")
	public void run() {
		while (true) {

			try {
				Thread.sleep(11);
				iniciarcliente();
				//se inician variables de escritura y lectura
				out2 = client.getOutputStream();
				in2 = client.getInputStream();
				escribe = new DataOutputStream(out2);
				lee = new DataInputStream(in2);
				if (bola.mandame==true)//si mandame esta true entonces da permiso para mandar datos por socket
				{
					mandar();	
					
				}else{//si mandame no esta true espera para recivir
				 
				recivir();
				}
				client.close();//cierra port
			} catch (IOException ex) {
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}

		}
	}

	public void mandar() throws IOException {


			vxmanda = Ball.vx;//obtiene velocidad en x de la bola
			vymanda = Ball.vy;//obtiene velocidad en u de la bola
			posymanda = Ball.y;//obtiene posicion en y de la bola
			escribe.writeInt(1);//protocolo de comunicacion
			escribe.writeInt(vxmanda);//manda velocidad en x
		    escribe.writeInt(vymanda);//manda velocidad en y
			escribe.writeInt(posymanda);//manda posicion en y
			Ball.mandame = false;//desactiva mandar
		    control=true;//asegura que se mando datos
		

	}

	public void recivir() throws IOException

	{
		if (control) {
			if(lee.readInt()==1){
			vxrecive = lee.readInt();//lee velocidad en x
		    vyrecive = lee.readInt();//lee velocidad en y
		 	posyrecive = lee.readInt();//lee posicion en y
		 	Ball.vx =vxrecive;//le pasa valores a bola de velocidad en x
			Ball.vy =vyrecive;//le pasa valores a bola de  velocidad en y
	        Ball.y=posyrecive;////le pasa valores a bola de posicion en y
	        Ball.x = Main.ANCHO-145;//localiza la bola en el borde la pantalla
			Ball.flagball = true;//activa el pintado cuando recive la bola
			control=false;//hace que no entre a recivir hasta que no envie
			}
			
		}

	}
     //metodo que inicia socket
	public void iniciarcliente() throws IOException {

		client = new Socket("localhost",6500);

	}

}
