package com.mis9.dao;

/**
 *
 * @author gdimitrova
 */
public interface SearchDao<E> extends CrudDao<E> {

    public E searchByName(String name);

}
