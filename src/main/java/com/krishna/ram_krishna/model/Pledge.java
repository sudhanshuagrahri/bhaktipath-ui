package com.krishna.ram_krishna.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uniqueId;
    private String sacredCommitment;
    private String personalCommitment;
}
