package com.example.demo.filters.search;

import com.example.demo.common.SearchOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
public class SearchSpecification<T> implements Specification<T> {
    private SearchCriterion criterion;

    public SearchSpecification(SearchCriterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        SearchOperation operation = SearchOperation.valueOf(criterion.getOperation());
        switch (operation) {
            case EQUALITY:
                return builder.equal(root.get(criterion.getField()), criterion.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criterion.getField()), criterion.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criterion.getField()), criterion.getValue().toString());
            case CONTAINS:
                return builder.like(root.get(criterion.getField()), "%" + criterion.getValue() + "%");
            default:
                return null;
        }
    }
}
