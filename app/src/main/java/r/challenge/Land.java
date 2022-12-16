package r.challenge;

public class Land {
    private String owner;
    private int width;
    private int height;

    public Land(String owner, String width, String height) {
        this.owner = owner;
        this.width = Integer.valueOf(width);
        this.height = Integer.valueOf(height);
    }

    public String getOwner() {
        return owner;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea() {
        return width * height;
    }

}
