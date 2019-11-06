/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.holder;

import com.mis9.domain.Entity;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public interface Holdable<E extends Entity> {

    public void addAll(List<E> entitites);

    public void add(E entity);

    public void remove(Object key);

    public E getEntity(Object name);

    public List<Object> getNames();

    public Object getName(E entity);

    public void clear();
}
