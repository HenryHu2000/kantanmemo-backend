package org.skygreen.kantanmemo.service;

import org.skygreen.kantanmemo.dto.PersonDto;
import org.skygreen.kantanmemo.dto.UserSettingsDto;

public interface IUserService {
    PersonDto getUserInfo(Long userId);

    Long register(String name);

    UserSettingsDto getUserSettings(Long userId);

    UserSettingsDto setUserSettings(Long userId, UserSettingsDto newUserSettings);

}
