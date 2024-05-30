package pwasnyo.rabbitmq_json_message.model.dto;

import lombok.Data;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
}
