package ru.geekbrains.winter.api;


public class ProductDto {
    private Long id;
    private String title;
    private int cost;
    private String categoryTitle;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, int cost, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.categoryTitle = categoryTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
