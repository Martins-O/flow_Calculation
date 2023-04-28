package com.example.flowcalculator.data.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AppDto {
    private Message message;
    private int code;
    private boolean success;
    private Long id;
}
