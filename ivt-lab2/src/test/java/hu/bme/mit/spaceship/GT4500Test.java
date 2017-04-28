package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockA;
  private TorpedoStore mockB;


  @Before
  public void init(){
	  mockA = mock(TorpedoStore.class);
	  mockB = mock(TorpedoStore.class); 
    this.ship = new GT4500(mockA, mockB);
    //this.ship = new GT4500();
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
	when(mockA.isEmpty()).thenReturn(false);
	when(mockB.isEmpty()).thenReturn(false);

    // Act
    //boolean result = ship.fireTorpedos(FiringMode.SINGLE);
    ship.fireTorpedos(FiringMode.SINGLE);


    // Assert
    //assertEquals(true, result);
    verify(mockA, times(1)).fire(1);
    //verify(mockB, times(1)).fire(1); //or connection?

  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
		when(mockA.isEmpty()).thenReturn(false);
		when(mockB.isEmpty()).thenReturn(false);
		when(mockA.fire(1)).thenReturn(true);
		when(mockB.fire(1)).thenReturn(true);
		
    // Act
    //boolean result = ship.fireTorpedos(FiringMode.ALL);
		ship.fireTorpedos(FiringMode.ALL);
		
    // Assert
    //assertEquals(true, result);
	    verify(mockA, times(1)).fire(1);
	    verify(mockB, times(1)).fire(1);
  }

  @Test
  public void single_primaryEmpty_secondarySuccess(){
    // Arrange
		when(mockA.isEmpty()).thenReturn(true);
		when(mockB.isEmpty()).thenReturn(false);
		when(mockA.fire(1)).thenReturn(true);
		when(mockB.fire(1)).thenReturn(true);
		
    // Act
		ship.fireTorpedos(FiringMode.SINGLE);
		
    // Assert
	    verify(mockA, times(0)).fire(1);
	    verify(mockB, times(1)).fire(1);
  }
  
  @Test
  public void single_bothEmpty(){
    // Arrange
		when(mockA.isEmpty()).thenReturn(true);
		when(mockB.isEmpty()).thenReturn(true);
		when(mockA.fire(1)).thenReturn(true);
		when(mockB.fire(1)).thenReturn(true);
		
    // Act
		ship.fireTorpedos(FiringMode.SINGLE);
		
    // Assert
	    verify(mockA, times(0)).fire(1);
	    verify(mockB, times(0)).fire(1);
  }
  
  @Test
  public void all_bothEmpty(){
    // Arrange
		when(mockA.isEmpty()).thenReturn(true);
		when(mockB.isEmpty()).thenReturn(true);
		when(mockA.fire(1)).thenReturn(true);
		when(mockB.fire(1)).thenReturn(true);
		
    // Act
		ship.fireTorpedos(FiringMode.ALL);
		
    // Assert
	    verify(mockA, times(0)).fire(1);
	    verify(mockB, times(0)).fire(1);
  }
  
  
  /*
  @Test
  public void first_overheat_second_success(){
    // Arrange
		when(mockA.isEmpty()).thenReturn(false);
		when(mockB.isEmpty()).thenReturn(false);
		when(mockA.fire(1)).thenReturn(true);
		when(mockB.fire(1)).thenReturn(true);
		
    // Act
		ship.fireTorpedos(FiringMode.SINGLE); //r=0.01
		
    // Assert
	    verify(mockA, times(1)).fire(1);
	    verify(mockB, times(1)).fire(1);
  }
  */
  
  
}
