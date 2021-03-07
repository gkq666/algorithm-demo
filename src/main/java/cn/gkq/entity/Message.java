package cn.gkq.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author GKQ
 * @Classname Message
 * @Description TODO
 * @Date 2021/1/25
 */
@Data
@Accessors(chain = true)
public class Message implements Delayed {
    private static final long serialVersionUID = 8767456240343138943L;

    private Long id;
    private String body;
    private Long executeTime;

    public Message(Long id, String body, Long delayime) {
        this.id = id;
        this.body = body;
        this.executeTime = TimeUnit.NANOSECONDS.convert(delayime, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed delay) {
        Message message = (Message) delay;
        return this.getId().longValue() > message.getId().longValue() ?
                1 : (this.getId().longValue() == message.getId().longValue() ? 0 : -1);
    }
}
