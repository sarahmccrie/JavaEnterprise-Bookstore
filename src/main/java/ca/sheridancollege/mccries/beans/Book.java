package ca.sheridancollege.mccries.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/* Name: Sarah McCrie 991405606
* Assignment: Final
*/

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {

	@NonNull
	Long bookId;
	
	@NonNull
	String title;
	
	String categoryName;
	
	
}
