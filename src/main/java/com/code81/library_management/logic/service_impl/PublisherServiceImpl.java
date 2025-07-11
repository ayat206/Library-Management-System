package com.code81.library_management.logic.service_impl;

import com.code81.library_management.data.entity.Publisher;
import com.code81.library_management.data.repository.PublisherRepository;
import com.code81.library_management.logic.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        if (publisherRepository.findByName(publisher.getName()).isPresent()) {
            throw new RuntimeException("Publisher already exists");
        }
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher updatePublisher(Long id, Publisher updatedPublisher) {
        Publisher existing = getPublisherById(id);
        existing.setName(updatedPublisher.getName());
        return publisherRepository.save(existing);
    }

    @Override
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

}
