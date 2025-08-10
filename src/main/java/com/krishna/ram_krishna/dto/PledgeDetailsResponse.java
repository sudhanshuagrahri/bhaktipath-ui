package com.krishna.ram_krishna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PledgeDetailsResponse {
    private String uniqueId;
    private String sacredCommitment;
    private String personalCommitment;
}
