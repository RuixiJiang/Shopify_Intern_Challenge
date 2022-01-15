package challenge.demo.inventory.service;

import challenge.demo.inventory.dao.InventoryItemRepository;
import challenge.demo.inventory.model.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryItemService(InventoryItemRepository inventoryItemRepository){
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public List<InventoryItem> getInventoryItems(){
        return inventoryItemRepository.findAll();
    }

    public void addNewInventoryItem(InventoryItem inventoryItem) {
        System.out.println(inventoryItem);
        inventoryItemRepository.save(inventoryItem);
    }

    public void deleteInventoryItem(Long inventoryItemId) {
        if(!inventoryItemRepository.existsById(inventoryItemId)){
            throw new IllegalStateException("Inventory item with id " + inventoryItemId + " does not exist.");
        }
        inventoryItemRepository.deleteById(inventoryItemId);
    }
}
