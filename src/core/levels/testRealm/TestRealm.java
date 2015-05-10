package core.levels.testRealm;

import core.enums.LevelID;
import core.levels.LevelBuilder;

public class TestRealm extends LevelBuilder {
	
	public TestRealm(LevelID lId, String filename) {
		super(lId, filename);
		
		bL1 = new TestRealmBL1();
	}
	
	
}
