package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Logo;

public class ImagesDAO {
	private HashMap<Integer, Logo> images = new HashMap<Integer, Logo>();
	private String path;

	public ImagesDAO() {

	}

	public ImagesDAO(String contextPath) {
		this.path = contextPath;
		loadImages(contextPath);

	}

	public void loadImages(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(contextPath + "/images.json");

		try {
			images = objectMapper.readValue(file, new TypeReference<HashMap<Integer, Logo>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveImages() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(this.path + "/images.json"), this.images);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Logo> getValues() {
		return images;
	}

	public Boolean updateImage(Logo updatedItem,Logo oldItem) {

		images.remove(updatedItem.getId());
		updatedItem.setId(oldItem.getId());
		images.put(updatedItem.getId(), updatedItem);
		saveImages();
		return true;

	}

	public Logo find(Integer id) {

		return images.get(id);

	}

	public void deleteImage(Integer id) {

		images.remove(id);
		saveImages();

	}

	public Logo addImage(Logo image) {

		image.setId(images.size() + 1);
		images.put(images.size() + 1, image);
		saveImages();
		return images.get(image.getId());

	}

}
