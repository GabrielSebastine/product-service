package com.kudi_test.product_service.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Pageable;

public class Utility {

    public static PageRequest getPageable(int pageNo, int pageSize){
        return PageRequest.of(pageNo, pageSize);
    }

    public static String getContextPath() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    public static String getRequestUrl() {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUriString();
    }
}
