package swsketch.domain.model.study;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import swsketch.domain.common.model.AbstractBaseEntity;

@Entity
public final class CategoryCount extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3746326226899600234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @JoinColumn(name = "userid")
    private Long userid;
	
    private String name;
	
	private Long count;
	
	public static CategoryCount create(Long userid, String name, Long Count) {
		CategoryCount categoryCount = new CategoryCount();
		categoryCount.userid = userid;
		categoryCount.name = name;
		categoryCount.count = Count;
		return categoryCount;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof CategoryCount)) return false;
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userid, name, count);
	}

	@Override
	public String toString() {
		return "CategoryCount [id=" + id + ", userid=" + userid + ", name=" + name + ", count=" + count + "]";
	}



}
