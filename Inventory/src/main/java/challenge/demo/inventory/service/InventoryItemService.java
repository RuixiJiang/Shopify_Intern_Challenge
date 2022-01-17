package challenge.demo.inventory.service;

import challenge.demo.inventory.dao.InventoryItemRepository;
import challenge.demo.inventory.model.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryItemService(InventoryItemRepository inventoryItemRepository){
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Transactional
    public List<InventoryItem> getInventoryItems(){
        return inventoryItemRepository.findAll();
    }

    @Transactional
    public InventoryItem addNewInventoryItem(InventoryItem inventoryItem) {
        String inventoryItemName = inventoryItem.getName();
        if(inventoryItemName != null && inventoryItemName.length() > 0){
            Optional<InventoryItem> inventoryItemOptional = inventoryItemRepository.findInventoryItemByName(inventoryItemName);
            if(inventoryItemOptional.isPresent()){
                throw new IllegalStateException("Name taken.");
            }
            return inventoryItemRepository.save(inventoryItem);
        }
        throw new IllegalStateException("Illegal action to create new inventory item.");
    }

    @Transactional
    public void deleteInventoryItem(Long inventoryItemId) {
        if(!inventoryItemRepository.existsById(inventoryItemId)){
            throw new IllegalStateException("Inventory item with id " + inventoryItemId + " does not exist.");
        }
        inventoryItemRepository.deleteById(inventoryItemId);
    }

    @Transactional
    public InventoryItem updateInventoryItem(Long inventoryItemId, String name, String description, LocalDate avail_date, Integer stock) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemId).orElseThrow(()
                -> new IllegalStateException("Inventory item with id " + inventoryItemId + " does not exist."));
        if(name != null && name.length() > 0 && !Objects.equals(name, inventoryItem.getName())){
            Optional<InventoryItem> inventoryItemOptional = inventoryItemRepository.findInventoryItemByName(name);
            if(inventoryItemOptional.isPresent()){
                throw new IllegalStateException("Name taken.");
            }
            inventoryItem.setName(name);
        }

        if(description != null && !Objects.equals(description, inventoryItem.getDescription())){
            inventoryItem.setDescription(description);
        }

        if(avail_date != null && !Objects.equals(avail_date, inventoryItem.getAvail_date())){
            inventoryItem.setAvail_date(avail_date);
        }

        if(stock != null && stock != inventoryItem.getStock()){
            inventoryItem.setStock(stock);
        }

        return inventoryItem;
    }
}
