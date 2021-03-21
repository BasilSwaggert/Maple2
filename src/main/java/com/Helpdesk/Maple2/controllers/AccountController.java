package com.Helpdesk.Maple2.controllers;

import com.Helpdesk.Maple2.entities.Account;
import com.Helpdesk.Maple2.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //Authentication used here may be incorrect
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile(Authentication authentication, ModelMap modelMap) {
        modelMap.put("account", accountService.findByUsername(authentication.getName()));
        return "account.profile";
    }

    @RequestMapping(value = "profile", method = RequestMethod.POST)
    public String profile(@ModelAttribute("account") Account account) {
        Account currentAccount = accountService.findByUsername(account.getUsername());
        if (!account.getPassword().isEmpty()) {
            currentAccount.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        }
        currentAccount.setEmail(account.getEmail());
        currentAccount.setFullName(account.getFullName());
        currentAccount.setPhone(account.getPhone());
        accountService.save(currentAccount);
        return "redirect:/account/profile";
    }
}
