package com.code81.library_management.logic.service;

import com.code81.library_management.data.entity.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getAllPublishers();
    Publisher getPublisherById(Long id);
    Publisher createPublisher(Publisher publisher);
    Publisher updatePublisher(Long id, Publisher publisher);
    void deletePublisher(Long id);
}
