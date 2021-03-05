package com.showcase.services;

import com.showcase.model.*;
import com.showcase.services.pojo.*;
import java.util.*;
import org.springframework.web.servlet.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@Controller
public class CategoryViewService {
    @Autowired
    private ShowcaseModel showcaseModel;

    @GetMapping("/showcase/categoriesView")
    public ModelAndView getCategories() {
        System.out.println("getCategories for CategoryView.jsp got executed");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", showcaseModel.getCategories());
        modelAndView.setViewName("CategoryView");
        return modelAndView;
    }
}