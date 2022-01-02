package org.skygreen.kantanmemo.service;

import org.skygreen.kantanmemo.dto.WordlistDto;

import java.util.List;

public interface IWordlistService {
    Long uploadCsv(String filename, String body);

    List<WordlistDto> getAllWordlists();

    WordlistDto userSelectWordlist(Long userId, Long wordlistId);

    WordlistDto userCurrentWordlist(Long userId);
}
