package com.tertulia;

import org.drools.FactHandle;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;

import com.tertulia.firealarm.*;

import static org.junit.Assert.*;

public class Main {

	public static void main(String[] args) {
		//studentValidation();
		fireAlarm();
	}
	
	private static void fireAlarm() {
		try {
			KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
			KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			kBuilder.add(ResourceFactory.newClassPathResource("fireAlarm.drl"), ResourceType.DRL);

			if(kBuilder.hasErrors()){
				System.err.println(kBuilder.getErrors().toString());
			}

			kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
			StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
			System.out.println("wepa");
			
			Room room = new Room("office");
			Fire fire = new Fire(room);
			Sprinkler sprinkler = new Sprinkler(room);
			
			kSession.insert(room);
			FactHandle fireHandle = (FactHandle) kSession.insert(fire);
			kSession.insert(sprinkler);
			
			kSession.fireAllRules();
			
			kSession.retract(fireHandle);
			
			kSession.fireAllRules();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void studentValidation(){
		try {	
			KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
			KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			kBuilder.add(ResourceFactory.newClassPathResource("HelloRules.drl"), ResourceType.DRL);

			if(kBuilder.hasErrors()){
				System.err.println(kBuilder.getErrors().toString());
			}
			
			kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
			
			StatelessKnowledgeSession kSession = kBase.newStatelessKnowledgeSession();
			
			Student student = new Student("Johan", 18);
			
			assertFalse(student.isValid());
			
			kSession.execute(student);
			
			assertTrue(student.isValid());
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	

}
