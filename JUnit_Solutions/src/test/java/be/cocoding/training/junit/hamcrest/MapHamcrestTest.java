package be.cocoding.training.junit.hamcrest;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MapHamcrestTest {

    Map<Integer, String> map;

    @Before
    public void setUp() {
        map = new HashMap<>();
        map.put(1, "Claudia");
        map.put(2, "Arnaud");
        map.put(3, "Médéric");
        map.put(4, "Anthony");
    }

    @Test
    public void testHasEntry_SingleEntry(){
        // Key = 1  ;  Value = "Claudia          --> Match:   1 - Claudia
        assertThat(map, hasEntry(1, "Claudia"));

        // Key > 1 ;  Value starts with "Arn"    --> Match : 2 - Arnaud
        assertThat(map, hasEntry( greaterThan(1), startsWith("Arn")  ));
    }

    @Test
    public void testHasEntry_MultipleEntry() {
        // Has both entries   3 - Méderic  and   4 - Anthony
        assertThat(map, allOf(
                hasEntry(3 , "Médéric"),
                hasEntry(4, "Anthony")
        ));
    }

    @Test
    public void testHasKey() {
        assertThat("At least one entry with key value <= 4", map, hasKey( lessThanOrEqualTo(4) ) );
        // équivalent à
        assertThat("At least one key with value <= 4", map.keySet(),  hasItem(lessThanOrEqualTo(4)));

        // All keys must be a value <= 4
        assertThat(map.keySet(),  everyItem( lessThanOrEqualTo(4) ));
    }

    @Test
    public void testHasValue() {
        assertThat(map,  hasValue("Claudia"));
        assertThat(map, allOf( hasValue("Claudia"), hasValue("Arnaud"), hasValue("Médéric"), hasValue("Anthony") ));
    }
}
