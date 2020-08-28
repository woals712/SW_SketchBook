package swsketch.domain.model.portfolio;

import javax.persistence.Id;
import javax.persistence.JoinColumn;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import swsketch.domain.common.model.AbstractBaseEntity;

@Entity
@Table(name = "portfolio")
public class Portfolio extends AbstractBaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -756870499150747687L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "userid")
	private Long userid;
	
	private String title;
	
	private String date;
	
	private String details;
	
	private String content;

	@Column(name = "inner_image")
	private String innerImage;

	@Column(name = "outer_image")
	private String outerImage;

	@Column(name = "github_link")
	private String githubLink;
	
	@Column(name = "youtube_link")
	private String youtubeLink;

	public static Portfolio create(Long userid, String title, String date, String details, String content, String innerImage,
			String outerImage, String githubLink, String youtubeLink) {
		Portfolio portfol = new Portfolio();
		portfol.userid = userid;
		portfol.title = title;
		portfol.date = date;
		portfol.details = details;
		portfol.content = content;
		portfol.innerImage = innerImage;
		portfol.outerImage = outerImage;
		portfol.githubLink = githubLink;
		portfol.youtubeLink = youtubeLink;
		return portfol;
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

	public void setUserid(Long userId) {
		this.userid = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInnerImage() {
		return innerImage;
	}

	public void setInnerImage(String innerImage) {
		this.innerImage = innerImage;
	}

	public String getOuterImage() {
		return outerImage;
	}

	public void setOuterImage(String outerImage) {
		this.outerImage = outerImage;
	}

	public String getGithubLink() {
		return githubLink;
	}

	public void setGithubLink(String githubLink) {
		this.githubLink = githubLink;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userid, title, details);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(!(obj instanceof Portfolio)) return false;
		return false;
	}

	@Override
	public String toString() {
		return "Portfol [id=" + id + ", userid=" + userid + ", title=" + title + ", date=" + date + ", details=" + details
				+ ", content=" + content + ", innerImage=" + innerImage + ", outerImage=" + outerImage + ", githubLink="
				+ githubLink + ", youtubeLink=" + youtubeLink + "]";
	}
}