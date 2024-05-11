package ca.sheridancollege.mccries.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.mccries.beans.Author;
import ca.sheridancollege.mccries.beans.Book;
import ca.sheridancollege.mccries.database.DatabaseAccess;

/* Name: Sarah McCrie 991405606
* Assignment: Final
*/

@Controller
public class HomeController {

	@Autowired
	@Lazy
	PasswordEncoder passwordEncoder;

	@Autowired
	@Lazy
	private DatabaseAccess da;

// register methods
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}

	@PostMapping("/register")
	public String postRegister(@RequestParam String username, @RequestParam String password) {
		da.addUser(username, password);
		Long userId = da.findUserAccount(username).getUserId();
		da.addRole(userId, Long.valueOf(1));
	//	da.addStudent(userId);
		return "redirect:/";
	}

// index methods (not secure)
	@GetMapping("/")
	public String index() {
		return "index";
	}

	// index methods (secure)
	@GetMapping("/secure")
	public String secureIndex(Model model, Authentication authentication) {
		String email = authentication.getName();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("email", email);
		model.addAttribute("roleList", roleList);
		return "/secure/index";
	}

	// login methods
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// permission denied page
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/permission-denied";
	}

	// password related
	@GetMapping("/pass")
	public String pass() {
		String password = passwordEncoder.encode("1234").toString();
		System.out.println("The encrypted password is " + password);
		boolean m = passwordEncoder.matches("1234", password);
		System.out.println("The password is " + m);
		boolean n = passwordEncoder.matches("124", password);
		System.out.println("The password is " + n);
		return "login";
	}
	
	@GetMapping("/secure/book")
	public String book(Model model) {
		List<Book> allBooks = da.getAllBooks();
		model.addAttribute("bookList", allBooks);
		return "secure/book";
	}
	
	@PostMapping("/secure/book")
	public String book(Model model, @ModelAttribute Book book) {
		da.insertNewBook(book);
		List<Book> allBooks = da.getAllBooks();
		model.addAttribute("bookList", allBooks);
		return "secure/book";
	}
	
	
	@GetMapping("/secure/author")
	public String author(Model model) {
		List<Author> allAuthors = da.getAllAuthors();
		model.addAttribute("authorList", allAuthors);
		return "secure/author";
	}
	
	@PostMapping("/secure/author")
	public String author(Model model, @ModelAttribute Author author) {
		da.insertNewAuthor(author);
		List<Author> allAuthors = da.getAllAuthors();
		model.addAttribute("authorList", allAuthors);
		return "secure/author";
	}
	
	@GetMapping("/secure/searchBook")
	public String searchBook(Model model) {
		model.addAttribute("book", new Book());
		return "secure/searchBook";
	}
	
	@PostMapping("/secure/searchBook")
	public String searchBook(Model model, @ModelAttribute Book book) {
		boolean isIdMatching = false;
		System.out.println("the id is: " + book.getBookId());
		book = da.getBookByBookId(book.getBookId());
		if (book == null) {
			System.out.println("the id is null");
			model.addAttribute("isIdMatching", isIdMatching);
			return "secure/searchBook";
		}
		List<Book> foundBook = new ArrayList<Book>();
		foundBook.add(book);
		isIdMatching = (foundBook != null);
		model.addAttribute("isIdMatching", isIdMatching);
		model.addAttribute("bookList", foundBook);
		model.addAttribute("book", new Book());
		return "secure/searchBook";
	}

}
