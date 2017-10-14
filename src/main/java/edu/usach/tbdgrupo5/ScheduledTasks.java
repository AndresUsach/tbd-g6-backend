package edu.usach.tbdgrupo5;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	public MongoConnection mc = new MongoConnection("tweets", "tweetsPrueba");
	public Lucene lucene = new Lucene(mc);

    @Scheduled(cron="*/10 * * * * *")
    public void reportCurrentTime() {
    	mc.connect();
    	lucene.indexCreate();
        //System.out.println("hola fuí programado\n");
    }
}