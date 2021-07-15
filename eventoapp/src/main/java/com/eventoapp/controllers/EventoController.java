package com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.models.Evento;
import com.eventoapp.repositories.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;
	
	//QUANDO FOR REQUISITADO "/cadastrarEvento" SERÁ REDIRECIONADO PARA O ARQUIVO "evento/formEvento"
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	//QUANDO FOR ENVIADO UMA REQUISIÇÃO, SERÁ CAPTURADO OS DADOS E ATRIBUIDOS AOS CAMPOS DO OBJETO E SALVO NO BANCO. 
	//PARA FINALIZAR A TELA RESÁ REINICIADA COM O "redirect:/cadastrarEvento".
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String form(Evento evento) {
		
		if(evento.getNome() != null) {
			System.out.println("Prencher todos os campos");
		}else {
			eventoRepository.save(evento);
		}
		
		
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping(value = "/eventos")// /eventos => AQUI ELE PEGA O OBJETO ESPECIFICADO NO ARQUIVO INDEX.HTML => ${eventos}
	public ModelAndView listaEventos() {
		
		ModelAndView modelAndView = new ModelAndView("index");// ModelAndView("index") =>  AQUI EU CONFIRMO EM QUAL ARQUIVO ELE VAI FAZER A BUSCA DA ESPECIFICAÇÃO DO OBJETO
		Iterable<Evento> eventos = eventoRepository.findAll();// eventoRepository.findAll() => MÉDOTO QUE BUSCA TODOS OS RESULTADOS DO BANCO
		modelAndView.addObject("eventos", eventos);// addObject("eventos", eventos) => AQUI PEGA CADA OBJETO E ENVIAR PARA O "eventos" ESPECIFICADO NO 'index.html' ${eventos}
		
		return modelAndView;
	}
}