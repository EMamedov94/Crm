package com.example.crm.utils;

import com.example.crm.entity.Person;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification {
    public static Specification<Person> bySearchValue(String searchValue) {
        return (root, query, criteriaBuilder) -> {
            Predicate byPhone = criteriaBuilder.equal(root.get("phoneNumber"), searchValue);
            Predicate byPassport = criteriaBuilder.equal(root.join("passport").get("passportNumber"), searchValue);
            return criteriaBuilder.or(byPhone, byPassport);
        };
    }
}
