package com.example.messageservice.config;

import com.example.messageservice.client.EmployeeClient;
import com.example.messageservice.client.EmployeeDutyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Value("${employee.baseUrl}")
    private String EMPLOYEE_BASE_URL;

    @Value("${employee.duty.baseUrl}")
    private String EMPLOYEE_DUTY_BASE_URL;

    @Bean
    public WebClient employeeDutyWebClient() {
        return WebClient
                .builder()
                .baseUrl(EMPLOYEE_DUTY_BASE_URL)
                .build();
    }

    @Bean
    public WebClient employeeWebClient() {
        return WebClient
                .builder()
                .baseUrl(EMPLOYEE_BASE_URL)
                .build();
    }

    @Bean
    public EmployeeDutyClient employeeDutyClient() {
        var httpServiceFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(employeeDutyWebClient()))
                .build();
        return httpServiceFactory.createClient(EmployeeDutyClient.class);
    }

    @Bean
    public EmployeeClient employeeClient() {
        var httpServiceFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(employeeWebClient()))
                .build();
        return httpServiceFactory.createClient(EmployeeClient.class);
    }
}
