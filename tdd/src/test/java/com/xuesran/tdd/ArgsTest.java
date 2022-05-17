package com.xuesran.tdd;

import com.xuesran.tdd.chapter01.Args;
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
    @Test
    public void should_set_boolean_option_true_if_flag_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");
        assertTrue(option.logging());
    }

    @Test
    public void should_set_boolean_option_to_false_if_flag_not_present() {
        BooleanOption option = Args.parse(BooleanOption.class);
        assertFalse(option.logging());
    }


    // TODO: -Integer -p 8080
    @Test
    public void should_parse_int_as_option_value() {
        IntOption intOption = Args.parse(IntOption.class, "-p", "8080");
        assertEquals(8080, intOption.port);
    }

    // TODO: -String -d /usr/log
    @Test
    public void should_parse_string_as_option_value(){
        StringOption stringOption = Args.parse(StringOption.class, "-d", "/usr/log");
        assertEquals("/usr/log", stringOption.directory);
    }

    // TODO: multi Options: -l -p 8080 -d /usr/logs
    @Test
    public void should_parse_multi_options() {
        MultiOptions multiOptions = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(multiOptions.logging());
        assertEquals(8080, multiOptions.port());
        assertEquals("/usr/logs", multiOptions.directory());
    }

    @Test
    @Disabled
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group);
        assertArrayEquals(new int[]{1, 2, -3, 5}, options.decimals);
    }
    // sad path:
    //     -bool -l t / -l t f
    //     -int -p/ -p 8080 80801
    //     -string -d/ -d /usr/logs /var/usr
    // default value"
    //   - bool : false
    //   - int : 0
    //   - string : ""





    static record MultiOptions(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {

    }

    static record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {

    }

    static record BooleanOption(@Option("l") boolean logging) {

    }

    static record IntOption(@Option("p") int port) {

    }

    static record StringOption(@Option("d") String directory){

    }
}
