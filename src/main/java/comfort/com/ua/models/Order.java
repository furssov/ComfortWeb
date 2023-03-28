package comfort.com.ua.models;

import jakarta.persistence.*;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotEmpty(message = "Будь ласка, введіть своє ім'я та прізвище")
    private String name;

    @Column(name = "comment")
    @NotEmpty(message = "напишіть будь ласка ваші побажання щодо заказу або якусь важливу інформацію")
    private String comment;

    @Column(name = "date_of_order")
    private LocalDate dateOfOrder;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\+380[0-9]{9}", message = "Введіть номер телефону у форматі +3801231223")
    @NotEmpty(message = "має бути номер телефона")
    @NotNull
    private String phoneNumber;

    @Column(name = "email")
    @NotEmpty(message = "email не має бути пустим")
    @Email(message = "Невірний запис email")
    private String email;

    @ManyToOne()
    @JoinColumn(name = "furniture_id", referencedColumnName = "id")
    private Furniture furnitureId;




}
