package model.dao;

import java.util.List;

import model.entities.Produtos;

public interface ProdutosDao {

	void insert(Produtos obj);         // Inserir no BD o obj que for passado como parâmetro
	void update(Produtos obj);
	void deleteById(Integer id);
	Produtos findById(Integer id);     // Consultar no BD um obj com esse id
	List<Produtos> findByName(String nome);
	List<Produtos> findAll();
}
