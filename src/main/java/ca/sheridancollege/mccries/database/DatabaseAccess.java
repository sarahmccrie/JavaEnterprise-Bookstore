package ca.sheridancollege.mccries.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.mccries.beans.Author;
import ca.sheridancollege.mccries.beans.Book;
import ca.sheridancollege.mccries.beans.User;
import lombok.NonNull;

/* Name: Sarah McCrie 991405606
* Assignment: Final
*/

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	// add user
	public void addUser(String email, String password) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO `user`" + "(email, encryptedPassword, enabled) "
				+ "VALUES (:email, :encryptedPassword, 1)";
		namedParameters.addValue("email", email);
		namedParameters.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, namedParameters);
	}

	// add role
	public void addRole(Long userId, Long roleId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO user_role (userId, roleId) " + "VALUES (:userId, :roleId)";
		namedParameters.addValue("userId", userId);
		namedParameters.addValue("roleId", roleId);
		jdbc.update(query, namedParameters);
	}

	// find user by email
	public User findUserAccount(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM `user` WHERE email = :email ";
		namedParameters.addValue("email", email);
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	// get roles by user id
	public List<String> getRolesById(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT role.roleName FROM user_role, role "
				+ "WHERE user_role.roleId = role.roleId AND userId = :userId";
		namedParameters.addValue("userId", userId);
		return jdbc.queryForList(query, namedParameters, String.class);
	}

	public List<Book> getAllBooks() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM book";
		try {
			return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	public List<Author> getAllAuthors() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM author";
		try {
			return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Author>(Author.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	public Book getBookByBookId(@NonNull Long bookId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource("bookId", bookId);
		String query = "SELECT * FROM book WHERE bookId = :bookId";
		namedParameters.addValue("bookId", bookId);
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	public void insertNewBook(Book book) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO `book` (title, categoryName) VALUES (:title, :categoryName)";
		namedParameters.addValue("title", book.getTitle());
		namedParameters.addValue("categoryName", book.getCategoryName());
		int rowsAffected = jdbc.update(query, namedParameters);
		System.out.println("Here: " + rowsAffected);
		
	}

	public void insertNewAuthor(Author author) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO `author` (firstName, lastName) VALUES (:firstName, :lastName)";
		namedParameters.addValue("firstName", author.getFirstName());
		namedParameters.addValue("lastName", author.getLastName());
		int rowsAffected = jdbc.update(query, namedParameters);
		System.out.println("Here: " + rowsAffected);
	}
	
	
}
