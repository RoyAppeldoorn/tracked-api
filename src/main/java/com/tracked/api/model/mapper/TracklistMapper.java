package com.tracked.api.model.mapper;

import com.tracked.api.model.Tracklist;
import com.tracked.api.model.dto.TracklistDto;
import org.modelmapper.ModelMapper;

public class TracklistMapper {

    private final static ModelMapper modelMapper = new ModelMapper();

    public static TracklistDto convertToDto(Tracklist tracklist) {
        TracklistDto tracklistDto = modelMapper.map(tracklist, TracklistDto.class);
        tracklistDto.setId(tracklist.getId());
        tracklistDto.setTitle(tracklist.getTitle());
        tracklistDto.setEmbed_url(tracklist.getEmbed_url());
        return tracklistDto;
    }
}
