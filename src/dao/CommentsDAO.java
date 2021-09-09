package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Comment;
import beans.enums.CommentStatus;

public class CommentsDAO {
	
	private HashMap<Integer, Comment> comments = new HashMap<Integer, Comment>();
	private String path;

	public CommentsDAO() {

	}

	public CommentsDAO(String contextPath) {
		this.path = contextPath;
		loadComments(contextPath);

	}

	public void loadComments(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(contextPath + "/comments.json");

		try {
			comments = objectMapper.readValue(file, new TypeReference<HashMap<Integer, Comment>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveComments() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(this.path + "/comments.json"), this.comments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Comment> getValues() {
		return comments;
	}

	public Boolean updateComment(Comment updatedItem, Comment oldItem) {

		comments.remove(oldItem.getId());
		updatedItem.setId(oldItem.getId());
		comments.put(updatedItem.getId(), updatedItem);
		saveComments();
		return true;

	}

	public Comment find(Integer id) {

		return comments.get(id);

	}

	public void deleteComment(Integer id) {

		comments.get(id).setLogicalDeleted(1);
		saveComments();

	}

	public Comment addComment(Comment comment) {

		comment.setId(comments.size() + 1);
		comments.put(comments.size()+1, comment);
		saveComments();
		return comments.get(comment.getId());

	}
	
	public HashMap<Integer, Comment> filterByStatus(CommentStatus status) {
		HashMap<Integer, Comment> commentsResult = new HashMap<Integer, Comment>();
		for (Comment item : getValues().values()) {
			if (item.getStatus().equals(status)) {
				commentsResult.put(item.getId(), item);
			}
		}
		return commentsResult;
	}

}
