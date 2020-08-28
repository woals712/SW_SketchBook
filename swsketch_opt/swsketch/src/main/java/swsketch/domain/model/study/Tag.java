package swsketch.domain.model.study;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import swsketch.domain.common.model.AbstractBaseEntity;

@Entity
@Table(name = "tag")
public class Tag extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8691721813850931923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Tag() {	}
	
	public static Tag create(String name)
	{
		Tag tag = new Tag();
		tag.name = name;
		
		return tag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (name.equals(obj))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}
	
}
