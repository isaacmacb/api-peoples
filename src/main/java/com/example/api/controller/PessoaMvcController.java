package com.example.api.controller;

import com.example.api.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PessoaMvcController {

    private final PessoaService pessoaService;

    @GetMapping("/pessoas")
    public String listarPessoas(Model model) {
        model.addAttribute("pessoas", pessoaService.listarTodasAsPessoas());
        return "lista-pessoas"; // nome do arquivo Thymeleaf lista-pessoas.html
    }
}
