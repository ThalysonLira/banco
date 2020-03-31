package client;

import java.rmi.Naming;
import javax.swing.JOptionPane;
import banco.Banco;
import server.Menu;

public class Client {

	public static void main(String args[]) throws Exception {
		Banco banco = ((Banco) Naming.lookup("rmi://127.0.0.1:1099/banco"));
		Menu menu = new Menu();
		int op = -1;

		while (op != 0) {
			op = menu.mainMenu(banco, op);
		}
		JOptionPane.showMessageDialog(null, "Obrigado.", "Programa encerrado", JOptionPane.INFORMATION_MESSAGE);
	}
	
}