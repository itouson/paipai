package com.moon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.entity.Type;
import com.moon.dao.TypeMapper;

import java.util.List;

@Transactional
@Service
public class TypeService {
	@Autowired
	private TypeMapper typeMapper;

	public TypeMapper getTypeMapper() {
		return typeMapper;
	}

	public List<Type> listTypes() {
		return typeMapper.selectAll();
	}
}