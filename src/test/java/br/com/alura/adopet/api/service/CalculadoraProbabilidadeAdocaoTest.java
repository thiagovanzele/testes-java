package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Em caso de pet com idade e peso baixo, a probabilidade de adoção é alta")
    void deveriaDevolverProbabilidadeAlta() {

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo teste", "977877877", "abrigoteste@teste.com.br"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 4, "Cinzda", 4.0f), abrigo);


        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidadeAdocao);
    }

    @Test
    @DisplayName("Em caso de pet com peso ou idade alta, a probabilidade de adoção é media")
    void deveriaDevolverProbabilidadeMedia() {
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo teste", "977877877", "abrigoteste@teste.com.br"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 14, "Cinzda", 4.0f), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidadeAdocao);
    }
}