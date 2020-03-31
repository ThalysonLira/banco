package banco;

public class Account {
	private double saldo;
	private final int numeroConta;
	private String senha;

	public Account(Double saldo, Integer numeroConta, String senha) {
		this.saldo = saldo;
		this.numeroConta = numeroConta;
		this.senha = senha;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}