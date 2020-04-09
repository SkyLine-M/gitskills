package com.ma.jpa.common.query;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @description ...
 * @date 2020-04-06 23:33:49
 * @author xiaoma
 */
public interface JpaSpec<T> extends Specification<T> {
    @SuppressWarnings("NullableProblems")
    @Override
    default Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        addPredicate(predicateList, root, criteriaBuilder);
        criteriaQuery.distinct(true);
        return criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()])).getRestriction();
    }

    void addPredicate(List<Predicate> predicates, Root<T> root, CriteriaBuilder criteriaBuilder);
}

