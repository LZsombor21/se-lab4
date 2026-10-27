package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_Success_DecreasesTorpedoCount() {
        TorpedoStore store = new TorpedoStore(10, 0.0);
        boolean result = store.fire(3);
        assertTrue(result);
        assertEquals(7, store.getTorpedoCount());
    }

    @Test
    void fire_Failure_KeepsTorpedoCount() {
        TorpedoStore store = new TorpedoStore(10, 1.0);
        boolean result = store.fire(1);
        assertFalse(result);
        assertEquals(10, store.getTorpedoCount());
    }

    @Test
    void fire_LastTorpedo_StoreBecomesEmpty() {
        TorpedoStore store = new TorpedoStore(1, 0.0);
        store.fire(1);
        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void isEmpty_StoreWithTorpedos_ReturnsFalse() {
        TorpedoStore store = new TorpedoStore(5, 0.0);
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        TorpedoStore store = new TorpedoStore(2, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
    }

    @Test
    void fire_ZeroTorpedos_ThrowsException() {
        TorpedoStore store = new TorpedoStore(5, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }
        @Test
    void getTorpedoCount_NewStore_ReturnsInitialCount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_AllTorpedosAtOnce_StoreBecomesEmpty() {
        TorpedoStore store = new TorpedoStore(4, 0.0);
        boolean result = store.fire(4);
        assertTrue(result);
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_EmptyStore_ThrowsException() {
        TorpedoStore store = new TorpedoStore(0, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void fire_NegativeNumber_ThrowsException() {
        TorpedoStore store = new TorpedoStore(5, 0.0);
        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    }
}