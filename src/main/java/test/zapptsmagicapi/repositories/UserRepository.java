package test.zapptsmagicapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import test.zapptsmagicapi.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByUserName(String userName);
}
