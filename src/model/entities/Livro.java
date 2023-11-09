package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private int anoPubli;
	private int qtdePaginas;
	private String editora;
	private String autor;
	
	private Genero genero;
	private Estado estado;
	
	public Livro() {
		
	}

	public Livro(Integer id, String nome, int anoPubli, int qtdePaginas, String editora, String autor, Genero genero,
			Estado estado) {
		this.id = id;
		this.nome = nome;
		this.anoPubli = anoPubli;
		this.qtdePaginas = qtdePaginas;
		this.editora = editora;
		this.autor = autor;
		this.genero = genero;
		this.estado = estado;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnoPubli() {
		return anoPubli;
	}

	public void setAnoPubli(int anoPubli) {
		this.anoPubli = anoPubli;
	}

	public int getQtdePaginas() {
		return qtdePaginas;
	}

	public void setQtdePaginas(int qtdePaginas) {
		this.qtdePaginas = qtdePaginas;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Livro [ID: " + id + ", Nome: " + nome + ", Publicado: " + anoPubli + ", Páginas: " + qtdePaginas
				+ ", Editora: " + editora + ", Autor: " + autor + ", Gênero: " + genero + ", Estado: " + estado + "]";
	}

}
