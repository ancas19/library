package co.com.ancas.postgres.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void forrmatStringSearch() {
        //Arrange
        String value = "value";
        //Act
        String result = Utils.forrmatStringSearch(value);
        //Assert
        assertEquals("%%%s%%".formatted(value), result);
    }

}