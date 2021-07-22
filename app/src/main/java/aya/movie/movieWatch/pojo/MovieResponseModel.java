package aya.movie.movieWatch.pojo;

import java.util.List;

public class MovieResponseModel {
    private Integer page;
    private List<MovieModel> results;
    private Integer totalResults;
    private Integer totalPages;

    public MovieResponseModel(Integer page, List<MovieModel> results, Integer totalResults, Integer totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }

    public List<MovieModel> getResults() {
        return results;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }


}
