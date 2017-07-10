package services;

import javax.sound.midi.Soundbank;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import db.DbManager;
import db.Solution;
/**
 * this class implement a rest Api 
 * @see getUser(@PathParam("board") String board) get a board compress in url and return the solution from DB in format "u,d,l,r.." and if dont have solution return null
 * @see addName(String boardAndSol) add board and board solution in format "compress board"+"="+"solution in format "u,d,l,r.." 
 * */
@Path("/SokobanServices")
public class SokobanServices {

	public DbManager db;

	public SokobanServices() {
		this.db = DbManager.getInstance();
	}
	/**get a board compress(example:"w7nw1O1@1 2nw7n) in url and return the solution from DB in format "u,d,l,r.." and if d'ont have solution return null*/
	@GET
	@Path("/get/{board}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(@PathParam("board") String board) {
		System.out.println(board);
		// get LevelSolutionData from DB
		String levelSolution = db.getLevelSolution(board);
		System.out.println("LevelSol From DB: " + levelSolution + " board" + board);
		return levelSolution;
	}
	/**add board and board solution in format "compress board"+"="+"solution in format "u,d,l,r.." <br>example: "w7nw1O1@1 2nw7n=l,l,l"*/
	@POST
	@Path("/add") public void addName(String boardAndSol) {
		String[] arrStr= boardAndSol.split("=",2);
		if(arrStr.length!=2){
			System.out.println("wrong input");
			return;
		}
		Solution sol =new Solution(arrStr[0],arrStr[1]);
		db.add(sol);
	  
	  }

}
