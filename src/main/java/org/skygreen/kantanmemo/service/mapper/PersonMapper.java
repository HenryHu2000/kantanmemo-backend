package org.skygreen.kantanmemo.service.mapper;

import org.mapstruct.Mapper;
import org.skygreen.kantanmemo.dto.PersonDto;
import org.skygreen.kantanmemo.entity.Person;
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
}
