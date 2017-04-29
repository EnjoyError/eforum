package org.eforum.front.controller;

import org.eforum.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivilegeController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(User user) {
        return null;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Object logout() {
        return null;
    }
}
