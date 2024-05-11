package ca.sheridancollege.mccries.beans;

import lombok.Data;

/* Name: Sarah McCrie 991405606
* Assignment: Final
*/

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class BookAuthor {
	
	@NonNull
	Long bookId;
	
	@NonNull
	Long authorId;
	

}
