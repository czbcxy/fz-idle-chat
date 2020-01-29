package fz.idle.chat.serverImpl;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 返回
 */
@Component
public class FutureResponse {

    public void setDate(Object msg) {
        EntryResponse<Object> response = new EntryResponse<>();
        System.out.println(msg);
    }

    @Data
    class EntryResponse<T> {
        private String type;
        private T date;
    }
}
