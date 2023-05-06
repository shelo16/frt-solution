package com.frt.product.service.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Getter
@Setter
public class UriLocationBuilder {

    public static URI buildUri(String path, Object expandObject) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath().path(path)
                .buildAndExpand(expandObject).toUri();
    }

}
