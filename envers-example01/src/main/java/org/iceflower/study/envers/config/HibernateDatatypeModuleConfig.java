package org.iceflower.study.envers.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateDatatypeModuleConfig {
  @Bean
  public com.fasterxml.jackson.databind.Module datatypeHibernateModule() {
    return new Hibernate5Module();
  }
}
