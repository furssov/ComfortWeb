package comfort.com.ua.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "furniture_type_of_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FurnitureTypeOfOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne()
    @JoinColumn(name = "order_type_id", referencedColumnName = "id")
    private TypeOfOrder orderId;


    @OneToMany(mappedBy = "furnitureTypeOfOrderId", cascade = CascadeType.REMOVE)
    private List<Furniture> furniture;

    @Column
    private String image;


}
