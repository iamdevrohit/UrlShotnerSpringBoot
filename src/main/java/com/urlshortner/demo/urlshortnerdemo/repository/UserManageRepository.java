package com.urlshortner.demo.urlshortnerdemo.repository;

import com.urlshortner.demo.urlshortnerdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserManageRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

}
