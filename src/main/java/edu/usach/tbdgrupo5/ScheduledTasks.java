package edu.usach.tbdgrupo5;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	public MongoConnection mc = new MongoConnection("tweets", "tweetsPrueba");
	public Lucene lucene = new Lucene(mc);

    @Scheduled(cron="*/600 * * * * *")
    public void indexCreateTask() {
    	mc.connect();
    	System.out.println("[Tarea Programada]Indexando...");
    	lucene.indexCreate();
    	System.out.println("[Tarea Programada]Fin de indexación.");
        //System.out.println("hola fuí programado\n");
    }
    @Scheduled(cron="*/10 * * * * *")
    public void indexSearchTask() {
    	System.out.println("[Tarea Programada]Indexando...");
    	lucene.indexSearch("bad bunny");
    	System.out.println("[Tarea Programada]Fin de indexación.");
        //System.out.println("hola fuí programado\n");
    }
}