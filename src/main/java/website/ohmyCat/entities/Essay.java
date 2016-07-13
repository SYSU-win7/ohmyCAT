package website.ohmyCat.entities;

public class Essay {
    private String content;
    private String image;
    private String author;
	public String getContent() {
		return content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
    
    
}
