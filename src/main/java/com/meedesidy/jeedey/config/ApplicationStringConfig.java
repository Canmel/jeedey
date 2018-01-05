package com.meedesidy.jeedey.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class ApplicationStringConfig {
	@Value("${server.context-path}")
	private String content_path;

	public String getContent_path() {
		return content_path;
	}

	public void setContent_path(String content_path) {
		this.content_path = content_path;
	}
}
