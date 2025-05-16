package com.order.food.service;

import com.order.food.dto.FoodCategoryGetDto;
import com.order.food.dto.FoodCategoryPostDto;
import com.order.food.model.FoodCategory;
import com.order.food.repository.FoodCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    FoodCategoryRepository foodCategoryRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public int addFoodCategory(FoodCategoryPostDto dto) {
        FoodCategory category = modelMapper.map(dto, FoodCategory.class);
        foodCategoryRepository.save(category);
        return 1;
    }

    @Override
    public List<FoodCategoryGetDto> fetchFoodCategory() {
        List<FoodCategory> categoryList = foodCategoryRepository.findAll();
        return categoryList.stream()
                .map(category -> {
                    FoodCategoryGetDto dto = new FoodCategoryGetDto();
                    dto.setId(category.getId());
                    dto.setName(category.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
