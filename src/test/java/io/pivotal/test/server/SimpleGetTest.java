package io.pivotal.test.server;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gemstone.gemfire.cache.Region;

import io.pivotal.test.server.conf.CacheConfig;
import io.pivotal.test.server.conf.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CacheConfig.class })
public class SimpleGetTest {

	@Resource(name = Constants.ACCOUNT_REGION_BEAN_NAME)
	private Region<String, String> accountRegion;

	@Before
	public void setUp() throws Exception {
		Assert.assertNotNull(accountRegion);
	}

	@Test
	public void simpleGetTest() {
		String x = accountRegion.get("X");
		Assert.assertNull(x);
		accountRegion.put("X", "X");
		x = accountRegion.get("X");
		Assert.assertEquals("X", x);
	}
}
