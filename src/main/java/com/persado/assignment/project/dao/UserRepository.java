package com.persado.assignment.project.dao;

import com.persado.assignment.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
