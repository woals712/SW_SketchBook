package swsketch.domain.model.study;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import swsketch.domain.common.model.AbstractBaseEntity;

@Entity
@Table(name = "category")
public class Category extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5800107761250883882L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @JoinColumn(name = "userid")
    private Long userid;
	
    private String name;

	public static Category create(Long userid, String name) {
		Category category = new Category();
		category.userid = userid;
		category.name = name;
		return category;
	}
	
	public Long getId() {
		return id;
	}

	public Long getUserid() {
		return userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Category)) return false;
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userid, name);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", userid=" + userid + ", name=" + name + "]";
	}
}
