package pl.edu.pk.ztpai_project.model;

import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long productId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private String review;

    public void setId(Long id) { this.id = id; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setStatus(Status status) { this.status = status; }
    public void setReview(String review) { this.review = review; }

    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public Status getStatus() { return this.status; }
    public String getReview() { return this.review; }
}
