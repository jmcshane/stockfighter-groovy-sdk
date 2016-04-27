package mc.shane.stockfighter.api;

import java.util.Map;
import java.util.List;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockfighterApiIntegrationTests {

	Logger logger = LoggerFactory.getLogger(StockfighterApiIntegrationTests.class);
	String apiKey = System.getProperty("SF_API_KEY");
	StockfighterApi api;

	@Before
	public void setUpApi() {
		api = new StockfighterApi(apiKey, "EXB123456","TESTEX","FOOBAR");
	}

	@Test 
	public void heartbeatWorksAtTestEndpoint() {
		Map<String,Object> map = api.getHeartbeat();
		assertEquals(map.get("ok"), true);
		assertEquals(map.get("error"),"");
	}

	@Test
	public void testVenueIsUp() {
		Map<String,Object> map = api.getVenueHeartbeat();
		assertEquals(map.get("ok"),true);
		assertEquals(map.get("venue"),"TESTEX");
	}

	@Test
	public void getVenueStocks() {
		Map<String,Object> map = api.getVenueStocks();
		assertEquals(map.get("ok"),true);
		List<Map<String,Object>> symbols = (List<Map<String,Object>>) map.get("symbols");
		assertEquals("FOOBAR", symbols.get(0).get("symbol"));
		assertEquals("Foreign Owned Occluded Bridge Architecture Resources", symbols.get(0).get("name"));
	}
}