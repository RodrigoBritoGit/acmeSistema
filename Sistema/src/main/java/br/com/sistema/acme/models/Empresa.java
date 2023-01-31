package br.com.sistema.acme.models;

import org.bson.types.ObjectId;

public class Empresa {

	private ObjectId id;

	private String nomeEmpresa;
	private String novoNome;
	private String cnpj;
	private String novoCnpj;
	private String status;
	private String novoStatus;
	private String local;
	private String novoLocal;

	public String getNovoNome() {
		return novoNome;
	}

	public void setNovoNome(String novoNome) {
		this.novoNome = novoNome;
	}

	public String getNovoCnpj() {
		return novoCnpj;
	}

	public void setNovoCnpj(String novoCnpj) {
		this.novoCnpj = novoCnpj;
	}

	public String getNovoStatus() {
		return novoStatus;
	}

	public void setNovoStatus(String novoStatus) {
		this.novoStatus = novoStatus;
	}

	public String getNovoLocal() {
		return novoLocal;
	}

	public void setNovoLocal(String novoLocal) {
		this.novoLocal = novoLocal;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Empresa criarId() {
		setId(new ObjectId());
		return this;
	}
}
