package core.levels.testRealm;

import core.enums.LevelID;
import core.levels.LevelSpawner;

public class TestRealm extends LevelSpawner {
	
	public TestRealm(LevelID lId, String filename) {
		super(lId, filename);
		
		bL1 = new TestRealmBL1();
	}
	
	
}
