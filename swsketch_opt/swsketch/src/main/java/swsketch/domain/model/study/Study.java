package swsketch.domain.model.study;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import swsketch.domain.common.model.AbstractBaseEntity;

@Entity
@Table(name = "study")
public class Study extends AbstractBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9028157873560404708L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
    private Long userid;
	
    private Long ctgid;

    private String title;
	
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "update_date")
	private Date updateDate;
	
	public Study() {}
	
	public static Study create(Long curNum, Long userid, String title, String content, Long categoryid) {
		Study study = new Study();
		study.id = "" + userid + "_" + (curNum + 1);
		study.userid = userid;
		study.ctgid = categoryid;
		study.title = title;
		study.content = content;
		study.createDate = new Date();
		study.updateDate = study.createDate;
		return study;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getCategoryId() {
		return ctgid;
	}

	public void setCategoryId(Long category) {
		this.ctgid = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date date) {
		this.createDate = date;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Study)) return false;
	    
	    return false;
	}

	@Override
	public String toString() {
		return "Study [id=" + id + ", userid=" + userid + ", categoryid=" + ctgid + ", title=" + title
				+ ", content=" + content + ", date=" + createDate + "]";
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id, userid, createDate);
	}
}
