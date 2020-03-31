package banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Banco extends Remote {

	String criar(String senha) throws RemoteException;

	String logar(int numerodaconta, String senha) throws RemoteException;

	String sair() throws RemoteException;

	String depositar(double valor, int numerodaconta) throws RemoteException;

	String depositar(double valor) throws RemoteException;
	
	String sacar(double valor) throws RemoteException;

	String extrato() throws RemoteException;

	String transferir(int numerodaconta, double valor) throws RemoteException;
}
