package ch.samt.customers.service;

import ch.samt.customers.data.MealGroupRepository;
import ch.samt.customers.domain.MealGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealGroupService {
    private final MealGroupRepository mealGroupRepository;

    @Autowired
    public MealGroupService(MealGroupRepository mealGroupRepository) {
        this.mealGroupRepository = mealGroupRepository;
    }

    public MealGroup findById(Long mealGroupId) {
        return this.mealGroupRepository.findById(mealGroupId).orElseThrow();

    }
}