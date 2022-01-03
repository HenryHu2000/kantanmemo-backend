package org.skygreen.kantanmemo.service.impl;

import org.skygreen.kantanmemo.dao.PersonDao;
import org.skygreen.kantanmemo.dto.PersonDto;
import org.skygreen.kantanmemo.entity.Person;
import org.skygreen.kantanmemo.service.IUserService;
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

    @Override
    public PersonDto getUserInfo(Long userId) {
        if (userId == null) {
            throw new ForbiddenException();
        }
        var personOpt = personDao.findById(userId);
        if (personOpt.isPresent()) {
            var person = personOpt.get();
            return PersonDto.personToDto(person);
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

}
