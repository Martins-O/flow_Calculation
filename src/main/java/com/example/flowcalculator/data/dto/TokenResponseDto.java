package com.example.flowcalculator.data.dto;

import jakarta.websocket.OnOpen;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    String token;
}
