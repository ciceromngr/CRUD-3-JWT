package com.example.demo.repository.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Tarefas;


@Repository
public class TarefaImpl extends JdbcDaoSupport implements TarefasDao{

	@Autowired
	DataSource dataSource;
	
	
	@PostConstruct
	private void initializer() {
		setDataSource(dataSource);
	}
	
	@Override
	public void insert(Tarefas tarefa, Long id) {
		String sql = "INSERT INTO tarefas_tb (completada, descricao_tarefa, nome_tarefa, titulo_tarefa, id_usuario)\r\n"
		 		+ "VALUES (?,?,?,?,?)";
		getJdbcTemplate().update(sql, 
				tarefa.getCompletada(),
				tarefa.getDescricaoTarefa(),
				tarefa.getNomeTarefa(),
				tarefa.getTituloTarefa(),
				id
				);
	}
}
