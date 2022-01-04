package org.skygreen.kantanmemo.service.mapper;

import org.mapstruct.*;
import org.skygreen.kantanmemo.dto.PersonDto;
import org.skygreen.kantanmemo.dto.UserSettingsDto;
import org.skygreen.kantanmemo.entity.Person;
import org.skygreen.kantanmemo.entity.UserSettings;
import org.skygreen.kantanmemo.entity.Wordlist;

import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "cdi", uses = WordlistMapper.class)
public abstract class PersonMapper {
    public abstract PersonDto personToPersonDto(Person person);

    public Map<Long, Integer> mapProgress(Map<Wordlist, Integer> progress) {
        var output = new HashMap<Long, Integer>();
        for (var key : progress.keySet()) {
            output.put(key.getId(), progress.get(key));
        }
        return output;
    }

    @Mapping(source = "currentWordlistId", target = "currentWordlist.id")
    public abstract UserSettings settingsDtoToSettings(UserSettingsDto userSettingsDto);

    @Mapping(source = "currentWordlist.id", target = "currentWordlistId")
    public abstract UserSettingsDto settingsToSettingsDto(UserSettings userSettings);
}
