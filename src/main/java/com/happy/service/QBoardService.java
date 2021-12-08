package com.happy.service;

import java.util.ArrayList;

import com.happy.vo.QBoard;

public interface QBoardService {
	public ArrayList<QBoard> getAllQBoard();

	public int addQBoard(QBoard qboard);
	
	public int editQBoard(QBoard qboard);
	
	public QBoard getQBoard(String no);
	
	public int deleteQBoard(String no);
}
