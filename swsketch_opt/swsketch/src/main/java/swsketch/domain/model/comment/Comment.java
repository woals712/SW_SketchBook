package swsketch.domain.model.comment;

import swsketch.domain.common.model.AbstractBaseEntity;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment extends AbstractBaseEntity {

  private static final long serialVersionUID = -1759127062966256817L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "user_name")
  private String username;

  @Column(name = "study_id")
  private String studyId;

  @Column(name = "detail")
  private String detail;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_date", nullable = false)
  private Date createdDate;

  public Comment() {
  }

  public static Comment create(final String username, final String studyId, final String detail) {
    final Comment reply = new Comment();
    reply.username = username;
    reply.studyId = studyId;
    reply.detail = detail;
    reply.createdDate = new Date();
    return reply;
  }

  public void changeDetail(final String detail) {
    this.detail = detail;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getStudyId() {
    return studyId;
  }

  public void setStudyId(final String studyid) {
    this.studyId = studyId;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(final String detail) {
    this.detail = detail;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreateDate(final Date date) {
    this.createdDate = createdDate;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if(!(obj instanceof Comment)) return false;
		return false;
	}

  @Override
  public int hashCode() {
    return Objects.hash(id,username, studyId, detail, createdDate);
  }

  @Override
  public String toString() {
    return "Comment[" +
      "id=" + id +
      ", username=" + username +
      ", studyId=" + studyId +
      ", detail='" + detail +
      ", createdDate=" + createdDate +
      "]";
  }
}