package converter;

import java.util.List;
import java.util.stream.Collectors;

import dto.PostagemDto;
import model.entity.Postagem;

public class PostagemConverter {
	
	private UsuarioConverter usuarioConverter = new UsuarioConverter();
	
	public PostagemConverter() {
		usuarioConverter = new UsuarioConverter();
	}

	public PostagemDto entityToDto(Postagem postagem) {
		PostagemDto postagemDto = new PostagemDto(
				postagem.getId(), 
				usuarioConverter.entityToDto(postagem.getCriador()),
				postagem.getTitulo(),
				postagem.getMensagem(),
				postagem.getCriacao()
		);
		return postagemDto;
	}

	public List<PostagemDto> entityToDto(List<Postagem> postagens) {
		return postagens.stream().map(u -> entityToDto(u)).collect(Collectors.toList());
	}

	public Postagem dtoToEntity(PostagemDto postagemDto) {
		Postagem postagem = new Postagem(
				postagemDto.getId(), 
				usuarioConverter.dtoToEntity(postagemDto.getCriador()), 
				postagemDto.getTitulo(),
				postagemDto.getMensagem(), 
				postagemDto.getCriacao()
			);
		return postagem;
	}

	public List<Postagem> dtoToEntity(List<PostagemDto> postagensDto) {
		return postagensDto.stream().map(u -> dtoToEntity(u)).collect(Collectors.toList());
	}

}
