package com.happy.dao;

import java.util.ArrayList;

import com.happy.vo.QBoard;

public interface QBoardDao {
	public ArrayList<QBoard> getAllQBoard();

	public int addQBoard(QBoard qboard);
	
	public int editQBoard(QBoard qboard);
	
	public QBoard getQBoard(String no);
	
	public int deleteQBoard(String no);
}
