package org.skygreen.kantanmemo.service.mapper;

import org.mapstruct.Mapper;
import org.skygreen.kantanmemo.dto.WordlistDto;
import org.skygreen.kantanmemo.entity.Wordlist;

@Mapper(componentModel = "cdi")
public interface WordlistMapper {
    WordlistDto wordlistToWordlistDto(Wordlist wordlist);
}
