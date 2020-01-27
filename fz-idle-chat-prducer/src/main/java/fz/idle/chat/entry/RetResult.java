package fz.idle.chat.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class RetResult implements Serializable {
    private static final long serialVersionUID = -1807235799203112009L;
    private String code;
    private String message;
    private Objects date;

}
