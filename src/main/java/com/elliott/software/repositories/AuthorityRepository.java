package com.elliott.software.repositories;

import com.elliott.software.models.Authority;
import com.elliott.software.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

}
