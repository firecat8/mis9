/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.dao;

import java.util.List;

/**
 *
 * @author gdimitrova
 * @param <Entity>
 */
public interface CrudDao<Entity> {

    public void save(Entity entity);

    public void delete(int id);

    public Entity loadById(int id) throws NotFoundResultsException;

    public void saveAll(List<Entity> entities);

    public List<Entity> loadAll();
}
