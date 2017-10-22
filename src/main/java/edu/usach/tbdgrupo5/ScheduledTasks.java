package edu.usach.tbdgrupo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.usach.tbdgrupo5.entities.Artista;
import edu.usach.tbdgrupo5.repository.ArtistaRepository;

@Component
public class ScheduledTasks
{

	public MongoConnection mc = new MongoConnection("tweets", "tweetsPrueba");
	public Lucene lucene = new Lucene(mc);
	@Autowired
	public ArtistaRepository artistarepository ;
	public Iterable<Artista> artistas = null;
    @Scheduled(cron="*/10 * * * * *")
    public void indexCreateTask()
	{
    	mc.connect();
    	System.out.println("[Scheduled Task][Start]: Indexing tweets.");
    	lucene.indexCreate();
    	System.out.println("[Scheduled Task] [End] : Indexing tweets.\n");
    }
    @Scheduled(cron="*/10 * * * * *")
    public void updateComments()
	{
    	
    	artistas= artistarepository.findAll();
    	System.out.println("[Scheduled Task][Start]: Update comments.");

    	for (Artista artista:artistas)
    	{
    		lucene.indexSearch(artista.getNombre());
    		//System.out.println("Artista:"+ artista.getNombre());
    		//System.out.println("Positivos: "+ lucene.getpositiveResult());
    		//System.out.println("Negativos: "+ lucene.getnegativeResult());
    		//System.out.println("Neutral: "+ lucene.getneutralResult());
    	    artista.setComentariosPositivos(lucene.getpositiveResult());
    	    artista.setComentariosNegativos(lucene.getnegativeResult());
			artista.setComentariosNeutros(lucene.getneutralResult());
    		artistarepository.save(artista);
    	}
    	System.out.println("[Scheduled Task] [End] : Update comments.\n");
    	
        //System.out.println("hola fuí programado\n");
    }
}