package swsketch.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import swsketch.domain.model.study.TagLink;
import swsketch.domain.model.study.TagLinkRepository;

@Repository
public class HibernateTagLinkRepository extends HibernateSupport<TagLink> implements TagLinkRepository {

	HibernateTagLinkRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void createTagLinkData(String studyid, Long tagid) {
		String sql = "Insert into taglink values(:studyid, :tagid)";
		Query<TagLink> query = getSession().createQuery(sql, TagLink.class);
		query.setParameter("studyid", studyid);
		query.setParameter("tagid", tagid);
		query.executeUpdate();
	}

	@Override
	public void createTagLinkList(List<TagLink> tlList) {
		super.InsertAll(tlList);
		super.flush();
	}

	@Override
	public void deleteTagLinkList(String studyid) {
		String sql = "delete from taglink where study_id = :study_id";
		NativeQuery<TagLink> query = getSession().createNativeQuery(sql, TagLink.class);
		query.setParameter("study_id", studyid);
		query.executeUpdate();
	}
}
