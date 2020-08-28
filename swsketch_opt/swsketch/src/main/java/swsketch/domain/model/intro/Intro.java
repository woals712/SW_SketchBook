package swsketch.domain.model.intro;

import swsketch.domain.common.model.AbstractBaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "introduce")
public class Intro extends AbstractBaseEntity {
	private static final long serialVersionUID = -4865946674835353945L;

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  @Column(name = "username")
	  private String username;

	  @Column(name = "detail")
	  private String detail;

	  public static Intro create(String username, String detail) {
		Intro intro = new Intro();
	    intro.username = username;
	    intro.detail = detail;
	    return intro;
	  }
	  
	  
	  public Long getId() {
	    return id;
	  }
	  
	  public void setId(Long id) {
			this.id = id;
		}

	  public String getUsername() {
	    return username;
	  }
	  
	  public void setUsername(String username) {
			this.username = username;
		}
	  
	  public String getDetail() {
	    return detail;
	  }
	  
	  public void setDetail(String detail) {
			this.detail = detail;
		}

	  @Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if(!(obj instanceof Intro)) return false;
			return false;
		}
	  
	  @Override
		public int hashCode() {
			return Objects.hash(id, username, detail);
		}
	  
	  @Override
	  public String toString() {
	    return "Intro [id=" + id + ", username=" + username + ", detail=" + detail + "]";
	  }
}
