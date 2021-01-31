package br.com.bexchallange.component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RotasComponent {
	
	private Reader reader;
	
	private Writer writer;
	
	@Bean
	private Reader carregarReader(ApplicationArguments arguments) throws IOException {
		reader = Files.newBufferedReader(Paths.get(arguments.getSourceArgs()[0]));
		 return reader;
	}
	
	@Bean
	private Writer carregarWriter(ApplicationArguments arguments) throws IOException {
		writer = new FileWriter(Paths.get(arguments.getSourceArgs()[0]).toString(), true);
		 return writer;
	}

	public Reader getReader() {
		return reader;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}
	
}
