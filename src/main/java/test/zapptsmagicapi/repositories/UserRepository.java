package test.zapptsmagicapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import test.zapptsmagicapi.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  List<User> findByUserName(String userName);
}
