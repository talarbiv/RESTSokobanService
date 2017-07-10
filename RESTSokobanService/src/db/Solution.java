package db;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * This class is responsible for communicating with the table "Solution" in SQL
 * <br>{@link Solution#board} its a compress sokoban board and primary key in table
 * <br>{@link Solution#solutionBoard} its a solution in this format "u,d,l,r,..."
 * */
@Entity(name="Solution")
public class Solution implements Serializable {
	
	@Column(name="board")
	@Id
	String board;
	



	@Column(name="solutionBoard")
	String solutionBoard;
	
	
	public Solution() {
	}
	
	public Solution(String board, String solutionBoard) {
		this.board = board;
		this.solutionBoard= solutionBoard;
	}
	
	
	
	@Override
	public String toString() {
		return "Solution [board=" + board +"\n solutionBoard=" + solutionBoard+ "]";
	}
	
	
	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getSolutionBoard() {
		return solutionBoard;
	}

	public void setSolutionBoard(String solutionBoard) {
		this.solutionBoard = solutionBoard;
	}

}
