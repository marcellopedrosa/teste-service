package br.com.csc.admin_service.configuration;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

@Configuration
public class BeanConfiguration {

    // @Bean
    // BeanFactoryPostProcessor
    // beanFactoryPostProcessor(ConfigurableApplicationContext beanRegistry) {
    // return beanFactory -> {
    // genericApplicationContext((BeanDefinitionRegistry)
    // beanRegistry.getBeanFactory());
    // };
    // }

    @Bean
    BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
        return beanFactory -> {
            genericApplicationContext(
                    (BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry)
                            .getBeanFactory());
        };
    }

    void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
        ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
        beanDefinitionScanner.addIncludeFilter(removeModelAndEntitiesFilter());
        beanDefinitionScanner.scan("br.com.csc.admin_service.usecases" );
    }

    static TypeFilter removeModelAndEntitiesFilter() {
        return (MetadataReader mr, MetadataReaderFactory mrf) -> !mr.getClassMetadata()
                .getClassName()
                .endsWith("Model");
    }
}
