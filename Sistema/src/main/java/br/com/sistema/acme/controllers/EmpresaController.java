package br.com.sistema.acme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.sistema.acme.models.Empresa;
import br.com.sistema.acme.repositorys.EmpresaRepository;

@Controller
public class EmpresaController {

	@Autowired
	private EmpresaRepository repository;

	@GetMapping("/templates/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "cadastrar";
	}

	@PostMapping("/empresa/salvar")
	public String salvar(@ModelAttribute Empresa empresa) {
		System.out.println("Primeira empresa cadastrada: " + empresa);
		repository.salvar(empresa);
		return "redirect:/templates/listar";
	}

	@GetMapping("/templates/listar")
	public String listar(Model model) {
		List<Empresa> empresas = repository.obterTodasEmpresas();
		model.addAttribute("empresas", empresas);
		return "listar";
	}

	@GetMapping("/empresa/apagar/{id}")
	public String deleteEmpresa(@ModelAttribute Empresa empresa) {
		System.out.println("Entrei na controller apagar");
		return "apagar";
	}

	@GetMapping("/empresa/editar/{id}")
	public String editarEmpresa(@ModelAttribute Empresa empresa) {
		System.out.println("Entrei na controller editar");
		return "editar";
	}

	@PostMapping("/empresa/atualizar/nomeEmpresa/novoNome/cnpj/novoCnpj/status/novoStatus/local/novoLocal")
	public String atualizarDados(@ModelAttribute Empresa empresa, String nomeEmpresa,
			String novoNome,String cnpj,String novoCnpj,String status,
			String novoStatus,String local,String novoLocal) {
		
		repository.editar(empresa,nomeEmpresa,novoNome,cnpj,novoCnpj,status,novoStatus,local,novoLocal);
		System.out.println("Entrei na controller atualizar dados");
		return "redirect:/templates/listar";
	}

	@PostMapping("/empresa/apagar/nomeEmpresa")
	public String apagarDados(@ModelAttribute Empresa empresa, String nomeEmpresa) {
		repository.deletar(empresa,nomeEmpresa);

		System.out.println("Entrei na controller apagar dados");
		return "redirect:/templates/listar";
	}
}
