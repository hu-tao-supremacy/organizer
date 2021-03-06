package app.onepass.organizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import app.onepass.organizer.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
