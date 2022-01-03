package org.skygreen.kantanmemo.service;

import org.skygreen.kantanmemo.dto.PersonDto;

public interface IUserService {
    PersonDto getUserInfo(Long userId);

    Long register(String name);
}
