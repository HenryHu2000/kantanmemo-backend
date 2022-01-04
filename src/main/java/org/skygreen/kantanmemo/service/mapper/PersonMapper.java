package org.skygreen.kantanmemo.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.skygreen.kantanmemo.dao.WordlistDao;
import org.skygreen.kantanmemo.dto.PersonDto;
import org.skygreen.kantanmemo.dto.UserSettingsDto;
import org.skygreen.kantanmemo.entity.Person;
import org.skygreen.kantanmemo.entity.UserSettings;
import org.skygreen.kantanmemo.entity.Wordlist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "cdi", uses = WordlistMapper.class)
public abstract class PersonMapper {
    public static final Logger LOG = LoggerFactory.getLogger(PersonMapper.class);

    @Inject
    WordlistDao wordlistDao;

    public abstract PersonDto personToPersonDto(Person person);

    public Map<Long, Integer> mapProgress(Map<Wordlist, Integer> progress) {
        var output = new HashMap<Long, Integer>();
        for (var key : progress.keySet()) {
            output.put(key.getId(), progress.get(key));
        }
        return output;
    }

    public UserSettings settingsDtoToSettings(UserSettingsDto userSettingsDto) {
        var userSettings = new UserSettings();

        Wordlist currentWordlist = null;
        var currentWordlistId = userSettingsDto.getCurrentWordlistId();
        if (currentWordlistId != null) {
            currentWordlist = wordlistDao.findById(currentWordlistId).orElse(null);
        }

        userSettings.setCurrentWordlist(currentWordlist);
        userSettings.setDailyNewWordNum(userSettingsDto.getDailyNewWordNum());
        userSettings.setDailyRevisingWordNum(userSettingsDto.getDailyRevisingWordNum());

        return userSettings;
    }

    @Mapping(source = "currentWordlist.id", target = "currentWordlistId")
    public abstract UserSettingsDto settingsToSettingsDto(UserSettings userSettings);

}
