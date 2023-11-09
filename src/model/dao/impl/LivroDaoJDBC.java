package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.LivroDao;
import model.entities.Estado;
import model.entities.Genero;
import model.entities.Livro;

public class LivroDaoJDBC implements LivroDao {

	private Connection conn;
	
	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Livro obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO livro "
					+ "(Nome, Ano_Publi, Qtde_Paginas, Editora, Autor, Genero_id, Estado_id) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
						
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getAnoPubli());
			st.setInt(3, obj.getQtdePaginas());
			st.setString(4, obj.getEditora());
			st.setString(5, obj.getAutor());
			st.setInt(6, obj.getGenero().getId());
			st.setInt(7, obj.getEstado().getId());
			
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM livro WHERE Livro_id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}
		

	@Override
	public Livro findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT livro.*,genero.Genero_nome, estado.Estado_nome "
					+ "FROM genero INNER JOIN livro "
					+ "ON genero.Genero_id = livro.Genero_id "
					+ "INNER JOIN estado "
					+ "ON livro.Estado_id = estado.Estado_id "
					+ "WHERE livro.Livro_id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Genero gen = instantiateGenero(rs); 
				Estado est = instantiateEstado(rs);
				Livro obj = instantiateLivro(rs, gen, est);
				return obj;
				
			}
			return null;
		}
		catch (SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Genero instantiateGenero(ResultSet rs) throws SQLException {
		Genero gen = new Genero();
		gen.setId(rs.getInt("Genero_id"));
		gen.setNome(rs.getString("Genero_nome"));
		return gen;
	}
	
	private Estado instantiateEstado(ResultSet rs) throws SQLException {
		Estado est = new Estado();
		est.setId(rs.getInt("Estado_id"));
		est.setNome(rs.getString("Estado_nome"));
		return est;
	}

	private Livro instantiateLivro(ResultSet rs, Genero gen, Estado est) throws SQLException {
		Livro obj = new Livro();
		obj.setId(rs.getInt("Livro_id"));
		obj.setNome(rs.getString("Nome"));
		obj.setAnoPubli(rs.getInt("Ano_Publi"));
		obj.setQtdePaginas(rs.getInt("Qtde_Paginas"));
		obj.setEditora(rs.getString("Editora"));
		obj.setAutor(rs.getString("Autor"));
		obj.setGenero(gen);
		obj.setEstado(est);
		return obj;
	}
	
	@Override
	public List<Livro> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT livro.*,genero.Genero_nome, estado.Estado_nome "
					+ "FROM genero INNER JOIN livro "
					+ "ON genero.Genero_id = livro.Genero_id "
					+ "INNER JOIN estado "
					+ "ON livro.Estado_id = estado.Estado_id "
					+ "ORDER BY livro.Nome");
						
			rs = st.executeQuery();
			
			List<Livro> list = new ArrayList<>();
			
			Map<Integer, Genero> map = new HashMap<>();
			Map<Integer, Estado> map2 = new HashMap<>();
			
			while (rs.next()) {
				
				Genero gen = map.get(rs.getInt("Genero_id"));
				Estado est = map2.get(rs.getInt("Estado_id"));
				
				
				if(gen == null) {
					gen = instantiateGenero(rs);
					map.put(rs.getInt("Genero_id"), gen);
				}
				
				if(est == null) {
					est = instantiateEstado(rs);
					map2.put(rs.getInt("Estado_id"), est);
				}
				
				Livro obj = instantiateLivro(rs, gen, est);
				list.add(obj);
				
			}
			return list;
		}
		catch (SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
