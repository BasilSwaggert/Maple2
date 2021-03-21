package com.Helpdesk.Maple2.controllers;

import com.Helpdesk.Maple2.entities.Account;
import com.Helpdesk.Maple2.entities.Tickets;
import com.Helpdesk.Maple2.services.AccountService;
import com.Helpdesk.Maple2.services.CategoryService;
import com.Helpdesk.Maple2.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping(value = "tickets")
public class TicketsController {

    @Autowired
    private TicketsService ticketsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AccountService accountService;

    //Authentication used here may be incorrect
    @RequestMapping(value = "send", method = RequestMethod.GET)
    public String send(ModelMap modelMap) {
        Tickets tickets = new Tickets();
        modelMap.put("tickets", tickets);
        modelMap.put("category", categoryService.findAll());
        return "tickets.send";
    }

    @RequestMapping(value = "send", method = RequestMethod.POST)
    public String send(@ModelAttribute("tickets") Tickets tickets,
                       Authentication authentication, RedirectAttributes redirectAttributes) {
        try {
            Account account = accountService.findByUsername(authentication.getName());
            tickets.setCreatedDate(new Date());
            tickets.setAccountByEmployeeId(account);
            ticketsService.save(tickets);
            redirectAttributes.addFlashAttribute("success", "Sent Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Send Failed");
        }
        return "redirect:/ticket/send";
    }

    @RequestMapping(value = "history", method = RequestMethod.GET)
    public String history(Authentication authentication, ModelMap modelMap) {
        modelMap.put("tickets", ticketsService.findTicketsByUsername(authentication.getName()));
        return "tickets.history";
    }
}
