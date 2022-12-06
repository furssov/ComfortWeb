package comfort.com.ua.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "furniture_type_of_order")
@Data
@NoArgsConstructor
public class FurnitureTypeOfOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToOne()
    @JoinColumn(name = "order_type_id", referencedColumnName = "id")
    private TypeOfOrder orderId;

    @OneToMany(mappedBy = "furnitureTypeOfOrderId")
    private List<Furniture> furniture;

}
