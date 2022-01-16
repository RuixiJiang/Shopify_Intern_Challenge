package challenge.demo.inventory.controller;

import challenge.demo.inventory.dao.InventoryItemRepository;
import challenge.demo.inventory.model.InventoryGroup;
import challenge.demo.inventory.model.InventoryItem;
import challenge.demo.inventory.service.InventoryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/inventory_group")
public class InventoryGroupController {
    private final InventoryGroupService inventoryGroupService;

    @Autowired
    public InventoryGroupController(InventoryGroupService inventoryGroupService) {
        this.inventoryGroupService = inventoryGroupService;
    }

    @PostMapping
    public void addNewGroup(@RequestBody InventoryGroup inventoryGroup){
        inventoryGroupService.addNewGroup(inventoryGroup);
    }

    @PutMapping("/{groupId}/items/{itemId}")
    public InventoryGroup putItemToGroup(@PathVariable Long groupId, @PathVariable Long itemId){
        return inventoryGroupService.putItemToGroup(groupId, itemId);
    }

}
