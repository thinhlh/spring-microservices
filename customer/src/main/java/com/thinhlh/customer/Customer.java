package com.thinhlh.customer;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.descriptor.jdbc.UUIDJdbcType;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="customer_id_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastname;
    private String email;
}
