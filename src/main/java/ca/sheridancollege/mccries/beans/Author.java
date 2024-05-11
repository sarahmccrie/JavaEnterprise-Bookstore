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
public class Author {
	@NonNull
	Long authorId;
	
	@NonNull
	String firstName;
	
	@NonNull
	String lastName;

}
