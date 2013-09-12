import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-8-28
 */
public class Redis {
    public static void main(String[] args) {
        Jedis redis=new Jedis("localhost");
        redis.set("inc".getBytes(),new byte[]{'1'});
        System.out.println(redis.incrBy("inc", 2));
    }
}
