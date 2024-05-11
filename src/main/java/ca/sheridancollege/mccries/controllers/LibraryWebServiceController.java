package ca.sheridancollege.mccries.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.mccries.beans.Book;
import ca.sheridancollege.mccries.database.DatabaseAccess;

@RestController
@RequestMapping("/api")
public class LibraryWebServiceController {

	@Autowired
	private DatabaseAccess databaseAccess;

	@PostMapping("/secure/consumeWebServices")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		try {
			databaseAccess.insertNewBook(book);
			return new ResponseEntity<>(book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/secure/consumeWebServices")
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			List<Book> books = databaseAccess.getAllBooks();
			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
