package challenge.demo.inventory.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "InventoryItem")
@Table(
        name = "InventoryItem",
        uniqueConstraints = {
                @UniqueConstraint(name = "inventory_name_unique", columnNames = "name")
        }
)
public class InventoryItem {
    @Id
    @SequenceGenerator(
            name = "inventoryItem_sequence",
            sequenceName = "inventoryItem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "inventoryItem_sequence"
    )
    @Column(
            name = "inventory_id",
            updatable = false
    )
    private Long inventory_id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "creation_date"
    )
    private LocalDate creation_date;

    @Column(
            name = "avail_status"
    )
    private Integer avail_status;

    @Column(
            name = "avail_date"
    )
    private LocalDate avail_date;

    @Column(
            name = "stock"
    )
    private Integer stock;

    public InventoryItem(String name, String description, LocalDate creation_date, Integer avail_status, LocalDate avail_date, Integer stock) {
        this.name = name;
        this.description = description;
        this.creation_date = creation_date;
        this.avail_status = avail_status;
        this.avail_date = avail_date;
        this.stock = stock;
    }

    public InventoryItem() {

    }

    public Long getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Long inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public Integer getAvail_status() {
        return avail_status;
    }

    public void setAvail_status(Integer avail_status) {
        this.avail_status = avail_status;
    }

    public LocalDate getAvail_date() {
        return avail_date;
    }

    public void setAvail_date(LocalDate avail_date) {
        this.avail_date = avail_date;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "inventory_id=" + inventory_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creation_date=" + creation_date +
                ", avail_status=" + avail_status +
                ", avail_date=" + avail_date +
                ", stock=" + stock +
                '}';
    }
}
