package co.com.ancas.uses_cases.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomCodeTest {

    @Test
    void generateRandomCode() {
        //Arrange
        //Act
        String result = RandomCode.generateRandomCode();
        //Assert
        assertNotNull(result);
    }

}