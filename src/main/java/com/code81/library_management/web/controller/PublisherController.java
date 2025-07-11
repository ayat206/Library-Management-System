package com.code81.library_management.web.controller;

import com.code81.library_management.data.entity.Publisher;
import com.code81.library_management.logic.service.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Publisher> getAll() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public Publisher getById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    public Publisher create(@RequestBody Publisher publisher) {
        return publisherService.createPublisher(publisher);
    }

    @PutMapping("/{id}")
    public Publisher update(@PathVariable Long id, @RequestBody Publisher publisher) {
        return publisherService.updatePublisher(id, publisher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }
}
