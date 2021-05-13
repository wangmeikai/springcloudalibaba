import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class TestJson {
    public static void main(String[] args) {
        byte[] b = JSON.toJSONBytes(new User("wmk", 25));
        System.out.println(Arrays.toString(b));
        User u = JSON.parseObject(b, User.class);
        System.out.println(u);
        Object o = JSON.toJSON(new User("wmk", 25));
        System.out.println(o);
        User user = JSON.parseObject(o.toString(), User.class);
        System.out.println(user);

    }
}
