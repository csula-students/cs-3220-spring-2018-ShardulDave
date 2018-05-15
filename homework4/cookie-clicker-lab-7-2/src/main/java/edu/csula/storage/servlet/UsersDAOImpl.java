package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

/**
 * To abstract the storage access from the business layer using HttpSession
 */
public class UsersDAOImpl implements UsersDAO {
	private final HttpSession context;
	protected static final String CONTEXT_NAME = "users";

	public UsersDAOImpl(HttpSession context) {
		this.context = context;
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO: check if username/password combination is valid and store the
		//       username/password into the session
		List<User> users=new ArrayList<>();
		users.add(new User(0,"admin","cs3220password"));
		if( users != null) {
			for(User u:users) {
				if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
					context.setAttribute(CONTEXT_NAME, u);
					return true;
				}
			}
			return false;
		}
		else{
			return false;
		}
	}

	@Override
	public Optional<User> getAuthenticatedUser() {
		// TODO: return the authenticated user if there is any
		User u=(User) context.getAttribute(CONTEXT_NAME);
		if(u != null){
			if(authenticate(u.getUsername(),u.getPassword())){
				return Optional.of(u);
			}
			else{
				return Optional.empty();
			}
		}
		return Optional.empty();
	}

	@Override
	public void logout() {
		// TOOD: log user out using `invalidate`
		context.invalidate();
	}
}