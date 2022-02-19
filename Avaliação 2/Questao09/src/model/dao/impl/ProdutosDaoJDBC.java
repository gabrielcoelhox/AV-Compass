package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ProdutosDao;
import model.entities.Produtos;

public class ProdutosDaoJDBC implements ProdutosDao {

	private Connection conn;

	public ProdutosDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// UPDATE
	@Override
	public void update(Produtos obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(                      // Atualizar o produto
					"UPDATE produtos "
					+ "SET Nome = ?, descricao = ?, valor = ?, desconto = ?, DataInicio = ? "
					+ "WHERE Id = ?");

			pst.setString(1, obj.getNome());
			pst.setString(2, obj.getDescricao());
			pst.setDouble(3, obj.getValor());
			pst.setDouble(4, obj.getDesconto());
			pst.setDate(5, Date.valueOf(obj.getDataInicio()));
			pst.setInt(6, obj.getId());

			pst.executeUpdate();

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(pst);
		}
	}

	// DELETE BY ID
	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("DELETE FROM produtos WHERE Id = ?");

			pst.setInt(1, id);

			pst.executeUpdate();

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(pst);
		}
	}

	// FIND BY ID
	@Override
	public Produtos findById(Integer id) throws NullPointerException{
		PreparedStatement pst = null;
		ResultSet result = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM produtos WHERE id = ?");

			pst.setInt(1, id);
			result = pst.executeQuery();

			if (result.next()) { // Se a consulta não retornou nenhum id retornará nulo
				Produtos obj = new Produtos();
				obj.setId(result.getInt("Id")); // Pega o ID
				obj.setNome(result.getString("Nome")); // Pega o Nome
				obj.setDescricao(result.getString("descricao"));
				obj.setValor(result.getDouble("valor"));
				obj.setDesconto(result.getDouble("desconto"));
				obj.setDataInicio(result.getDate("DataInicio").toLocalDate());
				return obj;
			}
			return null;

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(pst);
			DB.fechaResultSet(result);
		}

	}

	// FIND ALL
	@Override
	public List<Produtos> findAll() {
		PreparedStatement pst = null;
		ResultSet result = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM produtos ORDER BY Id");

			result = pst.executeQuery();

			List<Produtos> list = new ArrayList<>();

			while (result.next()) {  // Se a consulta não retornou nenhum id retornará nulo
				Produtos obj = new Produtos();
				obj.setId(result.getInt("Id"));
				obj.setNome(result.getString("Nome"));
				obj.setDescricao(result.getString("descricao"));
				obj.setValor(result.getDouble("valor"));
				obj.setDesconto(result.getDouble("desconto"));
				list.add(obj);
			}
			return list;

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(pst);
			DB.fechaResultSet(result);
		}
	}
	
	// FIND BY NAME
	@Override
	public List<Produtos> findByName(String nome) {
		PreparedStatement pst = null;
		ResultSet result = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM produtos WHERE Nome like ?");
			pst.setString(1, "%" + nome + "%");
			
			result = pst.executeQuery();

			List<Produtos> list = new ArrayList<>();

			while (result.next()) {  // Se a consulta não retornou nenhum id retornará nulo
				Produtos obj = new Produtos();
				obj.setId(result.getInt("Id"));
				obj.setNome(result.getString("Nome"));
				obj.setDescricao(result.getString("descricao"));
				obj.setValor(result.getDouble("valor"));
				obj.setDesconto(result.getDouble("desconto"));
				obj.setDataInicio(result.getDate("DataInicio").toLocalDate());
				list.add(obj);
			}
			return list;

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(pst);
			DB.fechaResultSet(result);
		}
	}

	// INSERT
	@Override
	public void insert(Produtos obj) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement(
					"INSERT INTO produtos "             // Inserir na tabela produtos
					+ "(Nome, descricao, valor, desconto, DataInicio)" 
					+ "VALUES " 
					+ "(?, ?, ?, ?, ?)",                   // Placeholder
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, obj.getNome());
			pst.setString(2, obj.getDescricao());
			pst.setDouble(3, obj.getValor());
			pst.setDouble(4, obj.getDesconto());
			pst.setDate(5, Date.valueOf(obj.getDataInicio()));

			int linhasAlteradas = pst.executeUpdate();

			if (linhasAlteradas > 0) {
				ResultSet result = pst.getGeneratedKeys();
				if (result.next()) {   // Se existir o id selecionado
					int id = result.getInt(1);
					obj.setId(id);
				}
				DB.fechaResultSet(result);
			} else { // Caso nenhuma linha tenha sido alterada
				throw new DbException("Erro inesperado. Nenhum dado alterado!");
			}
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.fechaStatement(pst);
		}
	}
}
