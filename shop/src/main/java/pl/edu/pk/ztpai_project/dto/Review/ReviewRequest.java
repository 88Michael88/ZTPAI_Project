package pl.edu.pk.ztpai_project.dto.Review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReviewRequest {
    @NotBlank(message = "Bruh, this is a review...")
    @Size(min = 2, max = 256)
    private String review;
    
    public String getReview() { return review; }
}
