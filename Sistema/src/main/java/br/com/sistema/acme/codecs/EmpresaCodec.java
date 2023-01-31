package br.com.sistema.acme.codecs;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import br.com.sistema.acme.models.Empresa;

public class EmpresaCodec implements CollectibleCodec<Empresa>{
	
	private Codec<Document> codec;
	
	public EmpresaCodec (Codec<Document> codec) {
		this.codec = codec;	
	}

	@Override
	public void encode(BsonWriter writer, Empresa empresa, EncoderContext encoder) {
		Object id = empresa.getId();
		String nomeEmpresa = empresa.getNomeEmpresa();
		String cnpj = empresa.getCnpj();
		String status = empresa.getStatus();
		String local = empresa.getLocal();
		
		Document document = new Document();
		
		document.put("_id", id);
		document.put("nomeEmpresa", nomeEmpresa);
		document.put("cnpj", cnpj);
		document.put("status", status);
		document.put("local", local);
		
		codec.encode(writer, document, encoder);
		
	}

	@Override
	public Class<Empresa> getEncoderClass() {
		return Empresa.class;
	}

	@Override
	public Empresa decode(BsonReader reader, DecoderContext decoder) {
		Document document = codec.decode(reader, decoder);
		Empresa empresa = new Empresa();
		
		empresa.setId(document.getObjectId("_id"));
		empresa.setNomeEmpresa(document.getString("nomeEmpresa"));
		empresa.setCnpj(document.getString("cnpj"));
		empresa.setStatus(document.getString("status"));
		empresa.setLocal(document.getString("local"));
		
		return empresa;
	}

	@Override
	public Empresa generateIdIfAbsentFromDocument(Empresa empresa) {
		return documentHasId(empresa) ? empresa.criarId() : empresa;
	}

	@Override
	public boolean documentHasId(Empresa empresa) {	
		return empresa.getId() == null;
	}

	@Override
	public BsonValue getDocumentId(Empresa empresa) {
		if(!documentHasId(empresa)) {
			throw new IllegalStateException("Esse documento não tem um id");
		}
		return new BsonString(empresa.getId().toHexString());
	}
	
}
	