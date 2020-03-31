package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import banco.Banco;
import banco.BancoImp;

public class Server {
	
	public static void main(String args[]){
		
		try {
			Banco banco = new BancoImp();
			Banco bancoStub = (Banco) UnicastRemoteObject.exportObject(banco, 0);
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("banco", bancoStub); 	
			System.out.println("Server está online!");
		} catch (RemoteException re) {
			System.out.println("Erro remoto: " + re.toString());
		} catch (Exception e) {
			System.out.println("Erro Local: " + e.toString());
		}
		
	}

}
