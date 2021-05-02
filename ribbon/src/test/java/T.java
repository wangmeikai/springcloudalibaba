/**
 * @USER: WangMeiKai
 * @DATE: 2021/5/2
 * @TIME: 16:47
 **/
public class T {
    public static void main(String[] args) {
        String s = "www.baidu.com";
        boolean matches = s.matches("www\\.\\w+\\.com");
        System.out.println(matches);
    }
}
