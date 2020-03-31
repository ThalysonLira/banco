package server;

import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import banco.Banco;
import banco.BancoImp;

public class Menu {

	public int mainMenu(Banco banco, int op) {
		op = optionValidation(JOptionPane.showInputDialog(null,
				"1 - Realizar depósito em conta\n" + "2 - Acessar sua conta\n" + "3 - Criar conta\n" + "0 - Sair",
				"Banco", JOptionPane.INFORMATION_MESSAGE), op);

		switch (op) {

		case 1:
			deposito(banco);
			break;
		case 2:
			logar(banco, op);
			break;

		case 3:
			novaConta(banco);
			break;

		case 0:
			return 0;

		default:
			System.out.println("Opção inválida!");
			break;

		}
		return op;
	}

	public int secondMenu(Banco banco, int op) {
		while (op != 11) {
			op = optionValidation(JOptionPane.showInputDialog(null,
					"1 - Realizar depósito em conta\n" + "2 - Realizar saque\n" + "3 - Verificar saldo\n"
							+ "4 - Realizar transferência\n" + "0 - Sair\n",
					"Escolha uma opção", JOptionPane.INFORMATION_MESSAGE), op);

			switch (op) {

			case 1:
				depositoLogado(banco);
				break;
			case 2:
				saque(banco);
				break;
			case 3:
				extrato(banco);
				break;
			case 4:
				transferencia(banco);
				break;
			default:
				sair(banco);
				return 11;

			}
		}
		return op;
	}

	public void novaConta(Banco banco) {
		String senha = "1", confirmSenha = "2";

		while (senha != confirmSenha) {
			senha = JOptionPane.showInputDialog(null, "Digite a senha :", "Entrada", JOptionPane.INFORMATION_MESSAGE);
			confirmSenha = JOptionPane.showInputDialog(null, "Digite novamente a senha :", "Entrada",
					JOptionPane.INFORMATION_MESSAGE);
			
			if(senha.equals(confirmSenha)) {
				try {
					JOptionPane.showMessageDialog(null, banco.criar(senha), "Informação", JOptionPane.INFORMATION_MESSAGE);
					return;
				} catch (RemoteException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocorreu um erro na tentativa de criar nova conta.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			else {
				JOptionPane.showMessageDialog(null, "As senhas não conferem.", "Ops ...",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void deposito(Banco banco) {
		int numeroConta;
		Double valor;

		try {
			numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta :", "Entrada",
					JOptionPane.INFORMATION_MESSAGE));
			System.out.print("Digite o valor a ser depositado : ");
			valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor a ser depositado :", "Entrada",
					JOptionPane.INFORMATION_MESSAGE));
			JOptionPane.showMessageDialog(null, banco.depositar(valor, numeroConta), "Informação",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "São permitidos apenas números!", "Opção Inválida",
					JOptionPane.ERROR_MESSAGE);
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na tentativa de depósito.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void logar(Banco banco, int op) {
		int numeroConta;
		String senha, login;

		try {
			numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta :", "Entrada",
					JOptionPane.INFORMATION_MESSAGE));
			senha = JOptionPane.showInputDialog(null, "Digite a senha :", "Entrada", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, banco.logar(numeroConta, senha), "Informação",
					JOptionPane.INFORMATION_MESSAGE);
			if(banco.logar(numeroConta, senha).equals("Login efeituado com sucesso."))
				secondMenu(banco, op);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "São permitidos apenas números!", "Opção Inválida",
					JOptionPane.ERROR_MESSAGE);
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na tentativa de login.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void depositoLogado(Banco banco) {
		Double valor;

		try {
			valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor a ser depositado :", "Entrada",
					JOptionPane.INFORMATION_MESSAGE));
			JOptionPane.showMessageDialog(null, banco.depositar(valor), "Informação", JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "São permitidos apenas números!", "Opção Inválida",
					JOptionPane.ERROR_MESSAGE);
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na tentativa de depósito.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void saque(Banco banco) {
		Double valor;

		try {
			valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor que deseja sacar :", "Entrada",
					JOptionPane.INFORMATION_MESSAGE));
			JOptionPane.showMessageDialog(null, banco.sacar(valor));
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "São permitidos apenas números!", "Opção Inválida",
					JOptionPane.ERROR_MESSAGE);
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na tentativa de saque.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void extrato(Banco banco) {
		try {
			JOptionPane.showMessageDialog(null, banco.extrato(), "Informação", JOptionPane.INFORMATION_MESSAGE);
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao emitir extrato.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void transferencia(Banco banco) {
		int numeroConta;
		Double valor;

		try {
			numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta :", "Entrada",
					JOptionPane.INFORMATION_MESSAGE));
			System.out.print("Digite o valor a ser depositado : ");
			valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor a ser depositado :", "Entrada",
					JOptionPane.INFORMATION_MESSAGE));
			JOptionPane.showMessageDialog(null, banco.transferir(numeroConta, valor), "Informação",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "São permitidos apenas números!", "Opção Inválida",
					JOptionPane.ERROR_MESSAGE);
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na tentativa de transferência.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public int sair(Banco banco) {
		try {
			JOptionPane.showMessageDialog(null, banco.sair(), "Informação", JOptionPane.INFORMATION_MESSAGE);
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao sair.", "Erro", JOptionPane.ERROR_MESSAGE);
		}

		return 11;
	}

	public int optionValidation(String message, int op) {
		try {
			message.isEmpty();
			return Integer.parseInt(message);

		} catch (NullPointerException npe) {
			return 0;
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "São permitidos apenas números!", "Opção Inválida",
					JOptionPane.ERROR_MESSAGE);
			return op;
		}
	}

}