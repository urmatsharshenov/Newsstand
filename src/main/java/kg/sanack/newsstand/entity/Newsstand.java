package kg.sanack.newsstand.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "newsstand")
public class Newsstand {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String openingHours;
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
