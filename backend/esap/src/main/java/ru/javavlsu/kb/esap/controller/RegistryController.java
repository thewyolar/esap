package ru.javavlsu.kb.esap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.model.Registry;
import ru.javavlsu.kb.esap.service.RegistryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegistryController {
    @Autowired
    private RegistryService registryService;

    @GetMapping("/registries")
    public List<Registry> getAllRegistries() { return registryService.getAll(); }
}
