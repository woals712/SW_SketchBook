package swsketch.domain.application.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import swsketch.domain.application.TagLinkService;
import swsketch.domain.model.study.Tag;
import swsketch.domain.model.study.TagLink;
import swsketch.domain.model.study.TagLinkRepository;

@Service
@Transactional
public class TagLinkServiceImpl implements TagLinkService {

	private TagLinkRepository repo;
	
	public TagLinkServiceImpl(TagLinkRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void createTagLinkList(String studyid, List<Tag> tagList) {
		List<TagLink> newList = new ArrayList<TagLink>();
		int lSize = tagList.size();
		
		for(int i = 0; i < lSize; ++i) {
			newList.add(TagLink.create(studyid, tagList.get(i).getId()));
		}
		
		repo.createTagLinkList(newList);
	}

	@Override
	public void deleteTagLinkList(String studyid) {
		repo.deleteTagLinkList(studyid);
	}

}
