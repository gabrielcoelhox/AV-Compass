package uol.compass.avaliacao.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import uol.compass.avaliacao.builder.AssociateBuilder;
import uol.compass.avaliacao.builder.PoliticalPartyBuilder;
import uol.compass.avaliacao.config.dto.AssociateDTO;
import uol.compass.avaliacao.config.dto.AssociateFormDTO;
import uol.compass.avaliacao.config.dto.AssociatePoliticalPartyFormDTO;
import uol.compass.avaliacao.config.entities.Associate;
import uol.compass.avaliacao.config.entities.PoliticalParty;
import uol.compass.avaliacao.config.enums.Ideology;
import uol.compass.avaliacao.config.enums.PoliticalOffice;
import uol.compass.avaliacao.config.exceptions.DefaultException;
import uol.compass.avaliacao.config.repositories.AssociateRepository;
import uol.compass.avaliacao.config.repositories.PoliticalPartyRepository;
import uol.compass.avaliacao.config.services.AssociateServiceImplementacao;

@SpringBootTest
public class AssociateServiceTest {

	@Autowired
	private AssociateServiceImplementacao associateImplementacaoservice;

	@MockBean
	private AssociateRepository repository;

	@MockBean
	PoliticalPartyRepository partyRepository;

	@Test
	public void deveriaInserirUmAssociado() {
		Associate ney = AssociateBuilder.getAssociate();

		when(this.partyRepository.findById(anyLong()))
				.thenReturn(Optional.of(PoliticalPartyBuilder.getPoliticalParty()));
		when(this.repository.save(any(Associate.class))).thenReturn(ney);

		AssociateDTO neyDto = this.associateImplementacaoservice.insert(AssociateBuilder.getAssociateFormDTO());

		assertThat(neyDto.getId()).isNotNull();
		assertThat(neyDto.getName()).isEqualTo(ney.getName());
		assertThat(neyDto.getPoliticalOffice()).isEqualTo(ney.getCargo());
		assertThat(neyDto.getBirthDate()).isEqualTo(ney.getBirthDate());
		assertThat(neyDto.getSex()).isEqualTo(ney.getSex());
		assertThat(neyDto.getPoliticalOffice()).isEqualTo(ney.getCargo());
	}

	@Test
	public void deveriaAtualizarUmAssociado() {
		Associate ney = AssociateBuilder.getAssociate();
		AssociateFormDTO associateFormDto = AssociateBuilder.getAssociateFormDTO();
		associateFormDto.setName("Neymar");

		when(this.partyRepository.findById(anyLong()))
				.thenReturn(Optional.of(PoliticalPartyBuilder.getPoliticalParty()));
		when(this.repository.findById(anyLong())).thenReturn(Optional.of(ney));
		when(this.repository.save(any(Associate.class))).thenReturn(ney);

		AssociateDTO neyDto = this.associateImplementacaoservice.update(ney.getId(), associateFormDto);

		assertThat(neyDto.getId()).isNotNull();
		assertThat(neyDto.getName()).isEqualTo(ney.getName());
		assertThat(neyDto.getPoliticalOffice()).isEqualTo(ney.getCargo());
		assertThat(neyDto.getBirthDate()).isEqualTo(ney.getBirthDate());
		assertThat(neyDto.getSex()).isEqualTo(ney.getSex());
		assertThat(neyDto.getPoliticalParty()).isEqualTo(ney.getPoliticalParty());
	}

	@Test
	public void naoDeveriaAtualizarPoisNaoFoiEncontradoAssociadoComEsteId() {
		when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

		assertThatExceptionOfType(DefaultException.class)
				.isThrownBy(() -> this.associateImplementacaoservice.update(3L, AssociateBuilder.getAssociateFormDTO()));
	}

