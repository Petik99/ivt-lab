package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryTorpedoStore = Mockito.mock(TorpedoStore.class);
  private TorpedoStore secondaryTorpedoStore = Mockito.mock(TorpedoStore.class);

  @BeforeEach
  public void init(){
    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    Mockito.when(primaryTorpedoStore.fire(1)).thenReturn(true);
    Mockito.when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    Mockito.when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    Mockito.when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single2_Success(){
    // Arrange
    Mockito.when(primaryTorpedoStore.fire(1)).thenReturn(false);
    Mockito.when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    Mockito.when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    Mockito.when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_DoubleSingle_Success(){
    // Arrange
    Mockito.when(primaryTorpedoStore.fire(1)).thenReturn(true);
    Mockito.when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    Mockito.when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    Mockito.when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
  }

  @Test
  public void fireTorpedo_SingleDoubleFirst_Success(){
    // Arrange
    Mockito.when(primaryTorpedoStore.fire(1)).thenReturn(true);
    Mockito.when(secondaryTorpedoStore.fire(1)).thenReturn(true);
    Mockito.when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    Mockito.when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, result);
    assertEquals(true, result2);
  }

}
