package com.showcase.services;

import com.showcase.model.*;
import com.showcase.services.pojo.*;
import com.showcase.dl.*;
import com.showcase.dl.exceptions.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;

@RestController
public class CategoryService {
    @Autowired
    private ShowcaseModel showcaseModel;

    @GetMapping("/showcase/getCategories")
    public List<Category> getAll() {
      try{
      Thread.sleep(5000);
    }catch(Exception e)
    {}
        return showcaseModel.getCategories();
    }

    @PostMapping("/showcase/addCategory")
    public ServiceResponse add(@RequestBody Category category) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if (category == null || category.getTitle() == null) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = "Categoy required.";
            return serviceResponse;
        }
        if (showcaseModel.hasCategory(category)) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = "Category exists.";
            return serviceResponse;
        }
        try {
            com.showcase.dl.pojo.Category dlCategory;
            dlCategory = new com.showcase.dl.pojo.Category();
            dlCategory.setTitle(category.getTitle());
            CategoryDAO categoryDAO = new CategoryDAO();
            categoryDAO.add(dlCategory);
            category.setCode(dlCategory.getCode());
            showcaseModel.addCategory(category);
            serviceResponse.hasResult = true;
            serviceResponse.result = category;
            return serviceResponse;
        } catch (DAOException daoException) {
            serviceResponse.success = false;
            serviceResponse.isError = true;
            serviceResponse.exception = daoException.getMessage();
            return serviceResponse;
        } catch (Throwable throwable) {
            serviceResponse.success = false;
            serviceResponse.isError = true;
            serviceResponse.error = "Cannot perform operation.";
            return serviceResponse;
        }
    }

    @PostMapping("/showcase/updateCategory")
    public ServiceResponse update(@RequestBody Category category) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if (category == null || category.getTitle() == null) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = "Category required.";
            return serviceResponse;
        }
        if (category.getCode() <= 0) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = "Category code is invalid.";
            return serviceResponse;
        }
        Category c = showcaseModel.getCategoryByTitle(category.getTitle());
        if (c != null && c.getCode() != category.getCode()) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = "Category exists.";
            return serviceResponse;
        }
        try {
            com.showcase.dl.pojo.Category dlCategory;
            dlCategory = new com.showcase.dl.pojo.Category();
            dlCategory.setCode(category.getCode());
            dlCategory.setTitle(category.getTitle());
            CategoryDAO categoryDAO = new CategoryDAO();
            categoryDAO.update(dlCategory);
            showcaseModel.updateCategory(category);
            serviceResponse.hasResult = true;
            serviceResponse.result = category;
            return serviceResponse;
        } catch (DAOException daoException) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = daoException.getMessage();
            return serviceResponse;
        } catch (Throwable throwable) {
            serviceResponse.success = false;
            serviceResponse.isError = true;
            serviceResponse.error = "Cannot perform operation.";
            return serviceResponse;
        }
    }

    @PostMapping("/showcase/deleteCategory")
    public ServiceResponse delete(@RequestParam(name = "categoryCode") Integer code) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if (code <= 0) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = "Invalid code";
            return serviceResponse;
        }
        Category category = showcaseModel.getCategoryByCode(code);
        if (category == null) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = "Invalid code";
            return serviceResponse;
        }
        try {
            com.showcase.dl.pojo.Category dlCategory;
            CategoryDAO categoryDAO = new CategoryDAO();
            categoryDAO.delete(code);
            showcaseModel.removeCategory(code);
            serviceResponse.hasResult = true;
            serviceResponse.result = category;
            return serviceResponse;
        } catch (DAOException daoException) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = daoException.getMessage();
            return serviceResponse;
        } catch (Throwable throwable) {
            serviceResponse.success = false;
            serviceResponse.isError = true;
            serviceResponse.error = "Cannot perform operation.";
            return serviceResponse;
        }
    }

    @PostMapping("/showcase/getCategoryByCode")
    public ServiceResponse getByCode(@RequestParam(name = "categoryCode") Integer code) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if (code <= 0) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = "Invalid code";
            return serviceResponse;
        }
        Category category = showcaseModel.getCategoryByCode(code);
        if (category == null) {
            serviceResponse.success = false;
            serviceResponse.isException = true;
            serviceResponse.exception = "Invalid code.";
            return serviceResponse;
        }
        serviceResponse.result = category;
        serviceResponse.hasResult = true;
        return serviceResponse;
    }

}
