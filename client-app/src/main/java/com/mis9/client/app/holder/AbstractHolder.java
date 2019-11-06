/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.holder;

import com.mis9.domain.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gdimitrova
 */
public abstract class AbstractHolder<E extends Entity> implements Holdable<E> {

    protected final Map<Object, E> entities = new HashMap<>();

    @Override
    public void remove(Object key) {
        entities.remove(key);
    }

    @Override
    public void addAll(List<E> cat) {
        cat.forEach((entity) -> {
            add(entity);
        });
    }

    @Override
    public Object getName(E entity) {
        return entities
                .entrySet()
                .stream()
                .filter(entry -> entity.equals(entry.getValue()))
                .findFirst().get().getKey();
    }

    @Override
    public E getEntity(Object name) {
        return entities.get(name);
    }

    @Override
    public List<Object> getNames() {
        return new ArrayList<>(entities.keySet());
    }

    @Override
    public void clear() {
        entities.clear();
    }
}
