import java.util.List;

/**
 * Generic repository interface for data access operations (DIP - Dependency Inversion Principle)
 */
public interface Repository<T, ID> {
    /**
     * Save an entity
     */
    T save(T entity);

    /**
     * Find entity by ID
     */
    T findById(ID id);

    /**
     * Get all entities
     */
    List<T> findAll();

    /**
     * Delete entity by ID
     */
    boolean deleteById(ID id);

    /**
     * Check if entity exists
     */
    boolean existsById(ID id);

    /**
     * Get count of entities
     */
    long count();
}
