package challenge.demo.inventory.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "InventoryGroup")
@Table(
        name = "InventoryGroup",
        uniqueConstraints = {
                @UniqueConstraint(name = "inventory_group_unique", columnNames = "name")
        }
)
public class InventoryGroup {
    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "group_sequence"
    )
    @Column(
            name = "group_id",
            updatable = false
    )
    private Long group_id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @ManyToMany
    @JoinTable(
            name = "items_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<InventoryItem> group_items = new HashSet<>();

    public InventoryGroup() {

    }

    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<InventoryItem> getGroup_items() {
        return group_items;
    }

    public InventoryGroup(String name) {
        this.name = name;
    }

    public void setGroup_items(Set<InventoryItem> group_items) {
        this.group_items = group_items;
    }

    public void putItem(InventoryItem inventoryItem) {
        group_items.add(inventoryItem);
        inventoryItem.getGroups().add(this);
    }

    public void removeItem(InventoryItem inventoryItem) {
        group_items.remove(inventoryItem);
        inventoryItem.getGroups().remove(this);
    }

    @Override
    public String toString() {
        return "InventoryGroup{" +
                "group_id=" + group_id +
                ", name='" + name + '\'' +
                ", group_items=" + group_items +
                '}';
    }

}
