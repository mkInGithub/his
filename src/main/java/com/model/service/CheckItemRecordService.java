package com.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.bean.CheckItemRecord;
import com.model.bean.CheckItemRecordList;
import com.model.mapper.CheckItemRecordMapper;

@Service
public class CheckItemRecordService {
	@Autowired
	private CheckItemRecordMapper  checkItemRecordMapper;
	
	public void saveCheckItemRecord(int pid,CheckItemRecordList cirList){
		for(CheckItemRecord cr:cirList.getCirList()){
			cr.setPid(pid);
			cr.setPaystatus(0);
			checkItemRecordMapper.saveCheckItemRecord(cr);
		}
	}
	
}
