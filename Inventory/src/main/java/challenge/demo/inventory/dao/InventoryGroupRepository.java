package challenge.demo.inventory.dao;

import challenge.demo.inventory.model.InventoryGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InventoryGroupRepository extends JpaRepository<InventoryGroup, Long> {
    @Query("SELECT i FROM InventoryGroup i WHERE i.name = ?1")
    Optional<InventoryGroup> findInventoryGroupByName(String name);
}
