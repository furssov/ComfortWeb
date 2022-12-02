package comfort.com.ua.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "type_of_order")
@Data
@NoArgsConstructor
public class TypeOfOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String type;

    @OneToMany(mappedBy = "orderId")
    private List<FurnitureTypeOfOrder> list;
}
