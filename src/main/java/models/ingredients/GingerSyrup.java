package models.ingredients;

import lombok.Builder;


@Builder(toBuilder = true)
public class GingerSyrup implements Ingredient {
  private Long quantity;

  public Long getQuantity() {
    if(quantity == null)
      return 0L;
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}
