package swsketch.domain.model.study;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import swsketch.domain.common.model.AbstractBaseEntity;

@Entity
@Table(name = "taglink")
public class TagLink extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -478539936695271972L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "study_id")
	private String studyid;
	
	@Column(name = "tag_id")
	private Long tagid;
	
	public static TagLink create(String studyid, Long tagid)
	{
		TagLink tagLink = new TagLink();
		tagLink.studyid = studyid;
		tagLink.tagid = tagid;
		
		return tagLink;
	}

	public String getStudyid() {
		return studyid;
	}

	public void setStudyid(String studyid) {
		this.studyid = studyid;
	}

	public Long getTagid() {
		return tagid;
	}

	public void setTagid(Long tagid) {
		this.tagid = tagid;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studyid == null) ? 0 : studyid.hashCode());
		result = prime * result + ((tagid == null) ? 0 : tagid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		TagLink other = (TagLink) obj;
		if (studyid == null) {
			if (other.studyid != null)
				return false;
		} else if (!studyid.equals(other.studyid))
			return false;
		if (tagid == null) {
			if (other.tagid != null)
				return false;
		} else if (!tagid.equals(other.tagid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TagLink [studyid=" + studyid + ", tagid=" + tagid + "]";
	}
	
	
}
