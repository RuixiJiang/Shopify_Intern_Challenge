package challenge.demo.inventory.dao;

import challenge.demo.inventory.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    @Query("SELECT i FROM InventoryItem i WHERE i.name = ?1")
    Optional<InventoryItem> findInventoryItemByName(String name);
}
