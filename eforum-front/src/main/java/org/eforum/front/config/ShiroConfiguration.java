package org.eforum.front.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.eforum.front.security.FrontAccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public EhCacheManager cacheManager() {
        LOG.info("配置shiro缓存管理器");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    @Bean
    public WebSecurityManager securityManager(AuthorizingRealm realm) {
        LOG.info("配置凭证匹配器");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        realm.setCredentialsMatcher(hashedCredentialsMatcher);

        LOG.info("配置shiro安全管理器");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(WebSecurityManager securityManager) {
        LOG.info("配置shiro过滤器");

        List<String> definitions = new ArrayList<>();
        definitions.add("/dashboard/** = access");
        definitions.add("/** = anon");

        Map<String, Filter> filters = new HashMap<>();
        filters.put("access", new FrontAccessControlFilter());

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setFilters(filters);
        shiroFilter.setFilterChainDefinitions(StringUtils.join(definitions, "\r\n"));
        return shiroFilter;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}