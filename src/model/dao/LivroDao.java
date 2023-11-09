package model.dao;

import java.util.List;

import model.entities.Livro;

public interface LivroDao {

	void insert(Livro obj);
	void deleteById(Integer id);
	Livro findById(Integer id);
	List<Livro> findAll();
}
