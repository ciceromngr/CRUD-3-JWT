package com.example.demo.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Tarefas;
import com.example.demo.repository.TarefasRepository;
import com.example.demo.repository.impl.TarefaImpl;
import com.example.demo.repository.impl.TarefasDao;

@Service
public class TarefasService {

	@Autowired
	private TarefasRepository tarefasRepository;
	
	@Autowired
	private TarefasDao tarefaDao;
	
	@Autowired
	private UserAuthenticationService userAuthenticationService;
	
	
	public List<Tarefas> pegarTodasTarefas(){
		return tarefasRepository.findAll();
	}
	
	public Tarefas pegarTarefaPorTitulo(String titulo) throws Exception {
		Tarefas tarefa = tarefasRepository.findByTituloTarefa(titulo);
		System.out.println(tarefa);
		return tarefa;
	}
	

	public void adicionarTarefa(Tarefas tarefa, Long id) throws Exception{
		
		if(tarefa.getTituloTarefa() != null && tarefa.getNomeTarefa() != null){
			tarefaDao.insert(tarefa, id);
		} else {
			throw new Exception("Titulo ou Nome da Tarefa estão");
		}
	}
	
	public Tarefas atualizar(Tarefas tarefa) throws Exception{
		return tarefasRepository.save(tarefa);
	}
	
	public void deletar(Long id) {
		tarefasRepository.deleteById(id);
	}
}
