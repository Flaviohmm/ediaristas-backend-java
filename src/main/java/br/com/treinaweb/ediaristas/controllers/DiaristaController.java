package br.com.treinaweb.ediaristas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.models.Diarista;
import br.com.treinaweb.ediaristas.repositories.DiaristaRepository;

@Controller
@RequestMapping("/admin/diaristas")
public class DiaristaController {

    @Autowired
    private DiaristaRepository repository;

    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("admin/diaristas/listar");

        modelAndView.addObject("diaristas", repository.findAll());

        return modelAndView;
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/diaristas/form");

        modelAndView.addObject("diarista", new Diarista());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Diarista diarista){
        repository.save(diarista);

        return "redirect:/admin/diaristas";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/diaristas/form");

        modelAndView.addObject("diarista", repository.getById(id));

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Diarista diarista) {
        repository.save(diarista);

        return "redirect:/admin/diaristas";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);

        return "redirect:/admin/diaristas";
    }
}