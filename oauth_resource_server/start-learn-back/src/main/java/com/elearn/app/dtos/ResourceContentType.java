package com.elearn.app.dtos;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class ResourceContentType {

    private Resource resource;
    private  String contentType;
}
