package challenge.demo.inventory.service;

import challenge.demo.inventory.dao.InventoryGroupRepository;
import challenge.demo.inventory.dao.InventoryItemRepository;
import challenge.demo.inventory.model.InventoryGroup;
import challenge.demo.inventory.model.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class InventoryGroupService {
    @Autowired
    private InventoryGroupRepository inventoryGroupRepository;
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Transactional
    public void addNewGroup(InventoryGroup inventoryGroup){
        String inventoryGroupName = inventoryGroup.getName();
        if(inventoryGroupName != null && inventoryGroupName.length() > 0){
            Optional<InventoryGroup> inventoryGroupOptional = inventoryGroupRepository.findInventoryGroupByName(inventoryGroupName);
            if(inventoryGroupOptional.isPresent()){
                throw new IllegalStateException("Name taken.");
            }
            inventoryGroupRepository.save(inventoryGroup);
        }
    }

    @Transactional
    public InventoryGroup putItemToGroup(Long groupId, Long itemId) {
        InventoryGroup inventoryGroup = inventoryGroupRepository.findById(groupId).orElseThrow(()
                -> new IllegalStateException("Inventory group with id " + groupId + " does not exist."));
        InventoryItem inventoryItem = inventoryItemRepository.findById(itemId).orElseThrow(()
                -> new IllegalStateException("Inventory item with id " + itemId + " does not exist."));
        inventoryGroup.putItem(inventoryItem);
        return inventoryGroupRepository.save(inventoryGroup);
    }

    @Transactional
    public InventoryGroup removeItemFromGroup(Long groupId, Long itemId) {
        InventoryGroup inventoryGroup = inventoryGroupRepository.findById(groupId).orElseThrow(()
                -> new IllegalStateException("Inventory group with id " + groupId + " does not exist."));
        InventoryItem inventoryItem = inventoryItemRepository.findById(itemId).orElseThrow(()
                -> new IllegalStateException("Inventory item with id " + itemId + " does not exist."));
        inventoryGroup.removeItem(inventoryItem);
        return inventoryGroupRepository.save(inventoryGroup);
    }
}
