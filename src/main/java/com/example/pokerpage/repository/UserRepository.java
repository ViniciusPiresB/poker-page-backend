package com.example.pokerpage.repository;

import com.example.pokerpage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class UserRepository implements JpaRepository<User, Integer> {
}
