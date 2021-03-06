package app.onepass.organizer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import app.onepass.organizer.entities.UserOrganizationEntity;

@Repository
public interface UserOrganizationRepository extends JpaRepository<UserOrganizationEntity, Integer> {

	List<UserOrganizationEntity> findByOrganizationId(int organizationId);

	UserOrganizationEntity findByUserIdAndOrganizationId(int userId, int organizationId);
}
