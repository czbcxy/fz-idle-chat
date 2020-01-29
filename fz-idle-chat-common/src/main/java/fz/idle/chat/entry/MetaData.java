package fz.idle.chat.entry;

import lombok.Data;

import java.io.Serializable;

@Data
public class MetaData<T> implements Serializable {

    private static final long serialVersionUID = 6297604864327678805L;

    public String type;
    public T date;
}
