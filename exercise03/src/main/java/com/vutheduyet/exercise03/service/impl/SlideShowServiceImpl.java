package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.SlideShow;
import com.vutheduyet.exercise03.repository.SlideShowRepository;
import com.vutheduyet.exercise03.service.SlideShowService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SlideShowServiceImpl implements SlideShowService {
    private SlideShowRepository slideShowRepository;

    @Override
    public SlideShow createSlideShow(SlideShow slideShow) {
        return slideShowRepository.save(slideShow);
    }

    @Override
    public SlideShow getSlideShowById(String slideShowId) {
        Optional<SlideShow> optionalSlideShow = slideShowRepository.findById(slideShowId);
        return optionalSlideShow.orElse(null);
    }

    @Override
    public List<SlideShow> getAllSlideShows() {
        return slideShowRepository.findAll();
    }

    @Override
    public SlideShow updateSlideShow(SlideShow slideShow) {
        Optional<SlideShow> optionalExistingSlideShow = slideShowRepository.findById(slideShow.getSlideShowId());
        if (optionalExistingSlideShow.isPresent()) {
            SlideShow existingSlideShow = optionalExistingSlideShow.get();
            existingSlideShow.setDestinationUrl(slideShow.getDestinationUrl());
            existingSlideShow.setImageUrl(slideShow.getImageUrl());
            existingSlideShow.setClicks(slideShow.getClicks());
            existingSlideShow.setUpdatedAt(LocalDateTime.now());

            SlideShow updatedSlideShow = slideShowRepository.save(existingSlideShow);
            return updatedSlideShow;
        }

        return null; // Hoặc xử lý theo yêu cầu của bạn khi không tìm thấy SlideShow tồn tại
    }

    @Override
    public void deleteSlideShow(String slideShowId) {
        slideShowRepository.deleteById(slideShowId);
    }
}
