package com.krishna.ram_krishna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArchanaBookingRequest {
    private String uniqueId;
    private String name;
    private String prayers;
    private Long serviceId;
    private String serviceName;
}
