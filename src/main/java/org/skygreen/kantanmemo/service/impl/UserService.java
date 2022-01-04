package org.skygreen.kantanmemo.service.impl;

import org.skygreen.kantanmemo.dao.PersonDao;
import org.skygreen.kantanmemo.dao.WordlistDao;
import org.skygreen.kantanmemo.dto.PersonDto;
import org.skygreen.kantanmemo.dto.UserSettingsDto;
import org.skygreen.kantanmemo.entity.Person;
import org.skygreen.kantanmemo.service.IUserService;
import org.skygreen.kantanmemo.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;

@ApplicationScoped
public class UserService implements IUserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Inject
    PersonDao personDao;
    @Inject
    WordlistDao wordlistDao;
    @Inject
    PersonMapper personMapper;

    @Override
    public PersonDto getUserInfo(Long userId) {
        if (userId == null) {
            throw new ForbiddenException();
        }
        var personOpt = personDao.findById(userId);
        if (personOpt.isPresent()) {
            var person = personOpt.get();
            return personMapper.personToPersonDto(person);
        } else {
            throw new ForbiddenException();
        }
    }

    @Override
    public Long register(String name) {
        if (name == null) {
            throw new ForbiddenException();
        }
        var person = new Person();
        person.setName(name);
        personDao.save(person);
        return person.getId();
    }

    @Override
    public UserSettingsDto getUserSettings(Long userId) {
        if (userId == null) {
            throw new ForbiddenException();
        }
        var personOpt = personDao.findById(userId);
        if (personOpt.isPresent()) {
            var person = personOpt.get();
            return personMapper.settingsToSettingsDto(person.getUserSettings());
        } else {
            throw new ForbiddenException();
        }
    }

    @Override
    public UserSettingsDto setUserSettings(Long userId, UserSettingsDto newUserSettings) {
        if (userId == null || newUserSettings == null) {
            throw new ForbiddenException();
        }
        if (newUserSettings.getCurrentWordlistId() == null || !wordlistDao.existsById(newUserSettings.getCurrentWordlistId())) {
            throw new ForbiddenException();
        }
        var personOpt = personDao.findById(userId);
        if (personOpt.isPresent()) {
            var person = personOpt.get();
            person.setUserSettings(personMapper.settingsDtoToSettings(newUserSettings));
            personDao.save(person);
            return personMapper.settingsToSettingsDto(person.getUserSettings());
        } else {
            throw new ForbiddenException();
        }
    }

}
