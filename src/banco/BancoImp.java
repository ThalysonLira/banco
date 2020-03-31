package banco;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class BancoImp implements Banco {

	private final ArrayList<Account> contas;
	private Account contaLogada;

	public BancoImp() throws RemoteException {
		this.contas = new ArrayList<Account>();
		this.contaLogada = null;
	}

	@Override
	public String criar(String senha) throws RemoteException {
		Account conta = new Account(0.0, contas.size(), senha);
		getContas().add(conta);
		return ("Sua conta foi criada com sucesso!\nNúmero da conta: " + conta.getNumeroConta() + ".");
	}

	@Override
	public String logar(int numeroConta, String senha) throws RemoteException {
		int n = contas.size();
		for (int i = 0; i < n; i++) {
			if (getContas().get(i).getNumeroConta() == numeroConta && getContas().get(i).getSenha().equals(senha)) {
				setContaLogada(getContas().get(i));
				return "Login efeituado com sucesso.";
			}
		}
		return ("Conta ou senha incorretos.");
	}

	@Override
	public String depositar(double valor, int numeroConta) throws RemoteException {
		for (Account conta : contas) {
			if (conta.getNumeroConta() == numeroConta) {
				conta.setSaldo(conta.getSaldo() + valor);
				return "Deposito realizado com sucesso.";
			}
		}
		return "Não foi possível localizar esta conta.";
	}

	@Override
	public String depositar(double valor) throws RemoteException {
		if (getContaLogada() == null)
			return "Antes de continuar, realize o login.";

		getContaLogada().setSaldo(getContaLogada().getSaldo() + valor);
		return "Depósito realizado com sucesso.";
	}

	@Override
	public String sacar(double valor) throws RemoteException {
		if (getContaLogada() == null)
			return "Antes de continuar, realize o login.";
		if (getContaLogada().getSaldo() >= valor) {
			getContaLogada().setSaldo(getContaLogada().getSaldo() - valor);
			return "Saque realizado com sucesso.";
		} else
			return "Saldo insuficiente.";
	}

	@Override
	public String extrato() throws RemoteException {
		if (getContaLogada() == null)
			return "Antes de continuar, realize o login.";

		return ("Saldo disponível: R$ " + getContaLogada().getSaldo().toString() + ".");
	}

	@Override
	public String transferir(int numeroConta, double valor) throws RemoteException {
		if (getContaLogada() == null)
			return "Antes de continuar, realize o login.";
		
		if (getContaLogada().getSaldo() >= valor) {

		for (Account conta : contas) {
			if (conta.getNumeroConta() == numeroConta) {
				getContaLogada().setSaldo(getContaLogada().getSaldo() - valor);
				conta.setSaldo(conta.getSaldo() + valor);
				return "Foram transferidos R$ " + valor + " para a conta " + conta.getNumeroConta() + ".";
			}
		}
		return "Não foi possível localizar esta conta.";

		} else
			return "Saldo insuficiente.";
	}

	@Override
	public String sair() throws RemoteException {
		setContaLogada(null);
		return "Obrigado, volte sempre!";
	}

	public Account getContaLogada() {
		return contaLogada;
	}

	public void setContaLogada(Account contaLogada) {
		this.contaLogada = contaLogada;
	}

	public ArrayList<Account> getContas() {
		if (contas == null)
			return new ArrayList<Account>();
		return contas;
	}

}