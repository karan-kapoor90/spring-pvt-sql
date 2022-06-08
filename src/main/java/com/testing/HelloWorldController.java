package com.testing;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	private final JdbcTemplate jdbcTemplate;

	public HelloWorldController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@GetMapping("/getTuples")
	public List<String> getTuples() {
		return this.jdbcTemplate.queryForList("SELECT * FROM accounts").stream()
				.map((m) -> m.values().toString())
				.collect(Collectors.toList());
	}

}
