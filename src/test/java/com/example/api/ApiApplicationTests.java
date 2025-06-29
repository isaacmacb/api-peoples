package com.example.api;

import com.example.api.entity.Pessoa;
import com.example.api.repository.PessoaRepository;
import com.example.api.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

	@Mock
	private PessoaRepository pessoaRepository;

	@InjectMocks
	private PessoaService pessoaService;

	@Test
	void deveListarTodasAsPessoas() {
		List<Pessoa> pessoas = List.of(
				new Pessoa(1L, "João", "Maciel", 24),
				new Pessoa(2L, "Maria", "Silva", 30)
		);

		when(pessoaRepository.findAll()).thenReturn(pessoas);

		List<Pessoa> resultado = pessoaService.listarTodasAsPessoas();

		assertEquals(2, resultado.size());
		assertEquals("João", resultado.get(0).getNome());
		assertEquals("Maria", resultado.get(1).getNome());
	}

	@Test
	void deveBuscarPessoaPorId() {
		Pessoa pessoa = new Pessoa(1L, "Isaac", "Bessa", 24);
		when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

		Optional<Pessoa> resultado = pessoaService.buscarPorId(1L);

		assertTrue(resultado.isPresent());
		Pessoa pessoaResultado = resultado.get();

		assertEquals("Isaac", pessoaResultado.getNome());
		assertEquals("Bessa", pessoaResultado.getSobreNome());
		assertEquals(24, pessoaResultado.getIdade());
	}

	@Test
	void deveLancarExcecaoSePessoaNaoEncontrada() {
		when(pessoaRepository.findById(99L)).thenReturn(Optional.empty());

		RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
			pessoaService.buscarPorId(99L).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
		});

		assertEquals("Pessoa não encontrada", excecao.getMessage());
	}
}
