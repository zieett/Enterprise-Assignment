package assignment.entity;

import assignment.id_generator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "OrderDetails")
public class OrderDetail {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ord_detail_seq")
    @GenericGenerator(
            name = "ord_detail_seq",
            strategy = "assignment.id_generator.SequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "DET_"),
                    @org.hibernate.annotations.Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "drug_id", referencedColumnName = "id", nullable = false)
    private Drug drug;

    @Column
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getTransaction() {
        return order;
    }

    public void setTransaction(Order order) {
        this.order = order;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderCost() {
        return quantity * drug.getMoney();
    }

    @Override
    public String toString() {
        return id + " -- " + order.getPurchaseTime() + " -- " + drug.getId() + " -- " + getOrderCost();
    }
}
