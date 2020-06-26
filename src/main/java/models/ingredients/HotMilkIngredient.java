package models.ingredients;

import lombok.Builder;


@Builder(toBuilder = true)
public class HotMilkIngredient implements Ingredient{
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
