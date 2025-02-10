package co.com.ancas.configuration;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ThymeleafConfigurationTest {

    @org.junit.jupiter.api.Test
    void templateResolver() {
        //Arrange
        ThymeleafConfiguration thymeleafConfiguration = new ThymeleafConfiguration();
        //Act
        var result = thymeleafConfiguration.templateResolver();
        //Assert
        assertNotNull(result);
    }

    @org.junit.jupiter.api.Test
    void templateEngine() {
        //Arrange
        ThymeleafConfiguration thymeleafConfiguration = new ThymeleafConfiguration();
        //Act
        var result = thymeleafConfiguration.templateEngine();
        //Assert
        assertNotNull(result);
    }

}