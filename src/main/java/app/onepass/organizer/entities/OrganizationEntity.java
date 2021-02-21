package app.onepass.organizer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "organization")
@Getter
public class OrganizationEntity {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private boolean is_verified;
}