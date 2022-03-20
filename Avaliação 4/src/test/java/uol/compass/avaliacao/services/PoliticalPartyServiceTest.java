package uol.compass.avaliacao.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import uol.compass.avaliacao.builder.AssociateBuilder;
import uol.compass.avaliacao.builder.PoliticalPartyBuilder;
import uol.compass.avaliacao.config.dto.PoliticalPartyDTO;
import uol.compass.avaliacao.config.dto.PoliticalPartyFormDTO;
import uol.compass.avaliacao.config.dto.PoliticalPartyWithAssociatesDTO;
import uol.compass.avaliacao.config.entities.Associate;
import uol.compass.avaliacao.config.entities.PoliticalParty;
import uol.compass.avaliacao.config.enums.Ideology;
import uol.compass.avaliacao.config.exceptions.DefaultException;
import uol.compass.avaliacao.config.repositories.PoliticalPartyRepository;
import uol.compass.avaliacao.config.services.PoliticalPartyServiceImplementacao;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PoliticalPartyServiceTest {

	@Autowired
	private PoliticalPartyServiceImplementacao politicalService;

	@MockBean
	private PoliticalPartyRepository repository;

	@Test
	public void deveriaBuscarUmaListaComTodosOsPartidos() {
		PoliticalParty partido = PoliticalPartyBuilder.getPoliticalParty();
		List<PoliticalParty> list = Arrays.asList(partido);

		when(this.repository.findAll()).thenReturn(list);

		List<PoliticalPartyDTO> resultList = this.politicalService.findAll();

		assertThat(resultList.size()).isGreaterThan(0);
		assertThat(resultList.get(0).getName()).isEqualTo(partido.getName());
	}

	@Test
	public void deveriaBuscarUmaListaComOsPartidosDeMesmaIdeologia() {
		PoliticalParty partido = PoliticalPartyBuilder.getPoliticalParty();
		List<PoliticalParty> list = Arrays.asList(partido);

		when(this.repository.filterIdeology(Ideology.Centro)).thenReturn(list);

		List<PoliticalPartyDTO> resultList = this.politicalService.findByIdeology("Centro");

		assertThat(resultList.size()).isGreaterThan(0);
		assertThat(resultList.get(0).getName()).isEqualTo(partido.getName());
	}

	@Test
	public void deveriaBuscarUmPartidoPeloId() {
		PoliticalParty partido = PoliticalPartyBuilder.getPoliticalParty();

		when(this.repository.findById(anyLong())).thenReturn(Optional.of(partido));

		PoliticalPartyDTO partyDto = this.politicalService.findById(1L);

		assertThat(partyDto.getId()).isNotNull();
		assertThat(partyDto.getName()).isEqualTo(partido.getName());
		assertThat(partyDto.getAcronym()).isEqualTo(partido.getAcronym());
		assertThat(partyDto.getIdeology()).isEqualTo(partido.getIdeology());
		assertThat(partyDto.getFoundationDate()).isEqualTo(partido.getFoundationDate());
	}

	@Test
	public void deveriaBuscarUmPartidoESeusAssociadosPeloId() {
		PoliticalParty partido = PoliticalPartyBuilder.getPoliticalParty();
		partido.setAssociates(Arrays.asList(AssociateBuilder.getAssociate()));

		when(this.repository.findById(anyLong())).thenReturn(Optional.of(partido));

		PoliticalPartyWithAssociatesDTO partyDto = this.politicalService.findByIdWithAssociates(1L);

		assertThat(partyDto.getId()).isNotNull();
		assertThat(partyDto.getName()).isEqualTo(partido.getName());
		assertThat(partyDto.getAcronym()).isEqualTo(partido.getAcronym());
		assertThat(partyDto.getIdeology()).isEqualTo(partido.getIdeology());
		assertThat(partyDto.getFoundationDate()).isEqualTo(partido.getFoundationDate());
		assertThat(partyDto.getAssociates()).isEqualTo(partido.getAssociates());
	}

	@Test
	public void deveriaInserirPartido() {
		PoliticalParty partido = PoliticalPartyBuilder.getPoliticalParty();

		when(this.repository.save(any(PoliticalParty.class))).thenReturn(partido);

		PoliticalPartyDTO partyDto = this.politicalService.insert(PoliticalPartyBuilder.getPoliticalPartyFormDTO());

		assertThat(partyDto.getId()).isNotNull();
		assertThat(partyDto.getName()).isEqualTo(partido.getName());
		assertThat(partyDto.getAcronym()).isEqualTo(partido.getAcronym());
		assertThat(partyDto.getIdeology()).isEqualTo(partido.getIdeology());
		assertThat(partyDto.getFoundationDate()).isEqualTo(partido.getFoundationDate());
	}

	@Test
	public void deveriaAtualizarUmPartido() {
		PoliticalParty partido = PoliticalPartyBuilder.getPoliticalParty();
		PoliticalPartyFormDTO partyFormDto = PoliticalPartyBuilder.getPoliticalPartyFormDTO();
		partyFormDto.setName("PODEMOS");
		partyFormDto.setIdeology(Ideology.Direita);

		when(this.repository.findById(anyLong())).thenReturn(Optional.of(partido));
		when(this.repository.save(any(PoliticalParty.class))).thenReturn(partido);
		
		PoliticalPartyDTO partyDto = this.politicalService.update(partido.getId(), partyFormDto);
		
		assertThat(partyDto.getId()).isNotNull();
		assertThat(partyDto.getName()).isEqualTo(partido.getName());
		assertThat(partyDto.getAcronym()).isEqualTo(partido.getAcronym());
		assertThat(partyDto.getIdeology()).isEqualTo(partido.getIdeology());
		assertThat(partyDto.getFoundationDate()).isEqualTo(partido.getFoundationDate());
	}
	
	@Test
	public void naoDeveriaAtualizarPqNaoFoiEncontradoPartidoComId() {
		when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
		
		assertThatExceptionOfType(DefaultException.class)
		.isThrownBy(() -> this.politicalService.update(1L, PoliticalPartyBuilder.getPoliticalPartyFormDTO()));
	}
	
	@Test
	public void deveriaDeletarUmPartidoPeloIdInformado() {
		PoliticalParty partido = PoliticalPartyBuilder.getPoliticalParty();
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(partido));
		
		this.politicalService.deleteById(1L);
		
		verify(this.repository, times(1)).delete(partido);
	}
	
	@Test
	public void naoDeveriaDeletarPqAindaExistemAssociados() {
		PoliticalParty partido = PoliticalPartyBuilder.getPoliticalParty();
		Associate associate = AssociateBuilder.getAssociate();
		associate.setPoliticalParty(partido);
		partido.setAssociates(Arrays.asList(associate));
		
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(partido));
		doThrow(new DefaultException()).when(this.repository).delete(any(PoliticalParty.class));
		
		assertThatExceptionOfType(DefaultException.class)
		.isThrownBy(() -> this.politicalService.deleteById(1L));
	}
	
	@Test
	public void naoDeveriaDeletarPqNaoFoiEncontradoPartido() {
		when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
		
		assertThatExceptionOfType(DefaultException.class)
		.isThrownBy(() -> this.politicalService.deleteById(1L));
	}

}
