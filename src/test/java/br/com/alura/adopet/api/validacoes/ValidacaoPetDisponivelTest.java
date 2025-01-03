package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @InjectMocks
    private ValidacaoPetDisponivel validacao;

    @Mock
    private PetRepository repository;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto solicitacaoAdocaoDto;

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoPet() {

        BDDMockito.given(solicitacaoAdocaoDto.idPet()).willReturn(7L);
        BDDMockito.given(repository.getReferenceById(solicitacaoAdocaoDto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);

        Assertions.assertDoesNotThrow(() -> validacao.validar(solicitacaoAdocaoDto));
    }

    @Test
    void naoDeveriaPermitirSolicitacaoDeAdocaoPet() {

        BDDMockito.given(solicitacaoAdocaoDto.idPet()).willReturn(7L);
        BDDMockito.given(repository.getReferenceById(solicitacaoAdocaoDto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(solicitacaoAdocaoDto));
    }
}