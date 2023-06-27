package io.sagar.power.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDTO<T> {
    private Boolean success;
    private String message;
    private T data;
}
