import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class DAGTest {
	
	@Test
	void testConstructor() {
		//Test error case
		assertEquals("Testing case for V < 0: ", -1, new DAG(-1).invalidV);
		
		//Test regular case (V = 0)
		assertEquals("Testing case for V = 0", 0, new DAG(0).V);
		
		//Test regular case (V > 0)
		assertEquals("Testing case for V > 0", 4, new DAG(4).V);
	}

}
