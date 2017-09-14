package com.ferasinka.prospring4mvc;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
}
