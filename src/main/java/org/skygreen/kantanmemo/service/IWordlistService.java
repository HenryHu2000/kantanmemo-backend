package org.skygreen.kantanmemo.service;

import java.util.Map;

public interface IWordlistService {
    Long uploadCsv(String filename, String body);

    Map<String, Long> getAllWordlists();

    Long userSelectWordlist(Long userId, Long wordlistId);
}
