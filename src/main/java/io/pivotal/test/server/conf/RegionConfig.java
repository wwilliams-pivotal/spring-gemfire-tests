package io.pivotal.test.server.conf;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.RegionFactory;
import com.gemstone.gemfire.cache.RegionShortcut;
import org.springframework.util.Assert;

@Configuration
public class RegionConfig implements Constants {

    @Resource(name = ACCOUNT_REGION_BEAN_NAME)
    private Region<String, String> accountRegion;

    private static final Logger LOG = LoggerFactory.getLogger(RegionConfig.class);


    @Bean(name = ACCOUNT_REGION_BEAN_NAME)
    public Region<String, String> createAccountRegion(Cache cache) {
        LOG.info("creating {}", ACCOUNT_REGION_BEAN_NAME);
        RegionFactory<String, String> rf = cache.createRegionFactory(RegionShortcut.PARTITION_PERSISTENT);
        return rf.create(ACCOUNT_REGION_NAME);
    }

    @PostConstruct
    public void post() {
        Assert.notNull(accountRegion, "accountRegion is null");
    }
}
