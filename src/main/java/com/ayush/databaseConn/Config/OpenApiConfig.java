package com.ayush.databaseConn.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(
        title = "RESTAPI",
        contact = @Contact(
                name = "Ayush",
                email = "ayush25@gmail.com",
                url = "http://null.com/"),
        version = "v1",
        description = "Api documentation ",
        license = @License(
                name = "License Info ",
                url = "http://license.com",
                identifier = "NoLicense"

        ))
)
public class OpenApiConfig {
}
