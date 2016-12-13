package com.accion.recruitment.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.accion.recruitment.jpa.entities.BaseEntity;

/**
 * @author Manas
 *
 */
public interface GenericService<T extends BaseEntity, ID extends Serializable> {

	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 *
	 * @param entity
	 * @return the saved entity
	 */
	T save(final T entity);

	/**
	 * Saves all given entities.
	 *
	 * @param entities
	 * @return List of the saved entities
	 */
	List<T> save(List<T> entities);

	/**
	 * Saves an entity and flushes changes instantly.
	 *
	 * @param entity
	 * @return the saved entity
	 */
	T saveAndFlush(T entity);

	/**
	 * Updates a given entity.
	 *
	 * @param entity
	 * @return updated entity
	 */
	T update(final T entity);

	/**
	 * Deletes the entity with the given id.
	 *
	 * @param id
	 */
	void delete(ID id);

	/**
	 *
	 * Deletes a given entity.
	 *
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * Deletes all entities
	 */
	void deleteAll();

	/**
	 * Deletes all the instances in batch.
	 *
	 * @param entities
	 */
	void delete(List<T> entities);

	/**
	 * Flushes all pending changes to the database.
	 */
	void flush();

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id
	 * @return the entity with the given id or {@literal null} if none found
	 */
	T get(ID id);

	/**
	 * Returns whether an entity with the given id exists.
	 *
	 * @param id
	 * @return true if an entity with the given id exists, {@literal false}
	 *         otherwise
	 */
	boolean exists(ID id);

	/**
	 *
	 * @return List of entities managed this service
	 */
	List<T> findAll();

	/**
	 * Returns all entities sorted by the given options.
	 *
	 * @param sort
	 * @return all entities sorted by the given options
	 */
	List<T> findAll(Sort sort);

	/**
	 * Returns all instances of the type with the given IDs.
	 *
	 * @param ids
	 * @return
	 */
	List<T> findAll(List<ID> ids);

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction
	 *
	 * @param page
	 * @param rows
	 * @param sortOrder
	 * @param orderByField
	 * @return a page of entities
	 */
	Page<T> findAll(int page, int rows, String sortOrder, String... orderByField);

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction and
	 * matching predicate with parameters
	 *
	 * @param page
	 * @param rows
	 * @param predicate
	 * @param params
	 * @return a page of entities
	 */
	Page<T> findAll(int page, int rows, String predicate, Map<String, Object> params);

	/**
	 *
	 * @param page
	 * @return @return a page of entities
	 */
	Page<T> findAll(Pageable page);

	/**
	 * Refresh the state of the instance from the database, overwriting changes
	 * made to the entity, if any.
	 *
	 * @param entity
	 */
	void refresh(T entity);

	/**
	 * Returns a list of entities with the ejbql provided.
	 *
	 * @param ejbql
	 * @param params
	 * @return
	 */
	List<T> executeQuery(String ejbql, Map<String, Object> params);

	/**
	 * Returns total number of entity managed by the service
	 *
	 * @return number of entities
	 */
	long count();

	/**
	 * Returns total count of entities matching query.
	 *
	 * @param ejbql
	 * @param params
	 * @return number of entities
	 */
	long count(String ejbql, Map<String, Object> params);
}