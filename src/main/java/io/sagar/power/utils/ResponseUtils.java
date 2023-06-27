package io.sagar.power.utils;

import io.sagar.power.dto.MessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class ResponseUtils {

    private final static String FAILED = "Failed";
    private final static String SUCCESS = "Success";


    public static MessageResponseDTO<?> responseGenerator(Object data, Boolean success, String message) {
        MessageResponseDTO<Object> messageResponseDTO = new MessageResponseDTO<Object>();
        messageResponseDTO.setSuccess(success);
        messageResponseDTO.setMessage(message);
        if (!Objects.isNull(data)) {
            messageResponseDTO.setData(data);
        }
        return messageResponseDTO;
    }

    public static ResponseEntity<MessageResponseDTO<?>> responseGenerator(Object data, Boolean success, HttpStatus httpStatus) {
        MessageResponseDTO<Object> messageResponseDTO = new MessageResponseDTO<Object>();
        messageResponseDTO.setSuccess(success);
        messageResponseDTO.setMessage(success ? SUCCESS : FAILED);
        if (!Objects.isNull(data)) {
            messageResponseDTO.setData(data);
        }
        return ResponseEntity.status(httpStatus.value()).body(messageResponseDTO);
    }
}
