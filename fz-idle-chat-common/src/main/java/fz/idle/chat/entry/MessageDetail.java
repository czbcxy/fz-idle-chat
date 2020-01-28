package fz.idle.chat.entry;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageDetail implements Serializable {
    private static final long serialVersionUID = 6297604862067678805L;

    private String action;
    private String clientId;
    private String friendId;
    private String detail;

}
