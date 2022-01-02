package org.skygreen.kantanmemo.service;

import org.skygreen.kantanmemo.dto.PersonDto;

public interface IUserService {
    PersonDto login(Long userId);

    PersonDto register(String name);
}
