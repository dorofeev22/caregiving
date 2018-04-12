package ru.dorofeev22.caregiving.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		BaseEntity that = (BaseEntity) obj;
		return !(id == null || that.id == null) && id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return id == null ? 0: Long.hashCode(id);
	}

}


