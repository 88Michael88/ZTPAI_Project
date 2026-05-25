package pl.edu.pk.ztpai_project.dto.Review;

import pl.edu.pk.ztpai_project.model.Review;

public class ReviewMapper {

    public static ReviewResponse toResponse(Review review) {
        ReviewResponse res = new ReviewResponse();
        res.setId(review.getId());
        res.setProductId(review.getProductId());
        res.setReview(review.getReview());
        return res;
    }

    public static NewReviewResponse toNewResponse(Review review) {
        NewReviewResponse res = new NewReviewResponse();
        res.setId(review.getId());
        return res;
    }

    public static Review toEntity(Long productId, ReviewRequest reviewReq) {
        Review req = new Review();

        req.setReview(reviewReq.getReview());
        req.setProductId(productId);
        return req;
    }


}
