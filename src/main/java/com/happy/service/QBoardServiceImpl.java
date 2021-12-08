package com.happy.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.dao.QBoardDao;
import com.happy.vo.QBoard;

@Service
public class QBoardServiceImpl implements QBoardService {
	@Autowired
	QBoardDao dao;

	@Override
	public ArrayList<QBoard> getAllQBoard() {
		return dao.getAllQBoard();
	}

	@Override
	public int addQBoard(QBoard qboard) {
		return dao.addQBoard(qboard);
	}

	@Override
	public int editQBoard(QBoard qboard) {
		return dao.editQBoard(qboard);
	}

	@Override
	public QBoard getQBoard(String no) {
		return dao.getQBoard(no);
	}

	@Override
	public int deleteQBoard(String no) {
		return dao.deleteQBoard(no);
	}

}
