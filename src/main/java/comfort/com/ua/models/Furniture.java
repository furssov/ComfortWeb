package comfort.com.ua.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "furniture")
@Data
@NoArgsConstructor
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String price;

    @ManyToOne
    @JoinColumn(name = "furniture_type_of_order_id", referencedColumnName = "id")
    private FurnitureTypeOfOrder furnitureTypeOfOrderId;

}
