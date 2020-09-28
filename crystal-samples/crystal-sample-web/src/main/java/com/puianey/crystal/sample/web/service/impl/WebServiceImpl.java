package com.puianey.crystal.sample.web.service.impl;

import com.puianey.crystal.sample.web.service.WebService;
import org.springframework.stereotype.Service;

@Service
public class WebServiceImpl implements WebService {

	@Override
	public String test() {
		return "result";
	}

	@Override
	public String test(Long id) {
		return "result:id=" + id;
	}
}
