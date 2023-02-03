package com.infy.infyinterns.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.infyinterns.entity.Mentor;

import java.util.*;

public interface MentorRepository extends CrudRepository<Mentor, Integer> {
    // add methods if required
    List<Mentor> findbyNumberOfProjectsMentored(Integer numberOfProjectsMentored);
}
