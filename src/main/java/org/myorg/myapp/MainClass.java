package org.myorg.myapp;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.infinispan.manager.DefaultCacheManager;

public class MainClass {

   private static final String INFINISPAN_CONFIG_FILE = "infinispan-config.xml";
   private static final String CACHE_NAME = "transactional-type";

   public static void main(String... args) throws Exception {
      System.out.println("==== Creating Cache Manager ====");
      InputStream infinispanConfig = MainClass.class.getClassLoader().getResourceAsStream(INFINISPAN_CONFIG_FILE);
      DefaultCacheManager cacheManager = new DefaultCacheManager(infinispanConfig);

      System.out.println("==== Putting data into the cache ====");
      cacheManager.getCache(CACHE_NAME).put("test", "test");

      while(true) {
         TimeUnit.SECONDS.sleep(5);
         System.out.println("Cluster members: " + cacheManager.getMembers());
      }
   }


}
