package com.cadastro.springboot.cadastro.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cadastro.springboot.cadastro.models.Agendamento;
import com.cadastro.springboot.cadastro.models.Veiculo;
import com.cadastro.springboot.cadastro.repository.AgendamentoRepository;
import com.cadastro.springboot.cadastro.repository.VeiculoRepository;

@Controller
public class AgendamentoController {
	
	@RequestMapping("/cadastrarAgendamento")
	public String form() {
		return "agendamento/formAgendamento";	
	}

	@Autowired
	private AgendamentoRepository ar;
	
	@Autowired
	private VeiculoRepository vr;
	
	
	@RequestMapping(value="/cadastrarAgendamento", method=RequestMethod.POST)
	public String form(@Valid Agendamento agendamento, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarAgendamento";
		}
		
		ar.save(agendamento);
		attributes.addFlashAttribute("mensagem", "Agendamento cadastrado com sucesso!");
		return "redirect:/cadastrarAgendamento";
	}
	
	@RequestMapping("/agendamento")
	public ModelAndView listaAgendamento(){
		ModelAndView mv = new ModelAndView("listaAgendamento");
		Iterable<Agendamento> agendamento = ar.findAll();
		mv.addObject("agendamento", agendamento);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesAgendamento(@PathVariable("codigo") long codigo){
		Agendamento agendamento = ar.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("agendamento/detalhesAgendamento");
		mv.addObject("agendamento", agendamento);
		
		Iterable<Veiculo> veiculo = vr.findByAgendamento(agendamento);
		mv.addObject("veiculos", veiculo);
		
		return mv;
	}
	
	@RequestMapping("/deletarAgendamento")
	public String deletarEvento(long codigo){
		Agendamento agendamento = ar.findByCodigo(codigo);
		ar.delete(agendamento);
		return "redirect:/agendamento";
	}
	
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesAgendamentoPost(@PathVariable("codigo") long codigo, @Valid Veiculo veiculo,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Agendamento agendamento = ar.findByCodigo(codigo);
		veiculo.setAgendamento(agendamento);
		vr.save(veiculo);
		attributes.addFlashAttribute("mensagem", "Veiculo adicionado com sucesso!");
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarVeiculo")
	public String deletarConvidado(long idVeiculo){
		Veiculo veiculo = vr.findByVeiculo(idVeiculo);
		vr.delete(veiculo);
		
		Agendamento agendamento = veiculo.getAgendamento();
		long codigoLong = agendamento.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
	
	
	
}
