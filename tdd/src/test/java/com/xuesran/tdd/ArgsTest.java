package com.xuesran.tdd;

import com.xuesran.tdd.chapter01.Option;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import static org.junit.Assert.*;

/**
 * The type Args test.
 *
 * @author xueshun
 */
public class ArgsTest {

    // -l -p 8080 -d /usr/logs
    // [-l] [-p, 8080], [-d, /usr/logs]
    // {-l:[], -p:[8080], -d:[/usr/logs]}
    // Single Options
    // TODO: -Bool -l
    // TODO: -Integer -p 8080
    // TODO: -String -d /usr/log
    // TODO: multi Options: -l -p 8080 -d /usr/logs
    // sad path:
    //     -bool -l t / -l t f
    //     -int -p/ -p 8080 80801
    //     -string -d/ -d /usr/logs /var/usr
    // default value"
    //   - bool : false
    //   - int : 0
    //   - string : ""


    @Test
    @Disabled // 不启用
    public void should_example_1() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.directory());
    }

    @Test
    @Disabled
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group);
        assertArrayEquals(new int[]{1, 2, -3, 5}, options.decimals);
    }

    static record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {

    }

    static record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {

    }
}
