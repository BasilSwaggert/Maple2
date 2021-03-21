package com.Helpdesk.Maple2.controllers;

import com.Helpdesk.Maple2.entities.Category;
import com.Helpdesk.Maple2.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Authentication used here may be incorrect
    @RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("categories", categoryService.findAll());
        return "category.index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        Category category = new Category();
        category.setStatus(true);
        modelMap.put("category", new Category());
        return "category.add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute("category") Category category, ModelMap modelMap) {
        try {
            categoryService.save(category);
            return "redirect:/category";
        } catch (Exception e) {
            modelMap.put("error", "Creation Failed");
            return "category.add";
        }
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, ModelMap modelMap) {
        Category category = categoryService.find(id);
        modelMap.put("category", category);
        return "category.edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("category") Category category, ModelMap modelMap) {
        try {
            categoryService.save(category);
            return "redirect:/category";
        } catch (Exception e) {
            modelMap.put("error", "Update Failed");
            modelMap.put("category", category);
            return "category.edit";
        }
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Deletion Failed");
        }
        return "redirect:/category";
    }
}
