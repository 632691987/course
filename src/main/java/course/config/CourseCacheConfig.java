package course.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@EnableCaching
@Configuration
public class CourseCacheConfig {
	
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
		EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
		bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		return bean;
	}
	
	@Bean
	public CacheManager cacheManager(){
		return ehCacheManagerFactoryBean().getObject();
	}
	
	@Bean
	public EhCacheCacheManager ehCacheCacheManager(){
		EhCacheCacheManager manager = new EhCacheCacheManager();
		manager.setCacheManager(cacheManager());
		return manager;
	}
	

	public static void main(String[] args) {
		//1. Create a cache manager
		CacheManager cm = CacheManager.getInstance();
		
		//2. Create a cache called "cache1"
		//cm.addCache("cache1");
		
		//3. Get a cache called "cache1"
		Cache cache = cm.getCache("cache1");
		
		//4. Put few elements in cache
		cache.put(new Element("1","Jan"));
		cache.put(new Element("2","Feb"));
		cache.put(new Element("3","Mar"));
		
		//5. Get element from cache
		Element ele = cache.get("1");
		
		//6. Print out the element
		String output = (ele == null ? null : ele.getObjectValue().toString());
		System.out.println(output);
		
		//7. Is key in cache?
		System.out.println(cache.isKeyInCache("1"));
		System.out.println(cache.isKeyInCache("5"));
		
		//8. shut down the cache manager
		cm.shutdown();
	}

}
