package core.gameobjects.ai;

import java.util.LinkedList;

import core.enums.EnemyAIState;
import core.enums.GenePool;

public abstract class EnemyAI {
	
	private final int NUM_ANCESTORS = 25;
	
	EnemyAIState currentState;
	float searchEfficiency;
	float walkSpeed;
	float attackSpeed;
	
	
	float height, width;
	
	float randomGeneChance;
	int numGenes;
	
	int[] ancestorDamage = new int[NUM_ANCESTORS];
	int damageDone;
	
	@SuppressWarnings("unchecked")
	protected LinkedList<GenePool>[] ancestors = new LinkedList[NUM_ANCESTORS];
	protected LinkedList<GenePool> genetics = new LinkedList<>();
	
	protected abstract void applyGenetics();
	
	protected abstract void setGenesFromAncestors();
	
	protected abstract void generateAncestors();
	
	protected abstract void addThisToAncestors();
	
	
}
