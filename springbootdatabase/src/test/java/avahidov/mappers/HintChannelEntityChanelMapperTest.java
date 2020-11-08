package avahidov.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HintChannelEntityChanelMapperTest {

    @Test
    void channelToHintChannelEntity() {

        HashMap<Integer, String> integerStringHashMap = new HashMap<>();
        integerStringHashMap.put(null, "eleven");
        integerStringHashMap.put(0, "twelve");
        integerStringHashMap.put(37, null);
        integerStringHashMap.put(17, null);
        integerStringHashMap.put(null, "thirteen");

        assertEquals(4, integerStringHashMap.size());
        assertNull(integerStringHashMap.get(37));
        assertEquals("thirteen", integerStringHashMap.get(null));
        assertEquals("twelve", integerStringHashMap.get(0));
    }

    @Test
    void listTest() {
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("fifteen");
        list.add(null);
        list.add("fourteen");

        assertEquals(4, list.size());
        assertTrue(list.contains(null));
        list.remove(null);
        assertEquals(3, list.size());
    }

    @Test
    void treeTest() {
        Set<Integer> set = new HashSet<>();
        Set<Integer> setTree = new TreeSet<>();
        Map<Integer, String> map = new TreeMap<>();

        assertThrows(NullPointerException.class, () -> setTree.add(null));

        assertThrows(NullPointerException.class, () -> map.put(null, "twenty"));

        set.add(null);

        assertEquals(1, set.size());

        set.add(55);
        set.add(null);
        assertEquals(2, set.size());
    }
}