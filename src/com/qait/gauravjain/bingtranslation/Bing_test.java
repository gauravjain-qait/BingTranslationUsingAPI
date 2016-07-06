package bing_translation;

import java.io.IOException;

import org.testng.*;
import org.testng.annotations.*;;

public class Bing_test {
	@Test
	public void test() throws IOException, InterruptedException, Exception{
		Assert.assertEquals(Bing_translation_api.translation(),Bing_translation_gui.translation());
	}
}
