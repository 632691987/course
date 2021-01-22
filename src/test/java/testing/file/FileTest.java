package testing.file;

import java.util.Arrays;

/**
 * Created by yan on 2016/3/24.
 */
public class FileTest {

    public static void main(String[] args) throws Exception {
        String source = "你好";

        source.getBytes("UTF-8");
        System.out.println(new String(source.getBytes("UTF-8")));
        Arrays.asList(source.getBytes("UTF-8")).stream().forEach(
                str -> System.out.println(str)
        );
    }
}
