package swsketch.domain.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import swsketch.domain.application.TagService;
import swsketch.domain.model.study.Tag;
import swsketch.domain.model.study.TagRepository;

@Service
@Transactional
public class TagServiceImpl implements TagService {

	private TagRepository repo;
		
	public TagServiceImpl(TagRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Tag> findAll() {
		return repo.findAll();
	}
	
	@Override
	public void createTags(String tagData) {
		List<Tag> newList = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(tagData, ",");
		
		while(st.hasMoreTokens()) {
			newList.add(Tag.create(st.nextToken()));
		}
		
		repo.insertAll(newList);
	}
	@Override
	public void createTagsList(List<Tag> newList) {
		repo.insertAll(newList);
	}

	@Override
	public List<Tag> findByUserId(Long userId) {
		return repo.findByUserId(userId);
	}
	
	@Override
	public List<Tag> findByStudyId(String studyId) {
		return repo.findByStudyId(studyId);
	}
}
