package com.krishna.ram_krishna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramJoinRequest {
    private String uniqueId;
    private String name;
    private String details;
    private Long programId;
}
