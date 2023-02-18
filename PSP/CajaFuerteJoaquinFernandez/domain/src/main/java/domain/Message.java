package domain;

import lombok.Data;

@Data
public class Message {
    private int id;
    private int id_folder;
    private String cipherText;
}
