package com.example.demo.filters.search;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SearchSpecificationFilter<T> {
    private final List<SearchCriterion> criteria;
    private final boolean isAndAllCriteria = true;

    public SearchSpecificationFilter(List<SearchCriterion> criteria) {
        this.criteria = criteria;
    }

    public SearchSpecificationFilter<T> filterWith(SearchCriterion criterion) {
        criteria.add(criterion);
        return this;
    }

    public Specification<T> build() {
        if (criteria.size() == 0) {
            return null;
        }
        List<Specification<T>> specifications = criteria.stream()
                .map(SearchSpecification<T>::new)
                .collect(Collectors.toList());
        Specification<T> result = specifications.get(0);
        for (int i = 1; i < criteria.size(); i++) {
            result = isAndAllCriteria
                    ? Specification.where(result).or(specifications.get(i))
                    : Specification.where(result).and(specifications.get(i));
        }
        return result;
    }
}
