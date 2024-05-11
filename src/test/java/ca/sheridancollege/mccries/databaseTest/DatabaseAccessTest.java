package ca.sheridancollege.mccries.databaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ca.sheridancollege.mccries.beans.Author;
import ca.sheridancollege.mccries.beans.Book;
import ca.sheridancollege.mccries.database.DatabaseAccess;

@ExtendWith(MockitoExtension.class)
public class DatabaseAccessTest {

	@Mock
	private NamedParameterJdbcTemplate jdbc;

	@InjectMocks
	private DatabaseAccess db;

	@Test
	public void testGetAllBooks() {
		List<Book> mockBooks = new ArrayList<>();
		mockBooks.add(new Book());
		mockBooks.add(new Book());
		when(jdbc.query(eq("SELECT * FROM book"), any(MapSqlParameterSource.class), any(BeanPropertyRowMapper.class)))
				.thenReturn(mockBooks);

		List<Book> books = db.getAllBooks();

		assertNotNull(books);
		assertEquals(2, books.size());
	}

	@Test
	public void testGetAllAuthors() {
		List<Author> mockAuthors = new ArrayList<>();
		mockAuthors.add(new Author());
		mockAuthors.add(new Author());
		when(jdbc.query(eq("SELECT * FROM author"), any(MapSqlParameterSource.class), any(BeanPropertyRowMapper.class)))
				.thenReturn(mockAuthors);

		List<Author> authors = db.getAllAuthors();

		assertNotNull(authors);
		assertEquals(2, authors.size());
	}
}
