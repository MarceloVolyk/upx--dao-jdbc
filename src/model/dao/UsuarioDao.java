package model.dao;

import java.util.List;

import model.entities.Usuario;

public interface UsuarioDao {

	void insert(Usuario obj);
	void update(Usuario obj);
	Usuario findById(Integer Id);
	List<Usuario> findAll();
}
