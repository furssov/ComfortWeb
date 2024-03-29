package comfort.com.ua.models;

import jakarta.persistence.*;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


import java.time.LocalDate;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Будь ласка, введіть своє ім'я та прізвище")
    private String name;

    @Column(name = "comment", length = 5000)
    @NotEmpty(message = "напишіть будь ласка ваші побажання щодо заказу або якусь важливу інформацію")
    @Size(max = 5000, message = "будь ласка, напишіть коментар коротший ")
    private String comment;

    @Column(name = "date_of_order")
    private LocalDate dateOfOrder;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\+380[0-9]{9}", message = "Введіть номер телефону у форматі +380731090986")
    @NotEmpty(message = "Номер телефона не має бути порожнім")
    @NotNull
    private String phoneNumber;

    @Column(name = "email")
    @NotEmpty(message = "Email не має бути порожнім")
    @Email(message = "Невірний запис email")
    private String email;

    @ManyToOne()
    @JoinColumn(name = "furniture_id", referencedColumnName = "id")
    private Furniture furnitureId;


    public Order(String name, String comment, LocalDate dateOfOrder, String phoneNumber, String email, Furniture furnitureId) {
        this.name = name;
        this.comment = comment;
        this.dateOfOrder = dateOfOrder;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.furnitureId = furnitureId;
    }
}
