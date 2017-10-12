package edu.usach.tbdgrupo5;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	public Lucene(){
		
	}
	public void indexCreate(){
		try{
			Directory dir = FSDirectory.open(Paths.get("indice/"));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			
			IndexWriter writer = new IndexWriter(dir,iwc);
			String ruta = "libros/";
			if(Files.isDirectory(Paths.get(ruta))){
				File directorioLibros = new File(ruta);
				File[] libros = directorioLibros.listFiles();
				Document doc = null;
				for (File f: libros){
					if (f.isFile() && f.canRead() && f.getName().endsWith(".txt")){
						doc = new Document();
						doc.add(new StringField("path",f.toString(),Field.Store.YES));
						FileReader fr = new FileReader(f);
						doc.add(new TextField("contenido",fr));
						if (writer.getConfig().getOpenMode() == OpenMode.CREATE){
							System.out.println("Indexando el archivo: "+f.getName());
							writer.addDocument(doc);
						}
						else{
							System.out.println("Actualizando el archivo: "+f.getName());
							writer.updateDocument(new Term("path"+f.toString()), doc);
						}
					}
				}
				
				
			}
			writer.close();
		}
		catch(IOException ioe){
				System.out.println(" caught a "+ ioe.getClass() + "\n with message: " + ioe.getMessage());
			
		}
	}
	public void indexTweets(MongoConnection mc){
		try{
			Directory dir = FSDirectory.open(Paths.get("indice/"));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			
			IndexWriter writer = new IndexWriter(dir,iwc);
			if(mc == null){
				System.out.println("soy nullo");
			}
			DBCursor cursor = mc.getTweets();
			Document doc = null;
			DBObject cur2 = cursor.next();
			
			while (cursor.hasNext()) {
			      DBObject cur = cursor.next();
			      doc = new Document();
			      doc.add(new StringField("contenido",(String) cur.get("text"),Field.Store.YES));
			      if (writer.getConfig().getOpenMode() == OpenMode.CREATE){
						System.out.println("Indexando el tweet: "+cur.get("text")+"\n");
						writer.addDocument(doc);
					}
				else{
						System.out.println("Actualizando el tweet: "+cur.get("text")+"\n");
						writer.updateDocument(new Term("contenido"+cur.get("text")), doc);
				}
			   }
			cursor.close();
			writer.close();
			System.out.println(cur2);
		}
		catch(IOException ioe){
				System.out.println(" caught a "+ ioe.getClass() + "\n with message: " + ioe.getMessage());
			
		}
		
	}
	public void indexSearchTweets(){
		try{
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("indice/")));
			IndexSearcher searcher = new IndexSearcher(reader);
			Analyzer analyzer = new StandardAnalyzer();
			
			QueryParser parser = new QueryParser("contenido",analyzer);
			Query query = parser.parse("Wisin");
			
			TopDocs result = searcher.search(query, 10);
			ScoreDoc[] hits =result.scoreDocs;
			System.out.println("hola estoy buscando\n");
			for (int i=0; i<hits.length;i++){
				Document doc = searcher.doc(hits[i].doc);
				String tweet =doc.get("contenido");
				System.out.println((i+1) + ".- score="+hits[i].score+" doc="+hits[i].doc+" path="+tweet);
			}
			reader.close();
			
		}
		
		catch(IOException ex){
			Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE,null,ex);
		}
		catch(ParseException ex){
			Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	public void indexSearch() throws ParseException{
		try{
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("indice/")));
			IndexSearcher searcher = new IndexSearcher(reader);
			Analyzer analyzer = new StandardAnalyzer();
			
			QueryParser parser = new QueryParser("contenido",analyzer);
			Query query = parser.parse("Harry Potter");
			
			TopDocs result = searcher.search(query, 10);
			ScoreDoc[] hits =result.scoreDocs;
			
			for (int i=0; i<hits.length;i++){
				Document doc = searcher.doc(hits[i].doc);
				String path =doc.get("path");
				System.out.println((i+1) + ".- score="+hits[i].score+" doc="+hits[i].doc+" path="+path);
			}
			reader.close();
			
		}
		
		catch(IOException ex){
			Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE,null,ex);
		}
		catch(ParseException ex){
			Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE,null,ex);
		}
	}

}
