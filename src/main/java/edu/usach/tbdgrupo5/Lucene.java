package edu.usach.tbdgrupo5;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;






public class Lucene {
	private MongoConnection mongoConnection;
	private List<String> idList = null;
	private int positiveResult=0;
	private int negativeResult=0;
	private int neutralResult=0;
	public Lucene(MongoConnection mongoConnection){
		this.mongoConnection = mongoConnection;
	}
	
	public void indexCreate(){
		try{
			Directory dir = FSDirectory.open(Paths.get("indice/"));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			iwc.setOpenMode(OpenMode.CREATE);
			
			IndexWriter writer = new IndexWriter(dir,iwc);
			DBCursor cursor = this.mongoConnection.getTweets();
			Document doc = null;
			//DBObject cur2 = cursor.next();
			//System.out.println("Index);
			while (cursor.hasNext()) {
			      DBObject cur = cursor.next();
			      doc = new Document();
			      doc.add(new StringField("id",cur.get("_id").toString(),Field.Store.YES));
			      doc.add(new TextField("contenido", cur.get("text").toString(),Field.Store.YES));
			      doc.add(new StringField("analysis",cur.get("analysis").toString(),Field.Store.YES));
			      if (writer.getConfig().getOpenMode() == OpenMode.CREATE){
						//System.out.println("Indexando el tweet: "+cur.get("text")+"\n");
						writer.addDocument(doc);
					}
				else{
						//System.out.println("Actualizando el tweet: "+cur.get("text")+"\n");
						writer.updateDocument(new Term("contenido"+cur.get("text")), doc);
				}
			   }
			cursor.close();
			writer.close();
			//System.out.println(cur2);
		}
		catch(IOException ioe){
				System.out.println(" caught a "+ ioe.getClass() + "\n with message: " + ioe.getMessage());
			
		}
		
	}
	public int indexSearch(String Artista){
		try{
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("indice/")));
			IndexSearcher searcher = new IndexSearcher(reader);
			Analyzer analyzer = new StandardAnalyzer();
			int resultados = 0;
			this.positiveResult=0;
			this.negativeResult=0;
			this.neutralResult=0;
			QueryParser parser = new QueryParser("contenido",analyzer);
			Query query = parser.parse(Artista);
			idList = new ArrayList<String>();
			TopDocs result = searcher.search(query,25000);
			ScoreDoc[] hits =result.scoreDocs;
			
			for (int i=0; i<hits.length;i++){
				Document doc = searcher.doc(hits[i].doc);
				idList.add(doc.get("id"));
				if((doc.get("analysis")).equals("Positive")){
					this.positiveResult++;
				}
				else if((doc.get("analysis")).equals("Negative")){
					this.negativeResult++;
				}
				else if((doc.get("analysis")).equals("Neutral")){
					this.neutralResult++;
				}
				//System.out.println((i+1) + ".- score="+hits[i].score+" doc="+hits[i].doc+" id="+doc.get("id")+ "twee="+doc.get("contenido"));
			}
			
			reader.close();
			return resultados;
			
			
		}
		
		catch(IOException ex){
			Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE,null,ex);
			
		}
		catch(ParseException ex){
			Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE,null,ex);
		}
		return 0;
	}
	public List<String> getIdList(){
		return this.idList;
	}
	public int getpositiveResult(){
		return this.positiveResult;
	}
	public int getnegativeResult(){
		return this.negativeResult;
	}
	public int getneutralResult(){
		return this.neutralResult;
	}

}
