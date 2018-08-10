package org.deep.pat.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.deep.pat.messenger.database.DatabaseClass;
import org.deep.pat.messenger.exception.DataNotFoundException;
import org.deep.pat.messenger.model.ErrorMessage;
import org.deep.pat.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello world", "Deep"));
		messages.put(2L, new Message(2, "Hello Jersey", "Deep"));
	}
	
	public List<Message> getAllMessages(){
		
		return new ArrayList<>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear( int year){
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year)
				messageForYear.add(message);
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
	
	List<Message> list = new ArrayList<>(messages.values());
	return list.subList(start, start + size);
}

	
	public Message getMessage(Long id) {
		Message message = messages.get(id);
		ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "http://org.deeep.pathak");
		//if(message == null) throw new DataNotFoundException("ID not found");
		Response response = Response
				.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		//if(message == null) throw new WebApplicationException(response);
		
		if(message == null) throw new NotFoundException(response);
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <=0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public void removeMessage(Long id) {
		messages.remove(id);
		
	}

}
