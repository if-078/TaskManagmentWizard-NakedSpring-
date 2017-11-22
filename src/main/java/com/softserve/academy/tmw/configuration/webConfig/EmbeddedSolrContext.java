package com.softserve.academy.tmw.configuration.webConfig;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import javax.annotation.Resource;

@Configuration
@EnableSolrRepositories("src.main.java.solr.repositories")
@Profile("dev")
@PropertySource("classpath:application.properties")
public class EmbeddedSolrContext {

    @Resource
    private Environment environment;

    @Bean
    public EmbeddedSolrServer solrServerFactoryBean() {
        String folder = "src/main/resources/server/solr/";
        CoreContainer container = new CoreContainer(folder);
        container.load();
        return new EmbeddedSolrServer(container, "myName");

    }

    @Bean
    public SolrTemplate solrTemplate(EmbeddedSolrServer server) throws Exception {
        SolrTemplate solrTemplate = new SolrTemplate(server);
        return solrTemplate;
    }
}