package org.skygreen.kantanmemo.service.impl;

import org.skygreen.kantanmemo.dao.PersonDao;
import org.skygreen.kantanmemo.dto.PersonDto;
import org.skygreen.kantanmemo.entity.Person;
import org.skygreen.kantanmemo.service.IUserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;

@ApplicationScoped
public class UserService implements IUserService {
    @Inject
    PersonDao personDao;

    @Override
    public PersonDto login(Long userId) {
        var personOpt = personDao.findById(userId);
        if (personOpt.isPresent()) {
            var person = personOpt.get();
            return PersonDto.personToDto(person);
        } else {
            throw new ForbiddenException();
        }
    }

    @Override
    public PersonDto register(String name) {
        var person = new Person();
        person.setName(name);
        personDao.save(person);
        return PersonDto.personToDto(person);
    }


}
