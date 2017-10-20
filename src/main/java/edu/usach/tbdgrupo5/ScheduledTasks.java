package edu.usach.tbdgrupo5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.usach.tbdgrupo5.entities.Artista;
import edu.usach.tbdgrupo5.repository.ArtistaRepository;
import edu.usach.tbdgrupo5.rest.ArtistaService;

@Component
public class ScheduledTasks {

	public MongoConnection mc = new MongoConnection("tweets", "tweetsPrueba");
	public Lucene lucene = new Lucene(mc);
	@Autowired
	public ArtistaRepository artistarepository ;
	public Iterable<Artista> artistas = null;
    @Scheduled(cron="*/10 * * * * *")
    public void indexCreateTask() {
    	mc.connect();
    	System.out.println("[Tarea Programada]Indexando...");
    	lucene.indexCreate();
    	System.out.println("[Tarea Programada]Fin de indexación.");
        //System.out.println("hola fuí programado\n");
    }
    @Scheduled(cron="*/10 * * * * *")
    public void indexSearchTask() {
    	
    	artistas= artistarepository.findAll();
    	System.out.println("[Tarea Programada]Buscando...");
    	//lucene.indexSearch("bad bunny");
    	//artistas.forEach(artista);
    	for (Artista artista:artistas){
    		lucene.indexSearch2(artista.getNombre());
    		System.out.println("Artista:"+ artista.getNombre());
    		System.out.println("Positivos: "+ lucene.getpositiveResult());
    		System.out.println("Negativos: "+ lucene.getnegativeResult());
    		System.out.println("Neutral: "+ lucene.getneutralResult());
    	}
    	System.out.println("[Tarea Programada]Fin de bucar.");
    	
        //System.out.println("hola fuí programado\n");
    }
}