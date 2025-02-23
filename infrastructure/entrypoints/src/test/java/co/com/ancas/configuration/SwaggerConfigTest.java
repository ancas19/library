package co.com.ancas.configuration;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    @org.junit.jupiter.api.Test
    void openAPI() {
        //Arrange
        SwaggerConfig swaggerConfig = new SwaggerConfig();
        //Act
        var result = swaggerConfig.openAPI();
        //Assert
        assertNotNull(result);
    }

}