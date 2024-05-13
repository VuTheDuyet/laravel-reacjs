package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Tag;
import com.vutheduyet.exercise03.repository.TagRepository;
import com.vutheduyet.exercise03.service.TagService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTagById(String tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        return optionalTag.orElse(null);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag updateTag(Tag tag) {
        Optional<Tag> optionalExistingTag = tagRepository.findById(tag.getTagId());
        if (optionalExistingTag.isPresent()) {
            Tag existingTag = optionalExistingTag.get();
            existingTag.setTagName(tag.getTagName());
            existingTag.setIcon(tag.getIcon());
            existingTag.setUpdatedAt(LocalDateTime.now());
            existingTag.setUpdatedBy(tag.getUpdatedBy());

            Tag updatedTag = tagRepository.save(existingTag);
            return updatedTag;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy tag tồn tại
    }

    @Override
    public void deleteTag(String tagId) {
        tagRepository.deleteById(tagId);
    }
}
