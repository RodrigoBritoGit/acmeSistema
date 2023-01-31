package br.com.sistema.acme.repositorys;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.sistema.acme.codecs.EmpresaCodec;
import br.com.sistema.acme.models.Empresa;

@Repository
public class EmpresaRepository {

	private MongoClient cliente;
	private MongoDatabase bancoDeDados;

	private void criarConexao() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		EmpresaCodec empresaCodec = new EmpresaCodec(codec);

		CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(empresaCodec));

		MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();

		this.cliente = new MongoClient("localhost:27017", opcoes);
		this.bancoDeDados = cliente.getDatabase("ACME");
	}

	public void salvar(Empresa empresa) {

		criarConexao();
		MongoDatabase bancoDeDados = cliente.getDatabase("ACME");
		MongoCollection<Empresa> cadastro = this.bancoDeDados.getCollection("cadastro", Empresa.class);
		cadastro.insertOne(empresa);
		cliente.close();
	}

	public List<Empresa> obterTodasEmpresas() {

		criarConexao();
		MongoCollection<Empresa> cadastro = this.bancoDeDados.getCollection("cadastro", Empresa.class);

		MongoCursor<Empresa> resultado = cadastro.find().iterator();

		List<Empresa> empresasEncontradas = new ArrayList<>();

		while (resultado.hasNext()) {
			Empresa empresa = resultado.next();
			empresasEncontradas.add(empresa);
		}
		cliente.close();

		return empresasEncontradas;
	}

	public Long findById(Long id) {
		return id;
	}

	public void deletar(Empresa empresa, String nomeEmpresa) {
		criarConexao();
		MongoDatabase bancoDeDados = cliente.getDatabase("ACME");
		MongoCollection<Empresa> cadastro = this.bancoDeDados.getCollection("cadastro", Empresa.class);

		cadastro.deleteOne(Filters.eq("nomeEmpresa", nomeEmpresa));
		System.out.println("Documento Apagado com sucesso");
		cliente.close();
	}

	public void editar(Empresa empresa, String nomeEmpresa, String novoNome,String cnpj,String novoCnpj,String status,
			String novoStatus,String local,String novoLocal) {
		criarConexao();
		MongoDatabase bancoDeDados = cliente.getDatabase("ACME");
		MongoCollection<Empresa> cadastro = this.bancoDeDados.getCollection("cadastro", Empresa.class);

		cadastro.updateOne(Filters.eq("nomeEmpresa", nomeEmpresa),
				new Document("$set", new Document("nomeEmpresa", novoNome)));
		
		cadastro.updateOne(Filters.eq("cnpj", cnpj),
				new Document("$set", new Document("cnpj", novoCnpj)));
		
		cadastro.updateOne(Filters.eq("status", status),
				new Document("$set", new Document("status", novoStatus)));
		
		cadastro.updateOne(Filters.eq("local", local),
				new Document("$set", new Document("local", novoLocal)));

       System.out.println("Documento atualizado com sucesso");
		cliente.close();
	}
}