	@Test
	public void deveriaAtualiazarOPartidoDeUmAssociado() {
		Associate ney = AssociateBuilder.getAssociate();
		AssociatePoliticalPartyFormDTO neyPartyForm = AssociateBuilder.getAssociatePoliticalPartyFormDTO();
		
		PoliticalParty neyModified = PoliticalPartyBuilder.getPoliticalParty();
		neyModified.setId(2L);
		neyModified.setName("PCdoB");
		neyModified.setIdeology(Ideology.Esquerda);

		when(this.partyRepository.findById(2L)).thenReturn(Optional.of(neyModified));
		when(this.repository.findById(3L)).thenReturn(Optional.of(ney));
		when(this.repository.save(any(Associate.class))).thenReturn(ney);

		AssociateDTO associateDto = this.associateImplementacaoservice.updatePoliticalParty(neyPartyForm);

		assertThat(associateDto.getPoliticalParty()).isEqualTo(neyModified);
	}

	@Test
	public void devariaDeletarUmAssociadoPeloId() {
		Associate ney = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyLong())).thenReturn(Optional.of(ney));

		this.associateImplementacaoservice.deleteById(ney.getId());

		verify(this.repository, times(1)).delete(ney);
	}

	@Test
	public void naoDeveriaDeletarPoisNaoFoiEncontratoAssociadoComEsteId() {
		Associate ney = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

		assertThatExceptionOfType(DefaultException.class).isThrownBy(() -> this.associateImplementacaoservice.deleteById(ney.getId()));
	}

	@Test
	public void deveriaDesassociarOAssociadoDeUmPartido() {
		Associate ney = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyLong())).thenReturn(Optional.of(ney));
		when(this.partyRepository.findById(anyLong()))
				.thenReturn(Optional.of(PoliticalPartyBuilder.getPoliticalParty()));

		this.associateImplementacaoservice.deleteAssociateFromParty(3L, 3L);

		assertThat(ney.getPoliticalParty()).isEqualTo(null);
	}

	@Test
	public void naoDeveriaDesasociarOAssociadoDeUmPartidoPoisEleNaoEstaAssociadoAoPartidoInformado() {
		Associate ney = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyLong())).thenReturn(Optional.of(ney));

		assertThatExceptionOfType(DefaultException.class)
				.isThrownBy(() -> this.associateImplementacaoservice.deleteAssociateFromParty(3L, 2L));
	}

	@Test
	public void deveriaBuscarUmaListaComTodosOsAssociados() {
		Associate ney = AssociateBuilder.getAssociate();
		List<Associate> neylist = Arrays.asList(ney);

		when(this.repository.findAll()).thenReturn(neylist);

		List<AssociateDTO> resultList = this.associateImplementacaoservice.findAll(null);

		assertThat(resultList.size()).isGreaterThan(0);
		assertThat(resultList.get(0).getName()).isEqualTo(ney.getName());
	}

	@Test
	public void deveriaBuscarUmaListaComOsAssociadosDeMesmoCargoPolitico() {
		Associate ney = AssociateBuilder.getAssociate();
		List<Associate> neylist = Arrays.asList(ney);

		when(this.repository.filterCargo(PoliticalOffice.Presidente)).thenReturn(neylist);

		List<AssociateDTO> resultList = this.associateImplementacaoservice.findAll("Presidente");

		assertThat(resultList.size()).isGreaterThan(0);
		assertThat(resultList.get(0).getName()).isEqualTo(ney.getName());
	}

	@Test
	public void deveriaBuscarUmAssociadoPeloId() {
		Associate ney = AssociateBuilder.getAssociate();

		when(this.repository.findById(anyLong())).thenReturn(Optional.of(ney));

		AssociateDTO neyDto = this.associateImplementacaoservice.findById(3L);

		assertThat(neyDto.getId()).isNotNull();
		assertThat(neyDto.getName()).isEqualTo(ney.getName());
		assertThat(neyDto.getPoliticalOffice()).isEqualTo(ney.getCargo());
		assertThat(neyDto.getBirthDate()).isEqualTo(ney.getBirthDate());
		assertThat(neyDto.getSex()).isEqualTo(ney.getSex());
		assertThat(neyDto.getPoliticalOffice()).isEqualTo(ney.getCargo());
	}

	@Test
	public void naoDeveriaBuscarUmAssociadoPeloIdPoisNaoExisteAssociadoComEsteId() {
		when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

		assertThatExceptionOfType(DefaultException.class).isThrownBy(() -> this.associateImplementacaoservice.findById(3L));
	}
}
