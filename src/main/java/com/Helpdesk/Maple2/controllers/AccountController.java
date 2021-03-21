package com.Helpdesk.Maple2.controllers;

import com.Helpdesk.Maple2.entities.Account;
import com.Helpdesk.Maple2.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("accounts", accountService.findAll());
        return "account.index";
    }

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

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        Account account = new Account();
        modelMap.put("account", account);
        return "account.add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute("account") Account account, ModelMap modelMap) {
        try {
            account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
            accountService.save(account);
            return "redirect:/account";
        } catch (Exception e) {
            modelMap.put("error", "Creation Failed");
            return "account.add";

        }
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            accountService.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Deletion Failed");
        }
        return "redirect:/account";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, ModelMap modelMap) {
        Account account = accountService.find(id);
        modelMap.put("account", account);
        return "account.edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("account") Account account, ModelMap modelMap) {
        try {
            if (account.getPassword().isEmpty()) {
                account.setPassword(accountService.find(account.getId()).getPassword());
            } else {
                account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
            }
            accountService.save(account);
            return "redirect:/account";
        } catch (Exception e) {
            modelMap.put("error", "Update Failed");
            modelMap.put("account", account);
            return "account.edit";
        }
    }
}
