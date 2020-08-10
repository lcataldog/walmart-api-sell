package com.walmart.api.sell.strategy;

import com.walmart.api.sell.strategies.StrategyFactory;
import com.walmart.api.sell.strategies.StrategyOperationByDescriptionAndBrand;
import com.walmart.api.sell.strategies.StrategyOperationById;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class StrategyFactoryTest {

    @InjectMocks
    private StrategyFactory strategyFactory;

    @Mock
    private StrategyOperationByDescriptionAndBrand strategyOperationByDescriptionAndBrand;

    @Mock
    private StrategyOperationById strategyOperationById;

    @Test
    public void ShouldReturnStrategyOperationByIdClassWhenSearchIsNumeric() {
        String searchProducts = "181";

        assertEquals(strategyOperationById.getClass(), strategyFactory.getStrategy(searchProducts).getClass());
    }

    @Test
    public void ShouldReturnStrategyOperationByDescriptionAndBrandClassWhenSearchIsNumeric() {
        String searchProducts = "ABBA";

        assertEquals(strategyOperationByDescriptionAndBrand.getClass(), strategyFactory.getStrategy(searchProducts).getClass());
    }

    @Test
    public void ShouldReturnStrategyOperationByDescriptionAndBrandClassWhenSearchIsNumericAndCharacter() {
        String searchProducts = "12ABBA";

        assertEquals(strategyOperationByDescriptionAndBrand.getClass(), strategyFactory.getStrategy(searchProducts).getClass());
    }

    @Test
    public void ShouldReturnIllegalArgumentExceptionWhenSearchIsNull() {

        assertThrows(IllegalArgumentException.class, () -> strategyFactory.getStrategy(null));
    }

    @Test
    public void ShouldReturnIllegalArgumentExceptionWhenSearchIsEmpty() {

        assertThrows(IllegalArgumentException.class, () -> strategyFactory.getStrategy(""));
    }
}
