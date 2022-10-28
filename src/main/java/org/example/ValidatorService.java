package org.example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    public String validateName(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

    public String validateSurName(String surName) {
        String[] surNames = surName.split("-");
        for (int i = 0; i < surNames.length; i++) {
            if (!StringUtils.isAlpha(surNames[i])) {
                throw new IncorrectSurNameException();
            }
            surNames[i] = StringUtils.capitalize(surNames[i].toLowerCase());
        }
        return String.join("-", surNames);
    }

}
