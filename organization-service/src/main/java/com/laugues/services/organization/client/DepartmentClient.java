package com.laugues.services.organization.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.laugues.services.organization.model.Department;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "department-service", fallback = DepartmentClient.DepartmentClientFallback.class)
public interface DepartmentClient
{

    @GetMapping("/organization/{organizationId}")
    List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId);

    @GetMapping("/organization/{organizationId}/with-employees")
    List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId);

    @Component
    class DepartmentClientFallback implements DepartmentClient {

        @Override
        public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
            System.out.println("From circuit breaker...");
            return new ArrayList<>();
        }

        @Override
        public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
            System.out.println("From circuit breaker...");
            return new ArrayList<>();
        }
    }
}
