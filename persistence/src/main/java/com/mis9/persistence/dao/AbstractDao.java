package com.mis9.persistence.dao;

import com.mis9.dao.NotFoundResultsException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;

/**
 *
 * @author gdimitrova
 */
public abstract class AbstractDao<E> {

    protected final String table;

    protected final EntityManager em;

    protected final Class<E> dtoClassName;

    protected AbstractDao(Class<E> dtoClass, EntityManager em) {
        this.table = resolveTableName(dtoClass);
        this.dtoClassName = dtoClass;
        this.em = em;
    }

    public Class<E> getDtoClassName() {
        return dtoClassName;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return em.getCriteriaBuilder();
    }

    protected E getResult(String property, Object value) {
        List<E> results = getResults(property, value);
        if (results.isEmpty()) {
            throw new NotFoundResultsException();
        }
        return results.get(0);
    }

    protected List<E> getResults(String property, Object value) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<E> query = cb.createQuery(dtoClassName);
        Expression<E> prop = query.from(dtoClassName).get(property);
        query.where(cb.equal(prop, value));
        return em.createQuery(query).getResultList();
    }

    protected List<E> getResults(String property, Double value, ComparisonSign sign) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<E> query = cb.createQuery(dtoClassName);
        Expression<Double> prop = query.from(dtoClassName).get(property);
        query.where(makeExpr(cb, prop, value, sign));
        return em.createQuery(query).getResultList();
    }

    private Expression<Boolean> makeExpr(CriteriaBuilder cb, Expression<Double> prop, Double val, ComparisonSign sign) {
        switch (sign) {
            case EQUAL:
                return cb.equal(prop, val);
            case LESS_THAN:
                return cb.lessThan(prop, val);
            default:
                throw new AssertionError(sign.name());
        }
    }

    private String resolveTableName(Class<E> dtoClass) {
        Table t = dtoClass.getAnnotation(Table.class);
        return t == null ? dtoClass.getSimpleName() : t.name();
    }

}
