package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import model.dao.DaoFactory;
import model.dao.LivroDao;
import model.dao.UsuarioDao;
import model.entities.Estado;
import model.entities.Genero;
import model.entities.Livro;
import model.entities.Usuario;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String[] itens = { "Cadastrar Usuário", "Cadastrar Livro", "Sair" };
		String selecao = (String) JOptionPane.showInputDialog(null, "Escolha um item", "Cadastro",
				JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);

		while (selecao != "Sair") {

			if (selecao.equals("Cadastrar Usuário")) {
				UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

				String[] opUsuario = { "Inserir Usuário", "Atualizar Usuário", "Selecionar Usuário",
						"Listar Usuários Cadastrados", "Voltar" };
				String escolhaUsuario = (String) JOptionPane.showInputDialog(null, "Escolha um item",
						"Cadastro de Usuário", JOptionPane.INFORMATION_MESSAGE, null, opUsuario, opUsuario[0]);

				while (escolhaUsuario != "Voltar") {
					switch (escolhaUsuario) {

					case "Inserir Usuário":
						System.out.println("\n=== TESTE 1: inserir usuário ===");
						Usuario usuario = new Usuario(null, "Gui", "996666623", "gui@gmail.com", "1666623", 54321);
						usuarioDao.insert(usuario);
						System.out.println("Inserted! New Id = " + usuario.getId());
						break;

					case "Atualizar Usuário":
						System.out.println("\n=== TESTE 2: update usuário ===");
						usuario = usuarioDao.findById(4);
						usuario.setSenha(22222);
						usuarioDao.update(usuario);
						System.out.println("Update completed!");
						break;

					case "Selecionar Usuário":
						System.out.println("=== TESTE 3: achar usuário por ID ===");
						Usuario newUsuario = usuarioDao.findById(1);
						System.out.println(newUsuario);
						break;

					case "Listar Usuários Cadastrados":
						System.out.println("\n=== TESTE 4: retornar todos os usuários ===");
						List<Usuario> list = new ArrayList<>();
						list = usuarioDao.findAll();
						for (Usuario obj : list) {
							System.out.println(obj);
						}
					}
					escolhaUsuario = (String) JOptionPane.showInputDialog(null, "Escolha um item",
							"Cadastro de Usuário", JOptionPane.INFORMATION_MESSAGE, null, opUsuario, opUsuario[0]);
				}
			} else {
				LivroDao livroDao = DaoFactory.createLivroDao();

				String[] opLivro = { "Inserir Livro", "Deletar Livro", "Selecionar Livro", "Listar Livros Cadastrados",
						"Voltar" };
				String escolhaLivro = (String) JOptionPane.showInputDialog(null, "Escolha um item", "Cadastro de Livro",
						JOptionPane.INFORMATION_MESSAGE, null, opLivro, opLivro[0]);

				while (escolhaLivro != "Voltar") {
					switch (escolhaLivro) {

					case "Inserir Livro":
						System.out.println("\n=== TESTE 1: inserir livro ===");
						Livro livro = new Livro(null, "Harry Potter", 1999, 300, "Rocco", "J. K. Rowling",
								new Genero(2, null), new Estado(1, null));
						livroDao.insert(livro);
						System.out.println("Inserted! New Id = " + livro.getId());
						break;

					case "Deletar Livro":
						System.out.println("\n=== TESTE 2: deletar livro ===");
						System.out.println("Enter id for delete test: ");
						int id = sc.nextInt();
						livroDao.deleteById(id);
						System.out.println("Delete completed!");
						break;

					case "Selecionar Livro":
						System.out.println("=== TESTE 3: achar livro por ID ===");
						Livro newLivro = livroDao.findById(6);
						System.out.println(newLivro);
						break;

					case "Listar Livros Cadastrados":
						System.out.println("\n=== TESTE 4: retornar todos os usuários ===");
						List<Livro> list = new ArrayList<>();
						list = livroDao.findAll();
						for (Livro obj : list) {
							System.out.println(obj);
						}
					}
					escolhaLivro = (String) JOptionPane.showInputDialog(null, "Escolha um item", "Cadastro de Livro",
							JOptionPane.INFORMATION_MESSAGE, null, opLivro, opLivro[0]);
				}
			}
			selecao = (String) JOptionPane.showInputDialog(null, "Escolha um item", "Cadastro",
					JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);
		}
		sc.close();
	}
}
