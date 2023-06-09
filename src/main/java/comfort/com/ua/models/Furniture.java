package comfort.com.ua.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "furniture")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 40000)
    private String description;

    @Column
    private String price;

    @ManyToOne
    @JoinColumn(name = "furniture_type_of_order_id", referencedColumnName = "id")
    private FurnitureTypeOfOrder furnitureTypeOfOrderId;

    @OneToMany(mappedBy = "furnitureId", cascade = CascadeType.ALL)
    private List<Order> orders;


    @Column
    @Enumerated(EnumType.STRING)
    private FurnitureType type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "furniture", cascade = CascadeType.ALL)
    private List<ImageDB> images;

    @Column
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Override
    public String toString() {
        return "Furniture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
