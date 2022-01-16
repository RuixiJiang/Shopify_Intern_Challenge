package challenge.demo.inventory.controller;

import challenge.demo.inventory.model.InventoryItem;
import challenge.demo.inventory.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/inventory_item")
public class InventoryItemController {
    private final InventoryItemService inventoryItemService;

    @Autowired
    public InventoryItemController(InventoryItemService inventoryItemService){
        this.inventoryItemService = inventoryItemService;
    }

    @GetMapping
    public List<InventoryItem> getInventoryItems(){
        return inventoryItemService.getInventoryItems();
    }

    @PostMapping
    public InventoryItem registerNewInventoryItem(@RequestBody InventoryItem inventoryItem){
        return inventoryItemService.addNewInventoryItem(inventoryItem);
    }

    @DeleteMapping(path = "{InventoryItemId}")
    public void deleteInventoryItem(@PathVariable("InventoryItemId")Long inventoryItemId){
        inventoryItemService.deleteInventoryItem(inventoryItemId);
    }

    @PutMapping(path = "/update/{InventoryItemId}")
    public void updateInventoryItem(@PathVariable("InventoryItemId")Long inventoryItemId,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String description,
                                    @RequestParam(required = false) LocalDate avail_date,
                                    @RequestParam(required = false) Integer stock){
        inventoryItemService.updateInventoryItem(inventoryItemId, name, description, avail_date, stock);
    }
}
