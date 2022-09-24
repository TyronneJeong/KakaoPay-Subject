package org.kakaopay.memebership;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MembershipApplication {

    private final static Logger log = LoggerFactory.getLogger(MembershipApplication.class);

    public static void main(String[] args) {
        log.debug("### application is start : "+System.currentTimeMillis());
        SpringApplication.run(MembershipApplication.class);
        log.debug("### application is end : "+System.currentTimeMillis());
    }
}