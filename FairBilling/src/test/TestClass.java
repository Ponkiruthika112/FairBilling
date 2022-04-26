package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import main.BuisnessLogic;  

public class TestClass {

	@Test
	public void testLogic() throws IOException {
		/*
		 * File f = new File("Source.txt"); System.out.println(f.getPath());
		 * System.out.println(f.getAbsolutePath());
		 * System.out.println(f.getCanonicalPath());
		 */
		BuisnessLogic bs = new BuisnessLogic();
		Map<String, List<Long>> hmap = new HashMap<>();
		List<Long> l = Arrays.asList(4l,240000l);
		List<Long> p =Arrays.asList(3l,37000l);
		hmap.put("ALICE99", l);
		hmap.put("CHARLIE", p);
		assertEquals(hmap,bs.processLogs("Source.txt"));
	}
}
