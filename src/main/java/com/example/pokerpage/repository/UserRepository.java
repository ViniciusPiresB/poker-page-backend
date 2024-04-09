package com.example.pokerpage.repository;

import com.example.pokerpage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByNome(String nome);
}
