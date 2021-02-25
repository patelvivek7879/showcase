package com.showcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import java.util.*;
import com.showcase.model.*;

@SpringBootApplication
public class ShowcaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowcaseApplication.class, args);
	}

	@Bean
	public ShowcaseModel getShowcaseModel() {
		ShowcaseModel showcaseModel = new ShowcaseModel();
		List<com.showcase.dl.pojo.Category> dlCategories = new com.showcase.dl.CategoryDAO().getAll();
		List<Category> categories = new LinkedList<Category>();
		dlCategories.forEach((category) -> {
			Category c = new Category();
			c.setCode(category.getCode());
			c.setTitle(category.getTitle());
			categories.add(c);
		});
		showcaseModel.setCategories(categories);
		return showcaseModel;
	}
}
